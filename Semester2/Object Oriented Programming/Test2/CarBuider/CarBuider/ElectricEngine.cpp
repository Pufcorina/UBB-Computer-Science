#include "ElectricEngine.h"


ElectricEngine::ElectricEngine()
{
}

ElectricEngine::ElectricEngine(std::string fuel, double base, int autonomy) : Engine(fuel, base)
{
	this->autonomy = autonomy;
}


ElectricEngine::~ElectricEngine()
{
}

std::string ElectricEngine::toString()
{
	return " -- Fuel type: " + this->fuelType + " -- " + " Autonomy: " + std::to_string(this->autonomy) + " -- " + " Engine price: " + std::to_string(this->basePrice) + " -- " + " Car price" + std::to_string(getPrice());
}

double ElectricEngine::getPrice()
{
	return (this->basePrice + (double)((double)this->autonomy * 0.01));
}

std::string ElectricEngine::getType()
{
	return this->fuelType;
}