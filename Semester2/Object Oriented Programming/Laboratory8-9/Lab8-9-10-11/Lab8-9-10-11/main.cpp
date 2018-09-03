#include "Console.h"
#include "CSVWatchList.h"
#include "HTMLWatchList.h"
#include "FileRepository.h"
#include "Tests.h"
#include <Windows.h>
#include <conio.h>

int main()
{
	/*Tests t;
	t.TestAll();*/

	std::string type = "";
	while (1)
	{
		system("cls");
		std::cout << "\n	For exit type: exit\n\n";
		std::cout << "	Choose file type (csv or html): ";
		std::getline(std::cin, type);
		if (type == "exit")
		{
			std::cout << "\n\n	Bye!!! :)\n";
			Sleep(2000);
			break;
		}
		if (type == "csv" || type == "html")
		{
			MemoryRepository* admin_repo = new FileRepository{ "Resources/movie_database.csv" };

			WatchList* user_repo;
			if (type == "csv")
				user_repo = new CSVWatchList();
			else
				user_repo = new HTMLWatchList();

			Controller* admin_ctrl = new Controller{ admin_repo };
			Console* console = new Console(*admin_ctrl, user_repo);

			console->runApplication();

			delete console;
			delete admin_ctrl;
			delete user_repo;
			delete admin_repo;
		}
	}
	Sleep(2000);
}