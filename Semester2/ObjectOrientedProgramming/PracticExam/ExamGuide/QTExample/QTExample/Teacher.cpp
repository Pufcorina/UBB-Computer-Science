#include "Teacher.h"
#include <string>


Teacher::Teacher()
{
}


Teacher::~Teacher()
{
}

std::string Teacher::toString()
{
	std::string buffer = this->name + ",";
	for (int i = 0; i < this->groups.size() - 1; i++)
		buffer = buffer + std::to_string(this->groups[i]) + ",";
	buffer = buffer + std::to_string(this->groups[this->groups.size() - 1]) + "\n";
	return buffer;
}