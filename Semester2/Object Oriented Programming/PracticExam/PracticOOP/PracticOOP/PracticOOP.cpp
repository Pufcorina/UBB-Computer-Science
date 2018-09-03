#include "PracticOOP.h"
#include <qmessagebox.h>
#include "ExceptionOpen.h"

PracticOOP::PracticOOP(Programmer p, Controller* c, QWidget *parent)
	: QWidget(parent)
{
	this->ctrl = c;
	this->prog = p;
	this->ctrl->repo->addObserver(this);
	ui.setupUi(this);
	this->setWindowTitle(QString::fromStdString(this->prog.name));

	this->ui.tasklineEdit->setPlaceholderText(QString::fromStdString("Insert task description"));

	this->ui.listWidget->clear();

	for (auto i : this->ctrl->sortS())
	{
		QString item = QString::fromStdString("Task: " + i.description + " has status: " + i.status + " and programmer id: " + std::to_string(i.id));
		this->ui.listWidget->addItem(item);
	}
	QObject::connect(this->ui.addpushButton, SIGNAL(clicked()), this, SLOT(addFunction()));
	QObject::connect(this->ui.removepushButton, SIGNAL(clicked()), this, SLOT(removeFunction()));
	QObject::connect(this->ui.exitpushButton, SIGNAL(clicked()), this, SLOT(closeFunction()));
	QObject::connect(this->ui.donepushButton, SIGNAL(clicked()), this, SLOT(doneFunction()));
	QObject::connect(this->ui.startpushButton, SIGNAL(clicked()), this, SLOT(startFunction()));
	QObject::connect(this->ui.listWidget, SIGNAL(itemSelectionChanged()), this, SLOT(enableFunction()));
}

void PracticOOP::enableFunction()
{
	if (this->ui.listWidget->selectedItems().size() != 0)
	{
		std::string text = this->ui.listWidget->currentItem()->text().toStdString();
		int pos1 = text.find("id: ");
		std::string id = text.substr(pos1 + 4);
		int pos2 = text.find("Task: ");
		pos1 = text.find(" has status: ");
		std::string description = text.substr(pos2 + 6, pos1 - pos2 - 6);
		pos1 = text.find("status: ");
		pos2 = text.find(" and p");
		std::string status = text.substr(pos1 + 8, pos2 - pos1 - 8);
		if (status == "progress" && this->prog.id == stoi(id))
			this->ui.donepushButton->setEnabled(true);
		else
			this->ui.donepushButton->setEnabled(false);
	}
}

void PracticOOP::doneFunction()
{
	if (this->ui.listWidget->selectedItems().size() != 0)
	{
		std::string text = this->ui.listWidget->currentItem()->text().toStdString();
		int pos2 = text.find("Task: ");
		int pos1 = text.find(" has status: ");
		std::string description = text.substr(pos2 + 6, pos1 - pos2 - 6);
		this->ctrl->updateTask(description, "close", std::to_string(this->prog.id));
	}
}

void PracticOOP::startFunction()
{
	if (this->ui.listWidget->selectedItems().size() != 0)
	{
		std::string text = this->ui.listWidget->currentItem()->text().toStdString();
		int pos1 = text.find("id: ");
		std::string id = text.substr(pos1 + 4);
		int pos2 = text.find("Task: ");
		pos1 = text.find(" has status: ");
		std::string description = text.substr(pos2 + 6, pos1 - pos2 - 6);
		pos1 = text.find("status: ");
		pos2 = text.find(" and p");
		std::string status = text.substr(pos1+8, pos2 -pos1 - 8);
		try{
			if(status == "open")
				this->ctrl->updateTask(description, "progress", std::to_string(this->prog.id));
			else
			{
				throw ExceptionOpen("This operation fails, the selected task is not open!");
			}
		}
		catch (ExceptionOpen& e)
		{
			QMessageBox::StandardButton reply = QMessageBox::information(this, "Error", QString::fromStdString(e.what()));
		}
	}
}

void PracticOOP::removeFunction()
{
	if (this->ui.listWidget->selectedItems().size() != 0)
	{
		std::string text = this->ui.listWidget->currentItem()->text().toStdString();
		int pos1 = text.find("id: ");
		std::string id = text.substr(pos1 + 4);
		int pos2 = text.find("Task: ");
		pos1 = text.find(" has status: ");
		std::string description = text.substr(pos2 + 6, pos1 - pos2 - 6);
		this->ctrl->removeTask(description, id);
	}
}

void PracticOOP::addFunction()
{
	std::string description = this->ui.tasklineEdit->text().toStdString();

	this->ctrl->repo->addTask(description, "open", -1);
}

void PracticOOP::closeFunction()
{

	this->ctrl->repo->saveToFile();
	close();
}

void PracticOOP::update()
{
	this->ui.listWidget->clear();
	for (auto i : this->ctrl->sortS())
	{
		QString item = QString::fromStdString("Task: " + i.description + " has status: " + i.status + " and programmer id: " + std::to_string(i.id));
		this->ui.listWidget->addItem(item);
	}
}