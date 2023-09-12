TEMPLATE = app

QT += qml quick sql serialport

SOURCES += main.cpp \
    arc.cpp \
    databasemanager.cpp \
    communicationmanager.cpp \
    localcommunicationhandler.cpp

RESOURCES += ./SluiceUI/qml.qrc

# Additional import path used to resolve QML modules in Qt Creator's code model
QML_IMPORT_PATH =

# Default rules for deployment.
include(deployment.pri)

HEADERS += \
    arc.h \
    connection.h \
    databasemanager.h \
    protocolconfig.h \
    communicationmanager.h \
    localcommunicationhandler.h \
    addcrc.h

OTHER_FILES +=
