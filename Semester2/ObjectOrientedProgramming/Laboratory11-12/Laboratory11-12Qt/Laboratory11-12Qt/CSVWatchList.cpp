#include "CSVWatchList.h"



CSVWatchList::CSVWatchList()
{
}

CSVWatchList::CSVWatchList(std::string type) : WatchList(type)
{
}

CSVWatchList::~CSVWatchList()
{
}

void CSVWatchList::saveToFile()
{
	int j = 1;
	std::ofstream f("Resources/watchlist.csv");
	for (auto i : this->movieList)
	{
		f << j << ". " << i << "\n";
		j++;
	}
	f.close();
}

void CSVWatchList::openInApp()
{
	system("notepad.exe Resources/watchlist.csv");
}
