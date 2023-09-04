/****************************************************************************
** Meta object code from reading C++ file 'frmemailtool.h'
**
** Created by: The Qt Meta Object Compiler version 68 (Qt 6.5.1)
**
** WARNING! All changes made in this file will be lost!
*****************************************************************************/

#include "../emailtool/frmemailtool.h"
#include <QtCore/qmetatype.h>

#if __has_include(<QtCore/qtmochelpers.h>)
#include <QtCore/qtmochelpers.h>
#else
QT_BEGIN_MOC_NAMESPACE
#endif


#include <memory>

#if !defined(Q_MOC_OUTPUT_REVISION)
#error "The header file 'frmemailtool.h' doesn't include <QObject>."
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
struct qt_meta_stringdata_CLASSfrmEmailToolENDCLASS_t {};
static constexpr auto qt_meta_stringdata_CLASSfrmEmailToolENDCLASS = QtMocHelpers::stringData(
    "frmEmailTool",
    "initForm",
    "",
    "receiveEmailResult",
    "result",
    "on_btnSend_clicked",
    "on_btnSelect_clicked",
    "on_cboxServer_currentIndexChanged",
    "index"
);
#else  // !QT_MOC_HAS_STRING_DATA
struct qt_meta_stringdata_CLASSfrmEmailToolENDCLASS_t {
    uint offsetsAndSizes[18];
    char stringdata0[13];
    char stringdata1[9];
    char stringdata2[1];
    char stringdata3[19];
    char stringdata4[7];
    char stringdata5[19];
    char stringdata6[21];
    char stringdata7[34];
    char stringdata8[6];
};
#define QT_MOC_LITERAL(ofs, len) \
    uint(sizeof(qt_meta_stringdata_CLASSfrmEmailToolENDCLASS_t::offsetsAndSizes) + ofs), len 
Q_CONSTINIT static const qt_meta_stringdata_CLASSfrmEmailToolENDCLASS_t qt_meta_stringdata_CLASSfrmEmailToolENDCLASS = {
    {
        QT_MOC_LITERAL(0, 12),  // "frmEmailTool"
        QT_MOC_LITERAL(13, 8),  // "initForm"
        QT_MOC_LITERAL(22, 0),  // ""
        QT_MOC_LITERAL(23, 18),  // "receiveEmailResult"
        QT_MOC_LITERAL(42, 6),  // "result"
        QT_MOC_LITERAL(49, 18),  // "on_btnSend_clicked"
        QT_MOC_LITERAL(68, 20),  // "on_btnSelect_clicked"
        QT_MOC_LITERAL(89, 33),  // "on_cboxServer_currentIndexCha..."
        QT_MOC_LITERAL(123, 5)   // "index"
    },
    "frmEmailTool",
    "initForm",
    "",
    "receiveEmailResult",
    "result",
    "on_btnSend_clicked",
    "on_btnSelect_clicked",
    "on_cboxServer_currentIndexChanged",
    "index"
};
#undef QT_MOC_LITERAL
#endif // !QT_MOC_HAS_STRING_DATA
} // unnamed namespace

Q_CONSTINIT static const uint qt_meta_data_CLASSfrmEmailToolENDCLASS[] = {

 // content:
      11,       // revision
       0,       // classname
       0,    0, // classinfo
       5,   14, // methods
       0,    0, // properties
       0,    0, // enums/sets
       0,    0, // constructors
       0,       // flags
       0,       // signalCount

 // slots: name, argc, parameters, tag, flags, initial metatype offsets
       1,    0,   44,    2, 0x08,    1 /* Private */,
       3,    1,   45,    2, 0x08,    2 /* Private */,
       5,    0,   48,    2, 0x08,    4 /* Private */,
       6,    0,   49,    2, 0x08,    5 /* Private */,
       7,    1,   50,    2, 0x08,    6 /* Private */,

 // slots: parameters
    QMetaType::Void,
    QMetaType::Void, QMetaType::QString,    4,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void, QMetaType::Int,    8,

       0        // eod
};

Q_CONSTINIT const QMetaObject frmEmailTool::staticMetaObject = { {
    QMetaObject::SuperData::link<QWidget::staticMetaObject>(),
    qt_meta_stringdata_CLASSfrmEmailToolENDCLASS.offsetsAndSizes,
    qt_meta_data_CLASSfrmEmailToolENDCLASS,
    qt_static_metacall,
    nullptr,
    qt_incomplete_metaTypeArray<qt_meta_stringdata_CLASSfrmEmailToolENDCLASS_t,
        // Q_OBJECT / Q_GADGET
        QtPrivate::TypeAndForceComplete<frmEmailTool, std::true_type>,
        // method 'initForm'
        QtPrivate::TypeAndForceComplete<void, std::false_type>,
        // method 'receiveEmailResult'
        QtPrivate::TypeAndForceComplete<void, std::false_type>,
        QtPrivate::TypeAndForceComplete<QString, std::false_type>,
        // method 'on_btnSend_clicked'
        QtPrivate::TypeAndForceComplete<void, std::false_type>,
        // method 'on_btnSelect_clicked'
        QtPrivate::TypeAndForceComplete<void, std::false_type>,
        // method 'on_cboxServer_currentIndexChanged'
        QtPrivate::TypeAndForceComplete<void, std::false_type>,
        QtPrivate::TypeAndForceComplete<int, std::false_type>
    >,
    nullptr
} };

void frmEmailTool::qt_static_metacall(QObject *_o, QMetaObject::Call _c, int _id, void **_a)
{
    if (_c == QMetaObject::InvokeMetaMethod) {
        auto *_t = static_cast<frmEmailTool *>(_o);
        (void)_t;
        switch (_id) {
        case 0: _t->initForm(); break;
        case 1: _t->receiveEmailResult((*reinterpret_cast< std::add_pointer_t<QString>>(_a[1]))); break;
        case 2: _t->on_btnSend_clicked(); break;
        case 3: _t->on_btnSelect_clicked(); break;
        case 4: _t->on_cboxServer_currentIndexChanged((*reinterpret_cast< std::add_pointer_t<int>>(_a[1]))); break;
        default: ;
        }
    }
}

const QMetaObject *frmEmailTool::metaObject() const
{
    return QObject::d_ptr->metaObject ? QObject::d_ptr->dynamicMetaObject() : &staticMetaObject;
}

void *frmEmailTool::qt_metacast(const char *_clname)
{
    if (!_clname) return nullptr;
    if (!strcmp(_clname, qt_meta_stringdata_CLASSfrmEmailToolENDCLASS.stringdata0))
        return static_cast<void*>(this);
    return QWidget::qt_metacast(_clname);
}

int frmEmailTool::qt_metacall(QMetaObject::Call _c, int _id, void **_a)
{
    _id = QWidget::qt_metacall(_c, _id, _a);
    if (_id < 0)
        return _id;
    if (_c == QMetaObject::InvokeMetaMethod) {
        if (_id < 5)
            qt_static_metacall(this, _c, _id, _a);
        _id -= 5;
    } else if (_c == QMetaObject::RegisterMethodArgumentMetaType) {
        if (_id < 5)
            *reinterpret_cast<QMetaType *>(_a[0]) = QMetaType();
        _id -= 5;
    }
    return _id;
}
QT_WARNING_POP
