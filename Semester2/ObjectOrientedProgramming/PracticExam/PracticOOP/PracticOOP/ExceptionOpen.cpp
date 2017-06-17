#include "ExceptionOpen.h"



ExceptionOpen::ExceptionOpen()
{
}

ExceptionOpen::ExceptionOpen(std::string m) : msg(m)
{
}

std::string ExceptionOpen::what() const
{
	return this->msg;
}


ExceptionOpen::~ExceptionOpen()
{
}
