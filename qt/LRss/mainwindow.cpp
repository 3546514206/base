#include "mainwindow.h"
#include "rssxmlparser.h"


#include <QtGui>
#include <QtWebKitWidgets/QWebView>
//#include <QtWebKit/QWebView>
#include <QList>

#include <iostream>



MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent)
{
    content=new QWebView;
    setCentralWidget(content);

    createActions();
    createMenus();
    createToolBars();
    createStatusBar();
    createDockWindows();

    setWindowTitle(tr("LRss"));

    connect(&process,SIGNAL(readyReadStandardError()),
            this,SLOT(updateStatusMessage()));
    connect(&process,SIGNAL(finished(int,QProcess::ExitStatus)),this,
            SLOT(processFinished(int,QProcess::ExitStatus)));
    connect(&process,SIGNAL(error(QProcess::ProcessError)),
            this,SLOT(processError(QProcess::ProcessError)));

    QString settingsPath=QDir::homePath()+"/.lrss/urls";
    QFile urlsCache(settingsPath);
    if(urlsCache.exists())
    {
        readCache();
    }
}

MainWindow::~MainWindow()
{
   
}

void MainWindow::createActions()
{
    //update one
    //update all
    //add one
    //delete one
    //mark all
    //makr one
    //next one
    //previous one


    addFeedAct=new QAction(tr("&Add Feed"),this);
    addFeedAct->setShortcut(QKeySequence::ZoomIn);
    connect(addFeedAct,SIGNAL(triggered()),this,SLOT(addFeed()));

    deleteFeedAct=new QAction(tr("&Delete Feed"),this);
    deleteFeedAct->setShortcut(QKeySequence::ZoomOut);

    quitAct=new QAction(tr("&Quit"),this);
    addFeedAct->setShortcut(QKeySequence::Quit);

    updateOneAct=new QAction(tr("&Update"),this);
    updateOneAct->setShortcut(QKeySequence::Refresh);

    updateAllAct=new QAction(tr("&Update All"),this);

    aboutAct=new QAction(tr("&About"),this);


}

void MainWindow::createMenus()
{
    feedsMenu=menuBar()->addMenu(tr("&Feeds"));
    feedsMenu->addAction(addFeedAct);
    feedsMenu->addAction(deleteFeedAct);
    feedsMenu->addSeparator();
    feedsMenu->addAction(quitAct);

    updateMenu=menuBar()->addMenu(tr("&Update"));
    updateMenu->addAction(updateOneAct);
    updateMenu->addAction(updateAllAct);


    viewMenu=menuBar()->addMenu(tr("&View"));
    //viewMenu->addAction(showFeedsDockAct);
    //viewMenu->addAction(showHeadersDockAct);

    menuBar()->addSeparator();


    helpMenu=menuBar()->addMenu(tr("&Help"));
    helpMenu->addAction(aboutAct);

}

void MainWindow::createToolBars()
{

}

void MainWindow::createStatusBar()
{

}

void MainWindow::createDockWindows()
{
    QDockWidget* dock=new QDockWidget(tr("Feeds"),this);
    dock->setAllowedAreas(Qt::LeftDockWidgetArea|Qt::RightDockWidgetArea);
    feedsList=new QListWidget(dock);
    dock->setWidget(feedsList);
    addDockWidget(Qt::LeftDockWidgetArea,dock);
    viewMenu->addAction(dock->toggleViewAction());
    connect(feedsList,SIGNAL(currentItemChanged(QListWidgetItem*,QListWidgetItem*)),
            this,SLOT(showHeaders()));

    dock=new QDockWidget(tr("Headers"),this);
    headersList = new QListWidget(dock);
    dock->setWidget(headersList);
    addDockWidget(Qt::LeftDockWidgetArea, dock);
    viewMenu->addAction(dock->toggleViewAction());
    connect(headersList,SIGNAL(currentItemChanged(QListWidgetItem*,QListWidgetItem*)),
            this,SLOT(showContent()));

    QDir datas=QDir(QDir::homePath()+"/.lrss");
    if(datas.exists())
    {

        return;
    }
    else
    {
        //first time startup.
        QDir::home().mkdir(".lrss");
        return ;
    }


}

