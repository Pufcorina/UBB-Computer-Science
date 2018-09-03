#include "Test.h"
#include "Controller.h"
#include <assert.h>

Test::Test()
{
}


Test::~Test()
{
}

void Test::TestAll()
{
	testControllerRemove();
	testControllerUpdate();
	testRepositoryRemove();
	testRepositoryUpdate();
}

void Test::testControllerRemove()
{

	Controller* ctrl = new Controller{ new Repository{} };
	ctrl->removeTask("Task1", "-1");
	assert(ctrl->repo->tasks.size() == 4);
	ctrl->removeTask("Task1", "-1");
	assert(ctrl->repo->tasks.size() == 4);
}

void Test::testControllerUpdate()
{
	Controller* ctrl = new Controller{ new Repository{} };
	ctrl->updateTask("Task1", "progress", "2");
	assert(ctrl->repo->tasks.size() == 5);
}

void Test::testRepositoryRemove()
{

	Repository* r = new Repository{};
	r->removeTask("Task1", -1);
	assert(r->tasks.size() == 4);
	r->removeTask("Task1", -1);
	assert(r->tasks.size() == 4);
}

void Test::testRepositoryUpdate()
{
	Repository* r = new Repository{};
	r->updateTask("Task1", "progress", 2);
	assert(r->tasks.size() == 5);
}