#include "util.h"
#include <sstream>
#include <algorithm>

std::string* splitString(std::string temp)
{
	std::string* args = new std::string[5];
	std::istringstream ss(temp);
	std::string token;
	int i = 0;

	while (std::getline(ss, token, ','))
		args[i++] = token;

	return args;
}