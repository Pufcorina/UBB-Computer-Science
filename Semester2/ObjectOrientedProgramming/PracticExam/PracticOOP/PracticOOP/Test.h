#pragma once
class Test
{
public:
	Test();
	~Test();
	void TestAll();

private:
	static void testControllerRemove();
	static void testControllerUpdate();
	static void testRepositoryRemove();
	static void testRepositoryUpdate();
};

