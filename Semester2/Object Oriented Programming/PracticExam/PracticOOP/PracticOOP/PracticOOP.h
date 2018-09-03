#pragma once

#include <QtWidgets/QWidget>
#include "ui_PracticOOP.h"
#include "Controller.h"
#include "Observer.h"

class PracticOOP : public QWidget, public Observer
{
	Q_OBJECT

public:
	PracticOOP(Programmer p, Controller* c, QWidget *parent = Q_NULLPTR);

private:
	Controller* ctrl;
	Programmer prog;
	Ui::PracticOOPClass ui;
	void update() override;
private slots:
	void addFunction();
	void enableFunction();
	void doneFunction();
	void startFunction();
	void removeFunction();
	void closeFunction();
};
