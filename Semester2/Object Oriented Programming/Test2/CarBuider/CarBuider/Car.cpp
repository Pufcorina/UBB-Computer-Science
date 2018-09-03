#include "Car.h"



Car::Car()
{
}

Car::Car(std::string body, std::string type, std::string fuel, int autonomy)
{
	if (body == "Sedan")
		if (type == "Electric")
		{
			Engine* e = new ElectricEngine(fuel, 11000, autonomy);
			this->engine = e;
		}
		else
		{
			Engine* e = new TurboEngine(fuel, 11000);
			this->engine = e;
		}
	else
		if (type == "Turbo")
		{
			Engine* e = new TurboEngine(fuel, 12000);
			this->engine = e;
		}
		else
		{
			Engine* e = new ElectricEngine(fuel, 12000, autonomy);
			this->engine = e;
		}
	this->type = type;
	this->bodyStyle = body;

}

double Car::computePrice()
{
	return this->engine->getPrice();
}


Car::~Car()
{
}

std::string Car::toString()
{
	return "Body style: " + this->bodyStyle + " -- " + " Engine type: " + this->type + " " + this->engine->toString() + "\n";
}

std::ostream& operator<<(std::ostream& os, Car c)
{
	os << c.toString();
	return os;
}

std::string Car::getType()
{
	return this->type;
}

std::string Car::getStyle()
{
	return this->bodyStyle;
}


std::string Car::getFuel()
{
	return this->engine->getType();
}