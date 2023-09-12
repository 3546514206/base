#-------------------------------------------------
#
# Project created by QtCreator 2012-10-29T13:55:33
#
#-------------------------------------------------

QT       += core gui
QT	 += widgets
QT += sql

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

TARGET = QtforSQL
TEMPLATE = app


SOURCES += main.cpp\
    login.cpp\
    mainform.cpp \
    addMark.cpp \
    addStudent.cpp \
    linksql.cpp \
    addCourse.cpp \
    addGrade.cpp \
    addFamily.cpp \
    appendInterface.cpp \
    aboutInterface.cpp \
    selectInterface.cpp \
    deleteInterface.cpp \
    updateInterface.cpp

HEADERS  += \
    login.h \
    mainform.h \
    addCourse.h \
    addMark.h \
    addStudent.h \
    linksql.h \
    addGrade.h \
    addFamily.h \
    appendInterface.h \
    aboutInterface.h \
    selectInterface.h \
    deleteInterface.h \
    updateInterface.h

FORMS    +=

RESOURCES += \
    image/image.qrc

OTHER_FILES +=

DISTFILES +=
