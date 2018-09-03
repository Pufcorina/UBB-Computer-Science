/********************************************************************************
** Form generated from reading UI file 'usergui.ui'
**
** Created by: Qt User Interface Compiler version 5.6.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_USERGUI_H
#define UI_USERGUI_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QButtonGroup>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QListWidget>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QSplitter>
#include <QtWidgets/QTextBrowser>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_UserGUI
{
public:
    QSplitter *splitter;
    QWidget *layoutWidget;
    QHBoxLayout *horizontalLayout;
    QLineEdit *searchLineEdit;
    QPushButton *searchButton;
    QLabel *movieLabel;
    QWidget *layoutWidget1;
    QHBoxLayout *horizontalLayout_4;
    QPushButton *addButton;
    QPushButton *nextButton;
    QWidget *layoutWidget2;
    QHBoxLayout *horizontalLayout_3;
    QListWidget *showListWidget;
    QTextBrowser *textBrowser;
    QWidget *layoutWidget3;
    QHBoxLayout *horizontalLayout_2;
    QPushButton *showButton;
    QPushButton *saveButton;
    QPushButton *openButton;
    QPushButton *deleteButton;
    QPushButton *exitButton;

    void setupUi(QWidget *UserGUI)
    {
        if (UserGUI->objectName().isEmpty())
            UserGUI->setObjectName(QStringLiteral("UserGUI"));
        UserGUI->resize(544, 433);
        UserGUI->setMinimumSize(QSize(544, 433));
        UserGUI->setMaximumSize(QSize(544, 433));
        QIcon icon;
        icon.addFile(QStringLiteral("Resources/user.ico"), QSize(), QIcon::Normal, QIcon::Off);
        UserGUI->setWindowIcon(icon);
        UserGUI->setStyleSheet(QStringLiteral("background: rgb(255,228,225)"));
        splitter = new QSplitter(UserGUI);
        splitter->setObjectName(QStringLiteral("splitter"));
        splitter->setGeometry(QRect(10, 20, 520, 401));
        splitter->setOrientation(Qt::Vertical);
        layoutWidget = new QWidget(splitter);
        layoutWidget->setObjectName(QStringLiteral("layoutWidget"));
        horizontalLayout = new QHBoxLayout(layoutWidget);
        horizontalLayout->setSpacing(6);
        horizontalLayout->setContentsMargins(11, 11, 11, 11);
        horizontalLayout->setObjectName(QStringLiteral("horizontalLayout"));
        horizontalLayout->setContentsMargins(0, 0, 0, 0);
        searchLineEdit = new QLineEdit(layoutWidget);
        searchLineEdit->setObjectName(QStringLiteral("searchLineEdit"));
        searchLineEdit->setStyleSheet(QStringLiteral("background: rgb(255,255,255)"));

        horizontalLayout->addWidget(searchLineEdit);

        searchButton = new QPushButton(layoutWidget);
        searchButton->setObjectName(QStringLiteral("searchButton"));
        searchButton->setStyleSheet(QStringLiteral("background: rgb(248,248,255)"));

        horizontalLayout->addWidget(searchButton);

        splitter->addWidget(layoutWidget);
        movieLabel = new QLabel(splitter);
        movieLabel->setObjectName(QStringLiteral("movieLabel"));
        movieLabel->setLineWidth(5);
        splitter->addWidget(movieLabel);
        layoutWidget1 = new QWidget(splitter);
        layoutWidget1->setObjectName(QStringLiteral("layoutWidget1"));
        horizontalLayout_4 = new QHBoxLayout(layoutWidget1);
        horizontalLayout_4->setSpacing(6);
        horizontalLayout_4->setContentsMargins(11, 11, 11, 11);
        horizontalLayout_4->setObjectName(QStringLiteral("horizontalLayout_4"));
        horizontalLayout_4->setContentsMargins(0, 0, 0, 0);
        addButton = new QPushButton(layoutWidget1);
        addButton->setObjectName(QStringLiteral("addButton"));
        addButton->setStyleSheet(QStringLiteral("background: rgb(248,248,255)"));

        horizontalLayout_4->addWidget(addButton);

        nextButton = new QPushButton(layoutWidget1);
        nextButton->setObjectName(QStringLiteral("nextButton"));
        nextButton->setStyleSheet(QStringLiteral("background: rgb(248,248,255)"));

        horizontalLayout_4->addWidget(nextButton);

        splitter->addWidget(layoutWidget1);
        layoutWidget2 = new QWidget(splitter);
        layoutWidget2->setObjectName(QStringLiteral("layoutWidget2"));
        horizontalLayout_3 = new QHBoxLayout(layoutWidget2);
        horizontalLayout_3->setSpacing(6);
        horizontalLayout_3->setContentsMargins(11, 11, 11, 11);
        horizontalLayout_3->setObjectName(QStringLiteral("horizontalLayout_3"));
        horizontalLayout_3->setContentsMargins(0, 0, 0, 0);
        showListWidget = new QListWidget(layoutWidget2);
        showListWidget->setObjectName(QStringLiteral("showListWidget"));
        showListWidget->setStyleSheet(QStringLiteral("background: rgb(255,255,255)"));

        horizontalLayout_3->addWidget(showListWidget);

        textBrowser = new QTextBrowser(layoutWidget2);
        textBrowser->setObjectName(QStringLiteral("textBrowser"));
        textBrowser->setStyleSheet(QStringLiteral("background: rgb(255,255,255)"));

        horizontalLayout_3->addWidget(textBrowser);

        splitter->addWidget(layoutWidget2);
        layoutWidget3 = new QWidget(splitter);
        layoutWidget3->setObjectName(QStringLiteral("layoutWidget3"));
        horizontalLayout_2 = new QHBoxLayout(layoutWidget3);
        horizontalLayout_2->setSpacing(6);
        horizontalLayout_2->setContentsMargins(11, 11, 11, 11);
        horizontalLayout_2->setObjectName(QStringLiteral("horizontalLayout_2"));
        horizontalLayout_2->setContentsMargins(0, 0, 0, 0);
        showButton = new QPushButton(layoutWidget3);
        showButton->setObjectName(QStringLiteral("showButton"));
        showButton->setStyleSheet(QStringLiteral("background: rgb(248,248,255)"));

        horizontalLayout_2->addWidget(showButton);

        saveButton = new QPushButton(layoutWidget3);
        saveButton->setObjectName(QStringLiteral("saveButton"));
        saveButton->setStyleSheet(QStringLiteral("background: rgb(248,248,255)"));

        horizontalLayout_2->addWidget(saveButton);

        openButton = new QPushButton(layoutWidget3);
        openButton->setObjectName(QStringLiteral("openButton"));
        openButton->setStyleSheet(QStringLiteral("background: rgb(248,248,255)"));

        horizontalLayout_2->addWidget(openButton);

        deleteButton = new QPushButton(layoutWidget3);
        deleteButton->setObjectName(QStringLiteral("deleteButton"));
        deleteButton->setStyleSheet(QStringLiteral("background: rgb(248,248,255)"));

        horizontalLayout_2->addWidget(deleteButton);

        exitButton = new QPushButton(layoutWidget3);
        exitButton->setObjectName(QStringLiteral("exitButton"));
        exitButton->setStyleSheet(QStringLiteral("background: rgb(248,248,255)"));

        horizontalLayout_2->addWidget(exitButton);

        splitter->addWidget(layoutWidget3);

        retranslateUi(UserGUI);

        QMetaObject::connectSlotsByName(UserGUI);
    } // setupUi

    void retranslateUi(QWidget *UserGUI)
    {
        UserGUI->setWindowTitle(QApplication::translate("UserGUI", "User Mode", 0));
        searchButton->setText(QApplication::translate("UserGUI", "Search", 0));
        movieLabel->setText(QString());
        addButton->setText(QApplication::translate("UserGUI", "Add movie", 0));
        nextButton->setText(QApplication::translate("UserGUI", "Next movie", 0));
        showButton->setText(QApplication::translate("UserGUI", "Show", 0));
        saveButton->setText(QApplication::translate("UserGUI", "Save", 0));
        openButton->setText(QApplication::translate("UserGUI", "Open", 0));
        deleteButton->setText(QApplication::translate("UserGUI", "Delete", 0));
        exitButton->setText(QApplication::translate("UserGUI", "EXIT", 0));
    } // retranslateUi

};

namespace Ui {
    class UserGUI: public Ui_UserGUI {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_USERGUI_H
