#include "WatchList.h"



WatchList::WatchList()
{
	this->movieList = std::vector<Movie>();
	this->currentPos = 0;
	this->maximumPos = 0;
	this->suggestions = std::vector<Movie>();
	this->type = "";
}

WatchList::WatchList(std::string type)
{
	this->movieList = std::vector<Movie>();
	this->currentPos = 0;
	this->maximumPos = 0;
	this->suggestions = std::vector<Movie>();
	this->type = type;
}


WatchList::~WatchList()
{
}

void WatchList::add()
{
	this->movieList.push_back(this->suggestions[this->currentPos]);
	this->suggestions.erase(this->suggestions.begin() + this->currentPos);
	this->maximumPos--;
}

int WatchList::del(std::string title)
{
	int pos = getPosition(title);

	if (pos == -1)
		return 0;
	this->movieList.erase(this->movieList.begin() + pos);
	return 1;
}

void WatchList::getSuggestions(std::vector<Movie> repo, std::string genre)
{
	if (genre == "")
		this->suggestions = repo;
	else
	{
		std::vector<Movie> suggestion = std::vector<Movie>();
		for (auto i : repo)
			if (i.getGenre() == genre)
				suggestion.push_back(i);
		this->suggestions = suggestion;
	}
	this->currentPos = 0;
	this->maximumPos = suggestions.size();
}

int WatchList::getPosition(std::string title)
{
	int counter = 0;
	for (auto i : this->movieList)
	{
		if (i.getTitle() == title)
			return counter;
		counter++;
	}
	return -1;
}

Movie WatchList::getCurrentMovie()
{
	if (this->currentPos == this->maximumPos)
		this->currentPos = 0;
	return this->suggestions[this->currentPos];
}

void WatchList::nextPos()
{
	if (this->currentPos == this->maximumPos)
		this->currentPos = 0;
	else
		this->currentPos++;
}