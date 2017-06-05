#include "mainform.h"
#include "CSVWatchList.h"
#include "HTMLWatchList.h"
#include "FileRepository.h"
#include "Controller.h"

MainForm::MainForm(QWidget * parent) : QWidget(parent) {
	MemoryRepository* admin_repo = new FileRepository{ "Resources/movie_database.csv" };
	this->ctrl = new Controller{ admin_repo };
	ui.setupUi(this);
	this->formInterface();
}

MainForm::~MainForm() {
	
}

void MainForm::formInterface()
{
	QObject::connect(this->ui.adminButton, SIGNAL(clicked()), this, SLOT(adminForm()));
	QObject::connect(this->ui.exitButton, SIGNAL(clicked()), this, SLOT(close()));
	QObject::connect(this->ui.userButton, SIGNAL(clicked()), this, SLOT(userForm()));
}


void MainForm::adminForm()
{
	if (this->ui.csvRadioButton->isChecked() == true)
		this->watchlist = new CSVWatchList("csv");
	else if (this->ui.htmlRadioButton->isChecked() == true)
		this->watchlist = new HTMLWatchList("html");
	//this->hide();
	AdminGUI* w = new AdminGUI(this->ctrl, this->watchlist);
	w->show();
}

void MainForm::userForm()
{
	if (this->ui.csvRadioButton->isChecked() == true)
		this->watchlist = new CSVWatchList("csv");
	else if (this->ui.htmlRadioButton->isChecked() == true)
		this->watchlist = new HTMLWatchList("html");
	//this->hide();
	UserGUI* w = new UserGUI(this->ctrl, this->watchlist);
	w->show();
}