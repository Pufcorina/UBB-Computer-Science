#pragma once
#include <vector>
#include <algorithm>

class Observer
{
public:
	virtual void update() = 0;
	virtual ~Observer() {}
};

class Observable
{
private:
	std::vector<Observer*> observers;
public:
	virtual ~Observable() {}

	void addObserver(Observer *obs)
	{
		observers.push_back(obs);
	}

	void removeObserver(Observer *obs)
	{
		observers.erase(std::remove(observers.begin(), observers.end(), obs));
	}

	void notify()
	{
		for (auto obs : observers)
		{
			obs->update();
		}
	}
};


