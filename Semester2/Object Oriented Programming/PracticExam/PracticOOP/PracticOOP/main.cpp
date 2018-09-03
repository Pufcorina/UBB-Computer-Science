#include "PracticOOP.h"
#include <QtWidgets/QApplication>
#include "Controller.h"
#include "Test.h"

int main(int argc, char *argv[])
{
	//Test t;
	//t.TestAll();
	Controller* ctrl = new Controller{ new Repository{} };
	QApplication a(argc, argv);
	std::vector<PracticOOP*> forms;
	for (auto i : ctrl->repo->programmers)
		forms.push_back(new PracticOOP{ i, ctrl });
	for (int i = 0; i < forms.size(); i++)
		forms[i]->show();
	return a.exec();
}
