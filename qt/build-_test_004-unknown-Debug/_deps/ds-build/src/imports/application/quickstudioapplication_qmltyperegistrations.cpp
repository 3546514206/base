/****************************************************************************
** Generated QML type registration code
**
** WARNING! All changes made in this file will be lost!
*****************************************************************************/

#include <QtQml/qqml.h>
#include <QtQml/qqmlmoduleregistration.h>

#include <quickstudioapplication_p.h>


#if !defined(QT_STATIC)
#define Q_QMLTYPE_EXPORT Q_DECL_EXPORT
#else
#define Q_QMLTYPE_EXPORT
#endif
Q_QMLTYPE_EXPORT void qml_register_types_QtQuick_Studio_Application()
{
    qmlRegisterModule("QtQuick.Studio.Application", 6, 0);
    qmlRegisterTypesAndRevisions<QuickStudioApplication>("QtQuick.Studio.Application", 6);
    qmlRegisterModule("QtQuick.Studio.Application", 6, 4);
}

static const QQmlModuleRegistration registration("QtQuick.Studio.Application", qml_register_types_QtQuick_Studio_Application);
