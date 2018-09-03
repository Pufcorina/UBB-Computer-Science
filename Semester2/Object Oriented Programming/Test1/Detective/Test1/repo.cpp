#include "repo.h"
#include <iostream>

void Repository::addEntity(const TElem e)
{
	int pos = findTElem(e);
	if (pos == -1)
		this->elements.push_back(e);
	else
		std::cout << "Duplicate Element\n";
}

void Repository::deleteEntity(const TElem e)
{
	int pos = findTElem(e);
	if (pos != -1)
		this->elements.erase(elements.begin() + pos);
	else
		std::cout << "No such Element\n";
}

void Repository::updateEntity(const TElem e)
{
	int pos = findTElem(e);
	if (pos != -1)
		this->elements[pos] = e;
	else
		std::cout << "No such Element\n";
}

int Repository::findTElem(const TElem e)
{
	for (int i = 0; i < this->elements.size(); i++)
		if (elements[i] == e)
			return i;
	return -1;
}