#pragma once
#include <vector>
#include "Car.h"

class Controller
{
private:
	std::vector<Car> cars;
public:
	Controller();
	~Controller();

	void addCar(std::string bodyStyle, std::string engineType, std::string fuel, int autonomy);
	int getPosition(Car c);
	std::vector<Car> getCars() const { return this->cars; }
	void saveToFile(std::string filename, int price);
	void writeToFile(std::string filename, std::vector<Car> v);
	std::vector<Car> sedanCars(std::vector<Car> v, int price);
	std::vector<Car> convertibleCars(std::vector<Car> v, int price);
};

