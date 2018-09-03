#include "Task.h"
#include <string>


Task::Task()
{
}


Task::~Task()
{
}

std::string Task::toString() const
{
	return this->description + "," + this->status + "," + std::to_string(this->id) + "\n";
}