/********************************************************************************
** Form generated from reading UI file 'PracticOOP.ui'
**
** Created by: Qt User Interface Compiler version 5.6.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_PRACTICOOP_H
#define UI_PRACTICOOP_H

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

class Ui_PracticOOPClass
{
public:
    QListWidget *listWidget;
    QSplitter *splitter;
    QSplitter *splitter_2;
    QLineEdit *tasklineEdit;
    QPushButton *addpushButton;
    QPushButton *removepushButton;
    QPushButton *startpushButton;
    QPushButton *donepushButton;
    QPushButton *exitpushButton;

    void setupUi(QWidget *PracticOOPClass)
    {
        if (PracticOOPClass->objectName().isEmpty())
            PracticOOPClass->setObjectName(QStringLiteral("PracticOOPClass"));
        PracticOOPClass->resize(601, 481);
        listWidget = new QListWidget(PracticOOPClass);
        listWidget->setObjectName(QStringLiteral("listWidget"));
        listWidget->setGeometry(QRect(20, 30, 341, 431));
        splitter = new QSplitter(PracticOOPClass);
        splitter->setObjectName(QStringLiteral("splitter"));
        splitter->setGeometry(QRect(370, 120, 211, 251));
        splitter->setOrientation(Qt::Vertical);
        splitter_2 = new QSplitter(splitter);
        splitter_2->setObjectName(QStringLiteral("splitter_2"));
        splitter_2->setOrientation(Qt::Vertical);
        tasklineEdit = new QLineEdit(splitter_2);
        tasklineEdit->setObjectName(QStringLiteral("tasklineEdit"));
        splitter_2->addWidget(tasklineEdit);
        addpushButton = new QPushButton(splitter_2);
        addpushButton->setObjectName(QStringLiteral("addpushButton"));
        splitter_2->addWidget(addpushButton);
        removepushButton = new QPushButton(splitter_2);
        removepushButton->setObjectName(QStringLiteral("removepushButton"));
        splitter_2->addWidget(removepushButton);
        startpushButton = new QPushButton(splitter_2);
        startpushButton->setObjectName(QStringLiteral("startpushButton"));
        splitter_2->addWidget(startpushButton);
        donepushButton = new QPushButton(splitter_2);
        donepushButton->setObjectName(QStringLiteral("donepushButton"));
        splitter_2->addWidget(donepushButton);
        splitter->addWidget(splitter_2);
        exitpushButton = new QPushButton(PracticOOPClass);
        exitpushButton->setObjectName(QStringLiteral("exitpushButton"));
        exitpushButton->setGeometry(QRect(430, 410, 91, 31));

        retranslateUi(PracticOOPClass);

        QMetaObject::connectSlotsByName(PracticOOPClass);
    } // setupUi

    void retranslateUi(QWidget *PracticOOPClass)
    {
        PracticOOPClass->setWindowTitle(QApplication::translate("PracticOOPClass", "PracticOOP", 0));
        addpushButton->setText(QApplication::translate("PracticOOPClass", "Add", 0));
        removepushButton->setText(QApplication::translate("PracticOOPClass", "Remove", 0));
        startpushButton->setText(QApplication::translate("PracticOOPClass", "Start", 0));
        donepushButton->setText(QApplication::translate("PracticOOPClass", "Done", 0));
        exitpushButton->setText(QApplication::translate("PracticOOPClass", "EXIT", 0));
    } // retranslateUi

};

namespace Ui {
    class PracticOOPClass: public Ui_PracticOOPClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_PRACTICOOP_H
