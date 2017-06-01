#include "Validator.h"



Validator::Validator()
{
}


Validator::~Validator()
{
}

void Validator::validate_movie(const Movie& movie)
{
	std::vector<std::string> errors;
	if (movie.getTitle().size() < 1)
		errors.push_back("The title name cannot be less than 1 characters!\n");
	if (!isupper(movie.getGenre()[0]))
		errors.push_back(std::string("The name of the genre must start with a capital letter!\n"));
	if (movie.getYear() < 1889 || movie.getYear() > 2100)
		errors.push_back(std::string("Year has to be an integer greater than 1889 and smaller than 2100!\n"));

	// search for "www" or "http" at the beginning of the source string
	int posWww = movie.getTrailer().find("www");
	int posHttp = movie.getTrailer().find("http");
	if (posWww != 0 && posHttp != 0)
		errors.push_back("The youtube source must start with one of the following strings: \"www\" or \"http\"\n");

	if (errors.size() > 0)
		throw MovieException(errors);
}


MovieException::MovieException(std::vector<std::string> _errors)
{
	this->errors = _errors;
}

std::vector<std::string> MovieException::getErrors() const
{
	return this->errors;
}