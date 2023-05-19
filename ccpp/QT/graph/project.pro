#-------------------------------------------------
#
# Project created by QtCreator 2018-06-12T15:50:47
#
#-------------------------------------------------
win: QT += core gui axcontainer
unix: QT += core gui
linux: QT += core gui

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

TARGET = project
TEMPLATE = app
CONFIG+=sdk_no_version_check
# The following define makes your compiler emit warnings if you use
# any feature of Qt which has been marked as deprecated (the exact warnings
# depend on your compiler). Please consult the documentation of the
# deprecated API in order to know how to port your code away from it.
DEFINES += QT_DEPRECATED_WARNINGS

# You can also make your code fail to compile if you use deprecated APIs.
# In order to do so, uncomment the following line.
# You can also select to disable deprecated APIs only up to a certain version of Qt.
#DEFINES += QT_DISABLE_DEPRECATED_BEFORE=0x060000    # disables all the APIs deprecated before Qt 6.0.0
INCLUDEPATH += inc/

SOURCES += \
    src/main.cpp \
    src/bellmanmark.cpp \
    src/floydmark.cpp \
    src/launchdialog.cpp \
    src/common.cpp \
    src/setdistancedialog.cpp \
    src/basevector.cpp \
    src/basematrix.cpp \
    src/nsmgraph.cpp \
    src/spgraph.cpp \
    src/spwindow.cpp \
    src/spframe.cpp \
    src/spvertex.cpp \
    src/spvertexparam.cpp \
    src/nsmvertex.cpp \
    src/nsmvertexparam.cpp \
    src/loadinfodialog.cpp \
    src/nsmwindow.cpp \
    src/nsmframe.cpp \
    src/setcapacityandcostdialog.cpp \
    src/setdemanddialog.cpp \
    src/nsmvertexdata.cpp \
    src/nsmvertexparamdata.cpp \
    src/nsmgraphdata.cpp \
    src/nsmdummyedge.cpp

HEADERS += \
    inc/common.h \
    inc/bellmanmark.h \
    inc/floydmark.h \
    inc/launchdialog.h \
    inc/setdistancedialog.h \
    inc/basevector.h \
    inc/basematrix.h \
    inc/nsmgraph.h \
    inc/spgraph.h \
    inc/spwindow.h \
    inc/spframe.h \
    inc/spvertex.h \
    inc/spvertexparam.h \
    inc/nsmvertex.h \
    inc/nsmvertexparam.h \
    inc/loadinfodialog.h \
    inc/nsmwindow.h \
    inc/nsmframe.h \
    inc/setcapacityandcostdialog.h \
    inc/setdemanddialog.h \
    inc/nsmvertexdata.h \
    inc/nsmvertexparamdata.h \
    inc/nsmgraphdata.h \
    inc/nsmdummyedge.h

FORMS += \
    form/launchdialog.ui \
    form/setdistancedialog.ui \
    form/spwindow.ui \
    form/loadinfodialog.ui \
    form/nsmwindow.ui \
    form/setcapacityandcostdialog.ui \
    form/setdemanddialog.ui

