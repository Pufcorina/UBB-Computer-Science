#include "Students.h"
#include <string>

Students::~Students()
{
}

std::string Students::toString()
{
	return std::to_string(this->id) + "," + this->name + "," + std::to_string(this->group) + "," + std::to_string(this->grade) + "," + this->teacher + "\n";
}