#include "Service.h"



Service::Service()
{
}


Service::~Service()
{
}

void Service::addDog(const string & breed, const string & name, const int & age, const string & photograph)
{
	Dog dog{ breed,name,age, photograph };
	ValidatorDog::validateDog(dog);
	this->repo.add(dog);
}

void Service::remDog(const string & breed, const string & name, const int & age, const string & photograph)
{
	Dog dog{ breed,name,age, photograph };
	ValidatorDog::validateDog(dog);
	this->repo.rem(dog);
}

void Service::updDog(const string & breed, const string & name, const int & age, const string & photograph)
{
	Dog dog{ breed,name,age, photograph };
	ValidatorDog::validateDog(dog);
	this->repo.upd(dog);
}

const vector<Dog> Service::getAllDogs()
{
	return this->repo.getAll();
}

void Service::addPetToWatchList(const Dog d)
{
	this->watchList.add(d);
	this->repo.rem(d);
}


vector<Dog> Service::filterPet(const string breed, int age)
{
	if (breed == "")
		return this->repo.getAll();
	Repo<Dog> r;
	for (auto i : this->repo.getAll())
	{
		if (i.getBreed() == breed && i.getAge() < age)
			r.add(i);
	}
	return r.getAll();
}

WatchList Service::getAll()
{
	return WatchList(this->repo);
}

WatchList Service::getFilter(const string breed, const int age)
{
	if (breed == "")
		return this->getAll();
	WatchList l;
	Dog mv;
	for (auto i : this->repo.getAll())
		if (i.getBreed() == breed && i.getAge() < age)
			l.add(i);
	return l;
}
