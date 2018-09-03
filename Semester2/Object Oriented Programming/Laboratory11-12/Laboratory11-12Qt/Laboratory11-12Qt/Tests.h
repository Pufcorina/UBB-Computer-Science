#pragma once
class Tests
{
public:
	Tests();
	~Tests();

	void TestAll();
	
	void testController();
	void testCSVWatchList();
	void testFileRepository();
	void testHTMLWatchList();
	void testMemoryRepository();
	void testMovie();
	void testRepository();
	void testRepositoryException();
	void testUtil();
	void testValidator();
	void testWatchList();
};

