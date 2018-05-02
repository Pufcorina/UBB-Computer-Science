#include "UI.h"
#include <string>
#include <iostream>
#include <windows.h>


UI::UI()
{
}


UI::~UI()
{
}

void UI::runApplication()
{
	while (true)
	{
		printMenuApplication();
		std::string command;
		std::getline(std::cin, command);
		if (command == "exit")
		{
			std::cout << "Bye!!! :)\n";
			break;
		}

		if (command == "admin")
		{
			system("cls");
			std::cout << "Now you are in Admin mode\n";
			runAdminMode();
			std::cout << "You are no more in Admin mode! ^_^\n";
			Sleep(2000);
			system("cls");
			continue;
		}
		if (command == "user")
		{
			{
				system("cls");
				std::cout << "Now you are in User mode\n";
				runUserMode();
				std::cout << "You are no more in User mode! ^_^\n";
				Sleep(2000);
				system("cls");
				continue;
			}
		}
		std::cout << "Invalid mode!\n";
	}
}

void UI::runAdminMode()
{
	while (true)
	{
		printMenuAdmin();
		std::string command;
		std::getline(std::cin, command);
		if (command == "exit")
			break;
		if (command == "add")
		{
			addPetToController();
			std::cout << '\n';
			continue;
		}
		if (command == "delete")
		{
			deletePetToController();
			std::cout << '\n';
			continue;
		}
		if (command == "update")
		{
			updatePetToController();
			std::cout << '\n';
			continue;
		}
		if (command == "list")
		{
			listAllPets();
			std::cout << '\n';
			continue;
		}
		std::cout << "Invalid command!\n\n";
	}
}

void UI::runUserMode()
{
}

void UI::printMenuApplication()
{
	std::cout << "Please select your mode from: admin / user\n";
	std::cout << "You are: ";
}

void UI::printMenuAdmin()
{
	std::cout << "add - Add a pet\n";
	std::cout << "delete - Delete a pet\n";
	std::cout << "update - Update a pet\n";
	std::cout << "list - List all ptes\n\n";
}

void UI::addPetToController()
{
	std::string breed, name, age, photograph;
	std::cout << "Insert breed: ";
	std::getline(std::cin, breed);
	std::cout << "Insert name: ";
	std::getline(std::cin, name);
	std::cout << "Insert age: ";
	std::getline(std::cin, age);
	std::cout << "Insert photograph: ";
	std::getline(std::cin, photograph);

	this->ctrl.addDog(breed, name, stoi(age), photograph);
}

void UI::deletePetToController()
{
	std::string breed, name, age, photograph;
	std::cout << "Insert breed: ";
	std::getline(std::cin, breed);
	std::cout << "Insert name: ";
	std::getline(std::cin, name);
	std::cout << "Insert age: ";
	std::getline(std::cin, age);
	std::cout << "Insert photograph: ";
	std::getline(std::cin, photograph);

	this->ctrl.remDog(breed, name, stoi(age), photograph);
}

void UI::updatePetToController()
{
	std::string breed, name, age, photograph;
	std::cout << "Insert breed: ";
	std::getline(std::cin, breed);
	std::cout << "Insert name: ";
	std::getline(std::cin, name);
	std::cout << "Insert age: ";
	std::getline(std::cin, age);
	std::cout << "Insert photograph: ";
	std::getline(std::cin, photograph);

	this->ctrl.updDog(breed, name, stoi(age), photograph);
}

void UI::listAllPets()
{
	vector<Dog> ls = this->ctrl.getAllDogs();
	for (auto i : ls)
		std::cout << "Pet with breed: " << i.getBreed() << " name: " << i.getName() << " with age: " << i.getAge() << " having photograph: " << i.getPhotograph() << "\n";
}
