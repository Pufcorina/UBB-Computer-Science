#include "Repository.h"
#include <string>
#include <fstream>
#include <sstream>

Repository::Repository()
{
	this->readFromFile();
}


Repository::~Repository()
{
}

void Repository::addProgrammer(std::string n, int i)
{
	this->programmers.push_back(Programmer{ n,i });
}

void Repository::addTask(std::string d, std::string s, int i)
{
	this->tasks.push_back(Task{ d,s,i });
	this->notify();
}

/*
	Update a given task from the tasks vector if it exists, otherwise do nothing
	input:: d - description task string
			s - status task string
			i - id task integer
	return:: None.
	throw:: None.
*/
void Repository::updateTask(std::string d, std::string s, int i)
{
	int pos;
	if (s == "progress")
		pos = this->getPos(d, -1);
	else
		pos = this->getPos(d, i);
	if (pos != -1)
	{
		this->tasks[pos].description = d;
		this->tasks[pos].id = i;
		this->tasks[pos].status = s;
	}
	this->notify();
}

/*
	Remove a given task from the tasks vector if it exists, otherwise do nothing
	input:: d - description task string
			i - id task integer
	return:: None.
	throw:: None.
*/
void Repository::removeTask(std::string description, int id)
{
	int pos = getPos(description, id);
	if (pos != -1)
	{
		this->tasks.erase(this->tasks.begin() + pos);
		this->notify();
	}
}


int Repository::getPos(std::string description, int id)
{
	for (int i = 0; i < this->tasks.size(); i++)
		if (this->tasks[i].description == description && this->tasks[i].id == id)
			return i;
	return -1;
}

void Repository::saveToFile()
{
	std::ofstream f("tasks.txt");
	for (auto i : this->tasks)
		f << i.toString();
	f.close();
}


void Repository::readFromFile()
{
	std::ifstream f("programmers.txt");

	if (!f.is_open())
		throw std::exception("	The file could not be opened!\n");

	std::string temp;
	std::string* args;

	while (!f.eof())
	{
		std::getline(f, temp);
		if (f.eof() || temp == "")
		{
			f.close();
			break;
		}

		args = splitString(temp);
		this->addProgrammer(args[0], stoi(args[1]));
	}
	f.close();

	std::ifstream f2("tasks.txt");

	if (!f2.is_open())
		throw std::exception("	The file could not be opened!\n");

	std::string temp2;
	std::string* args2;

	while (!f2.eof())
	{
		std::getline(f2, temp2);
		if (f2.eof() || temp2 == "")
		{
			f2.close();
			break;
		}

		args2 = splitString(temp2);
		this->addTask(args2[0], args2[1], stoi(args2[2]));
	}
	f2.close();
}

std::string* Repository::splitString(std::string temp)
{
	std::string* args = new std::string[100]{ "" };
	std::istringstream ss(temp);
	std::string token;
	int i = 0;

	while (std::getline(ss, token, ','))
		args[i++] = token;

	return args;
}