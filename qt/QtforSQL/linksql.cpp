#include "linksql.h"
#include <QDebug>
#include <QtSql/QSqlRecord>

LinkSql::LinkSql(){
}

LinkSql::~LinkSql(){
}

bool LinkSql::isLinkToSql(){
    db = QSqlDatabase::addDatabase("QODBC");
    db.setDatabaseName("kcsj");
    db.setUserName("sa");
    db.setPassword("sa");
    if(!db.open()){
        QMessageBox::critical(0,QObject::tr("Database Error"),db.lastError().text());
        return false;
    }
    else{
        QMessageBox isMsg;
        isMsg.setText("Open SQL succeed!");
        isMsg.exec();
    }
    return true;
}

void LinkSql::updateFromSql(QString str1,QString str2,QString str3,QString str4,QString str5,QString str6){
    QSqlQuery query(db);
    QString s = "'";
    s.append(str3);
    s.append("'");
    QString s2 = "'";
    s2.append(str6);
    s2.append("'");

    QString updateStr;
    updateStr = "update " + str1 + " set " + str2 + " = " + s + " where " + str4 + "." + str5 + " = " + s2;
    query.prepare(updateStr);
    if(query.exec()){
        qDebug() << updateStr + "\n Update is succeed";
        QMessageBox updateMsg;
        updateMsg.setText(updateStr + "\n Update is succeed");
        updateMsg.exec();
    }
    else{
        qDebug() << updateStr ;
        QMessageBox updateMsg2;
        updateMsg2.setText(updateStr + "\n and Update is failed");
        updateMsg2.exec();
    }
    return;
}

void LinkSql::deleteFromSql(QString str1,QString str2,QString str3){
    QSqlQuery query(db);
    QString s = "'";
    s.append(str3);
    s.append("'");
    QString delStr;
    delStr = "delete from " + str1 + " where " + str2 + " = " + s;
    query.prepare(delStr);
    if(query.exec()){
        qDebug() << delStr + " \n succeed ";
        QMessageBox deleteMsg;
        deleteMsg.setText(delStr + "\n and delete is succeed!");
        deleteMsg.exec();
    }
    else{
        qDebug() << "failed";
        QMessageBox deleteMsg2;
        deleteMsg2.setText(delStr + "\n and delete is failed!");
        deleteMsg2.exec();
    }
    return;
}

QSqlQuery LinkSql::selectFromSql(QString str1,QString str2,QString str3){
    QSqlQuery query(db);
    QString s;
    QString s2 = "'";
    s2.append(str3);
    s2.append("'");
    s = "select * from " + str1 + " where " + str2 + " = " + s2;

    qDebug() << s;
    query.prepare(s);
    model.setQuery(s);
    if(query.exec())
    {
        qDebug() << "succeed";
    }
    else
    {
        qDebug() << "failed";
        QMessageBox selectMsg;
        selectMsg.setText(s + "\n and select is failed!");
        selectMsg.exec();
    }
    return query;
}

void LinkSql::selectAllInf(int i){
    switch (i) {
    case 0:
        model.setQuery("select * from Course");
        break;
    case 1:
        model.setQuery("select * from Family");
        break;
    case 2:
        model.setQuery("select * from Grade");
        break;
    case 3:
        model.setQuery("select * from Mark");
        break;
    case 4:
        model.setQuery("select * from Student");
        break;
    }

}

QSqlQuery LinkSql::addToCourse(QStringList list){
    QSqlQuery query(db);
    QString Cno = list.at(0);
    query.prepare("select sname from Course where Cno = :Cno");
    query.bindValue(":Cno",Cno);
    query.exec();
    if(query.next()){
        QMessageBox addCourseMsg;
        addCourseMsg.setText("This Cno has already in the database!\nPlease reset!");
        addCourseMsg.exec();
        return query;
    }
    query.prepare("insert into Course(Cno,Cname,Cteacher)" "values(?,?,?)");
    for(int i = 0;i < list.size(); ++i){
        qDebug() << list.at(i);
        query.bindValue(i,list.at(i));
    }
    if(query.exec()){
        qDebug() << "succeed";
        QMessageBox addCourseMsg2;
        addCourseMsg2.setText("Add to Course succeed!");
        addCourseMsg2.exec();
    }
    else{
        qDebug() << "failed";
        QMessageBox addCourseMsg3;
        addCourseMsg3.setText("Add to Course failed!");
        addCourseMsg3.exec();
    }
    return query;
}

