#pragma once
#include "Service.h"
using namespace std;

class UI
{
private:
	Service ctrl;
public:
	UI();
	~UI();
	UI(const Service& ctrl) :ctrl{ ctrl } {}
	
	void runApplication();
	void runAdminMode();
	void runUserMode();

private:
	static void printMenuApplication();
	static void printMenuAdmin();

	static void printMenuUser();
	static void printWatchMenu();

	void userFilter();
	void printWatchList();
	void watchStart();

	void addPetToController();
	void deletePetToController();
	void updatePetToController();
	void listAllPets();
};

