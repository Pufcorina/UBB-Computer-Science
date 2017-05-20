#include "ui.h"
#include <iostream>
#include <string>
#include <windows.h>

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
		if (command == "add")
		{
			addTElemToController();
			std::cout << '\n';
			continue;
		}
		if (command == "delete")
		{
			deleteTElemToController();
			std::cout << '\n';
			continue;
		}
		if (command == "inv")
		{
			Investigate();
			std::cout << '\n';
			continue;
		}
		if (command == "list")
		{
			listAllTelems(this->cnt.getRepo().getElements());
			std::cout << '\n';
			continue;
		}
		std::cout << "Invalid mode!\n";
	}
}

void UI::printMenuApplication()
{
	std::cout << "You can: \n";
	std::cout << "add    - Add a new clue \n";
	std::cout << "delete - Remove all the clues which provides to be false \n";
	std::cout << "inv    - Investigate a clue \n";
	std::cout << "list   - List all current clues \n";
	std::cout << "exit\n\n";
}

void UI::addTElemToController()
{
	std::string text, investig, truth;
	std::cout << "Insert the text: ";
	std::getline(std::cin, text);
	std::cout << "Insert the investigation value: ";
	std::getline(std::cin, investig);
	std::cout << "Insert the truth: ";
	std::getline(std::cin, truth);
	bool a, b;
	if (investig == "true")
		a = true;
	else
		a = false;
	if(truth == "true")
		b = true;
	else
		b = false;
	this->cnt.addTElemToRepository(text, a, b);
}

void UI::deleteTElemToController()
{
	this->cnt.deleteTElemToRepository();
}

void UI::listAllTelems(std::vector<TElem> v)
{
	if (v.empty())
	{
		std::cout << "No element in your container!\n";
		return;
	}
	for (int i = 0; i < v.size(); i++)
	{
		std::cout << "Clue: "<< v[i].getText() << " was/wasn't investigated: " << v[i].getInvestigation() << " and the clue was: " << v[i].getTruth() << "\n";
	}
}

void UI::Investigate()
{
	std::cout << "It is day: " << this->day << '\n';
	updateDay();

	std::string text, truth;
	std::cout << "Insert the text of: ";
	std::getline(std::cin, text);
	std::cout << "Insert the truth of: ";
	std::getline(std::cin, truth);
	bool b;
	if (truth == "true")
		b = true;
	else
		b = false;

	if (this->cnt.checkInvestigated(text) == true)
		std::cout << "Already investigated\n";
	else
	{
		this->cnt.updateTElemToRepository(text, true, b);
		std::cout << "Insert the first new clue: \n";
		addTElemToController();
		std::cout << "Insert the second new clue: \n";
		addTElemToController();
	}
}