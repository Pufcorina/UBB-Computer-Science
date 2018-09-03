#include "movie.h"
#include <Windows.h>
#include <shellapi.h>

Movie::Movie() : title(""), genre(""), year(0), nbLikes(0), trailer("") {}

Movie::Movie(const std::string ti, const std::string ge, const int yr, const int nbL, const std::string tr)
{
	this->title = ti;
	this->genre = ge;
	this->year = yr;
	this->nbLikes = nbL;
	this->trailer = tr;
}

void Movie::playTrailer()
{
	ShellExecuteA(NULL, NULL, "chrome.exe", this->getTrailer().c_str(), NULL, SW_SHOWMAXIMIZED);
}

bool Movie::operator==(Movie mv2)
{
	if (this->genre == mv2.getGenre() && this->nbLikes == mv2.getLikes() && this->title == mv2.getTitle() && this->trailer == mv2.getTrailer() && this->year == mv2.getYear())
		return true;
	return false;
}


