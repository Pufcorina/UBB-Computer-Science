#pragma once
#include "WatchList.h"
class CSVWatchList :
	public WatchList
{
public:
	CSVWatchList();
	CSVWatchList(std::string type);
	~CSVWatchList();

	void saveToFile();
	void openInApp();
};

