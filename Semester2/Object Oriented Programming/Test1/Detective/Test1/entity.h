#pragma once
#include <iostream>

class Clue
{
private:
	std::string text;
	bool investigated;
	bool good;

public:
	Clue();
	Clue(const std::string t, const bool i, const bool g);

	std::string getText() const { return text; }
	bool getInvestigation() const { return investigated; }
	bool getTruth() const { return good; }

	bool operator==(Clue c);
};