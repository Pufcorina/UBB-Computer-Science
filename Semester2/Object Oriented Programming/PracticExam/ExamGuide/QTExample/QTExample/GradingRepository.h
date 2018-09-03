#pragma once
#include "Students.h"
#include "Teacher.h"
#include "Observer.h"

class GradingRepository : public Observable
{
public:
	std::vector<Students> stud;
	std::vector<Teacher> teach;
public:
	GradingRepository();
	~GradingRepository();
	void addStudent(int id, std::string name, int group, double grade, std::string teacher);
	void update(int id, std::string name, int group, double grade, std::string teacher);
	int getPosition(int id, std::string name, int group);
	void removeStudent(int id, std::string name, int group);
	void readFromFile();
	std::string * splitString(std::string temp);
	void saveToFile();
};

