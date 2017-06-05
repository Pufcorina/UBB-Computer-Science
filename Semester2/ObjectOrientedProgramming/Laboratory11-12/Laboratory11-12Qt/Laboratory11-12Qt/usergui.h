#pragma once
#include <QtWidgets/QWidget>
#include "ui_usergui.h"
#include "Controller.h"

class UserGUI : public QWidget {
	Q_OBJECT

private:
	Controller* ctrl;
	WatchList* watchlist;

public:
	UserGUI(Controller* ctrl, WatchList* watchlist, QWidget * parent = Q_NULLPTR);
	~UserGUI();

	void userInterface();

	void printMovie(Movie* m);

private slots:
	void userSearch();
	void userAdd();
	void userNext();
	void userSave();
	void userOpen();
	void userDelete();
	void userShow();

private:
	Ui::UserGUI ui;
};
