/****************************************************************************
** Meta object code from reading C++ file 'sendemailthread.h'
**
** Created by: The Qt Meta Object Compiler version 68 (Qt 6.5.1)
**
** WARNING! All changes made in this file will be lost!
*****************************************************************************/

#include "../emailtool/sendemailthread.h"
#include <QtCore/qmetatype.h>

#if __has_include(<QtCore/qtmochelpers.h>)
#include <QtCore/qtmochelpers.h>
#else
QT_BEGIN_MOC_NAMESPACE
#endif


#include <memory>

#if !defined(Q_MOC_OUTPUT_REVISION)
#error "The header file 'sendemailthread.h' doesn't include <QObject>."
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
struct qt_meta_stringdata_CLASSSendEmailThreadENDCLASS_t {};
static constexpr auto qt_meta_stringdata_CLASSSendEmailThreadENDCLASS = QtMocHelpers::stringData(
    "SendEmailThread",
    "receiveEmailResult",
    "",
    "result",
    "stop",
    "setEmailTitle",
    "emailTitle",
    "setSendEmailAddr",
    "sendEmailAddr",
    "setSendEmailPwd",
    "sendEmailPwd",
    "setReceiveEmailAddr",
    "receiveEmailAddr",
    "append",
    "content",
    "fileName"
);
#else  // !QT_MOC_HAS_STRING_DATA
struct qt_meta_stringdata_CLASSSendEmailThreadENDCLASS_t {
    uint offsetsAndSizes[32];
    char stringdata0[16];
    char stringdata1[19];
    char stringdata2[1];
    char stringdata3[7];
    char stringdata4[5];
    char stringdata5[14];
    char stringdata6[11];
    char stringdata7[17];
    char stringdata8[14];
    char stringdata9[16];
    char stringdata10[13];
    char stringdata11[20];
    char stringdata12[17];
    char stringdata13[7];
    char stringdata14[8];
    char stringdata15[9];
};
#define QT_MOC_LITERAL(ofs, len) \
    uint(sizeof(qt_meta_stringdata_CLASSSendEmailThreadENDCLASS_t::offsetsAndSizes) + ofs), len 
Q_CONSTINIT static const qt_meta_stringdata_CLASSSendEmailThreadENDCLASS_t qt_meta_stringdata_CLASSSendEmailThreadENDCLASS = {
    {
        QT_MOC_LITERAL(0, 15),  // "SendEmailThread"
        QT_MOC_LITERAL(16, 18),  // "receiveEmailResult"
        QT_MOC_LITERAL(35, 0),  // ""
        QT_MOC_LITERAL(36, 6),  // "result"
        QT_MOC_LITERAL(43, 4),  // "stop"
        QT_MOC_LITERAL(48, 13),  // "setEmailTitle"
        QT_MOC_LITERAL(62, 10),  // "emailTitle"
        QT_MOC_LITERAL(73, 16),  // "setSendEmailAddr"
        QT_MOC_LITERAL(90, 13),  // "sendEmailAddr"
        QT_MOC_LITERAL(104, 15),  // "setSendEmailPwd"
        QT_MOC_LITERAL(120, 12),  // "sendEmailPwd"
        QT_MOC_LITERAL(133, 19),  // "setReceiveEmailAddr"
        QT_MOC_LITERAL(153, 16),  // "receiveEmailAddr"
        QT_MOC_LITERAL(170, 6),  // "append"
        QT_MOC_LITERAL(177, 7),  // "content"
        QT_MOC_LITERAL(185, 8)   // "fileName"
    },
    "SendEmailThread",
    "receiveEmailResult",
    "",
    "result",
    "stop",
    "setEmailTitle",
    "emailTitle",
    "setSendEmailAddr",
    "sendEmailAddr",
    "setSendEmailPwd",
    "sendEmailPwd",
    "setReceiveEmailAddr",
    "receiveEmailAddr",
    "append",
    "content",
    "fileName"
};
#undef QT_MOC_LITERAL
#endif // !QT_MOC_HAS_STRING_DATA
} // unnamed namespace

Q_CONSTINIT static const uint qt_meta_data_CLASSSendEmailThreadENDCLASS[] = {

 // content:
      11,       // revision
       0,       // classname
       0,    0, // classinfo
       7,   14, // methods
       0,    0, // properties
       0,    0, // enums/sets
       0,    0, // constructors
       0,       // flags
       1,       // signalCount

 // signals: name, argc, parameters, tag, flags, initial metatype offsets
       1,    1,   56,    2, 0x06,    1 /* Public */,

 // slots: name, argc, parameters, tag, flags, initial metatype offsets
       4,    0,   59,    2, 0x0a,    3 /* Public */,
       5,    1,   60,    2, 0x0a,    4 /* Public */,
       7,    1,   63,    2, 0x0a,    6 /* Public */,
       9,    1,   66,    2, 0x0a,    8 /* Public */,
      11,    1,   69,    2, 0x0a,   10 /* Public */,
      13,    2,   72,    2, 0x0a,   12 /* Public */,

 // signals: parameters
    QMetaType::Void, QMetaType::QString,    3,

 // slots: parameters
    QMetaType::Void,
    QMetaType::Void, QMetaType::QString,    6,
    QMetaType::Void, QMetaType::QString,    8,
    QMetaType::Void, QMetaType::QString,   10,
    QMetaType::Void, QMetaType::QString,   12,
    QMetaType::Void, QMetaType::QString, QMetaType::QString,   14,   15,

       0        // eod
};

