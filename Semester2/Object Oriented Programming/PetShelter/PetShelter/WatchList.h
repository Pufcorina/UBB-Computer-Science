#pragma once
#include "Dog.h"
#include "Repo.h"
class WatchList
{
private:
	Repo<Dog> repo;
	int current;
public:
	WatchList();
	WatchList(const Repo<Dog>& r) : repo{ r } {};
	~WatchList();

	vector<Dog> getAll() { return this->repo.getAll(); }
	void play();
	void next();
	bool isEmpty();

	Dog getCurrent();
	void add(const Dog d);
	void deletePet(const Dog d);
};

