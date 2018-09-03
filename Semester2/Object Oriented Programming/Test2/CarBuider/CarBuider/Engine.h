#pragma once
#include <iostream>
#include <string>

class Engine
{
protected:
	std::string fuelType;
	double basePrice;

public:
	Engine();
	~Engine();

	Engine(std::string fuel, double base);

	virtual double getPrice() = 0;
	virtual std::string toString() = 0;
	virtual std::string getType() = 0;
};

