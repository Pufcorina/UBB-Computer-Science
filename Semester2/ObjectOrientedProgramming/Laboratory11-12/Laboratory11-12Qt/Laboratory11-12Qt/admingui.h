#ifndef ADMINGUI_H
#define ADMINGUI_H

#include <QtWidgets/QWidget>
#include "ui_admingui.h"
#include "Controller.h"

#include <qgridlayout.h>
#include <qlayout.h>
#include <qpushbutton.h>
#include <qlistwidget.h>
#include <qlineedit.h>

class AdminGUI : public QWidget
{
	Q_OBJECT

public:
	AdminGUI(QWidget *parent = 0);
	AdminGUI(Controller* ctrl, WatchList* watchlist, QWidget *parent = 0);
	void AdminInterface();
	~AdminGUI();

private:
	Controller* ctrl;
	WatchList* watchlist;

	QGridLayout* mainLayout;
	QVBoxLayout* linesEdit;
	QVBoxLayout* listWidgetAdmin;
	QHBoxLayout* buttonsAdmin;
	QPushButton* addButton;
	QPushButton* deleteButton;
	QPushButton* updateButton;
	QPushButton* listButton;
	QPushButton* exitButton;
	QListWidget* listWidget;
	QLineEdit* titleLineEdit;
	QLineEdit* genreLineEdit;
	QLineEdit* yearLineEdit;
	QLineEdit* nbLikesLineEdit;
	QLineEdit* linkLineEdit;

private:
	Ui::AdminGUIClass ui;

private slots:
	void consoleAdd();
	void consoleDelete();
	void consoleUpdate();
	void closeForm();
	void consoleList();
	void clearListWidget();
	void clearLinesEdit();
};

#endif // ADMINGUI_H
