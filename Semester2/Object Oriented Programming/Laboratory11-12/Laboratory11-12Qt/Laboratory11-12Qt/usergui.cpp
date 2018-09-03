#include "usergui.h"
#include <string>
#include <qmessagebox.h>
#include <qfiledialog.h>
#include <qtextstream.h>

UserGUI::UserGUI(Controller* ctrl, WatchList* watchlist, QWidget * parent) : QWidget(parent) {
	this->ctrl = ctrl;
	this->watchlist = watchlist;
	this->ctrl->saveToFile();
	this->watchlist->saveToFile();
	ui.setupUi(this);
	this->userInterface();
}

UserGUI::~UserGUI() {
	
}

void UserGUI::userInterface()
{
	this->ui.searchLineEdit->setPlaceholderText(QString::fromStdString("Please insert movie genre"));
	QObject::connect(this->ui.addButton, SIGNAL(clicked()), this, SLOT(userAdd()));
	QObject::connect(this->ui.exitButton, SIGNAL(clicked()), this, SLOT(close()));
	QObject::connect(this->ui.nextButton, SIGNAL(clicked()), this, SLOT(userNext()));
	QObject::connect(this->ui.searchButton, SIGNAL(clicked()), this, SLOT(userSearch()));
	QObject::connect(this->ui.saveButton, SIGNAL(clicked()), this, SLOT(userSave()));
	QObject::connect(this->ui.openButton, SIGNAL(clicked()), this, SLOT(userOpen()));
	QObject::connect(this->ui.deleteButton, SIGNAL(clicked()), this, SLOT(userDelete()));
	QObject::connect(this->ui.showButton, SIGNAL(clicked()), this, SLOT(userShow()));
	this->ui.movieLabel->setText(QString("Title: \nGenre: \nYear: \nNumber of likes: \nLink: "));
}

void UserGUI::userSearch()
{
	this->watchlist->getSuggestions(this->ctrl->getItems(), this->ui.searchLineEdit->text().toStdString());
	if (this->watchlist->getMaximumPos() == 0)
		this->ui.movieLabel->setText(QString("No suggestions for this genre, sorry!"));
	Movie m = this->watchlist->getCurrentMovie();
	this->printMovie(&m);
}

void UserGUI::printMovie(Movie* m)
{
	std::string text = "Title: " + m->getTitle() + "\nGenre: " + m->getGenre() + "\nYear: " + std::to_string(m->getYear()) + "\nNumber of likes: " + std::to_string(m->getLikes()) + "\nLink: " + m->getTrailer();
	this->ui.movieLabel->setText(QString::fromStdString(text));
}

void UserGUI::userAdd()
{
	if (this->watchlist->getMaximumPos() != -1)
	{
		this->watchlist->add();
		this->userNext();
	}
	else
		this->ui.movieLabel->setText(QString::fromStdString("That's all for now!"));
}

void UserGUI::userNext()
{
	this->watchlist->nextPos();
	Movie m = this->watchlist->getCurrentMovie();
	this->printMovie(&m);
}

void UserGUI::userSave()
{
	this->watchlist->saveToFile();
}

void UserGUI::userOpen()
{
	if (this->watchlist->getType() == "csv")
	{
		QFile file("Resources/watchlist.csv");
		QTextStream in(&file);

		if (!file.open(QIODevice::ReadOnly))
			QMessageBox::information(0, "info", file.errorString());

		this->ui.textBrowser->setText(in.readAll());
	}
	else
	{
		QFile file("Resources/watchlist.html");
		QTextStream in(&file);

		if (!file.open(QIODevice::ReadOnly))
			QMessageBox::information(0, "info", file.errorString());

		this->ui.textBrowser->setText(in.readAll());
	}
}

void UserGUI::userDelete()
{
	if (this->ui.showListWidget->selectedItems().size() != 0)
	{
		std::string text = this->ui.showListWidget->currentItem()->text().toStdString();
		int pos1 = text.find(':');
		std::string subtext = text.substr(pos1 + 2);
		int pos2 = subtext.find("genre:");
		std::string title = subtext.substr(0, pos2 - 1);
		int res = this->watchlist->del(title);
		QMessageBox::StandardButton reply = QMessageBox::question(this, "Movie", "Like the movie ?", QMessageBox::Yes | QMessageBox::No);
		if (reply == QMessageBox::Yes)
			this->ctrl->incLikes(title);
	}
}

void UserGUI::userShow()
{
	this->ui.showListWidget->clear();
	if (this->watchlist->getList().size() == 0)
		this->ui.showListWidget->addItem(QString::fromStdString("No movie in your WatchList!"));
	else
		for (auto i : this->watchlist->getList())
		{
			QString itemInList = QString::fromStdString("Movie with title: " + i.getTitle() + " genre: " + i.getGenre() + " from year: " + std::to_string(i.getYear()) + " which has: " + std::to_string(i.getLikes()) + " likes and has the trailer: " + i.getTrailer());
			this->ui.showListWidget->addItem(itemInList);
		}
}

