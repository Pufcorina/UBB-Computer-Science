#include "GradingController.h"
#include <iostream>
#include <Windows.h>
#include <vector>
#include <string>
#include <algorithm>
#include <numeric>


GradingController::GradingController()
{
}


GradingController::~GradingController()
{
}

int GradingController::studentCompare(const Students & a, const Students & b)
{
	if (a.group != b.group)
		return a.group > b.group;
	return a.name > b.name;
}

std::vector<Students> GradingController::sortS()
{
	if (this->repo->stud.empty())
		return std::vector<Students>();
	std::vector<Students> sorted = this->repo->stud;
	for(int i = 0; i < sorted.size(); i++ )
		for( int j = i + 1; j < sorted.size(); j++ )
			if (studentCompare(sorted[i], sorted[j]))
			{
				Students aux = sorted[i];
				sorted[i] = sorted[j];
				sorted[j] = aux;
			}
	//sort(sorted.begin(), sorted.end(), studentCompare);
	return sorted;
}
