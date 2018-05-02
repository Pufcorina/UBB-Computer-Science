#include "stdafx.h"
#include "Dog.h"
#include <sstream>
#include <Windows.h>
#include <shellapi.h>
#include "Exceptions.h"


Dog::Dog()
{
}


Dog::~Dog()
{
}

Dog::Dog(const string & breed, const string & name, const int & age, const string & photo) :breed{ breed }, name{ name }, age{ age }, photograph{ photo } {}

void Dog::showPhotograph()
{
	ShellExecuteA(NULL, NULL, "chrome.exe", this->getPhotograph().c_str(), NULL, SW_SHOWMAXIMIZED);
}

const string & Dog::toString()
{
	stringstream ss;
	ss << this->name << ", " << this->breed << " - " << this->age;
	return ss.str();
}

void ValidatorDog::validateDog(const Dog & g)
{
	string errorMsg = "";
	if (g.breed == "")
		errorMsg += "Incorrect breed\n";
	if (g.name == "")
		errorMsg += "Name cannot be empty\n";
	if (g.photograph == "")
		errorMsg += "Photograph cannot be empty\n";
	if (g.age < 1)
		errorMsg += "PUPPY CANNOT BE ADOPTED THAT YOUNG OHMYGOSH\n";
	if (errorMsg.size() > 0)
		throw ValidationException{ errorMsg };
}
