#pragma once
#include "WatchList.h"
class HTMLWatchList :
	public WatchList
{
public:
	HTMLWatchList();
	HTMLWatchList(std::string type);
	~HTMLWatchList();

	void saveToFile();
	void openInApp();
};