void MainWindow::showHeaders()
{
    QString feedName=feedsList->currentItem()->text();
    //content->load(QUrl(rssData->getMap()[urlName]));
    currentFeed=feedsHeaders[feedName];
    if(headersList->count()>0)
        headersList->clear();
    QMapIterator<QString,QString> i(currentFeed);
    while(i.hasNext())
    {
        std::cerr<<qPrintable(i.next().key())<<std::endl;
        new QListWidgetItem(i.next().key(),headersList);
    }

}

void MainWindow::updateStatusMessage()
{
    QByteArray newData=process.readAllStandardError();
    statusBar()->showMessage(QString::fromLocal8Bit(newData));
}

void MainWindow::processFinished(int exitCode, QProcess::ExitStatus exitStatus)
{
    if(exitStatus==QProcess::CrashExit)
    {
        statusBar()->showMessage("Error:Http download crashed...");

    }
    else if(exitCode!=0)
    {
        statusBar()->showMessage("Error:Http download failed...");
    }
    else
    {
      addContent(currentFileName);
    }

}

void MainWindow::processError(QProcess::ProcessError error)
{
    if(error==QProcess::FailedToStart)
    {
        QString path=QDir::currentPath();
        //statusBar()->showMessage("Error:Can't start http download...");
        statusBar()->showMessage(path);
    }
}

void MainWindow::addFeed()
{
        bool ok;
        QString url=QInputDialog::getText(this,tr("Feed's url:"),tr("Url:"),QLineEdit::Normal,"",&ok);
        writeCache(url);
        addFeed(url);

}
void MainWindow::addFeed(QString url)
{
    QStringList urls;
    urls<<url;

    //have exist?
    currentFileName=QFileInfo(QUrl(url).path()).fileName();
    process.start("./httpget",urls);
    if(!process.waitForFinished())
        return ;

}

void MainWindow::readCache()
{
    QString fileName=QDir::homePath()+"/.lrss/urls";
    QFile file(fileName);
    if(!file.open(QIODevice::ReadOnly|QIODevice::Text))
    {
        statusBar()->showMessage("can't read urls...");
        return;
    }
    QTextStream in(&file);
    while(!in.atEnd())
    {
        QString line=in.readLine();
        addFeed(line);

    }
    file.close();

}

void MainWindow::addContent(QString fileName)
{
    QString fullFileName=QDir::homePath()+"/.lrss/"+fileName;
    QFile xmlFile(fullFileName);
    if (!xmlFile.open(QIODevice::ReadOnly))
    {
        statusBar()->showMessage("Cannot open rss file "+fileName);
        return ;
    }

    rssData=new RssXmlParser(&xmlFile);
    if(!feedsList->findItems(rssData->getNetworkName(),Qt::MatchExactly).isEmpty())
    {

    }
    else
    {
       new QListWidgetItem(rssData->getNetworkName(),feedsList);
    }
    headersList->clearSelection();
    headersList->clear();
    QMapIterator<QString,QString> i(rssData->getMap());
    while(i.hasNext())
    {
        new QListWidgetItem(i.next().key(),headersList);
    }
    feedsHeaders.insert(rssData->getNetworkName(),rssData->getMap());
    currentFeed=feedsHeaders[rssData->getNetworkName()];

}

void MainWindow::showContent()
{
    if(headersList->currentRow()<0||headersList->count()<=0)
        return;
    QString urlName=headersList->currentItem()->text();
    content->load(QUrl(currentFeed[urlName]));
}




void MainWindow::writeCache(QString url)
{

    QString fileName=QDir::homePath()+"/.lrss/urls";
    QFile file(fileName);
    if(!file.open(QIODevice::WriteOnly|QIODevice::Append|QIODevice::Text))
    {
        statusBar()->showMessage("can't store urls...");
        return;
    }
    QTextStream out(&file);
    out<<qPrintable(url)<<"\n";
    file.close();
}
