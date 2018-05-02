#pragma once
#include "Dog.h"
#include "Repo.h"
class Service
{
private:
	Repo<Dog> repo;
public:
	Service();
	~Service();

	Service(const Repo<Dog>& repo) : repo{ repo } {};
	void addDog(const string& breed, const string& name, const int& age, const string& photograph);
	void remDog(const string& breed, const string& name, const int& age, const string& photograph);
	void updDog(const string& breed, const string& name, const int& age, const string& photograph);

	const vector<Dog> getAllDogs();
};

