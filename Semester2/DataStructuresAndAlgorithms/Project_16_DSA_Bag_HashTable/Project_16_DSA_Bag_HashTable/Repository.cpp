#include "Repository.h"
#include <string>
#include <fstream>


Repository::Repository()
{
	this->filename = "";
	elements.init();
}


Repository::Repository(std::string file)
{
	elements.init();
	this->filename = file;
	readFromFile();
}


Repository::~Repository()
{
}


Repository& Repository::operator=(const Repository& other)
{
	if (this == &other)
		return *this;
	this->elements = other.elements;
	return *this;
}


void Repository::readFromFile()
{
	std::ifstream f(this->filename);

	if (!f.is_open())
		throw std::exception("The file could not be opened!\n");

	std::string temp;
	std::string* args;

	while (!f.eof())
	{
		std::getline(f, temp);
		if (f.eof() || temp == "")
		{
			f.close();
			break;
		}

		args = splitString(temp);
		this->add(stoi(args[0]));
	}
	f.close();
}


void Repository::add(const int& e)
{
	this->elements.add(e);
}


bool Repository::search(const int& e)
{
	return this->elements.search(e);
}


Bag<int>::Iterator Repository::iterator()
{
	Bag<int>::Iterator it;
	it.init(&this->elements);
	return it;
}