#pragma once
#include "Bag.h"
#include "util.h"

class Repository
{
private:
	Bag<int> elements;
	std::string filename;

public:
	//Constructor
	Repository();
	Repository(std::string file);

	//Copy constructor
	Repository(const Repository& r) { this->elements = r.elements; }

	//Destructor
	~Repository();

	//Assignment overload operator
	Repository& operator=(const Repository& other);


	/*
		Search the password in the container
			params:: e - password
			return:: True if it exists, False otherwise
			throws:: No exception.
	*/
	bool search(const int& e);

	/*
		Get an iterator to the current container
			params:: None.
			return:: bag iterator
			throws:: No exception.
	*/
	Bag<int>::Iterator iterator();
private:
	//Read passwords from data.txt file
	void readFromFile();

	/*
	Add the element e to the Bag
	params:: e - password
	return:: None.
	throws:: No exception.
	*/
	void add(const int & e);
};

