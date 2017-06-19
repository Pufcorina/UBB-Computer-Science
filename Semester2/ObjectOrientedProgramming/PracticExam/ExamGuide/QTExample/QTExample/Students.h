#pragma once
#include <iostream>

class Students
{
public:
	int id;
	std::string name;
	int group;
	double grade;
	std::string teacher;
public:
	Students(int i, std::string n, int g, double gr, std::string t) : id(i), name(n), group(g), grade(gr), teacher(t) {}
	Students() : id(0), name(""), group(0), grade(1), teacher("") {}
	Students(int i, std::string n, int g) : id(i), name(n), group(g), grade(1), teacher("") {}
	~Students();

	std::string toString();

	//int getId() const { return this->id; }
	//std::string getName() const { return this->name; }
	//int getGroup()
};

