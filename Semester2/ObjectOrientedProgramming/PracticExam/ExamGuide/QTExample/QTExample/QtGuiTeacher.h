#pragma once

#include <QWidget>
#include "ui_QtGuiTeacher.h"
#include "Observer.h"
#include "GradingController.h"

class QtGuiTeacher : public QWidget, public Observer
{
	Q_OBJECT

public:
	QtGuiTeacher(Teacher t, GradingController* c, QWidget *parent = Q_NULLPTR);
	~QtGuiTeacher();

	void update() override;

private slots:
	void gradeFunction();

private:
	Teacher teacher;
	GradingController* ctrl;
	Ui::QtGuiTeacher ui;
};
