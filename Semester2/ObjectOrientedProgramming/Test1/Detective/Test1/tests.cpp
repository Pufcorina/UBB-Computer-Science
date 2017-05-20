#include "tests.h"
#include "repo.h"
#include "controller.h"
#include <assert.h>

void Tests::testAll()
{
	testAddReopsitory();
	testAddController();
	testDeleteController();
}

void Tests::testAddReopsitory()
{
	Repository repo;
	TElem e3("a", false, false);
	repo.addEntity(e3);

	assert(repo.getElements().size() == 1);

	TElem e4("a", false, false);
	repo.addEntity(e4);

	assert(repo.getElements().size() == 1);
}
void Tests::testAddController()
{
	Repository repo;
	Controller cnt(repo);
	cnt.addTElemToRepository("a", false, false);
	assert(cnt.getAllElements().size() == 1);
	cnt.addTElemToRepository("a", false, false);
	assert(cnt.getAllElements().size() == 1);
}
void Tests::testDeleteController()
{
	Repository repo;
	Controller cnt(repo);
	cnt.addTElemToRepository("a", false, false);
	assert(cnt.getAllElements().size() == 1);
	cnt.addTElemToRepository("b", false, false);
	assert(cnt.getAllElements().size() == 2);
	cnt.addTElemToRepository("c", false, true);
	assert(cnt.getAllElements().size() == 3);


	cnt.deleteTElemToRepository();
	assert(cnt.getAllElements().size() == 1);
}