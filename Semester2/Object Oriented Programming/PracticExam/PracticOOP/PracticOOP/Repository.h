#pragma once
#include <vector>
#include "Task.h"
#include "Programmer.h"
#include "Observer.h"

class Repository : public Observable
{
public:
	std::vector<Task> tasks;
	std::vector<Programmer> programmers;
public:
	Repository();
	~Repository();
	void addProgrammer(std::string n, int i);
	void addTask(std::string d, std::string s, int i);
	void updateTask(std::string d, std::string s, int i);
	void removeTask(std::string description, int id);
	int getPos(std::string description, int id);
	void saveToFile();
	void readFromFile();
	std::string * splitString(std::string temp);
};
