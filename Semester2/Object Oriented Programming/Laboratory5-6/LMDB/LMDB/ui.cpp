#include "ui.h"
#include <string>
#include <iostream>
#include <windows.h>

void UI::runApplication()
{
	while (true)
	{
		printMenuApplication();
		std::string command;
		std::getline(std::cin, command);
		if (command == "exit")
		{
			std::cout << "Bye!!! :)\n";
			break;
		}

		if (command == "admin")
		{
			system("cls");
			std::cout << "Now you are in Admin mode\n";
			runAdminMode();
			std::cout << "You are no more in Admin mode! ^_^\n";
			Sleep(2000);
			system("cls");
			continue;
		}
		if (command == "user")
		{
			{
				system("cls");
				std::cout << "Now you are in User mode\n";
				runUserMode();
				std::cout << "You are no more in User mode! ^_^\n";
				Sleep(2000);
				system("cls");
				continue;
			}
		}
		std::cout << "Invalid mode!\n";
	}
}

void UI::runAdminMode()
{
	while (true)
	{
		printMenuAdmin();
		std::string command;
		std::getline(std::cin, command);
		if (command == "exit")
			break;
		if (command == "add")
		{
			addMovieToController();
			std::cout << '\n';
			continue;
		}
		if (command == "delete")
		{
			deleteMovieToController();
			std::cout << '\n';
			continue;
		}
		if (command == "update")
		{
			updateMovieToController();
			std::cout << '\n';
			continue;
		}
		if (command == "list")
		{
			listAllMovies();
			std::cout << '\n';
			continue;
		}
		std::cout << "Invalid command!\n\n";
	}
}

void UI::runUserMode()
{
	while (true)
	{
		printMenuUser();
		std::string command;
		std::getline(std::cin, command);
		if (command == "exit")
			break;
		if (command == "watch")
		{
			watchStart();
			std::cout << '\n';
			continue;
		}
		if (command == "show")
		{
			printWatchList(this->ctrl.getWatchList());
			std::cout << '\n';
			continue;
		}
		if (command == "delete")
		{
			userDelete();
			std::cout << '\n';
			continue;
		}
		std::cout << "Invalid command!\n\n";
	}
}

void UI::userDelete()
{
	std::string title, genre, year, nbLikes, trailer, like;
	std::cout << "Insert title: ";
	std::getline(std::cin, title);
	std::cout << "Insert genre: ";
	std::getline(std::cin, genre);
	std::cout << "Insert year: ";
	std::getline(std::cin, year);
	std::cout << "Do you like the movie? yes/no: ";
	std::getline(std::cin, like);

	if (like == "yes")
	{
		Movie m = this->ctrl.getWatchList().findMovieByInformation(title, genre, stoi(year));
		this->ctrl.likeMovie(m);
	}
	this->ctrl.deleteMovieFromWatchList(title, genre, stoi(year));
}

void UI::watchStart()
{
	std::cout << "Please insert the genre: ";
	std::string genre;
	std::getline(std::cin, genre);
	WatchList ls = this->ctrl.filterMoviesByGenre(genre);
	Movie m;
	std::string command;
	while(!ls.isEmpty())
	{ 
		printWatchMenu();
		ls.play();
		m = ls.getCurrentMovie();
		std::cout << "\nMovie with title: " << m.getTitle() << " genre: " << m.getGenre() << " from year: " << m.getYear() << " which has: " << m.getLikes() << " likes and has the trailer: " << m.getTrailer() << "\n\n";
		std::getline(std::cin, command);
		if (command == "exit")
			break;
		if (command == "add")
		{
			this->ctrl.addMovieToWatchList(m);
			ls.deleteMovie(m);
		}
		if (command == "next")
			ls.next();
	}
	if (ls.isEmpty())
		std::cout << "There are no movies!\n";
}

void UI::printWatchMenu()
{
	std::cout << "You can: \n";
	std::cout << "next - go to the next element\n";
	std::cout << "add - add to the WatchList\n";
	std::cout << "exit\n";
}

void UI::printWatchList(WatchList ls)
{
	if (ls.isEmpty())
	{
		std::cout << "No movie in your WatchList!\n";
		return;
	}
	Movie m;
	int cnt = 0;
	while(!ls.isEmpty())
	{
		m = ls.getCurrentMovie();
		std::cout << "Movie with title: " << m.getTitle() << " genre: " << m.getGenre() << " from year: " << m.getYear() << " which has: " << m.getLikes() << " likes and has the trailer: " << m.getTrailer() << "\n";
		cnt++;
		if (cnt == ls.getSize())
			break;
		ls.next();
	}
}

void UI::printMenuApplication()
{
	std::cout << "Please select your mode from: admin / user\n";
	std::cout << "You are: ";
}

void UI::printMenuAdmin()
{
	std::cout << "add - Add a movie\n";
	std::cout << "delete - Delete a movie\n";
	std::cout << "update - Update a movie\n";
	std::cout << "list - List all movies\n\n";
}

void UI::printMenuUser()
{
	std::cout << "watch - See the movies by genre\n";
	std::cout << "delete - Delete Movie from the WatchList by title\n";
	std::cout << "show - See the WatchList\n\n";
}

void UI::addMovieToController()
{
	std::string title, genre, year, nbLikes, trailer;
	std::cout << "Insert title: ";
	std::getline(std::cin, title);
	std::cout << "Insert genre: ";
	std::getline(std::cin, genre);
	std::cout << "Insert year: ";
	std::getline(std::cin, year);
	std::cout << "Insert number of likes: ";
	std::getline(std::cin, nbLikes);
	std::cout << "Insert trailer link: ";
	std::getline(std::cin, trailer);

	this->ctrl.addMovieToRepository(title, genre, stoi(year), stoi(nbLikes), trailer);
}

void UI::deleteMovieToController()
{
	std::string title, genre, year, nbLikes, trailer;
	std::cout << "Insert title: ";
	std::getline(std::cin, title);
	std::cout << "Insert genre: ";
	std::getline(std::cin, genre);
	std::cout << "Insert year: ";
	std::getline(std::cin, year);

	this->ctrl.deleteMovieToRepository(title, genre, stoi(year));
}

void UI::updateMovieToController()
{
	std::string title, genre, year, nbLikes, trailer;
	std::cout << "Insert title: ";
	std::getline(std::cin, title);
	std::cout << "Insert genre: ";
	std::getline(std::cin, genre);
	std::cout << "Insert year: ";
	std::getline(std::cin, year);
	std::cout << "Insert number of likes: ";
	std::getline(std::cin, nbLikes);
	std::cout << "Insert trailer link: ";
	std::getline(std::cin, trailer);

	this->ctrl.updateMovieToRepository(title, genre, stoi(year), stoi(nbLikes), trailer);
}

void UI::listAllMovies()
{
	DynamicVector<Movie> ls = this->ctrl.getAllMovies();
	Movie m;
	for (int i = 0; i < ls.getSize(); i++)
	{
		m = ls.getElement(i);
		std::cout << "Movie with title: " << m.getTitle() << " genre: " << m.getGenre() << " from year: " << m.getYear() << " which has: " << m.getLikes() << " likes and has the trailer: " << m.getTrailer() << "\n";
	}
}