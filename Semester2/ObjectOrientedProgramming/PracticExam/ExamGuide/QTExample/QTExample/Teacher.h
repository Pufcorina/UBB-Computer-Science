#pragma once
#include <vector>
#include <iostream>
class Teacher
{
public:
	std::string name;
	std::vector<int> groups;
public:
	Teacher();
	Teacher(std::string n, std::vector<int> v) : name(n), groups(v) {}
	~Teacher();
	std::string toString();
};

