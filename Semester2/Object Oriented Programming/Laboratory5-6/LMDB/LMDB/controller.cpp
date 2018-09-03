#include "controller.h"

void Controller::addMovieToRepository(const std::string title, const std::string genre, const int year, const int nbLikes, const std::string trailer)
{
	Movie m = Movie(title, genre, year, nbLikes, trailer);
	this->repo.addMovie(m);
}

void Controller::deleteMovieToRepository(const std::string title, const std::string genre, const int year)
{
	Movie m = Movie(title, genre, year, 0, "");
	this->repo.deleteMovie(m);
}

void Controller::updateMovieToRepository(const std::string title, const std::string genre, const int year, const int nbLikes, const std::string trailer)
{
	Movie m = Movie(title, genre, year, nbLikes, trailer);
	this->repo.updateMovie(m);
}

void Controller::addMovieToWatchList(const Movie mv)
{
	this->watchList.add(mv);
}

void Controller::deleteMovieFromWatchList(const std::string title, const std::string genre, const int year)
{
	Movie m = Movie(title, genre, year, 0, "");
	this->watchList.deleteMovie(m);
}

void Controller::startWatchList()
{
	this->watchList.play();
}

void Controller::nextMovieWatchList()
{
	this->watchList.next();
}

WatchList Controller::filterMoviesByGenre(const std::string genre)
{
	if (genre == "")
		return this->watchList;
	WatchList l;
	Movie mv;
	for (int i = 0; i < this->repo.getMovies().getSize(); i++)
	{
		mv = this->repo.getMovies().getElement(i);
		if (mv.getGenre() == genre)
			l.add(mv);
	}
	return l;
}

void Controller::likeMovie(Movie mv)
{
	Movie mu = Movie(mv.getTitle(), mv.getGenre(), mv.getYear(), mv.getLikes() + 1, mv.getTrailer());
	this->repo.updateMovie(mu);
}
