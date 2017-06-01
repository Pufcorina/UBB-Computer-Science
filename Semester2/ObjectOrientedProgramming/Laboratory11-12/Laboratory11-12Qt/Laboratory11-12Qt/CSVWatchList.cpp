#include "CSVWatchList.h"



CSVWatchList::CSVWatchList()
{
}


CSVWatchList::~CSVWatchList()
{
}

void CSVWatchList::saveToFile()
{
	std::ofstream f("Resources/watchlist.csv");
	for (auto i : this->movieList)
		f << i << "\n";
	f.close();
}

void CSVWatchList::openInApp()
{
	system("notepad.exe Resources/watchlist.csv");
}
