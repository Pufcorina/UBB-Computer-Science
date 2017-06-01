#include "MemoryRepository.h"
#include "RepositoryException.h"

MemoryRepository::MemoryRepository()
{
	this->items = std::vector<Movie>();
	this->validator_class = Validator();
}


MemoryRepository::~MemoryRepository()
{
}

MemoryRepository& MemoryRepository::operator=(const MemoryRepository& other)
{
	if (this == &other) return *this;
	this->items = other.items;
	return *this;
}

void MemoryRepository::add(const Movie& movie)
{
	validator_class.validate_movie(movie);
	if (getPosition(movie.getTitle()) != -1)
		throw DuplicateMovieException();
	this->items.push_back(movie);
}

int MemoryRepository::del(const std::string& title)
{
	int pos = getPosition(title);
	if (pos == -1)
	{
		std::cout << "	No such movie\n";
		return 0;
	}
	this->items.erase(this->items.begin() + pos);
	return 1;
}

int MemoryRepository::update(const Movie& movie)
{
	int pos = getPosition(movie.getTitle());
	if (pos == -1)
	{
		std::cout << "	No such movie\n";
		return 0;
	}
	this->items[pos] = movie;
	return 1;
}

int MemoryRepository::getPosition(const std::string& title)
{
	int counter = 0;
	for (auto i : this->items)
	{
		if (i.getTitle() == title)
			return counter;
		counter++;
	}
	return -1;
}

void MemoryRepository::saveToFile()
{}