#pragma once


#include "Engine.h"
class ElectricEngine :
	public Engine
{
private:
	int autonomy;
public:
	ElectricEngine();
	~ElectricEngine();

	ElectricEngine(std::string fuel, double base, int autonomy);

	std::string toString() override;
	double getPrice() override;
	std::string getType() override;
};

