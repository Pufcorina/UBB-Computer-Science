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
