/********************************************************************************
** Form generated from reading UI file 'admingui.ui'
**
** Created by: Qt User Interface Compiler version 5.6.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_ADMINGUI_H
#define UI_ADMINGUI_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QButtonGroup>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_AdminGUIClass
{
public:

    void setupUi(QWidget *AdminGUIClass)
    {
        if (AdminGUIClass->objectName().isEmpty())
            AdminGUIClass->setObjectName(QStringLiteral("AdminGUIClass"));
        AdminGUIClass->resize(600, 400);

        retranslateUi(AdminGUIClass);

        QMetaObject::connectSlotsByName(AdminGUIClass);
    } // setupUi

    void retranslateUi(QWidget *AdminGUIClass)
    {
        AdminGUIClass->setWindowTitle(QApplication::translate("AdminGUIClass", "AdminGUI", 0));
    } // retranslateUi

};

namespace Ui {
    class AdminGUIClass: public Ui_AdminGUIClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_ADMINGUI_H
