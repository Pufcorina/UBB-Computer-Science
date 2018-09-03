#pragma once
class Tests
{
public:
	Tests();
	~Tests();

	void testAll();

private:
	static void testConstructrors();
	static void testAddFunctions();
	static void testSearchFunction();
	static void testRemoveFunction();
	static void testIterator();
};

