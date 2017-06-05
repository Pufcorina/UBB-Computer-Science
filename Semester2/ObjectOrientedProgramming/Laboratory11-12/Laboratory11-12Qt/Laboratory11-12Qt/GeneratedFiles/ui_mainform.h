/********************************************************************************
** Form generated from reading UI file 'mainform.ui'
**
** Created by: Qt User Interface Compiler version 5.6.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_MAINFORM_H
#define UI_MAINFORM_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QButtonGroup>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QLabel>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QRadioButton>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_MainForm
{
public:
    QVBoxLayout *verticalLayout_2;
    QVBoxLayout *verticalLayout;
    QLabel *label;
    QRadioButton *csvRadioButton;
    QRadioButton *htmlRadioButton;
    QPushButton *adminButton;
    QPushButton *userButton;
    QPushButton *exitButton;

    void setupUi(QWidget *MainForm)
    {
        if (MainForm->objectName().isEmpty())
            MainForm->setObjectName(QStringLiteral("MainForm"));
        MainForm->setWindowModality(Qt::ApplicationModal);
        MainForm->resize(204, 324);
        MainForm->setMinimumSize(QSize(204, 275));
        MainForm->setMaximumSize(QSize(204, 500));
        MainForm->setBaseSize(QSize(233, 206));
        MainForm->setAcceptDrops(false);
        QIcon icon;
        icon.addFile(QStringLiteral("Resources/music.ico"), QSize(), QIcon::Normal, QIcon::Off);
        MainForm->setWindowIcon(icon);
        MainForm->setAutoFillBackground(false);
        MainForm->setStyleSheet(QStringLiteral("background: rgb(255,228,225)"));
        verticalLayout_2 = new QVBoxLayout(MainForm);
        verticalLayout_2->setSpacing(6);
        verticalLayout_2->setContentsMargins(11, 11, 11, 11);
        verticalLayout_2->setObjectName(QStringLiteral("verticalLayout_2"));
        verticalLayout = new QVBoxLayout();
        verticalLayout->setSpacing(6);
        verticalLayout->setObjectName(QStringLiteral("verticalLayout"));
        label = new QLabel(MainForm);
        label->setObjectName(QStringLiteral("label"));
        label->setPixmap(QPixmap(QString::fromUtf8("Resources/ubb.ico")));
        label->setScaledContents(true);
        label->setAlignment(Qt::AlignCenter);

        verticalLayout->addWidget(label);

        csvRadioButton = new QRadioButton(MainForm);
        csvRadioButton->setObjectName(QStringLiteral("csvRadioButton"));
        csvRadioButton->setChecked(false);
        csvRadioButton->setAutoExclusive(false);

        verticalLayout->addWidget(csvRadioButton);

        htmlRadioButton = new QRadioButton(MainForm);
        htmlRadioButton->setObjectName(QStringLiteral("htmlRadioButton"));

        verticalLayout->addWidget(htmlRadioButton);

        adminButton = new QPushButton(MainForm);
        adminButton->setObjectName(QStringLiteral("adminButton"));
        adminButton->setEnabled(true);
        QSizePolicy sizePolicy(QSizePolicy::Minimum, QSizePolicy::Maximum);
        sizePolicy.setHorizontalStretch(0);
        sizePolicy.setVerticalStretch(0);
        sizePolicy.setHeightForWidth(adminButton->sizePolicy().hasHeightForWidth());
        adminButton->setSizePolicy(sizePolicy);
        adminButton->setAutoFillBackground(false);
        adminButton->setStyleSheet(QStringLiteral("background: rgb(248,248,255)"));

        verticalLayout->addWidget(adminButton);

        userButton = new QPushButton(MainForm);
        userButton->setObjectName(QStringLiteral("userButton"));
        sizePolicy.setHeightForWidth(userButton->sizePolicy().hasHeightForWidth());
        userButton->setSizePolicy(sizePolicy);
        userButton->setAutoFillBackground(false);
        userButton->setStyleSheet(QStringLiteral("background: rgb(248,248,255)"));

        verticalLayout->addWidget(userButton);

        exitButton = new QPushButton(MainForm);
        exitButton->setObjectName(QStringLiteral("exitButton"));
        sizePolicy.setHeightForWidth(exitButton->sizePolicy().hasHeightForWidth());
        exitButton->setSizePolicy(sizePolicy);
        exitButton->setAutoFillBackground(false);
        exitButton->setStyleSheet(QStringLiteral("background: rgb(248,248,255)"));
        exitButton->setAutoDefault(false);
        exitButton->setFlat(false);

        verticalLayout->addWidget(exitButton);


        verticalLayout_2->addLayout(verticalLayout);


        retranslateUi(MainForm);

        exitButton->setDefault(false);


        QMetaObject::connectSlotsByName(MainForm);
    } // setupUi

    void retranslateUi(QWidget *MainForm)
    {
        MainForm->setWindowTitle(QApplication::translate("MainForm", "LMDB", 0));
        label->setText(QString());
        csvRadioButton->setText(QApplication::translate("MainForm", "CSV", 0));
        htmlRadioButton->setText(QApplication::translate("MainForm", "HTML", 0));
        adminButton->setText(QApplication::translate("MainForm", "Admin mode", 0));
        userButton->setText(QApplication::translate("MainForm", "User mode", 0));
        exitButton->setText(QApplication::translate("MainForm", "EXIT", 0));
    } // retranslateUi

};

namespace Ui {
    class MainForm: public Ui_MainForm {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_MAINFORM_H
