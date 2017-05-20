#include "Engine.h"



Engine::Engine()
{
}


Engine::~Engine()
{
}

Engine::Engine(std::string fuel, double base)
{
	this->fuelType = fuel;
	this->basePrice = base;
}