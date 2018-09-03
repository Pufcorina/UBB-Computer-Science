#pragma once
#include "Movie.h"
#include <exception>
#include <vector>

class Validator
{
public:
	Validator();
	~Validator();

	void validate_movie(const Movie& movie);
};

class MovieException
{
private:
	std::vector<std::string> errors;

public:
	MovieException(std::vector<std::string> _errors);
	std::vector<std::string> getErrors() const;
};
