#ifndef LOADINFODIALOG_H
#define LOADINFODIALOG_H

#include <QDialog>
#include <QList>
namespace Ui {
class LoadInfoDialog;
}
class Vertex3Pos{
public:
    double x;
    double y;
    double z;
};
class NoDirEdge{
public:
    int v1;
    int v2;
    double dis;
    NoDirEdge(int v1,int v2,double dis){
        this->v1=v1;
        this->v2=v2;
        this->dis=dis;
    }
};
class LoadInfoDialog : public QDialog
{
    Q_OBJECT

public:
    explicit LoadInfoDialog(QWidget *parent = 0);
    ~LoadInfoDialog();
    double getDis(Vertex3Pos v1,Vertex3Pos v2);

    bool getSuccess() const;

    QList<NoDirEdge> *getNodirEdge() const;

    QList<Vertex3Pos> *getVpos() const;

public slots:
    void onBtnVexelClicked();
    void onBtnVertexClicked();
    void onBtnOKClicked();
private:
    Ui::LoadInfoDialog *ui;
    bool success;
    QList<Vertex3Pos>* vpos;
    QList<NoDirEdge>* nodirEdge;
    int vcnt;
};

#endif // LOADINFODIALOG_H
