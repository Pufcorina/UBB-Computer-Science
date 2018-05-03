#pragma once
#include "WatchList.h"
class Service
{
private:
	Repo<Dog> repo;
	WatchList watchList;
	
public:
	Service();
	~Service();

	Service(const Repo<Dog>& repo) : repo{ repo } {};
	void addDog(const string& breed, const string& name, const int& age, const string& photograph);
	void remDog(const string& breed, const string& name, const int& age, const string& photograph);
	void updDog(const string& breed, const string& name, const int& age, const string& photograph);

	const vector<Dog> getAllDogs();

	WatchList getWatchList() { return this->watchList; }
	void addPetToWatchList(const Dog d);
	vector<Dog> filterPet(const string breed, int age);
	WatchList getAll();
	WatchList getFilter(const string breed, const int age);
};

