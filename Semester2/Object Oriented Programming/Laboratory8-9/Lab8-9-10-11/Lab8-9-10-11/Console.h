#pragma once
#include "Controller.h"

class Console
{
private:
	Controller ctrl;
	WatchList* watchlist;
public:
	Console();
	Console(Controller ctrl, WatchList* watchlist);
	~Console();

	void runApplication();

private:
	void runAdminMode();
	void runUserMode();

	static void printMenuApplication();
	static void printMenuAdmin();
	static void printMenuUser();
	static void printWatchMenu(const Movie& m);
	
	void consoleAdd();
	void consoleDelete();
	void consoleUpdate();
	void consoleList();

	void consoleUserWatch();
	void consoleUserShow();
	void consoleUserDelete();
	void consoleUserSave();
	void consoleUserOpen();
};

