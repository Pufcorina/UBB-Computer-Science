#pragma once
#include "Controller.h"

class Console
{
private:
	Controller ctrl;
public:
	Console();
	Console(Controller c);
	~Console();

	void runApplication();
private:
	void uiAdd();
	void uiShow();
	void uiSave();
};

