#include "tcpserver.h"
#include "ui_tcpserver.h"

#include <QFile>
#include <QTcpServer>
#include <QTcpSocket>
#include <QMessageBox>
#include <QFileDialog>
#include <QDebug>

TcpServer::TcpServer(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::TcpServer)
{
    ui->setupUi(this);

    setFixedSize(350,180);

    tcpPort = 6666;
    tcpServer = new QTcpServer(this);
    connect(tcpServer,SIGNAL(newConnection()),this,SLOT(sendMessage()));

    //initServer();
}

TcpServer::~TcpServer()
{
    delete ui;
}

void TcpServer::initServer()
{
    payloadSize = 64*1024;
    TotalBytes = 0;
    bytesWritten = 0;
    bytesToWrite = 0;
    ui->serverStatusLabel->setText(tr("请选择要传送的文件"));
    ui->progressBar->reset();
    ui->serverOpenBtn->setEnabled(true);
    ui->serverSendBtn->setEnabled(false);

    tcpServer->close();
}
void TcpServer::sendMessage()//---------------------------------------------------????-------------------------------------------
{
    ui->serverSendBtn->setEnabled(false);
    clientConnection = tcpServer->nextPendingConnection();
    connect(clientConnection,SIGNAL(bytesWritten(qint64)),this,SLOT(updateClientProgress(qint64)));

    ui->serverStatusLabel->setText(tr("开始传输文件 %1").arg(theFileName));

    localFile = new QFile(fileName);
    if(!localFile->open(QFile::ReadOnly)){
        QMessageBox::warning(this,tr("应用程序"),tr("无法读取文件 %1\n%2").arg(fileName).arg(localFile->errorString()));
        return;
    }
    TotalBytes = localFile->size();
    QDataStream sendOut(&outBlock,QIODevice::WriteOnly);
    sendOut.setVersion(QDataStream::Qt_5_1);
    time.start();
    QString currentFile = fileName.right(fileName.size() - fileName.lastIndexOf('/')-1);
    sendOut<<qint64(0)<<qint64(0)<<currentFile;
    TotalBytes += outBlock.size();
    sendOut.device()->seek(0);
    sendOut<<TotalBytes<<qint64(outBlock.size() - sizeof(qint64)*2);
    bytesToWrite = TotalBytes - clientConnection->write(outBlock);
    outBlock.resize(0);
}
void TcpServer::updateClientProgress(qint64 numBytes)
{
    qApp->processEvents();
    bytesWritten += (int) numBytes;
    if(bytesToWrite > 0){
        outBlock = localFile->read(qMin(bytesToWrite,payloadSize));
        bytesToWrite -= (int)clientConnection->write(outBlock);
        outBlock.resize(0);
    } else {
        localFile->close();
    }

    ui->progressBar->setMaximum(TotalBytes);
    ui->progressBar->setValue(bytesWritten);

    float useTime = time.elapsed();
    double speed = bytesWritten/useTime;
    ui->serverStatusLabel->setText(tr("已发送 %1MB( %2MB/s)"
                                      "\n共 %3MB 已用时：%4秒\n估计剩余时间：%5秒")
                                   .arg(bytesWritten/(1024*1024))
                                   .arg(speed * 1000/(1024*1024),0,'f',2)
                                   .arg(TotalBytes/(1024*1024))
                                   .arg(useTime/1000,0,'f',0)
                                   .arg(TotalBytes/speed/1000 - useTime/1000,0,'f',0));

    if(bytesWritten == TotalBytes){
        localFile->close();
        tcpServer->close();
        ui->serverStatusLabel->setText(tr("传送文件 %1 成功").arg(fileName));
    }
}

void TcpServer::on_serverOpenBtn_clicked()
{
    fileName = QFileDialog::getOpenFileName(this);
    if(!fileName.isEmpty()){
        theFileName = fileName.right(fileName.size()-fileName.lastIndexOf('/')-1);
        ui->serverStatusLabel->setText(tr("要传输的文件为： %1").arg(theFileName));
        ui->serverSendBtn->setEnabled(true);
        ui->serverOpenBtn->setEnabled(false);
    }
}

void TcpServer::on_serverSendBtn_clicked()
{
    if(!tcpServer->listen(QHostAddress::Any,tcpPort)){
        qDebug()<<tcpServer->errorString();
        close();
        return;
    }
    ui->serverStatusLabel->setText(tr("等待对方接收... ..."));
    emit sendFileName(theFileName);
}

void TcpServer::on_serverCloseBtn_clicked()
{
    if(tcpServer->isListening()){
        tcpServer->close();
        if(localFile && localFile->isOpen())
            localFile->close();
        clientConnection->abort();
    }
    close();
}

void TcpServer::refused()
{
    tcpServer->close();
    ui->serverStatusLabel->setText(tr("对方拒绝接收！！！"));
}

void TcpServer::closeEvent(QCloseEvent *)
{
    on_serverCloseBtn_clicked();
}
