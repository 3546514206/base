#-------------------------------------------------
#
# Project created by QtCreator 2013-08-19T21:30:28
#
#-------------------------------------------------

QT       += core gui
QT       += network

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

TARGET = chat
TEMPLATE = app


SOURCES += main.cpp\
        widget.cpp \
    tcpserver.cpp \
    tcpclient.cpp \
    logindialog.cpp \
    mytextedit.cpp

HEADERS  += widget.h \
    tcpserver.h \
    tcpclient.h \
    logindialog.h \
    mytextedit.h

FORMS    += widget.ui \
    tcpserver.ui \
    tcpclient.ui \
    logindialog.ui

RESOURCES += \
    Resource.qrc
RC_FILE   += myico.rc
