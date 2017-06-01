#include "admingui.h"
#include <QtWidgets/QApplication>
#include "CSVWatchList.h"
#include "HTMLWatchList.h"
#include "FileRepository.h"
#include "Controller.h"

int main(int argc, char *argv[])
{
	QApplication a(argc, argv);
	MemoryRepository* admin_repo = new FileRepository{ "Resources/movie_database.csv" };

	WatchList* user_repo;
	user_repo = new CSVWatchList();

	Controller* admin_ctrl = new Controller{ admin_repo };
	AdminGUI* w = new AdminGUI(admin_ctrl, user_repo);
	w->show();

	return a.exec();
}
