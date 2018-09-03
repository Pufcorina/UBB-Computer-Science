#include "Console.h"



Console::Console()
{
}

Console::Console(Controller c)
{
	this->ctrl = c;
}

Console::~Console()
{
}

void Console::runApplication()
{
	std::string command;
	while(1)
	{ 
		std::cout << "\nadd\n";
		std::cout << "show\n";
		std::cout << "save\n";
		std::cout << "exit\n\n";
		std::getline(std::cin, command);
		if (command == "add")
		{
			uiAdd();
			std::cout << "\n";
			continue;
		}
		if (command == "show")
		{
			uiShow();
			std::cout << "\n";
			continue;
		}
		if (command == "exit")
			break;
		if (command == "save")
		{
			uiSave();
		}
	}
}

void Console::uiAdd()
{
	std::string bodyType;
	std::string engineType;
	std::string fuelType;
	std::string autonomy;
	std::cout << "Please select body type (Sedan/Convertible): ";
	std::getline(std::cin, bodyType);
	std::cout << "Please select engine type(Electric/Trubo): ";
	std::getline(std::cin, engineType);
	std::cout << "Please select fuelType(gasoline/diesel): ";
	std::getline(std::cin, fuelType);
	std::cout << "Please insert autonomy: ";
	std::getline(std::cin, autonomy);

	try
	{
		this->ctrl.addCar(bodyType, engineType, fuelType, stoi(autonomy));
	}
	catch (std::runtime_error e)
	{
		std::cout << e.what() << "\n";
	}
}

void Console::uiShow()
{
	for (auto i : this->ctrl.getCars())
		std::cout << i.toString();
}

void Console::uiSave()
{
	std::string filename;
	std::string price;
	std::cout << "Please insert the filename: ";
	std::getline(std::cin, filename);
	std::cout << "Please insert the price: ";
	std::getline(std::cin, price);
	this->ctrl.saveToFile(filename, stoi(price));
}