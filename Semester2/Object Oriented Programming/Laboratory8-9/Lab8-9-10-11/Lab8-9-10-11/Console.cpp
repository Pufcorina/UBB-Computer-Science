#include "Console.h"
#include <Windows.h>
#include <conio.h>
#include "RepositoryException.h"

Console::Console()
{
}

Console::~Console()
{
}

Console::Console(Controller ctrl, WatchList* watchlist)
{
	this->ctrl = ctrl;
	this->watchlist = watchlist;
}

void Console::runApplication()
{

	this->ctrl.saveToFile();
	this->watchlist->saveToFile();
	while (true)
	{
		printMenuApplication();
		std::string command;
		std::getline(std::cin, command);
		if (command == "exit")
			break;

		if (command == "admin")
		{
			system("cls");
			std::cout << "\n	Now you are in Admin mode\n";
			Sleep(2000);
			runAdminMode();
			system("cls");
			std::cout << "\n	You are no more in Admin mode! ^_^\n";
			Sleep(2000);
			continue;
		}
		if (command == "user")
		{
			{
				system("cls");
				std::cout << "\n	Now you are in User mode\n";
				Sleep(2000);
				runUserMode();
				system("cls");
				std::cout << "\n	You are no more in User mode! ^_^\n";
				Sleep(2000);
				continue;
			}
		}
		std::cout << "	Invalid mode!\n";
		Sleep(2000);
	}
	this->ctrl.saveToFile();
	this->watchlist->saveToFile();
}

void Console::runAdminMode()
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
			consoleAdd();
			Sleep(2000);
			continue;
		}
		if (command == "delete")
		{
			consoleDelete();
			Sleep(2000);
			continue;
		}
		if (command == "update")
		{
			consoleUpdate();
			Sleep(2000);
			continue;
		}
		if (command == "list")
		{
			consoleList();

			Sleep(10000);
			continue;
		}
		std::cout << "	Invalid command!\n\n";
		Sleep(2000);
	}
}

void Console::runUserMode()
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
			consoleUserWatch();
			continue;
		}
		if (command == "show")
		{
			consoleUserShow();
			continue;
		}
		if (command == "delete")
		{
			consoleUserDelete();
			continue;
		}

		if (command == "save")
		{
			consoleUserSave();
			continue;
		}
		if (command == "open")
		{
			consoleUserOpen();
			continue;
		}
		std::cout << "	Invalid command!\n\n";
		Sleep(2000);
	}
}

void Console::printMenuApplication()
{
	system("cls");
	std::cout << "\n	Please select your mode from: admin / user\n";
	std::cout << "	For exit type: exit\n\n";
	std::cout << "		You are: ";
}

void Console::printMenuAdmin()
{
	system("cls");
	std::cout << "\n	add - Add a movie\n";
	std::cout << "	delete - Delete a movie\n";
	std::cout << "	update - Update a movie\n";
	std::cout << "	list - List all movies\n";
	std::cout << "	For exit type: exit\n\n";

	std::cout << "		Your command: ";
}

void Console::printMenuUser()
{
	system("cls");
	std::cout << "\n	watch - See the movies by genre\n";
	std::cout << "	delete - Delete Movie from the WatchList by title\n";
	std::cout << "	show - See the WatchList\n";
	std::cout << "	open - Display WatchList\n";
	std::cout << "	For exit type: exit\n\n";
	std::cout << "		Your command: ";
}

void Console::printWatchMenu(const Movie& m)
{
	system("cls");

	std::cout << "\n	Movie with title: " << m.getTitle() << "\n";
	std::cout << "\n	You can: \n";
	std::cout << "		next - go to the next element\n";
	std::cout << "		add - add to the WatchList\n";
	std::cout << "		For exit type: exit\n\n";
	std::cout << "			Your command: ";
}

void Console::consoleAdd()
{
	std::string title, genre, year, nbLikes, trailer;
	std::cout << "	Insert title: ";
	std::getline(std::cin, title);
	std::cout << "	Insert genre: ";
	std::getline(std::cin, genre);
	std::cout << "	Insert year: ";
	std::getline(std::cin, year);
	std::cout << "	Insert number of likes: ";
	std::getline(std::cin, nbLikes);
	std::cout << "	Insert trailer link: ";
	std::getline(std::cin, trailer);

	try
	{
		this->ctrl.add(title, genre, stoi(year), stoi(nbLikes), trailer);
	}
	catch (MovieException& e)
	{
		for (auto s : e.getErrors())
			std::cout << s;
	}
	catch (RepositoryException& e)
	{
		std::cout << e.what() << '\n';
	}
	catch (FileException& e)
	{
		std::cout << e.what() << '\n';
	}
	catch (std::invalid_argument e)
	{
		std::cout << "Insert numbers\n";
	}
}

