/********************************************************************************
** Form generated from reading UI file 'QTExample.ui'
**
** Created by: Qt User Interface Compiler version 5.6.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_QTEXAMPLE_H
#define UI_QTEXAMPLE_H

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

class Ui_QTExampleClass
{
public:
    QSplitter *splitter;
    QLineEdit *idlineEdit;
    QLineEdit *namelineEdit;
    QLineEdit *grouplineEdit;
    QLineEdit *gradelineEdit;
    QLineEdit *teacherlineEdit;
    QPushButton *addPushButton;
    QPushButton *removepushButton;
    QPushButton *exitPushButton;
    QListWidget *listWidget;

    void setupUi(QWidget *QTExampleClass)
    {
        if (QTExampleClass->objectName().isEmpty())
            QTExampleClass->setObjectName(QStringLiteral("QTExampleClass"));
        QTExampleClass->resize(721, 561);
        splitter = new QSplitter(QTExampleClass);
        splitter->setObjectName(QStringLiteral("splitter"));
        splitter->setGeometry(QRect(410, 30, 281, 391));
        splitter->setOrientation(Qt::Vertical);
        idlineEdit = new QLineEdit(splitter);
        idlineEdit->setObjectName(QStringLiteral("idlineEdit"));
        splitter->addWidget(idlineEdit);
        namelineEdit = new QLineEdit(splitter);
        namelineEdit->setObjectName(QStringLiteral("namelineEdit"));
        splitter->addWidget(namelineEdit);
        grouplineEdit = new QLineEdit(splitter);
        grouplineEdit->setObjectName(QStringLiteral("grouplineEdit"));
        splitter->addWidget(grouplineEdit);
        gradelineEdit = new QLineEdit(splitter);
        gradelineEdit->setObjectName(QStringLiteral("gradelineEdit"));
        splitter->addWidget(gradelineEdit);
        teacherlineEdit = new QLineEdit(splitter);
        teacherlineEdit->setObjectName(QStringLiteral("teacherlineEdit"));
        splitter->addWidget(teacherlineEdit);
        addPushButton = new QPushButton(splitter);
        addPushButton->setObjectName(QStringLiteral("addPushButton"));
        splitter->addWidget(addPushButton);
        removepushButton = new QPushButton(splitter);
        removepushButton->setObjectName(QStringLiteral("removepushButton"));
        splitter->addWidget(removepushButton);
        exitPushButton = new QPushButton(splitter);
        exitPushButton->setObjectName(QStringLiteral("exitPushButton"));
        splitter->addWidget(exitPushButton);
        listWidget = new QListWidget(QTExampleClass);
        listWidget->setObjectName(QStringLiteral("listWidget"));
        listWidget->setGeometry(QRect(10, 10, 391, 531));

        retranslateUi(QTExampleClass);

        QMetaObject::connectSlotsByName(QTExampleClass);
    } // setupUi

    void retranslateUi(QWidget *QTExampleClass)
    {
        QTExampleClass->setWindowTitle(QApplication::translate("QTExampleClass", "QTExample", 0));
        addPushButton->setText(QApplication::translate("QTExampleClass", "Add", 0));
        removepushButton->setText(QApplication::translate("QTExampleClass", "Remove", 0));
        exitPushButton->setText(QApplication::translate("QTExampleClass", "EXIT", 0));
    } // retranslateUi

};

namespace Ui {
    class QTExampleClass: public Ui_QTExampleClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_QTEXAMPLE_H
