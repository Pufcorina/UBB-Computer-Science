#pragma once
#include "controller.h"


class UI
{
private:
	Controller ctrl;

public:
	UI(const Controller c) : ctrl(c) {}

	void runApplication();
	void runAdminMode();
	void runUserMode();

private:
	static void printMenuApplication();
	static void printMenuAdmin();
	static void printMenuUser();
	static void printWatchMenu();

	void userDelete();

	void printWatchList(WatchList ls);

	void watchStart();

	void addMovieToController();
	void deleteMovieToController();
	void updateMovieToController();
	void listAllMovies();
};