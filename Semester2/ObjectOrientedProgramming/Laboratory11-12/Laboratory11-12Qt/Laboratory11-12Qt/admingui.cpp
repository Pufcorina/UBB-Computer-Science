#include "admingui.h"
#include "RepositoryException.h"
#include <string>
#include <qlabel.h>
#include <qwidget.h>

AdminGUI::AdminGUI(QWidget *parent)
	: QWidget(parent)
{
	ui.setupUi(this);
}

AdminGUI::AdminGUI(Controller* ctrl, WatchList* watchlist, QWidget *parent)
	: QWidget(parent)
{
	this->ctrl = ctrl;
	this->watchlist = watchlist;
	this->ctrl->saveToFile();
	this->watchlist->saveToFile();
	ui.setupUi(this);
	this->AdminInterface();
}

AdminGUI::~AdminGUI()
{

}

void AdminGUI::AdminInterface()
{
	this->setWindowTitle(QString("Administrator mode"));
	this->setWindowIcon(QIcon("Resources/admin.ico"));
	QLabel* label = new QLabel("<img src='Resources/ubb.ico' />");
	this->mainLayout = new QGridLayout{ this };
	this->linesEdit = new QVBoxLayout();
	this->listWidgetAdmin = new QVBoxLayout();
	this->buttonsAdmin = new QHBoxLayout();
	this->mainLayout->addLayout(this->linesEdit, 0, 0, 7, 10);
	this->mainLayout->addLayout(this->listWidgetAdmin, 0, 10, 7, 70);
	this->mainLayout->addLayout(this->buttonsAdmin, 8, 0, 1, 20);

	this->addButton = new QPushButton();
	this->addButton->setText("Add");
	this->buttonsAdmin->addWidget(this->addButton);

	this->deleteButton = new QPushButton();
	this->deleteButton->setText("Delete");
	this->buttonsAdmin->addWidget(this->deleteButton);

	this->updateButton = new QPushButton();
	this->updateButton->setText("Update");
	this->buttonsAdmin->addWidget(this->updateButton);

	this->listButton = new QPushButton();
	this->listButton->setText("List");
	this->buttonsAdmin->addWidget(this->listButton);

	this->exitButton = new QPushButton();
	this->exitButton->setText("EXIT");
	this->buttonsAdmin->addWidget(this->exitButton);

	this->listWidget = new QListWidget();
	this->listWidgetAdmin->addWidget(this->listWidget);

	this->linesEdit->addWidget(label);
	this->titleLineEdit = new QLineEdit();
	this->titleLineEdit->setPlaceholderText(QString::fromStdString("Please insert movie title"));
	this->genreLineEdit = new QLineEdit();
	this->genreLineEdit->setPlaceholderText(QString::fromStdString("Please insert movie genre"));
	this->yearLineEdit = new QLineEdit();
	this->yearLineEdit->setPlaceholderText(QString::fromStdString("Please insert movie year aparition"));
	this->nbLikesLineEdit = new QLineEdit();
	this->nbLikesLineEdit->setPlaceholderText(QString::fromStdString("Please insert movie number of likes"));
	this->linkLineEdit = new QLineEdit();
	this->linkLineEdit->setPlaceholderText(QString::fromStdString("Please insert movie link"));

	this->linesEdit->addWidget(this->titleLineEdit);
	this->linesEdit->addWidget(this->genreLineEdit);
	this->linesEdit->addWidget(this->yearLineEdit);
	this->linesEdit->addWidget(this->nbLikesLineEdit);
	this->linesEdit->addWidget(this->linkLineEdit);

	QObject::connect(this->listButton, SIGNAL(clicked()), this, SLOT(consoleList()));
	QObject::connect(this->addButton, SIGNAL(clicked()), this, SLOT(consoleAdd()));
	QObject::connect(this->deleteButton, SIGNAL(clicked()), this, SLOT(consoleDelete()));
	QObject::connect(this->updateButton, SIGNAL(clicked()), this, SLOT(consoleUpdate()));
	QObject::connect(this->exitButton, SIGNAL(clicked()), this, SLOT(closeForm()));

	this->setStyleSheet("background: rgb(255,228,225)");
	this->listWidget->setStyleSheet("background: rgb(255,255,255)");
	this->titleLineEdit->setStyleSheet("background: rgb(255,255,255)");
	this->genreLineEdit->setStyleSheet("background: rgb(255,255,255)");
	this->yearLineEdit->setStyleSheet("background: rgb(255,255,255)");
	this->nbLikesLineEdit->setStyleSheet("background: rgb(255,255,255)");
	this->linkLineEdit->setStyleSheet("background: rgb(255,255,255)");
	this->addButton->setStyleSheet("background: rgb(248,248,255)");
	this->deleteButton->setStyleSheet("background: rgb(248,248,255)");
	this->listButton->setStyleSheet("background: rgb(248,248,255)");
	this->updateButton->setStyleSheet("background: rgb(248,248,255)");
	this->exitButton->setStyleSheet("background: rgb(248,248,255)");
}

void AdminGUI::clearListWidget()
{
	if (this->listWidget->count() > 0)
		this->listWidget->clear();
}

void AdminGUI::clearLinesEdit()
{
	this->titleLineEdit->clear();
	this->genreLineEdit->clear();
	this->yearLineEdit->clear();
	this->nbLikesLineEdit->clear();
	this->linkLineEdit->clear();

	this->titleLineEdit->setStyleSheet("background: rgb(255, 255, 255)");
	this->genreLineEdit->setStyleSheet("background: rgb(255, 255, 255)");
	this->yearLineEdit->setStyleSheet("background: rgb(255, 255, 255)");
	this->linkLineEdit->setStyleSheet("background: rgb(255, 255, 255)");
	this->nbLikesLineEdit->setStyleSheet("background: rgb(255, 255, 255)");
}

