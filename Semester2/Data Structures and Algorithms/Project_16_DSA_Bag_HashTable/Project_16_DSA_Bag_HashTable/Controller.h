#pragma once
#include "Repository.h"

class Controller
{
private:
	Repository repo;
public:
	//Constructor
	Controller();

	//Copy constructor
	Controller(const Repository& r);

	//Destructor
	~Controller();

	//Assignment overload operator
	Controller& operator=(const Controller& other);


	/*
		Search the password in the container
			params:: number - password
			return:: True if it exists, False otherwise
			throws:: No exception.
	*/
	bool searchPassword(int number);


	/*
		Find the frequecy of a given password
			params:: number - password
			return:: the frequency
			throws:: No exception.
	*/
	int getFrequency(int number);
};

