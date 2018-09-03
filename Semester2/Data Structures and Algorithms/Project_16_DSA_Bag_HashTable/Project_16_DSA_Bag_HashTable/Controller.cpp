#include "Controller.h"



Controller::Controller()
{
}


Controller::~Controller()
{
}


Controller::Controller(const Repository& r)
{
	this->repo = r;
}


Controller& Controller::operator=(const Controller& other)
{
	if (this == &other)
		return *this;
	this->repo = other.repo;
	return *this;
}


bool Controller::searchPassword(int number)
{

	return this->repo.search(number);
}


int Controller::getFrequency(int number)
{
	if (this->repo.search(number) == true)
	{
		Bag<int>::Iterator it{ this->repo.iterator() };
		int fr = 0;
		int e = number;
		while (it.valid())
		{
			if (it.getCurrent() == e)
				fr++;
			it.next();
		}
		return fr;
	}
	else
		return 0;
}