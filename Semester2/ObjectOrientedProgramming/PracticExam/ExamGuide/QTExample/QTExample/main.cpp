#include "QTExample.h"
#include <QtWidgets/QApplication>
#include "QtGuiTeacher.h"

int main(int argc, char *argv[])
{
	QApplication a(argc, argv);
	GradingController* ctrl = new GradingController{ new GradingRepository{} };
	QTExample w{ ctrl };
	w.show();
	std::vector<QtGuiTeacher*> te;
	for (auto i : ctrl->repo->teach)
		te.push_back(new QtGuiTeacher{ i, ctrl });
	for (int i = 0; i < te.size(); i++)
		te[i]->show();
	return a.exec();
}
