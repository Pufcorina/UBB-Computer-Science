#pragma once
#include "Movie.h"
#include "Validator.h"

class Repository
{
public:
	Repository();
	~Repository();

	virtual std::vector<Movie> getItems() = 0;
	virtual int getPosition(const std::string& name) = 0;
	virtual int getLength() = 0;
	
	virtual void add(const Movie& movie) = 0;
	virtual int del(const std::string& title) = 0;
	virtual int update(const Movie& movie) = 0;
	virtual void incLikes(std::string title) = 0;
	virtual void saveToFile() = 0;
};

