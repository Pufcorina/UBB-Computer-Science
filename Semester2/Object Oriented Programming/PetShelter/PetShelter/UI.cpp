#include "UI.h"
#include <string>
#include <iostream>
#include <windows.h>
#include <exception>


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
		try {
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
		catch (exception& e) {
			cout << "Standard exception: " << e.what() << endl;
		}
	}
}

void UI::runAdminMode()
{
	while (true)
	{
		try {
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
		catch (exception& e) {
			cout << "Standard exception: " << e.what() << endl;
		}
	}
}

void UI::runUserMode()
{
	while (true)
	{
		try {
			printMenuUser();
			std::string command;
			std::getline(std::cin, command);
			if (command == "exit")
				break;
			if (command == "watch")
			{
				watchStart();
				std::cout << '\n';
				continue;
			}
			if (command == "show")
			{
				printWatchList();
				std::cout << '\n';
				continue;
			}
			if (command == "filter")
			{
				userFilter();
				std::cout << '\n';
				continue;
			}
			std::cout << "Invalid command!\n\n";
		}
		catch (exception& e) {
			cout << "Standard exception: " << e.what() << endl;
		}
	}
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

void UI::printMenuUser()
{
	std::cout << "watch - See the pets by breed\n";
	std::cout << "filter - Filter pets from the WatchList by breed and age\n";
	std::cout << "show - See the WatchList\n\n";
}

void UI::printWatchMenu()
{
	std::cout << "You can: \n";
	std::cout << "next - go to the next element\n";
	std::cout << "add - add to the WatchList\n";
	std::cout << "exit\n";
}

void UI::userFilter()
{
	string breed;
	string age;
	std::cout << "Insert breed: ";
	std::getline(std::cin, breed);
	std::cout << "Insert age: ";
	std::getline(std::cin, age);
	WatchList ls = this->ctrl.getFilter(breed, stoi(age));
	Dog d;
	std::string command;
	while (!ls.isEmpty())
	{
		printWatchMenu();
		ls.play();
		d = ls.getCurrent();
		std::cout << "Pet with breed: " << d.getBreed() << " name: " << d.getName() << " with age: " << d.getAge() << " having photograph: " << d.getPhotograph() << "\n";
		std::getline(std::cin, command);
		if (command == "exit")
			break;
		if (command == "add")
		{
			this->ctrl.addPetToWatchList(d);
			ls.deletePet(d);
		}
		if (command == "next")
			ls.next();
	}
	if (ls.isEmpty())
		std::cout << "There are no pets!\n";
}

void UI::printWatchList()
{
	if (this->ctrl.getWatchList().getAll().size() == 0) {
		std::cout << "No pet in your WatchList!\n";
		return;
	}
	Dog d;
	int cnt = 0;
	while (this->ctrl.getWatchList().getAll().size() != 0)
	{
		d = this->ctrl.getWatchList().getCurrent();
		std::cout << "Pet with breed: " << d.getBreed() << " name: " << d.getName() << " with age: " << d.getAge() << " having photograph: " << d.getPhotograph() << "\n";
		cnt++;
		if (cnt == this->ctrl.getWatchList().getAll().size())
			break;
		this->ctrl.getWatchList().next();
	}
}

void UI::watchStart()
{
	WatchList ls = this->ctrl.getAll();
	Dog d;
	std::string command;
	while (!ls.isEmpty())
	{
		printWatchMenu();
		ls.play();
		d = ls.getCurrent();
		std::cout << "Pet with breed: " << d.getBreed() << " name: " << d.getName() << " with age: " << d.getAge() << " having photograph: " << d.getPhotograph() << "\n";
		std::getline(std::cin, command);
		if (command == "exit")
			break;
		if (command == "add")
		{
			this->ctrl.addPetToWatchList(d);
			ls.deletePet(d);
		}
		if (command == "next")
			ls.next();
	}
	if (ls.isEmpty())
		std::cout << "There are no pets!\n";
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
