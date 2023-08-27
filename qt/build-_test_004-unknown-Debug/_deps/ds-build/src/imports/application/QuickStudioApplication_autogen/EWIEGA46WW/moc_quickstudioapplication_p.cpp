/****************************************************************************
** Meta object code from reading C++ file 'quickstudioapplication_p.h'
**
** Created by: The Qt Meta Object Compiler version 68 (Qt 6.5.1)
**
** WARNING! All changes made in this file will be lost!
*****************************************************************************/

#include "../../../../../../ds-src/src/imports/application/quickstudioapplication_p.h"
#include <QtCore/qmetatype.h>

#if __has_include(<QtCore/qtmochelpers.h>)
#include <QtCore/qtmochelpers.h>
#else
QT_BEGIN_MOC_NAMESPACE
#endif


#include <memory>

#if !defined(Q_MOC_OUTPUT_REVISION)
#error "The header file 'quickstudioapplication_p.h' doesn't include <QObject>."
#elif Q_MOC_OUTPUT_REVISION != 68
#error "This file was generated using the moc from 6.5.1. It"
#error "cannot be used with the include files from this version of Qt."
#error "(The moc has changed too much.)"
#endif

#ifndef Q_CONSTINIT
#define Q_CONSTINIT
#endif

QT_WARNING_PUSH
QT_WARNING_DISABLE_DEPRECATED
QT_WARNING_DISABLE_GCC("-Wuseless-cast")
namespace {

#ifdef QT_MOC_HAS_STRINGDATA
struct qt_meta_stringdata_CLASSQuickStudioApplicationENDCLASS_t {};
static constexpr auto qt_meta_stringdata_CLASSQuickStudioApplicationENDCLASS = QtMocHelpers::stringData(
    "QuickStudioApplication",
    "QML.Element",
    "StudioApplication",
    "QML.AddedInVersion",
    "1538",
    "fontPathChanged",
    "",
    "fontPath"
);
#else  // !QT_MOC_HAS_STRING_DATA
struct qt_meta_stringdata_CLASSQuickStudioApplicationENDCLASS_t {
    uint offsetsAndSizes[16];
    char stringdata0[23];
    char stringdata1[12];
    char stringdata2[18];
    char stringdata3[19];
    char stringdata4[5];
    char stringdata5[16];
    char stringdata6[1];
    char stringdata7[9];
};
#define QT_MOC_LITERAL(ofs, len) \
    uint(sizeof(qt_meta_stringdata_CLASSQuickStudioApplicationENDCLASS_t::offsetsAndSizes) + ofs), len 
Q_CONSTINIT static const qt_meta_stringdata_CLASSQuickStudioApplicationENDCLASS_t qt_meta_stringdata_CLASSQuickStudioApplicationENDCLASS = {
    {
        QT_MOC_LITERAL(0, 22),  // "QuickStudioApplication"
        QT_MOC_LITERAL(23, 11),  // "QML.Element"
        QT_MOC_LITERAL(35, 17),  // "StudioApplication"
        QT_MOC_LITERAL(53, 18),  // "QML.AddedInVersion"
        QT_MOC_LITERAL(72, 4),  // "1538"
        QT_MOC_LITERAL(77, 15),  // "fontPathChanged"
        QT_MOC_LITERAL(93, 0),  // ""
        QT_MOC_LITERAL(94, 8)   // "fontPath"
    },
    "QuickStudioApplication",
    "QML.Element",
    "StudioApplication",
    "QML.AddedInVersion",
    "1538",
    "fontPathChanged",
    "",
    "fontPath"
};
#undef QT_MOC_LITERAL
#endif // !QT_MOC_HAS_STRING_DATA
} // unnamed namespace

Q_CONSTINIT static const uint qt_meta_data_CLASSQuickStudioApplicationENDCLASS[] = {

 // content:
      11,       // revision
       0,       // classname
       2,   14, // classinfo
       1,   18, // methods
       1,   25, // properties
       0,    0, // enums/sets
       0,    0, // constructors
       0,       // flags
       1,       // signalCount

 // classinfo: key, value
       1,    2,
       3,    4,

 // signals: name, argc, parameters, tag, flags, initial metatype offsets
       5,    0,   24,    6, 0x06,    2 /* Public */,

 // signals: parameters
    QMetaType::Void,

 // properties: name, type, flags
       7, QMetaType::QUrl, 0x00015103, uint(0), 0,

       0        // eod
};

