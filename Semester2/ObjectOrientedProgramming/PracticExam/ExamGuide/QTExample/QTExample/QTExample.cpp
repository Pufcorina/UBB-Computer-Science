#include "QTExample.h"
#include "qmessagebox.h"

QTExample::QTExample(GradingController* c, QWidget *parent)
	: QWidget(parent)
{
	this->ctrl = c;
	this->ctrl->repo->addObserver(this);
	ui.setupUi(this);

	this->ui.idlineEdit->setPlaceholderText(QString::fromStdString("Insert id"));

	this->ui.listWidget->clear();

	for (auto i : this->ctrl->sortS())
	{
		QString item = QString::fromStdString("Student: " + i.name + " id: " + std::to_string(i.id) + " group: " + std::to_string(i.group) + " has grade: " + std::to_string(i.grade) + " from teacher: " + i.teacher);
		this->ui.listWidget->addItem(item);
	}
	QObject::connect(this->ui.addPushButton, SIGNAL(clicked()), this, SLOT(addFunction()));
	QObject::connect(this->ui.removepushButton, SIGNAL(clicked()), this, SLOT(removeFunction()));
	QObject::connect(this->ui.exitPushButton, SIGNAL(clicked()), this, SLOT(closeFunction()));
}

void QTExample::closeFunction()
{

	this->ctrl->repo->saveToFile();
	close();
}

void QTExample::addFunction()
{
	int id = stoi(this->ui.idlineEdit->text().toStdString());
	std::string name = this->ui.namelineEdit->text().toStdString();
	//std::string teacher = this->ui.teacherlineEdit->text().toStdString();
	int group = stoi(this->ui.grouplineEdit->text().toStdString());
	//double grade = stod(this->ui.gradelineEdit->text().toStdString());

	this->ctrl->repo->addStudent(id, name, group, 1, "");
}

void QTExample::removeFunction()
{
	if (this->ui.listWidget->selectedItems().size() != 0)
	{
		std::string text = this->ui.listWidget->currentItem()->text().toStdString();
		int pos1 = text.find("id: ");
		int pos2 = text.find(" group");
		std::string id = text.substr(pos1 + 4, pos2-pos1-4);
		pos2 = text.find(" has grade: ");
		pos1 = text.find(" group");
		std::string group = text.substr(pos1 + 8, pos2 - pos1 - 8);
		pos2 = text.find(" id: ");
		pos1 = text.find("Student: ");
		std::string name = text.substr(pos1 + 9, pos2 - pos1 - 9);
		QMessageBox::StandardButton reply = QMessageBox::question(this, "Remove", "Remove student?", QMessageBox::Yes | QMessageBox::No);
		if (reply == QMessageBox::Yes)
			this->ctrl->repo->removeStudent(stoi(id), name, stoi(group));
	}
}

void QTExample::update()
{
	this->ui.listWidget->clear();
	for (auto i : this->ctrl->sortS())
	{
		QString item = QString::fromStdString("Student: " + i.name + " id: " + std::to_string(i.id) + " group: " + std::to_string(i.group) + " has grade: " + std::to_string(i.grade) + " from teacher: " + i.teacher);
		this->ui.listWidget->addItem(item);
	}
}
