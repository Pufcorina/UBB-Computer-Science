#pragma once
#include "DynamicVector.h"
#include "movie.h"

class WatchList
{
private:
	DynamicVector<Movie> movies;
	int current;

public:
	WatchList();

	int getSize() const { return this->movies.getSize(); }

	void add(const Movie mv);
	void deleteMovie(const Movie mv);

	Movie getCurrentMovie();
	Movie findMovieByInformation(std::string title, std::string genre, int year);

	void play();

	void next();

	bool isEmpty();

private:
	int findMovie(const Movie mv);
};