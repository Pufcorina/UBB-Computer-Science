#include "QtGuiTeacher.h"
#include "Exception.h"
#include <qmessagebox.h>

QtGuiTeacher::QtGuiTeacher(Teacher t, GradingController* c, QWidget *parent)
	: QWidget(parent)
{
	this->teacher = t;
	this->ctrl = c;
	this->ctrl->repo->addObserver(this);
	ui.setupUi(this);
	this->setWindowTitle(QString::fromStdString(t.name));
	for(auto i: t.groups)
		for(auto j: c->repo->stud)
			if (i == j.group)
			{
				QString item = QString::fromStdString("Student: " + j.name + " id: " + std::to_string(j.id) + " group: " + std::to_string(j.group) + " has grade: " + std::to_string(j.grade) + " from teacher: " + j.teacher);
				this->ui.listWidget->addItem(item);
			}

	QObject::connect(this->ui.pushButton, SIGNAL(clicked()), this, SLOT(gradeFunction()));
}

void QtGuiTeacher::gradeFunction()
{
	double grade = stod(this->ui.lineEdit->text().toStdString());
	if (this->ui.listWidget->selectedItems().size() != 0)
	{
		std::string text = this->ui.listWidget->currentItem()->text().toStdString();
		int pos1 = text.find("id: ");
		int pos2 = text.find(" group");
		std::string id = text.substr(pos1 + 4, pos2 - pos1 - 4);
		pos2 = text.find(" has grade: ");
		pos1 = text.find(" group");
		std::string group = text.substr(pos1 + 8, pos2 - pos1 - 8);
		pos2 = text.find(" id: ");
		pos1 = text.find("Student: ");
		std::string name = text.substr(pos1 + 9, pos2 - pos1 - 9);
		try
		{
			this->ctrl->repo->update(stoi(id), name, stoi(group), grade, this->teacher.name);
		}
		catch (Exception e)
		{
			QMessageBox::StandardButton error = QMessageBox::warning(this, QString::fromStdString("Error"), QString::fromStdString(e.what()), QMessageBox::Ok);
		}
	}
}

QtGuiTeacher::~QtGuiTeacher()
{
}

void QtGuiTeacher::update()
{
	this->ui.listWidget->clear();
	for (auto i : this->teacher.groups)
		for (auto j : this->ctrl->repo->stud)
			if (i == j.group)
			{
				QString item = QString::fromStdString("Student: " + j.name + " id: " + std::to_string(j.id) + " group: " + std::to_string(j.group) + " has grade: " + std::to_string(j.grade) + " from teacher: " + j.teacher);
				this->ui.listWidget->addItem(item);
			}
}