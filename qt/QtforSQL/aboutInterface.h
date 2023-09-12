#ifndef ABOUTINTERFACE_H
#define ABOUTINTERFACE_H

#include <QWidget>
#include <QLabel>
#include <QPushButton>
#include <QString>
#include <QPixmap>
#include <QVBoxLayout>
#include <QHBoxLayout>
#include <QApplication>

class aboutInterface : public QWidget
{
    Q_OBJECT
public:
    aboutInterface(QWidget *parent = 0);
    ~aboutInterface();
private:
    QLabel *aboutPictureLabel;
    QLabel *aboutTextLabel;
    QLabel *aboutVLineLabel;
    QLabel *aboutHLineLabel;

    QPushButton *quitBtn;

    QVBoxLayout *aboutLeftVBoxLayout;
    QVBoxLayout *aboutRightVBoxLayout;
    QHBoxLayout *aboutHBoxLayout;

};

#endif // ABOUTINTERFACE_H
