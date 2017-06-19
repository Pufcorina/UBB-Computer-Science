#include "Console.h"
#include <Windows.h>
#include <conio.h>


Console::Console()
{
}


Console::Console(const Controller& c)
{
	this->ctrl = c;
}


Console::~Console()
{
}


Console& Console::operator=(const Console& other)
{
	if (this == &other)
		return *this;
	this->ctrl = other.ctrl;
	return *this;
}


void Console::runApplication()
{
	while (true)
	{
		printMenuApplication();
		std::string number;
		std::getline(std::cin, number);
		if (number == "exit")
		{
			std::cout << "\nByeee !!! :)\n";
			break;
		}
		try
		{
			consoleSearch(number);
		}
		catch (std::exception e)
		{
			std::cout << "	" << e.what() << "\n";
		}

		Sleep(2000);
	}
}


void Console::printProblemStatement()
{
	std::cout << "Problem statement: \n";
	std::cout << "	Having an initial password database, memorised by it`s password code, check if a given password exists.\n";
	std::cout << "	Password format consisting in numbers ( each number has to have at least 4 digits and maximum 10 ).\n";
	std::cout << "	A password is not necessarly unique, so for the given password if it exists show also the number of apparitions.\n\n";
}


void Console::printMenuApplication()
{
	system("cls");
	printProblemStatement();

	std::cout << "For exit type: exit\n";
	std::cout << "Please insert the password to be checked: ";
}


void Console::consoleSearch(std::string number)
{
	if (stoi(number) < 1000 || stoi(number) >= 100000000)
		throw std::exception("Invalid password, at least 4 digits and maximum 8. Try again in 5 seconds!\n");
	else
	{
		bool find = this->ctrl.searchPassword(stoi(number));
		if (find == true)
		{
			std::cout << "Password was found in database!\n";
			consoleFindFequency(number);
		}
		else
			std::cout << "Inexistent password!\n";
	}
}


void Console::consoleFindFequency(std::string number)
{
	int fr = this->ctrl.getFrequency(stoi(number));
	std::cout << "Password frequency is: " << fr << "\n";
}