QSqlQuery LinkSql::addToFamily(QStringList list)
{
    QSqlQuery query(db);
    QString Fno = list.at(0);
    query.prepare("select Fno from Family where Fno = :Fno");
    query.bindValue(":Fno",Fno);
    query.exec();
    if(query.next()){
        QMessageBox addFamilyMsg;
        addFamilyMsg.setText("This Fno has already in the database!\nPlease reset!");
        addFamilyMsg.exec();
        return query;
    }
    query.prepare("insert into Family(Fno,Fname,Fphone,Frelation,Sno)" "values(?,?,?,?,?)");
    for(int i = 0;i < list.size(); ++i){
        qDebug() << list.at(i);
        query.bindValue(i,list.at(i));
    }
    if(query.exec()){
        qDebug() << "succeed";
        QMessageBox addFamilyMsg2;
        addFamilyMsg2.setText("Add to Family succeed!");
        addFamilyMsg2.exec();
    }
    else{
        qDebug() << "failed";
        QMessageBox addFamilyMsg3;
        addFamilyMsg3.setText("Add to Family failed!");
        addFamilyMsg3.exec();
    }
    return query;
}

QSqlQuery LinkSql::addToGrade(QStringList list)
{
    QSqlQuery query(db);
    QString Gno = list.at(0);
    query.prepare("select Gno from Grade where Gno = :Gno");
    query.bindValue(":Gno",Gno);
    query.exec();
    if(query.next()){
        QMessageBox addGradeMsg;
        addGradeMsg.setText("This Gno has already in the database!\nPlease reset!");
        addGradeMsg.exec();
        return query;
    }
    query.prepare("insert into Grade(Gno,Gperiod,Ggrade,Gstunum,Gcharge)" "values(?,?,?,?,?)");
    for(int i = 0;i < list.size(); ++i){
        qDebug() << list.at(i);
        query.bindValue(i,list.at(i));
    }
    if(query.exec()){
        qDebug() << "succeed";
        QMessageBox addGradeMsg2;
        addGradeMsg2.setText("Add to Grade succeed!");
        addGradeMsg2.exec();
    }
    else{
        qDebug() << "failed";
        QMessageBox addGradeMsg3;
        addGradeMsg3.setText("Add to Grade failed!");
        addGradeMsg3.exec();
    }
    return query;
}

QSqlQuery LinkSql::addToMark(QStringList list)
{
    QSqlQuery query(db);
    QString Cno = list.at(0);
    query.prepare("select Cno from Course where Cno = :Cno");
    query.bindValue(":Cno",Cno);
    query.exec();
    if(!query.next()){
        QMessageBox addMarkMsg;
        addMarkMsg.setText("This Cno has not in the database!\nPlease reset!");
        addMarkMsg.exec();
        return query;
    }
    query.prepare("insert into Mark(Cno,Sno,Mpoint,Msemester)" "values(?,?,?,?)");
    for(int i = 0;i < list.size(); ++i){
        qDebug() << list.at(i);
        query.bindValue(i,list.at(i));
    }
    if(query.exec()){
        qDebug() << "succeed";
        QMessageBox addMarkMsg2;
        addMarkMsg2.setText("Add to Mark succeed!");
        addMarkMsg2.exec();
    }
    else{
        qDebug() << "failed";
        QMessageBox addMarkMsg3;
        addMarkMsg3.setText("Add to Mark failed!");
        addMarkMsg3.exec();
    }
    return query;
}

QSqlQuery LinkSql::addToStudent(QStringList list)
{
    QSqlQuery query(db);
    QString Sno = list.at(0);
    query.prepare("select Sno from Student where Sno = :Sno");
    query.bindValue(":Sno",Sno);
    query.exec();
    if(query.next()){
        QMessageBox addStudentMsg;
        addStudentMsg.setText("This Sno has already in the database!\nPlease reset!");
        addStudentMsg.exec();
        return query;
    }
    query.prepare("insert into Student(Sno,Sname,Ssex,Sdate,Snation,Sphone,Saddress,Sgraduate,Gno)" "values(?,?,?,?,?,?,?,?,?)");
    for(int i = 0;i < list.size(); ++i){
        qDebug() << list.at(i);
        query.bindValue(i,list.at(i));
    }
    if(query.exec()){
        qDebug() << "succeed";
        QMessageBox addStudentMsg2;
        addStudentMsg2.setText("Add to Student succeed!");
        addStudentMsg2.exec();
    }
    else{
        qDebug() << "failed";
        QMessageBox addStudentMsg3;
        addStudentMsg3.setText("Add to Student failed!");
        addStudentMsg3.exec();
    }
    return query;
}
