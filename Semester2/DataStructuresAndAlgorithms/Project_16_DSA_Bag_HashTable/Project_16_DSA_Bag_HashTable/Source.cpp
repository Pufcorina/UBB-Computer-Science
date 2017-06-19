#include "Bag.h"
#include "Tests.h"
#include <crtdbg.h>
#include "Console.h"

int main()
{
	/*{
		Tests t;
		t.testAll();
	}
	std::cout <<  "Memory leaks: " << _CrtDumpMemoryLeaks() << "\n";*/

	try {
		Repository r{ "Resources/data.txt" };
		Controller ctrl{ r };
		Console cons{ ctrl };

		cons.runApplication();
	}
	catch (std::exception e)
	{
		std::cout << e.what() << "\n";
	}
	return 0;
}