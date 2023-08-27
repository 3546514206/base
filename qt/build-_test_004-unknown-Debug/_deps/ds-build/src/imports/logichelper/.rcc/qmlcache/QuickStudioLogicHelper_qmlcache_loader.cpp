#include <QtQml/qqmlprivate.h>
#include <QtCore/qdir.h>
#include <QtCore/qurl.h>
#include <QtCore/qhash.h>
#include <QtCore/qstring.h>

namespace QmlCacheGeneratedCode {
namespace _0x5f_QtQuick_Studio_LogicHelper_BidirectionalBinding_qml { 
    extern const unsigned char qmlData[];
    extern const QQmlPrivate::TypedFunction aotBuiltFunctions[];
    const QQmlPrivate::CachedQmlUnit unit = {
        reinterpret_cast<const QV4::CompiledData::Unit*>(&qmlData), &aotBuiltFunctions[0], nullptr
    };
}
namespace _0x5f_QtQuick_Studio_LogicHelper_RangeMapper_qml { 
    extern const unsigned char qmlData[];
    extern const QQmlPrivate::TypedFunction aotBuiltFunctions[];
    const QQmlPrivate::CachedQmlUnit unit = {
        reinterpret_cast<const QV4::CompiledData::Unit*>(&qmlData), &aotBuiltFunctions[0], nullptr
    };
}
namespace _0x5f_QtQuick_Studio_LogicHelper_MinMaxMapper_qml { 
    extern const unsigned char qmlData[];
    extern const QQmlPrivate::TypedFunction aotBuiltFunctions[];
    const QQmlPrivate::CachedQmlUnit unit = {
        reinterpret_cast<const QV4::CompiledData::Unit*>(&qmlData), &aotBuiltFunctions[0], nullptr
    };
}
namespace _0x5f_QtQuick_Studio_LogicHelper_StringMapper_qml { 
    extern const unsigned char qmlData[];
    extern const QQmlPrivate::TypedFunction aotBuiltFunctions[];
    const QQmlPrivate::CachedQmlUnit unit = {
        reinterpret_cast<const QV4::CompiledData::Unit*>(&qmlData), &aotBuiltFunctions[0], nullptr
    };
}
namespace _0x5f_QtQuick_Studio_LogicHelper_OrOperator_qml { 
    extern const unsigned char qmlData[];
    extern const QQmlPrivate::TypedFunction aotBuiltFunctions[];
    const QQmlPrivate::CachedQmlUnit unit = {
        reinterpret_cast<const QV4::CompiledData::Unit*>(&qmlData), &aotBuiltFunctions[0], nullptr
    };
}
namespace _0x5f_QtQuick_Studio_LogicHelper_AndOperator_qml { 
    extern const unsigned char qmlData[];
    extern const QQmlPrivate::TypedFunction aotBuiltFunctions[];
    const QQmlPrivate::CachedQmlUnit unit = {
        reinterpret_cast<const QV4::CompiledData::Unit*>(&qmlData), &aotBuiltFunctions[0], nullptr
    };
}
namespace _0x5f_QtQuick_Studio_LogicHelper_NotOperator_qml { 
    extern const unsigned char qmlData[];
    extern const QQmlPrivate::TypedFunction aotBuiltFunctions[];
    const QQmlPrivate::CachedQmlUnit unit = {
        reinterpret_cast<const QV4::CompiledData::Unit*>(&qmlData), &aotBuiltFunctions[0], nullptr
    };
}

}
namespace {
struct Registry {
    Registry();
    ~Registry();
    QHash<QString, const QQmlPrivate::CachedQmlUnit*> resourcePathToCachedUnit;
    static const QQmlPrivate::CachedQmlUnit *lookupCachedUnit(const QUrl &url);
};

Q_GLOBAL_STATIC(Registry, unitRegistry)


Registry::Registry() {
    resourcePathToCachedUnit.insert(QStringLiteral("/QtQuick/Studio/LogicHelper/BidirectionalBinding.qml"), &QmlCacheGeneratedCode::_0x5f_QtQuick_Studio_LogicHelper_BidirectionalBinding_qml::unit);
    resourcePathToCachedUnit.insert(QStringLiteral("/QtQuick/Studio/LogicHelper/RangeMapper.qml"), &QmlCacheGeneratedCode::_0x5f_QtQuick_Studio_LogicHelper_RangeMapper_qml::unit);
    resourcePathToCachedUnit.insert(QStringLiteral("/QtQuick/Studio/LogicHelper/MinMaxMapper.qml"), &QmlCacheGeneratedCode::_0x5f_QtQuick_Studio_LogicHelper_MinMaxMapper_qml::unit);
    resourcePathToCachedUnit.insert(QStringLiteral("/QtQuick/Studio/LogicHelper/StringMapper.qml"), &QmlCacheGeneratedCode::_0x5f_QtQuick_Studio_LogicHelper_StringMapper_qml::unit);
    resourcePathToCachedUnit.insert(QStringLiteral("/QtQuick/Studio/LogicHelper/OrOperator.qml"), &QmlCacheGeneratedCode::_0x5f_QtQuick_Studio_LogicHelper_OrOperator_qml::unit);
    resourcePathToCachedUnit.insert(QStringLiteral("/QtQuick/Studio/LogicHelper/AndOperator.qml"), &QmlCacheGeneratedCode::_0x5f_QtQuick_Studio_LogicHelper_AndOperator_qml::unit);
    resourcePathToCachedUnit.insert(QStringLiteral("/QtQuick/Studio/LogicHelper/NotOperator.qml"), &QmlCacheGeneratedCode::_0x5f_QtQuick_Studio_LogicHelper_NotOperator_qml::unit);
    QQmlPrivate::RegisterQmlUnitCacheHook registration;
    registration.structVersion = 0;
    registration.lookupCachedQmlUnit = &lookupCachedUnit;
    QQmlPrivate::qmlregister(QQmlPrivate::QmlUnitCacheHookRegistration, &registration);
}

Registry::~Registry() {
    QQmlPrivate::qmlunregister(QQmlPrivate::QmlUnitCacheHookRegistration, quintptr(&lookupCachedUnit));
}

const QQmlPrivate::CachedQmlUnit *Registry::lookupCachedUnit(const QUrl &url) {
    if (url.scheme() != QLatin1String("qrc"))
        return nullptr;
    QString resourcePath = QDir::cleanPath(url.path());
    if (resourcePath.isEmpty())
        return nullptr;
    if (!resourcePath.startsWith(QLatin1Char('/')))
        resourcePath.prepend(QLatin1Char('/'));
    return unitRegistry()->resourcePathToCachedUnit.value(resourcePath, nullptr);
}
}
int QT_MANGLE_NAMESPACE(qInitResources_qmlcache_QuickStudioLogicHelper)() {
    ::unitRegistry();
    return 1;
}
Q_CONSTRUCTOR_FUNCTION(QT_MANGLE_NAMESPACE(qInitResources_qmlcache_QuickStudioLogicHelper))
int QT_MANGLE_NAMESPACE(qCleanupResources_qmlcache_QuickStudioLogicHelper)() {
    return 1;
}
