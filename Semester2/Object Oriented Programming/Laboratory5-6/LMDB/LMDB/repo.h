#pragma once
#include "movie.h"
#include "DynamicVector.h"

class Repository
{
private:
	DynamicVector<Movie> movies;

public:
	Repository() {}

	// Check if the movie already exists, otherwise insert in the container
	// Input: mv - Movie entity
	// Output: NONE
	void addMovie(const Movie mv);

	// Check if the movie exists and remove it from the container
	// Input: mv - Movie entity
	// Output: NONE
	void deleteMovie(const Movie mv);

	// Check if the movie exists and update with the new Movie entity
	// Input: mv - new Movie entity
	// Output: NONE
	void updateMovie(const Movie mv);

	// Find a movie by titlem genre and year
	// Input: title - movie title
	//		  genre - movie genre
	//		  year - movie year
	// Output: 1 - find, 0 otherwise
	int findMovie(const std::string title, const std::string genre, const int year);

	// Return movie collection
	DynamicVector<Movie> getMovies() const { return movies;  }

};