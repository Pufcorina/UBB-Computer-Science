#include "Controller.h"
#include <iostream>
#include <Windows.h>
#include <vector>
#include <string>
#include <algorithm>
#include <numeric>


Controller::Controller()
{
}


Controller::~Controller()
{
}

int Controller::taskCompare(const Task& a, const Task& b)
{
	return a.status > b.status;
}

std::vector<Task> Controller::sortS()
{
	if (this->repo->tasks.empty())
		return std::vector<Task>();
	std::vector<Task> sorted = this->repo->tasks;
	for (int i = 0; i < sorted.size(); i++)
		for (int j = i + 1; j < sorted.size(); j++)
			if (taskCompare(sorted[i], sorted[j]))
			{
				Task aux = sorted[i];
				sorted[i] = sorted[j];
				sorted[j] = aux;
			}
	//sort(sorted.begin(), sorted.end(), studentCompare);
	return sorted;
}

/*
	Controller function which cast the string received from console, and call the remove function from Repository
	input:: d - description task string
			i - id task string
	return:: None.
	throw:: None.
*/
void Controller::removeTask(std::string d, std::string i)
{
	this->repo->removeTask(d, stoi(i));
}


/*
	Controller function which cast the string received from console, and call the update function from Repository
	input:: d - description task string
			s - status task string
			i - id task string
	return:: None.
	throw:: None.
*/
void Controller::updateTask(std::string d, std::string s, std::string i)
{
	this->repo->updateTask(d, s, stoi(i));
}