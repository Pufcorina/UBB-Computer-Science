#include "repo.h"

void Repository::addMovie(const Movie mv)
{
	int pos = findMovie(mv.getTitle(), mv.getGenre(), mv.getYear());
	if (pos == -1)
		this->movies.addItem(mv);
	else
		std::cout << "Duplicate Movie\n";
}

void Repository::deleteMovie(const Movie mv)
{
	int pos = findMovie(mv.getTitle(), mv.getGenre(), mv.getYear());
	if (pos != -1)
		this->movies.deleteItem(pos);
	else
		std::cout << "No such movie\n";
}

void Repository::updateMovie(const Movie mv)
{
	int pos = findMovie(mv.getTitle(), mv.getGenre(), mv.getYear());
	if (pos != -1)
		this->movies.updateItem(pos, mv);
	else
		std::cout << "No such movie\n";
}

int Repository::findMovie(const std::string title, const std::string genre, const int year)
{
	Movie m;
	for (int i = 0; i < this->movies.getSize(); i++)
	{
		m = this->movies.getElement(i);
		if (m.getTitle() == title && m.getGenre() == genre && m.getYear() == year)
			return i;
	}
	return -1;
}
