#include "WatchList.h"



WatchList::WatchList()
{
	this->current = 0;
}


WatchList::~WatchList()
{
}

void WatchList::play()
{
	if (this->repo.getAll().size() == 0)
		return;
	Dog currentDog = this->getCurrent();
	currentDog.showPhotograph();
}

void WatchList::next()
{
	if (this->current + 1 == this->repo.getAll().size())
	{
		this->current = 0;
		return;
	}
	this->current++;
}

bool WatchList::isEmpty()
{
	return this->repo.getAll().size() == 0;
}

Dog WatchList::getCurrent()
{
	return this->repo.getAll().at(this->current);
}

void WatchList::add(const Dog d)
{
	this->repo.add(d);
}

void WatchList::deletePet(const Dog d)
{
	this->repo.rem(d);
}
