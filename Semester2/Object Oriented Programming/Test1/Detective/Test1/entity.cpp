#include "entity.h"

Clue::Clue() : text(""), investigated(false), good(false) {}
Clue::Clue(const std::string t, const bool i, const bool g)
{
	this->text = t;
	this->investigated = i;
	this->good = g;
}

bool Clue::operator==(Clue c)
{
	if (this->text == c.getText())
		return true;
	return false;
}