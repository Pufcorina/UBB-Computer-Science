#pragma once
#include <string>
using namespace std;

class Dog
{
private:
	string breed;
	string name;
	int age;
	string photograph;
public:
	friend class ValidatorDog;

	Dog();
	~Dog();

	Dog(const string& breed, const string& name, const int& age, const string& photo);
	const string& getBreed() const { return this->breed; }
	const string& getName() const { return this->name; }
	const int& getAge() const { return this->age; }
	const string& getPhotograph() const { return this->photograph; }

	void showPhotograph();
	const string& toString();
	bool operator == (const Dog& alalalt) const { return this->name == alalalt.name; }
	bool operator != (const Dog& alalalt) const { return this->name != alalalt.name; }
};

class ValidatorDog {
public:
	static void validateDog(const Dog& g);
};