void AdminGUI::consoleList()
{
	clearListWidget();

	for (auto i : this->ctrl->getItems())
	{
		QString itemInList = QString::fromStdString("Movie with title: " + i.getTitle() + " genre: " + i.getGenre() + " from year: " + std::to_string(i.getYear()) + " which has: " + std::to_string(i.getLikes()) + " likes and has the trailer: " + i.getTrailer());
		this->listWidget->addItem(itemInList);
	}
}

void AdminGUI::consoleAdd()
{
	clearListWidget();

	std::string title = this->titleLineEdit->text().toStdString();
	std::string genre = this->genreLineEdit->text().toStdString();
	std::string year = this->yearLineEdit->text().toStdString();
	std::string nbLikes = this->nbLikesLineEdit->text().toStdString();
	std::string trailer = this->linkLineEdit->text().toStdString();

	try
	{
		this->ctrl->add(title, genre, stoi(year), stoi(nbLikes), trailer);
		this->clearLinesEdit();
	}
	catch (MovieException& e)
	{
		for (auto s : e.getErrors())
		{
			if (s.find("title") != -1)
				this->titleLineEdit->setStyleSheet("background: rgb(203, 65, 84)");
			if (s.find("genre") != -1)
				this->genreLineEdit->setStyleSheet("background: rgb(203, 65, 84)");
			if (s.find("Year") != -1)
				this->yearLineEdit->setStyleSheet("background: rgb(203, 65, 84)");
			if (s.find("source") != -1)
				this->linkLineEdit->setStyleSheet("background: rgb(203, 65, 84)");
			this->listWidget->addItem(QString::fromStdString(s));
		}
	}
	catch (RepositoryException& e)
	{
		this->listWidget->addItem(QString::fromStdString(e.what()));
	}
	catch (FileException& e)
	{
		this->listWidget->addItem(QString::fromStdString(e.what()));
	}
	catch (std::invalid_argument e)
	{
		this->listWidget->addItem(QString::fromStdString("Insert correct numbers"));
		this->yearLineEdit->setStyleSheet("background: rgb(203, 65, 84)");
		this->nbLikesLineEdit->setStyleSheet("background: rgb(203, 65, 84)");
	}
}

void AdminGUI::consoleDelete()
{
	clearListWidget();

	std::string title = this->titleLineEdit->text().toStdString();

	try
	{
		this->ctrl->del(title);
		this->clearLinesEdit();
	}
	catch (MovieException& e)
	{
		for (auto s : e.getErrors())
		{
			if (s.find("title") != -1)
				this->titleLineEdit->setStyleSheet("background: rgb(203, 65, 84)");
			this->listWidget->addItem(QString::fromStdString(s));
		}
	}
	catch (RepositoryException& e)
	{
		this->listWidget->addItem(QString::fromStdString(e.what()));
	}
	catch (DuplicateMovieException& e)
	{
		this->listWidget->addItem(QString::fromStdString(e.what()));
	}
	catch (FileException& e)
	{
		this->listWidget->addItem(QString::fromStdString(e.what()));
	}
	catch (std::invalid_argument e)
	{
		this->listWidget->addItem(QString::fromStdString("Insert correct numbers"));
		this->yearLineEdit->setStyleSheet("background: rgb(203, 65, 84)");
		this->nbLikesLineEdit->setStyleSheet("background: rgb(203, 65, 84)");
	}
}

void AdminGUI::consoleUpdate()
{
	clearListWidget();

	std::string title = this->titleLineEdit->text().toStdString();
	std::string genre = this->genreLineEdit->text().toStdString();
	std::string year = this->yearLineEdit->text().toStdString();
	std::string nbLikes = this->nbLikesLineEdit->text().toStdString();
	std::string trailer = this->linkLineEdit->text().toStdString();

	try
	{
		this->ctrl->update(title, genre, stoi(year), stoi(nbLikes), trailer);
		this->clearLinesEdit();
	}
	catch (MovieException& e)
	{
		for (auto s : e.getErrors())
		{
			if (s.find("title") != -1)
				this->titleLineEdit->setStyleSheet("background: rgb(203, 65, 84)");
			if (s.find("genre") != -1)
				this->genreLineEdit->setStyleSheet("background: rgb(203, 65, 84)");
			if (s.find("Year") != -1)
				this->yearLineEdit->setStyleSheet("background: rgb(203, 65, 84)");
			if (s.find("source") != -1)
				this->linkLineEdit->setStyleSheet("background: rgb(203, 65, 84)");
			this->listWidget->addItem(QString::fromStdString(s));
		}
	}
	catch (RepositoryException& e)
	{
		this->listWidget->addItem(QString::fromStdString(e.what()));
	}
	catch (FileException& e)
	{
		this->listWidget->addItem(QString::fromStdString(e.what()));
	}
	catch (std::invalid_argument e)
	{
		this->listWidget->addItem(QString::fromStdString("Insert correct numbers"));
		this->yearLineEdit->setStyleSheet("background: rgb(203, 65, 84)");
		this->nbLikesLineEdit->setStyleSheet("background: rgb(203, 65, 84)");
	}
}

void AdminGUI::closeForm()
{
	this->close();
}