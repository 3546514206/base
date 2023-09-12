#-------------------------------------------------
#
# Project created by QtCreator 2015-06-18T19:46:58
#
#-------------------------------------------------

QT       += core gui

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

TARGET = Qt-2048
TEMPLATE = app


SOURCES += main.cpp\
    app.cpp \
    gamemodel.cpp \
    gameview.cpp \
    mask.cpp \
    numbergrid.cpp \
    scoreboard.cpp \
    tile.cpp

HEADERS  += \
    app.h \
    gamedatadef.h \
    gamemodel.h \
    gameview.h \
    mask.h \
    numbergrid.h \
    scoreboard.h \
    tile.h
