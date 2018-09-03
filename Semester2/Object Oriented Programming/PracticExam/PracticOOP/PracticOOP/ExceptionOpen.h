#pragma once
#include <iostream>

class ExceptionOpen
{
private:
	std::string msg;
public:
	ExceptionOpen();
	ExceptionOpen(std::string m);
	std::string what() const;
	~ExceptionOpen();
};

