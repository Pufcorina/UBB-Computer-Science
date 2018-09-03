#pragma once
#include <iostream>

class Movie
{
private:
	std::string title;
	std::string genre;
	int year;
	int nbLikes;
	std::string trailer; // link

public:
	Movie();
	Movie(const std::string ti, const std::string ge, const int yr, const int nbL, const std::string tr);

	std::string getTitle() const { return title;  }
	std::string getGenre() const { return genre;  }
	int getYear() const { return year;  }
	int getLikes() const { return nbLikes;  }
	std::string getTrailer() const { return trailer;  }

	void playTrailer();
	bool operator==(Movie mv2);
};