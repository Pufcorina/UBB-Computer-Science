#include "GradingRepository.h"
#include <sstream>
#include <algorithm>
#include <fstream>
#include "Exception.h"

GradingRepository::GradingRepository()
{
	this->readFromFile();
}


GradingRepository::~GradingRepository()
{
}

void GradingRepository::addStudent(int id, std::string name, int group, double grade, std::string teacher)
{
	this->stud.push_back(Students{ id, name, group, grade, teacher });
	this->notify();
}

void GradingRepository::update(int id, std::string name, int group, double grade, std::string teacher)
{
	int pos = this->getPosition(id, name, group);
	if (this->stud[pos].grade != double(1))
		throw Exception("Cannot grade");
	this->stud[pos].grade = grade;
	this->stud[pos].teacher = teacher;
	this->notify();
}

int GradingRepository::getPosition(int id, std::string name, int group)
{
	for (int i = 0; i < this->stud.size(); i++)
		if (this->stud[i].id == id && this->stud[i].name == name && this->stud[i].group == group)
			return i;
	return -1;
}

void GradingRepository::removeStudent(int id, std::string name, int group)
{
	int pos = this->getPosition(id, name, group);
	if (pos != -1)
	{
		this->stud.erase(this->stud.begin() + pos);
		this->notify();
	}
}

void GradingRepository::readFromFile()
{
	std::ifstream f("students.txt");

	if (!f.is_open())
		throw std::exception("	The file could not be opened!\n");

	std::string temp;
	std::string* args;

	while (!f.eof())
	{
		std::getline(f, temp);
		if (f.eof() || temp == "")
		{
			f.close();
			break;
		}

		args = splitString(temp);
		this->addStudent(stoi(args[0]), args[1], stoi(args[2]), stod(args[3]), args[4]);
	}
	f.close();

	std::ifstream f2("teacher.txt");

	if (!f2.is_open())
		throw std::exception("	The file could not be opened!\n");

	std::string temp2;
	std::string* args2;

	while (!f2.eof())
	{
		std::getline(f2, temp2);
		if (f2.eof() || temp2 == "")
		{
			f2.close();
			break;
		}

		args2 = splitString(temp2);
		std::vector<int> gr;
		int i = 1;
		while (args2[i] != "")
		{
			gr.push_back(stoi(args2[i]));
			i++;
		}
		this->teach.push_back(Teacher{ args2[0], gr });
	}
	f2.close();
}

std::string* GradingRepository::splitString(std::string temp)
{
	std::string* args = new std::string[100]{ "" };
	std::istringstream ss(temp);
	std::string token;
	int i = 0;

	while (std::getline(ss, token, ','))
		args[i++] = token;

	return args;
}

void GradingRepository::saveToFile()
{
	std::ofstream f("students.txt");
	for (auto i : this->stud)
		f << i.toString();
	f.close();
	std::ofstream f2("teacher.txt");
	for (auto i : this->teach)
		f2 << i.toString();
	f2.close();
}