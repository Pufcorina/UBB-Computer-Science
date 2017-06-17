/****************************************************************************
** Meta object code from reading C++ file 'PracticOOP.h'
**
** Created by: The Qt Meta Object Compiler version 67 (Qt 5.6.0)
**
** WARNING! All changes made in this file will be lost!
*****************************************************************************/

#include "../../PracticOOP.h"
#include <QtCore/qbytearray.h>
#include <QtCore/qmetatype.h>
#if !defined(Q_MOC_OUTPUT_REVISION)
#error "The header file 'PracticOOP.h' doesn't include <QObject>."
#elif Q_MOC_OUTPUT_REVISION != 67
#error "This file was generated using the moc from 5.6.0. It"
#error "cannot be used with the include files from this version of Qt."
#error "(The moc has changed too much.)"
#endif

QT_BEGIN_MOC_NAMESPACE
struct qt_meta_stringdata_PracticOOP_t {
    QByteArrayData data[8];
    char stringdata0[95];
};
#define QT_MOC_LITERAL(idx, ofs, len) \
    Q_STATIC_BYTE_ARRAY_DATA_HEADER_INITIALIZER_WITH_OFFSET(len, \
    qptrdiff(offsetof(qt_meta_stringdata_PracticOOP_t, stringdata0) + ofs \
        - idx * sizeof(QByteArrayData)) \
    )
static const qt_meta_stringdata_PracticOOP_t qt_meta_stringdata_PracticOOP = {
    {
QT_MOC_LITERAL(0, 0, 10), // "PracticOOP"
QT_MOC_LITERAL(1, 11, 11), // "addFunction"
QT_MOC_LITERAL(2, 23, 0), // ""
QT_MOC_LITERAL(3, 24, 14), // "enableFunction"
QT_MOC_LITERAL(4, 39, 12), // "doneFunction"
QT_MOC_LITERAL(5, 52, 13), // "startFunction"
QT_MOC_LITERAL(6, 66, 14), // "removeFunction"
QT_MOC_LITERAL(7, 81, 13) // "closeFunction"

    },
    "PracticOOP\0addFunction\0\0enableFunction\0"
    "doneFunction\0startFunction\0removeFunction\0"
    "closeFunction"
};
#undef QT_MOC_LITERAL

static const uint qt_meta_data_PracticOOP[] = {

 // content:
       7,       // revision
       0,       // classname
       0,    0, // classinfo
       6,   14, // methods
       0,    0, // properties
       0,    0, // enums/sets
       0,    0, // constructors
       0,       // flags
       0,       // signalCount

 // slots: name, argc, parameters, tag, flags
       1,    0,   44,    2, 0x08 /* Private */,
       3,    0,   45,    2, 0x08 /* Private */,
       4,    0,   46,    2, 0x08 /* Private */,
       5,    0,   47,    2, 0x08 /* Private */,
       6,    0,   48,    2, 0x08 /* Private */,
       7,    0,   49,    2, 0x08 /* Private */,

 // slots: parameters
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,

       0        // eod
};

void PracticOOP::qt_static_metacall(QObject *_o, QMetaObject::Call _c, int _id, void **_a)
{
    if (_c == QMetaObject::InvokeMetaMethod) {
        PracticOOP *_t = static_cast<PracticOOP *>(_o);
        Q_UNUSED(_t)
        switch (_id) {
        case 0: _t->addFunction(); break;
        case 1: _t->enableFunction(); break;
        case 2: _t->doneFunction(); break;
        case 3: _t->startFunction(); break;
        case 4: _t->removeFunction(); break;
        case 5: _t->closeFunction(); break;
        default: ;
        }
    }
    Q_UNUSED(_a);
}

const QMetaObject PracticOOP::staticMetaObject = {
    { &QWidget::staticMetaObject, qt_meta_stringdata_PracticOOP.data,
      qt_meta_data_PracticOOP,  qt_static_metacall, Q_NULLPTR, Q_NULLPTR}
};


const QMetaObject *PracticOOP::metaObject() const
{
    return QObject::d_ptr->metaObject ? QObject::d_ptr->dynamicMetaObject() : &staticMetaObject;
}

void *PracticOOP::qt_metacast(const char *_clname)
{
    if (!_clname) return Q_NULLPTR;
    if (!strcmp(_clname, qt_meta_stringdata_PracticOOP.stringdata0))
        return static_cast<void*>(const_cast< PracticOOP*>(this));
    if (!strcmp(_clname, "Observer"))
        return static_cast< Observer*>(const_cast< PracticOOP*>(this));
    return QWidget::qt_metacast(_clname);
}

int PracticOOP::qt_metacall(QMetaObject::Call _c, int _id, void **_a)
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
            *reinterpret_cast<int*>(_a[0]) = -1;
        _id -= 6;
    }
    return _id;
}
QT_END_MOC_NAMESPACE
