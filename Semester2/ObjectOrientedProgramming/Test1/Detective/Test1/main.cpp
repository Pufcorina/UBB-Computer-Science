#include "repo.h"
#include "controller.h"
#include "ui.h"
#include "tests.h"

int main()
{
	//Tests::testAll();
	Repository repo;
	TElem e1("Julie is a friend of corrupt police officer Martin", false, false);
	repo.addEntity(e1);
	TElem e2("Julie was last seen at underground station Victoria", false, false);
	repo.addEntity(e2);
	TElem e3("a", false, false);
	repo.addEntity(e3);
	TElem e4("b", false, false);
	repo.addEntity(e4);
	Controller cnt(repo);
	UI ui(cnt);
	ui.runApplication();

	system("pause");
}