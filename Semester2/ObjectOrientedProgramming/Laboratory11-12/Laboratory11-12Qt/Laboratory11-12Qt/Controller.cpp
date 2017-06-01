#include "Controller.h"



Controller::Controller()
{
}


Controller::~Controller()
{
}

void Controller::add(const std::string& title, const std::string genre, const int& year, const int& likes, std::string trailer)
{
	this->repo->add(Movie{ title, genre, year, likes, trailer });
}

int Controller::update(const std::string& title, const std::string genre, const int& year, const int& likes, std::string trailer)
{
	return this->repo->update(Movie{ title, genre, year, likes, trailer });
}
