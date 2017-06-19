#pragma once
#include <iostream>
#include <string>

class Programmer
{
public:
	std::string name;
	int id;
public:
	Programmer();
	Programmer(std::string n, int i) : name(n), id(i) {}
	~Programmer();
	std::string toString() const;
};

