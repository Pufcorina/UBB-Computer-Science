#define _CRTDBG_MAP_ALLOC
#include <crtdbg.h>
#include <iostream>
#include "ui.h"
#include "tests.h"


void Initialize(Repository& repo);

int main()
{
	{
		Tests::testAll();
		Repository repo{};
		Initialize(repo);
		Controller ctrl{ repo };
		UI ui{ ctrl };
		ui.runApplication();
		system("pause");
	}
	_CrtDumpMemoryLeaks();
	
}

void Initialize(Repository& repo)
{
	Movie m = Movie("The Shawshank Redemption", "Crime", 1994, 17796764, "https://www.youtube.com/watch?v=6hB3S9bIaco");
	repo.addMovie(m);
	m = Movie("Schindler's List", "Biography", 1993, 921634, "https://www.youtube.com/watch?v=M5FpB6qDGAE");
	repo.addMovie(m);
	m = Movie("Forrest Gump", "Comedy", 1994, 1345887, "https://www.youtube.com/watch?v=bLvqoHBptjg");
	repo.addMovie(m);
	m = Movie("American Sniper", "Action", 2014, 350230, "https://www.youtube.com/watch?v=99k3u9ay1gs");
	repo.addMovie(m);
	m = Movie("Beauty and the Beast", "Romance", 2017, 79791, "https://www.youtube.com/watch?v=e3Nl_TCQXuw");
	repo.addMovie(m);
	m = Movie("Sweeney Todd: The Demon Barber of Fleet Street", "Crime", 2007, 294745, "https://www.youtube.com/watch?v=_4R72KROZ20");
	repo.addMovie(m);
	m = Movie("The Silence of the Lambs", "Crime", 1991, 951770, "https://www.youtube.com/watch?v=lQKs169Sl0I");
	repo.addMovie(m);
	m = Movie("Minions", "Animation", 2015, 157889, "https://www.youtube.com/watch?v=SvKmSNxFHyQ");
	repo.addMovie(m);
	m = Movie("Corpse Bride", "Animation", 2005, 199731, "https://www.youtube.com/watch?v=AGACeWVdFqo");
	repo.addMovie(m);
	m = Movie("Frozen", "Animation", 2013, 448325, "https://www.youtube.com/watch?v=TbQm5doF_Uc");
	repo.addMovie(m);
}