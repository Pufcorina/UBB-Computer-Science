#include "Movie.h"

Movie::Movie()
{
	this->title = "";
	this->genre = "";
	this->year = 2017;
	this->likes = 0;
	this->trailer = "www";
}

Movie::Movie(const std::string& title, const std::string& genre, const int& year, const int& likes, const std::string& trailer)
{
	this->title = title;
	this->genre = genre;
	this->year = year;
	this->likes = likes;
	this->trailer = trailer;
}

Movie::Movie(const Movie& movie)
{
	this->title = movie.getTitle();
	this->genre = movie.getGenre();
	this->year = movie.getYear();
	this->likes = movie.getLikes();
	this->trailer = movie.getTrailer();
}


Movie::~Movie()
{
}

void Movie::incLikes()
{
	this->likes++;
}

std::ostream& operator<<(std::ostream& os, const Movie& movie)
{
	os << movie.getTitle() << "," << movie.getGenre() << "," << movie.getYear() << "," << movie.getLikes() << "," << movie.getTrailer();
	return os;
}
