#pragma once
#include <iostream>

class Exception
{
private:
	std::string msg;
public:
	Exception();
	Exception(std::string m) : msg(m) {}
	std::string what() { return this->msg; }

	~Exception();
};

