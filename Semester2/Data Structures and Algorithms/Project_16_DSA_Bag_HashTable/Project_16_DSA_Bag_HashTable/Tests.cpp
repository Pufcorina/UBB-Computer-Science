#include "Tests.h"
#include <assert.h>
#include "Bag.h"
#include <iostream>
#include <string>

Tests::Tests()
{
}


Tests::~Tests()
{
}

void Tests::testAll()
{
	testConstructrors();
	testAddFunctions();
	testSearchFunction();
	testRemoveFunction();
	testIterator();
}


void Tests::testConstructrors()
{
	Bag<int> b1;
	b1.init();
	b1 = b1;
	assert(b1.size() == 0);
	Bag<double> b2;
	assert(b2.size() == 0);
	Bag<int> b3{ b1 };
	assert(b3.size() == 0);
	Bag<int> b4;
	assert(b4.size() == 0);
	b4 = b1;
	assert(b4.size() == 0);
}

void Tests::testAddFunctions()
{
	Bag<int> b1;
	b1.init();
	b1.add(3);
	assert(b1.size() == 1);
	b1.add(13);
	b1.add(33);
	b1.add(5);
	b1.add(13);
	b1.add(22);
	b1.add(1);
	b1.add(11);
	b1.add(10);
	b1.add(3);
	b1.add(4);
	b1.add(5);
	b1.add(6);
	b1.add(7);
	b1.add(8);
	b1.add(9);
	assert(b1.size() == 16);
}


void Tests::testSearchFunction()
{
	Bag<int> b;
	b.init();
	b.add(3);
	b.add(13);
	b.add(33);
	b.add(5);
	b.add(13);
	b.add(22);
	assert(b.search(22) == true);
	assert(b.search(3) == true);
	assert(b.search(12) == false);
}


void Tests::testRemoveFunction()
{
	Bag<int> b;
	b.init();
	try
	{
		b.remove(1);
	} 
	catch (std::exception e)
	{
		std::cout << "Element " << std::to_string(1) << " " << e.what() << '\n';
	}

	b.add(3);
	b.add(13);
	b.add(33);
	b.add(23);
	b.add(2);
	b.add(43);

	b.remove(23);
	b.remove(33);
	b.remove(43);
	b.remove(3);
	b.add(10);
	b.remove(10);

	assert(b.size() == 2);
}


void Tests::testIterator()
{
	Bag<int> b;
	b.init();
	b.add(3);
	b.add(13);
	b.add(33);
	b.add(23);
	b.add(2);
	b.add(43);

	Bag<int>::Iterator it;

	assert(it.valid() == false);

	it.init(&b);
	while (it.valid())
	{
		std::cout << it.getCurrent() << "\n";
		it.next();
	}
}