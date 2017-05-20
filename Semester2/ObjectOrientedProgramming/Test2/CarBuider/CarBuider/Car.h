#pragma once
#include "Engine.h"
#include "ElectricEngine.h"
#include "TurboEngine.h"

class Car
{
private:
	std::string bodyStyle;
	Engine* engine;
	std::string type;
public:
	Car();
	Car(std::string body, std::string type, std::string fuel, int autonomy);
	~Car();

	double computePrice();
	std::string toString();
	friend std::ostream& operator<<(std::ostream& os, Car c);
	std::string getType();
	std::string getStyle();
	std::string getFuel();
};

