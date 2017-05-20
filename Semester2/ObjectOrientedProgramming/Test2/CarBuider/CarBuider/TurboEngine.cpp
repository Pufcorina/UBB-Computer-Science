#include "TurboEngine.h"


TurboEngine::TurboEngine()
{
}


TurboEngine::~TurboEngine()
{
}

TurboEngine::TurboEngine(std::string fuel, double base) : Engine(fuel, base)
{
}

std::string TurboEngine::toString()
{
	return " -- Fuel type: " + this->fuelType + " -- " + " Engine price: " + std::to_string(this->basePrice) + " -- " + " Car price" + std::to_string(getPrice());
}

double TurboEngine::getPrice()
{
	if (this->fuelType == "gasoline")
		return (this->basePrice + (0.01 * 100));
	else
		return (this->basePrice + (0.01 * 150));
}

std::string TurboEngine::getType()
{
	return this->fuelType;
}
