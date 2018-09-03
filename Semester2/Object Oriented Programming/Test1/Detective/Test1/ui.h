#pragma once
#include "controller.h"

class UI
{
private:
	Controller cnt;
	int day;

public:
	UI(const Controller c) : cnt(c) {}

	void runApplication();

private:
	static void printMenuApplication();
	
	void updateDay() { this->day++; }
	void Investigate();
	void addTElemToController();
	void deleteTElemToController();
	void listAllTelems(std::vector<TElem> v);

};