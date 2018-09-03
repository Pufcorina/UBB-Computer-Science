#pragma once
#include "GradingRepository.h"

class GradingController
{
public:
	GradingRepository* repo;
public:
	GradingController();
	GradingController(GradingRepository* r) : repo(r) {}
	~GradingController();
	std::vector<Students> sortS();
	static int studentCompare(const Students & a, const Students & b);
};