Q_CONSTINIT const QMetaObject SendEmailThread::staticMetaObject = { {
    QMetaObject::SuperData::link<QThread::staticMetaObject>(),
    qt_meta_stringdata_CLASSSendEmailThreadENDCLASS.offsetsAndSizes,
    qt_meta_data_CLASSSendEmailThreadENDCLASS,
    qt_static_metacall,
    nullptr,
    qt_incomplete_metaTypeArray<qt_meta_stringdata_CLASSSendEmailThreadENDCLASS_t,
        // Q_OBJECT / Q_GADGET
        QtPrivate::TypeAndForceComplete<SendEmailThread, std::true_type>,
        // method 'receiveEmailResult'
        QtPrivate::TypeAndForceComplete<void, std::false_type>,
        QtPrivate::TypeAndForceComplete<const QString &, std::false_type>,
        // method 'stop'
        QtPrivate::TypeAndForceComplete<void, std::false_type>,
        // method 'setEmailTitle'
        QtPrivate::TypeAndForceComplete<void, std::false_type>,
        QtPrivate::TypeAndForceComplete<const QString &, std::false_type>,
        // method 'setSendEmailAddr'
        QtPrivate::TypeAndForceComplete<void, std::false_type>,
        QtPrivate::TypeAndForceComplete<const QString &, std::false_type>,
        // method 'setSendEmailPwd'
        QtPrivate::TypeAndForceComplete<void, std::false_type>,
        QtPrivate::TypeAndForceComplete<const QString &, std::false_type>,
        // method 'setReceiveEmailAddr'
        QtPrivate::TypeAndForceComplete<void, std::false_type>,
        QtPrivate::TypeAndForceComplete<const QString &, std::false_type>,
        // method 'append'
        QtPrivate::TypeAndForceComplete<void, std::false_type>,
        QtPrivate::TypeAndForceComplete<const QString &, std::false_type>,
        QtPrivate::TypeAndForceComplete<const QString &, std::false_type>
    >,
    nullptr
} };

void SendEmailThread::qt_static_metacall(QObject *_o, QMetaObject::Call _c, int _id, void **_a)
{
    if (_c == QMetaObject::InvokeMetaMethod) {
        auto *_t = static_cast<SendEmailThread *>(_o);
        (void)_t;
        switch (_id) {
        case 0: _t->receiveEmailResult((*reinterpret_cast< std::add_pointer_t<QString>>(_a[1]))); break;
        case 1: _t->stop(); break;
        case 2: _t->setEmailTitle((*reinterpret_cast< std::add_pointer_t<QString>>(_a[1]))); break;
        case 3: _t->setSendEmailAddr((*reinterpret_cast< std::add_pointer_t<QString>>(_a[1]))); break;
        case 4: _t->setSendEmailPwd((*reinterpret_cast< std::add_pointer_t<QString>>(_a[1]))); break;
        case 5: _t->setReceiveEmailAddr((*reinterpret_cast< std::add_pointer_t<QString>>(_a[1]))); break;
        case 6: _t->append((*reinterpret_cast< std::add_pointer_t<QString>>(_a[1])),(*reinterpret_cast< std::add_pointer_t<QString>>(_a[2]))); break;
        default: ;
        }
    } else if (_c == QMetaObject::IndexOfMethod) {
        int *result = reinterpret_cast<int *>(_a[0]);
        {
            using _t = void (SendEmailThread::*)(const QString & );
            if (_t _q_method = &SendEmailThread::receiveEmailResult; *reinterpret_cast<_t *>(_a[1]) == _q_method) {
                *result = 0;
                return;
            }
        }
    }
}

const QMetaObject *SendEmailThread::metaObject() const
{
    return QObject::d_ptr->metaObject ? QObject::d_ptr->dynamicMetaObject() : &staticMetaObject;
}

void *SendEmailThread::qt_metacast(const char *_clname)
{
    if (!_clname) return nullptr;
    if (!strcmp(_clname, qt_meta_stringdata_CLASSSendEmailThreadENDCLASS.stringdata0))
        return static_cast<void*>(this);
    return QThread::qt_metacast(_clname);
}

int SendEmailThread::qt_metacall(QMetaObject::Call _c, int _id, void **_a)
{
    _id = QThread::qt_metacall(_c, _id, _a);
    if (_id < 0)
        return _id;
    if (_c == QMetaObject::InvokeMetaMethod) {
        if (_id < 7)
            qt_static_metacall(this, _c, _id, _a);
        _id -= 7;
    } else if (_c == QMetaObject::RegisterMethodArgumentMetaType) {
        if (_id < 7)
            *reinterpret_cast<QMetaType *>(_a[0]) = QMetaType();
        _id -= 7;
    }
    return _id;
}

// SIGNAL 0
void SendEmailThread::receiveEmailResult(const QString & _t1)
{
    void *_a[] = { nullptr, const_cast<void*>(reinterpret_cast<const void*>(std::addressof(_t1))) };
    QMetaObject::activate(this, &staticMetaObject, 0, _a);
}
QT_WARNING_POP
