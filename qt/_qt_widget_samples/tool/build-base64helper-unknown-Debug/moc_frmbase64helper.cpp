/****************************************************************************
** Meta object code from reading C++ file 'frmbase64helper.h'
**
** Created by: The Qt Meta Object Compiler version 68 (Qt 6.5.1)
**
** WARNING! All changes made in this file will be lost!
*****************************************************************************/

#include "../base64helper/frmbase64helper.h"
#include <QtCore/qmetatype.h>

#if __has_include(<QtCore/qtmochelpers.h>)
#include <QtCore/qtmochelpers.h>
#else
QT_BEGIN_MOC_NAMESPACE
#endif


#include <memory>

#if !defined(Q_MOC_OUTPUT_REVISION)
#error "The header file 'frmbase64helper.h' doesn't include <QObject>."
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
struct qt_meta_stringdata_CLASSfrmBase64HelperENDCLASS_t {};
static constexpr auto qt_meta_stringdata_CLASSfrmBase64HelperENDCLASS = QtMocHelpers::stringData(
    "frmBase64Helper",
    "on_btnOpen_clicked",
    "",
    "on_btnClear_clicked",
    "on_btnImageToBase64_clicked",
    "on_btnBase64ToImage_clicked",
    "on_btnTextToBase64_clicked",
    "on_btnBase64ToText_clicked"
);
#else  // !QT_MOC_HAS_STRING_DATA
struct qt_meta_stringdata_CLASSfrmBase64HelperENDCLASS_t {
    uint offsetsAndSizes[16];
    char stringdata0[16];
    char stringdata1[19];
    char stringdata2[1];
    char stringdata3[20];
    char stringdata4[28];
    char stringdata5[28];
    char stringdata6[27];
    char stringdata7[27];
};
#define QT_MOC_LITERAL(ofs, len) \
    uint(sizeof(qt_meta_stringdata_CLASSfrmBase64HelperENDCLASS_t::offsetsAndSizes) + ofs), len 
Q_CONSTINIT static const qt_meta_stringdata_CLASSfrmBase64HelperENDCLASS_t qt_meta_stringdata_CLASSfrmBase64HelperENDCLASS = {
    {
        QT_MOC_LITERAL(0, 15),  // "frmBase64Helper"
        QT_MOC_LITERAL(16, 18),  // "on_btnOpen_clicked"
        QT_MOC_LITERAL(35, 0),  // ""
        QT_MOC_LITERAL(36, 19),  // "on_btnClear_clicked"
        QT_MOC_LITERAL(56, 27),  // "on_btnImageToBase64_clicked"
        QT_MOC_LITERAL(84, 27),  // "on_btnBase64ToImage_clicked"
        QT_MOC_LITERAL(112, 26),  // "on_btnTextToBase64_clicked"
        QT_MOC_LITERAL(139, 26)   // "on_btnBase64ToText_clicked"
    },
    "frmBase64Helper",
    "on_btnOpen_clicked",
    "",
    "on_btnClear_clicked",
    "on_btnImageToBase64_clicked",
    "on_btnBase64ToImage_clicked",
    "on_btnTextToBase64_clicked",
    "on_btnBase64ToText_clicked"
};
#undef QT_MOC_LITERAL
#endif // !QT_MOC_HAS_STRING_DATA
} // unnamed namespace

Q_CONSTINIT static const uint qt_meta_data_CLASSfrmBase64HelperENDCLASS[] = {

 // content:
      11,       // revision
       0,       // classname
       0,    0, // classinfo
       6,   14, // methods
       0,    0, // properties
       0,    0, // enums/sets
       0,    0, // constructors
       0,       // flags
       0,       // signalCount

 // slots: name, argc, parameters, tag, flags, initial metatype offsets
       1,    0,   50,    2, 0x08,    1 /* Private */,
       3,    0,   51,    2, 0x08,    2 /* Private */,
       4,    0,   52,    2, 0x08,    3 /* Private */,
       5,    0,   53,    2, 0x08,    4 /* Private */,
       6,    0,   54,    2, 0x08,    5 /* Private */,
       7,    0,   55,    2, 0x08,    6 /* Private */,

 // slots: parameters
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,

       0        // eod
};

Q_CONSTINIT const QMetaObject frmBase64Helper::staticMetaObject = { {
    QMetaObject::SuperData::link<QWidget::staticMetaObject>(),
    qt_meta_stringdata_CLASSfrmBase64HelperENDCLASS.offsetsAndSizes,
    qt_meta_data_CLASSfrmBase64HelperENDCLASS,
    qt_static_metacall,
    nullptr,
    qt_incomplete_metaTypeArray<qt_meta_stringdata_CLASSfrmBase64HelperENDCLASS_t,
        // Q_OBJECT / Q_GADGET
        QtPrivate::TypeAndForceComplete<frmBase64Helper, std::true_type>,
        // method 'on_btnOpen_clicked'
        QtPrivate::TypeAndForceComplete<void, std::false_type>,
        // method 'on_btnClear_clicked'
        QtPrivate::TypeAndForceComplete<void, std::false_type>,
        // method 'on_btnImageToBase64_clicked'
        QtPrivate::TypeAndForceComplete<void, std::false_type>,
        // method 'on_btnBase64ToImage_clicked'
        QtPrivate::TypeAndForceComplete<void, std::false_type>,
        // method 'on_btnTextToBase64_clicked'
        QtPrivate::TypeAndForceComplete<void, std::false_type>,
        // method 'on_btnBase64ToText_clicked'
        QtPrivate::TypeAndForceComplete<void, std::false_type>
    >,
    nullptr
} };

void frmBase64Helper::qt_static_metacall(QObject *_o, QMetaObject::Call _c, int _id, void **_a)
{
    if (_c == QMetaObject::InvokeMetaMethod) {
        auto *_t = static_cast<frmBase64Helper *>(_o);
        (void)_t;
        switch (_id) {
        case 0: _t->on_btnOpen_clicked(); break;
        case 1: _t->on_btnClear_clicked(); break;
        case 2: _t->on_btnImageToBase64_clicked(); break;
        case 3: _t->on_btnBase64ToImage_clicked(); break;
        case 4: _t->on_btnTextToBase64_clicked(); break;
        case 5: _t->on_btnBase64ToText_clicked(); break;
        default: ;
        }
    }
    (void)_a;
}

const QMetaObject *frmBase64Helper::metaObject() const
{
    return QObject::d_ptr->metaObject ? QObject::d_ptr->dynamicMetaObject() : &staticMetaObject;
}

void *frmBase64Helper::qt_metacast(const char *_clname)
{
    if (!_clname) return nullptr;
    if (!strcmp(_clname, qt_meta_stringdata_CLASSfrmBase64HelperENDCLASS.stringdata0))
        return static_cast<void*>(this);
    return QWidget::qt_metacast(_clname);
}

int frmBase64Helper::qt_metacall(QMetaObject::Call _c, int _id, void **_a)
{
    _id = QWidget::qt_metacall(_c, _id, _a);
    if (_id < 0)
        return _id;
    if (_c == QMetaObject::InvokeMetaMethod) {
        if (_id < 6)
            qt_static_metacall(this, _c, _id, _a);
        _id -= 6;
    } else if (_c == QMetaObject::RegisterMethodArgumentMetaType) {
        if (_id < 6)
            *reinterpret_cast<QMetaType *>(_a[0]) = QMetaType();
        _id -= 6;
    }
    return _id;
}
QT_WARNING_POP
