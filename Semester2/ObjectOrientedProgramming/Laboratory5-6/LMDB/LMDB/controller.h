#pragma once
#include "repo.h"
#include "watchList.h"


class Controller
{
private:
	Repository repo;
	WatchList watchList;

public:
	Controller(const Repository r) : repo(r) {}

	Repository getRepo() const { return repo; }

	void addMovieToRepository(const std::string title, const std::string genre, const int year, const int nbLikes, const std::string trailer);
	void deleteMovieToRepository(const std::string title, const std::string genre, const int year);
	void updateMovieToRepository(const std::string title, const std::string genre, const int year, const int nbLikes, const std::string trailer);
	DynamicVector<Movie> getAllMovies() const { return this->repo.getMovies(); }

	WatchList getWatchList() const { return watchList;  }
	void addMovieToWatchList(const Movie mv);
	void deleteMovieFromWatchList(const std::string title, const std::string genre, const int year);

	void startWatchList();
	void nextMovieWatchList();
	WatchList filterMoviesByGenre(const std::string genre);
	void likeMovie(Movie mv);
};