#pragma once
#include "WatchList.h"
class HTMLWatchList :
	public WatchList
{
public:
	HTMLWatchList();
	~HTMLWatchList();

	void saveToFile();
	void openInApp();
};