void Console::consoleDelete()
{
	std::string title;
	std::cout << "	Insert title: ";
	std::getline(std::cin, title);

	try
	{
		this->ctrl.del(title);
	}
	catch (MovieException& e)
	{
		for (auto s : e.getErrors())
			std::cout << s;
	}
	catch (RepositoryException& e)
	{
		std::cout << e.what() << '\n';
	}
	catch (DuplicateMovieException& e)
	{
		std::cout << e.what() << '\n';
	}
	catch (FileException& e)
	{
		std::cout << e.what() << '\n';
	}
	catch (std::invalid_argument e)
	{
		std::cout << "Insert numbers\n";
	}
}

void Console::consoleUpdate()
{
	std::string title, genre, year, nbLikes, trailer;
	std::cout << "	Insert title: ";
	std::getline(std::cin, title);
	std::cout << "	Insert genre: ";
	std::getline(std::cin, genre);
	std::cout << "	Insert year: ";
	std::getline(std::cin, year);
	std::cout << "	Insert number of likes: ";
	std::getline(std::cin, nbLikes);
	std::cout << "	Insert trailer link: ";
	std::getline(std::cin, trailer);

	try
	{
		this->ctrl.update(title, genre, stoi(year), stoi(nbLikes), trailer);
	}
	catch (MovieException& e)
	{
		for (auto s : e.getErrors())
			std::cout << s;
	}
	catch (RepositoryException& e)
	{
		std::cout << e.what() << '\n';
	}
	catch (FileException& e)
	{
		std::cout << e.what() << '\n';
	}
	catch (std::invalid_argument e)
	{
		std::cout << "Insert numbers\n";
	}
}

void Console::consoleList()
{
	for (auto i : this->ctrl.getItems())
		std::cout << "  Movie with title: " << i.getTitle() << " genre: " << i.getGenre() << " from year: " << i.getYear() << " which has: " << i.getLikes() << " likes and has the trailer: " << i.getTrailer() << "\n";
}


void Console::consoleUserWatch()
{
	std::cout << "	Please insert the genre (type \"all\" for all sugestions): ";
	std::string genre;
	std::getline(std::cin, genre);

	std::vector<Movie> suggestions;

	this->watchlist->getSuggestions(this->ctrl.getItems(), genre);

	if (this->watchlist->getMaximumPos() == 0)
		std::cout << "	No suggestions for this genre, sorry!\n";
	else
		while (1)
		{
			if (this->watchlist->getMaximumPos() == 0)
				break;
			Movie m = this->watchlist->getCurrentMovie();
			printWatchMenu(m);

			std::string command;
			std::getline(std::cin, command);

			if (command == "exit")
				break;

			if (command == "add")
			{
				this->watchlist->add();
				continue;
			}

			if (command == "next")
			{
				this->watchlist->nextPos();
				continue;
			}

			std::cout << "	Invalid command!\n\n";
			Sleep(2000);

		}

	if (suggestions.size() == 0)
	{
		std::cout << "	That's all for now!\n";
		Sleep(3000);
	}
}

void Console::consoleUserShow()
{
	if (this->watchlist->getList().size() == 0)
		std::cout << "\n	No movie in your WatchList!\n";
	else
		for (auto m : this->watchlist->getList())
			std::cout << "Movie with title: " << m.getTitle() << " genre: " << m.getGenre() << " from year: " << m.getYear() << " which has: " << m.getLikes() << " likes and has the trailer: " << m.getTrailer() << "\n";
	Sleep(5000);
}

void Console::consoleUserDelete()
{
	std::cout << "Input title: ";
	std::string title;
	std::getline(std::cin, title);
	if (this->watchlist->del(title) == 0)
	{
		std::cout << "	Inexistent movie!\n";
		Sleep(2000);
	}
	else
	{
		std::cout << "	Like the movie? (yes / no): ";
		std::string liked;

		do
		{
			std::getline(std::cin, liked);
			if (liked != "yes" && liked != "no")
				std::cout << "	Invalid option\n";
		} while (liked != "yes" && liked != "no");

		if (liked == "yes")
			this->ctrl.incLikes(title);
	}
}

void Console::consoleUserSave()
{
	this->watchlist->saveToFile();
}

void Console::consoleUserOpen()
{
	this->watchlist->openInApp();
}