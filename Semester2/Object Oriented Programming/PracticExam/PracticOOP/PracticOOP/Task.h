#pragma once
#include <iostream>
class Task
{
public:
	std::string description;
	std::string status;
	int id;
public:
	Task();
	Task(std::string d, std::string s, int i) : description(d), status(s), id(i) {}
	Task(std::string d) : description(d), status("open"), id(-1) {}
	std::string toString() const;
	~Task();
};

