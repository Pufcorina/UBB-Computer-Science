#pragma once
#include "Repository.h"
class Controller
{
public:
	Repository* repo;
	Controller();
	Controller(Repository* r) : repo(r) {}
	~Controller();
	std::vector<Task> sortS();
	void removeTask(std::string d, std::string i);
	void updateTask(std::string d, std::string s, std::string i);
	static int taskCompare(const Task & a, const Task & b);
};



