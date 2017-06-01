#pragma once
#include "Movie.h"
#include <vector>

class WatchList
{
protected:
	std::vector<Movie> movieList;
	std::vector<Movie> suggestions;
	int currentPos, maximumPos;
	std::string type;

public:
	WatchList();
	WatchList(std::string type);
	~WatchList();

	void add();
	int del(std::string title);

	void getSuggestions(std::vector<Movie> repo, std::string genre);
	std::vector<Movie> getList() { return this->movieList; }
	int getPosition(std::string title);
	Movie getCurrentMovie();
	int getMaximumPos() const { return this->maximumPos; }
	std::string getType() const { return this->type; }

	void setCurrentPos(int value) { this->currentPos += value; }
	void nextPos();

	virtual void saveToFile() = 0;
	virtual void openInApp() = 0;
};

