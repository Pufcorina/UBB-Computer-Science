#pragma once
#include <iostream>
#include <fstream>
#include <string>

class Movie
{
private:
	std::string title;
	std::string genre;
	std::string trailer;
	int year;
	int likes;

public:
	Movie();

	// Movie constructor with parameters
	Movie(const std::string& title, const std::string& genre, const int& year, const int& likes, const std::string& trailer);
	
	//Movie copy constructor
	Movie(const Movie& movie);

	//Movie destructor
	~Movie();

	//Movie title getter
	//Input: None
	//Return: title - string
	std::string getTitle() const { return this->title; }

	//Movie genre getter
	//Input: None
	//Return: genre - string
	std::string getGenre() const { return this->genre; }

	//Movie trailer getter
	//Input: None
	//Return: trailer - string
	std::string getTrailer() const { return this->trailer; }

	//Movie year getter
	//Input: None
	//Return: year - int
	int getYear() const { return this->year; }

	//Movie likes getter
	//Input: None
	//Return: likes - int
	int getLikes() const { return this->likes; }

	// << operator overload
	// Input: None
	// Return: entity in CSV style for file output
	friend std::ostream& operator<<(std::ostream& os, const Movie& movie);

	// Function that increments the likes for the movie
	void incLikes();
};

