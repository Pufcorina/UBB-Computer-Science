#include "Controller.h"
#include <fstream>


Controller::Controller()
{
}


Controller::~Controller()
{
}

void Controller::addCar(std::string bodyStyle, std::string engineType, std::string fuel, int autonomy)
{
	if (bodyStyle != "Sedan" && bodyStyle != "Convertible")
		throw std::runtime_error("Error body style");
	if (engineType != "Electric" && engineType != "Turbo")
		throw std::runtime_error("Error body style");

	Car c = Car(bodyStyle, engineType, fuel, autonomy);
	int pos = getPosition(c);

	if (pos != -1)
		throw std::runtime_error("Duplicate Car");
	this->cars.push_back(c);
	std::cout << c.toString();
}

int Controller::getPosition(Car c)
{
	int count = 0;
	for (auto i : this->cars)
		if (i.computePrice() == c.computePrice())
			return count;
		else
			count++;
	return -1;
}

std::vector<Car> Controller::sedanCars(std::vector<Car> v, int price)
{
	for (auto i : this->cars)
		if (i.getStyle() == "Sedan" && i.computePrice() < price)
			v.push_back(i);
	return v;
}

std::vector<Car> Controller::convertibleCars(std::vector<Car> v, int price)
{
	for (auto i : this->cars)
		if (i.getStyle() == "Convertible" && i.computePrice() < price)
			v.push_back(i);
	return v;
}

void Controller::saveToFile(std::string filename, int price)
{
	std::vector<Car> v;
	v = sedanCars(v, price);
	v = convertibleCars(v, price);
	writeToFile(filename, v);
}

void Controller::writeToFile(std::string filename, std::vector<Car> v)
{
	std::ofstream f(filename);
	
	for (auto i : v)
		f << i;

	f.close();
}

