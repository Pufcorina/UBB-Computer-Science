#pragma once
#include "Controller.h"

class Console
{
private:
	Controller ctrl;
public:
	//Constructor
	Console();

	//Copy constructor
	Console(const Controller& c);

	//Destructor
	~Console();

	//Assignment overload operator
	Console& operator=(const Console& other);

	//Aplication control function
	void runApplication();

private:
	static void printProblemStatement();
	static void printMenuApplication();
	void consoleSearch(std::string number);
	void consoleFindFequency(std::string number);
};

