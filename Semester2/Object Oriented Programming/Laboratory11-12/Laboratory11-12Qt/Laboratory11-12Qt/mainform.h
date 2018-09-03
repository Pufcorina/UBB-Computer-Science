#pragma once
#include <QWidget>
#include "ui_mainform.h"
#include "admingui.h"
#include "usergui.h"

class MainForm : public QWidget {
	Q_OBJECT
private:
	Controller* ctrl;
	WatchList* watchlist;

public:
	MainForm(QWidget * parent = Q_NULLPTR);
	~MainForm();

private:
	Ui::MainForm ui;
	void formInterface();

private slots:
	void adminForm();
	void userForm();
};
