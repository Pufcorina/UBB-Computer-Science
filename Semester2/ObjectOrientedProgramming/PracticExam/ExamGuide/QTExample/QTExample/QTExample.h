#pragma once

#include <QtWidgets/QWidget>
#include "ui_QTExample.h"
#include "GradingController.h"
#include "Observer.h"

class QTExample : public QWidget, public Observer
{
	Q_OBJECT

public:
	QTExample(GradingController* c, QWidget *parent = Q_NULLPTR);
	void update() override;

private slots:
	void addFunction();
	void closeFunction();
	void removeFunction();

private:
	GradingController* ctrl;
	Ui::QTExampleClass ui;
};
