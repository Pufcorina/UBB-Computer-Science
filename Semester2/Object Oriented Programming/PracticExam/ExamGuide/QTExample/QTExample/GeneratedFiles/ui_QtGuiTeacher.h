/********************************************************************************
** Form generated from reading UI file 'QtGuiTeacher.ui'
**
** Created by: Qt User Interface Compiler version 5.6.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_QTGUITEACHER_H
#define UI_QTGUITEACHER_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QButtonGroup>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QListWidget>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QSplitter>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_QtGuiTeacher
{
public:
    QListWidget *listWidget;
    QSplitter *splitter;
    QLineEdit *lineEdit;
    QPushButton *pushButton;

    void setupUi(QWidget *QtGuiTeacher)
    {
        if (QtGuiTeacher->objectName().isEmpty())
            QtGuiTeacher->setObjectName(QStringLiteral("QtGuiTeacher"));
        QtGuiTeacher->resize(607, 434);
        listWidget = new QListWidget(QtGuiTeacher);
        listWidget->setObjectName(QStringLiteral("listWidget"));
        listWidget->setGeometry(QRect(20, 20, 421, 401));
        splitter = new QSplitter(QtGuiTeacher);
        splitter->setObjectName(QStringLiteral("splitter"));
        splitter->setGeometry(QRect(460, 150, 133, 111));
        splitter->setOrientation(Qt::Vertical);
        lineEdit = new QLineEdit(splitter);
        lineEdit->setObjectName(QStringLiteral("lineEdit"));
        splitter->addWidget(lineEdit);
        pushButton = new QPushButton(splitter);
        pushButton->setObjectName(QStringLiteral("pushButton"));
        splitter->addWidget(pushButton);

        retranslateUi(QtGuiTeacher);

        QMetaObject::connectSlotsByName(QtGuiTeacher);
    } // setupUi

    void retranslateUi(QWidget *QtGuiTeacher)
    {
        QtGuiTeacher->setWindowTitle(QApplication::translate("QtGuiTeacher", "QtGuiTeacher", 0));
        pushButton->setText(QApplication::translate("QtGuiTeacher", "Grade", 0));
    } // retranslateUi

};

namespace Ui {
    class QtGuiTeacher: public Ui_QtGuiTeacher {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_QTGUITEACHER_H
