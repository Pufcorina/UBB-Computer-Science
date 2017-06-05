#include "HTMLWatchList.h"
#include <Windows.h>
#include <shellapi.h>

HTMLWatchList::HTMLWatchList()
{
}

HTMLWatchList::HTMLWatchList(std::string type) : WatchList(type)
{
}


HTMLWatchList::~HTMLWatchList()
{
}

void HTMLWatchList::saveToFile()
{
	std::ofstream f("Resources/watchlist.html");

	f << "<!DOCTYPE html>" << "\n";
	f << "<html>" << "\n";

	f << "<head>" << "\n";
	f << "<title>Movie Playlist</title>" << "\n";
	f << "</head>" << "\n";

	f << "<body>" << "\n";

	f << "<table border=\"1\">" << "\n";

	f << "<tr>" << "\n";
	f << "<td>Title</td>" << "\n";
	f << "<td>Genre</td>" << "\n";
	f << "<td>Year</td>" << "\n";
	f << "<td>Likes</td>" << "\n";
	f << "<td>Trailer</td>" << "\n";
	f << "</tr>" << "\n";

	for (auto i : this->movieList)
	{
		f << "<tr>" << "\n";

		f << "<td>" << i.getTitle() << "</td>" << "\n";
		f << "<td>" << i.getGenre() << "</td>" << "\n";
		f << "<td>" << i.getYear() << "</td>" << "\n";
		f << "<td>" << i.getLikes() << "</td>" << "\n";
		f << "<td><a href =" << i.getTrailer() << ">Link</a></td>";

		f << "</tr>" << "\n";
	}

	f << "</table>" << "\n";

	f << "</body>" << "\n";
	
	f << "</html>" << "\n";
	f.close();

}

void HTMLWatchList::openInApp()
{
	ShellExecuteA(NULL, NULL, "chrome.exe", "Resources/watchlist.html", NULL, SW_SHOWMAXIMIZED);
	system("PAUSE");
}