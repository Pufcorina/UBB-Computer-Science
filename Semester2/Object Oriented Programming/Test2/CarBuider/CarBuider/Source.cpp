#include "Controller.h"
#include "Console.h"

int main()
{
	Controller c = Controller();

	//Init
	c.addCar("Sedan", "Electric", "gasoline", 10);
	c.addCar("Sedan", "Electric", "gasoline", 12);
	c.addCar("Sedan", "Electric", "diesel", 13);
	c.addCar("Sedan", "Electric", "diesel", 14);
	c.addCar("Sedan", "Turbo", "gasoline", 10);
	c.addCar("Sedan", "Turbo", "diesel", 13);
	c.addCar("Convertible", "Electric", "gasoline", 10);
	c.addCar("Convertible", "Electric", "gasoline", 12);
	c.addCar("Convertible", "Electric", "diesel", 13);
	c.addCar("Convertible", "Electric", "diesel", 14);
	c.addCar("Convertible", "Turbo", "gasoline", 10);
	c.addCar("Convertible", "Turbo", "diesel", 13);

	Console cons = Console(c);
	cons.runApplication();
	return 0;
}