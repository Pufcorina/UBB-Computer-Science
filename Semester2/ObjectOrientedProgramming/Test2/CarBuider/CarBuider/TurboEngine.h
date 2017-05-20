#pragma once
#include "Engine.h"
class TurboEngine :
	public Engine
{
public:
	TurboEngine();
	~TurboEngine();

	TurboEngine(std::string fuel, double base);

	std::string toString() override;
	double getPrice() override;
	std::string getType() override;
};