Q_CONSTINIT const QMetaObject QuickStudioApplication::staticMetaObject = { {
    QMetaObject::SuperData::link<QObject::staticMetaObject>(),
    qt_meta_stringdata_CLASSQuickStudioApplicationENDCLASS.offsetsAndSizes,
    qt_meta_data_CLASSQuickStudioApplicationENDCLASS,
    qt_static_metacall,
    nullptr,
    qt_metaTypeArray<
        // property 'fontPath'
        QUrl,
        // Q_OBJECT / Q_GADGET
        QuickStudioApplication,
        // method 'fontPathChanged'
        void
    >,
    nullptr
} };

void QuickStudioApplication::qt_static_metacall(QObject *_o, QMetaObject::Call _c, int _id, void **_a)
{
    if (_c == QMetaObject::InvokeMetaMethod) {
        auto *_t = static_cast<QuickStudioApplication *>(_o);
        (void)_t;
        switch (_id) {
        case 0: _t->fontPathChanged(); break;
        default: ;
        }
    } else if (_c == QMetaObject::IndexOfMethod) {
        int *result = reinterpret_cast<int *>(_a[0]);
        {
            using _t = void (QuickStudioApplication::*)();
            if (_t _q_method = &QuickStudioApplication::fontPathChanged; *reinterpret_cast<_t *>(_a[1]) == _q_method) {
                *result = 0;
                return;
            }
        }
    }else if (_c == QMetaObject::ReadProperty) {
        auto *_t = static_cast<QuickStudioApplication *>(_o);
        (void)_t;
        void *_v = _a[0];
        switch (_id) {
        case 0: *reinterpret_cast< QUrl*>(_v) = _t->fontPath(); break;
        default: break;
        }
    } else if (_c == QMetaObject::WriteProperty) {
        auto *_t = static_cast<QuickStudioApplication *>(_o);
        (void)_t;
        void *_v = _a[0];
        switch (_id) {
        case 0: _t->setFontPath(*reinterpret_cast< QUrl*>(_v)); break;
        default: break;
        }
    } else if (_c == QMetaObject::ResetProperty) {
    } else if (_c == QMetaObject::BindableProperty) {
    }
    (void)_a;
}

const QMetaObject *QuickStudioApplication::metaObject() const
{
    return QObject::d_ptr->metaObject ? QObject::d_ptr->dynamicMetaObject() : &staticMetaObject;
}

void *QuickStudioApplication::qt_metacast(const char *_clname)
{
    if (!_clname) return nullptr;
    if (!strcmp(_clname, qt_meta_stringdata_CLASSQuickStudioApplicationENDCLASS.stringdata0))
        return static_cast<void*>(this);
    return QObject::qt_metacast(_clname);
}

int QuickStudioApplication::qt_metacall(QMetaObject::Call _c, int _id, void **_a)
{
    _id = QObject::qt_metacall(_c, _id, _a);
    if (_id < 0)
        return _id;
    if (_c == QMetaObject::InvokeMetaMethod) {
        if (_id < 1)
            qt_static_metacall(this, _c, _id, _a);
        _id -= 1;
    } else if (_c == QMetaObject::RegisterMethodArgumentMetaType) {
        if (_id < 1)
            *reinterpret_cast<QMetaType *>(_a[0]) = QMetaType();
        _id -= 1;
    }else if (_c == QMetaObject::ReadProperty || _c == QMetaObject::WriteProperty
            || _c == QMetaObject::ResetProperty || _c == QMetaObject::BindableProperty
            || _c == QMetaObject::RegisterPropertyMetaType) {
        qt_static_metacall(this, _c, _id, _a);
        _id -= 1;
    }
    return _id;
}

// SIGNAL 0
void QuickStudioApplication::fontPathChanged()
{
    QMetaObject::activate(this, &staticMetaObject, 0, nullptr);
}
QT_WARNING_POP
