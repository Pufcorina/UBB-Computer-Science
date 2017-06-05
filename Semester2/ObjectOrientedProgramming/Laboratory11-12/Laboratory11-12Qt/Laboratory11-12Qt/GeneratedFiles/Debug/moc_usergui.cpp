/****************************************************************************
** Meta object code from reading C++ file 'usergui.h'
**
** Created by: The Qt Meta Object Compiler version 67 (Qt 5.6.0)
**
** WARNING! All changes made in this file will be lost!
*****************************************************************************/

#include "../../usergui.h"
#include <QtCore/qbytearray.h>
#include <QtCore/qmetatype.h>
#if !defined(Q_MOC_OUTPUT_REVISION)
#error "The header file 'usergui.h' doesn't include <QObject>."
#elif Q_MOC_OUTPUT_REVISION != 67
#error "This file was generated using the moc from 5.6.0. It"
#error "cannot be used with the include files from this version of Qt."
#error "(The moc has changed too much.)"
#endif

QT_BEGIN_MOC_NAMESPACE
struct qt_meta_stringdata_UserGUI_t {
    QByteArrayData data[9];
    char stringdata0[75];
};
#define QT_MOC_LITERAL(idx, ofs, len) \
    Q_STATIC_BYTE_ARRAY_DATA_HEADER_INITIALIZER_WITH_OFFSET(len, \
    qptrdiff(offsetof(qt_meta_stringdata_UserGUI_t, stringdata0) + ofs \
        - idx * sizeof(QByteArrayData)) \
    )
static const qt_meta_stringdata_UserGUI_t qt_meta_stringdata_UserGUI = {
    {
QT_MOC_LITERAL(0, 0, 7), // "UserGUI"
QT_MOC_LITERAL(1, 8, 10), // "userSearch"
QT_MOC_LITERAL(2, 19, 0), // ""
QT_MOC_LITERAL(3, 20, 7), // "userAdd"
QT_MOC_LITERAL(4, 28, 8), // "userNext"
QT_MOC_LITERAL(5, 37, 8), // "userSave"
QT_MOC_LITERAL(6, 46, 8), // "userOpen"
QT_MOC_LITERAL(7, 55, 10), // "userDelete"
QT_MOC_LITERAL(8, 66, 8) // "userShow"

    },
    "UserGUI\0userSearch\0\0userAdd\0userNext\0"
    "userSave\0userOpen\0userDelete\0userShow"
};
#undef QT_MOC_LITERAL

static const uint qt_meta_data_UserGUI[] = {

 // content:
       7,       // revision
       0,       // classname
       0,    0, // classinfo
       7,   14, // methods
       0,    0, // properties
       0,    0, // enums/sets
       0,    0, // constructors
       0,       // flags
       0,       // signalCount

 // slots: name, argc, parameters, tag, flags
       1,    0,   49,    2, 0x08 /* Private */,
       3,    0,   50,    2, 0x08 /* Private */,
       4,    0,   51,    2, 0x08 /* Private */,
       5,    0,   52,    2, 0x08 /* Private */,
       6,    0,   53,    2, 0x08 /* Private */,
       7,    0,   54,    2, 0x08 /* Private */,
       8,    0,   55,    2, 0x08 /* Private */,

 // slots: parameters
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,

       0        // eod
};

void UserGUI::qt_static_metacall(QObject *_o, QMetaObject::Call _c, int _id, void **_a)
{
    if (_c == QMetaObject::InvokeMetaMethod) {
        UserGUI *_t = static_cast<UserGUI *>(_o);
        Q_UNUSED(_t)
        switch (_id) {
        case 0: _t->userSearch(); break;
        case 1: _t->userAdd(); break;
        case 2: _t->userNext(); break;
        case 3: _t->userSave(); break;
        case 4: _t->userOpen(); break;
        case 5: _t->userDelete(); break;
        case 6: _t->userShow(); break;
        default: ;
        }
    }
    Q_UNUSED(_a);
}

const QMetaObject UserGUI::staticMetaObject = {
    { &QWidget::staticMetaObject, qt_meta_stringdata_UserGUI.data,
      qt_meta_data_UserGUI,  qt_static_metacall, Q_NULLPTR, Q_NULLPTR}
};


const QMetaObject *UserGUI::metaObject() const
{
    return QObject::d_ptr->metaObject ? QObject::d_ptr->dynamicMetaObject() : &staticMetaObject;
}

void *UserGUI::qt_metacast(const char *_clname)
{
    if (!_clname) return Q_NULLPTR;
    if (!strcmp(_clname, qt_meta_stringdata_UserGUI.stringdata0))
        return static_cast<void*>(const_cast< UserGUI*>(this));
    return QWidget::qt_metacast(_clname);
}

int UserGUI::qt_metacall(QMetaObject::Call _c, int _id, void **_a)
{
    _id = QWidget::qt_metacall(_c, _id, _a);
    if (_id < 0)
        return _id;
    if (_c == QMetaObject::InvokeMetaMethod) {
        if (_id < 7)
            qt_static_metacall(this, _c, _id, _a);
        _id -= 7;
    } else if (_c == QMetaObject::RegisterMethodArgumentMetaType) {
        if (_id < 7)
            *reinterpret_cast<int*>(_a[0]) = -1;
        _id -= 7;
    }
    return _id;
}
QT_END_MOC_NAMESPACE
