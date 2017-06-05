#include "mainform.h"
#include <QtWidgets/QApplication>

int main(int argc, char *argv[])
{
	QApplication a(argc, argv);

	MainForm* f = new MainForm();
	f->show();

	return a.exec();
}
