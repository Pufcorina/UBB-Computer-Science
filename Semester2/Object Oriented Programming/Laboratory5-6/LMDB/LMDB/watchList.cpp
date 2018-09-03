#include "watchList.h"

WatchList::WatchList()
{
	this->current = 0;
}

void WatchList::add(const Movie mv)
{
	this->movies.addItem(mv);
}

void WatchList::deleteMovie(const Movie mv)
{
	int pos = findMovie(mv);
	if (pos != -1)
		this->movies.deleteItem(pos);
	else
		std::cout << "Movie not in your WatchList!\n";
}

int WatchList::findMovie(const Movie mv)
{
	for (int i = 0; i < this->movies.getSize(); i++)
		if (this->movies.getElement(i).getGenre() == mv.getGenre() && this->movies.getElement(i).getTitle() == mv.getTitle() && this->movies.getElement(i).getYear() == mv.getYear())
			return i;
	return -1;
}

Movie WatchList::findMovieByInformation(std::string title, std::string genre, int year)
{
	for (int i = 0; i < this->movies.getSize(); i++)
		if (this->movies.getElement(i).getGenre() == genre && this->movies.getElement(i).getTitle() == title && this->movies.getElement(i).getYear() == year)
			return this->movies.getElement(i);
	Movie m;
	return m;
}

Movie WatchList::getCurrentMovie()
{
	return this->movies.getElement(this->current);
}

void WatchList::play()
{
	if (this->movies.getSize() == 0)
		return;
	Movie currentMovie = this->getCurrentMovie();
	currentMovie.playTrailer();
}

void WatchList::next()
{
	if (this->current + 1 == this->movies.getSize())
	{
		this->current = 0;
		return;
	}
	this->current++;
}

bool WatchList::isEmpty()
{
	return this->movies.getSize() == 0;
}