#include "Tests.h"
#include "Console.h"
#include "FileRepository.h"
#include "CSVWatchList.h"
#include "HTMLWatchList.h"
#include <assert.h>
#include "RepositoryException.h"

Tests::Tests()
{
}


Tests::~Tests()
{
}

void Tests::TestAll()
{
	try
	{

		testFileRepository();
	}
	catch (FileException& e)
	{
		std::cout << e.what() << '\n';
	}

	testCSVWatchList();

	testHTMLWatchList();
	
	try
	{
		testController();
	}
	catch (RepositoryException e)
	{
		std::cout << e.what() << "\n";
	}

	try
	{
		testMemoryRepository();
	}
	catch (RepositoryException e)
	{
		std::cout << e.what() << "\n";
	}
	testMovie();
	testRepository();
	testRepositoryException();
	testUtil();
	testValidator();
	testWatchList();
	
}


void Tests::testController()
{
	Controller c = Controller();
	MemoryRepository* admin_repo = new FileRepository( "movie.csv" );
	MemoryRepository a;
	MemoryRepository b;
	b = a;
	a.saveToFile();

	FileRepository admin_repo1 = FileRepository("movie.csv");
	FileRepository admin_repo2;
	admin_repo2 = admin_repo1;
	admin_repo1 = admin_repo1;

	admin_repo->saveToFile();


	Controller admin_ctrl = Controller(admin_repo);

	admin_ctrl.add("The Other Boleyn Girls", "Drama", 2008, 878, "https://www.youtube.com/watch?v=NX0LoorqtRM");
	int res = admin_ctrl.update("The Other Boleyn Girls", "Drama", 2008, 87, "https://www.youtube.com/watch?v=NX0LoorqtRM");
	admin_ctrl.incLikes("The Other Boleyn Girls");
	res = admin_ctrl.getLength();
	res = admin_ctrl.getPosition("The Other Boleyn Girls");
	admin_ctrl.saveToFile();
	std::vector<Movie> it = admin_ctrl.getItems();
	res = admin_ctrl.del("The Other Boleyn Girls");
	admin_ctrl.saveToFile();
	throw RepositoryException();
}

void Tests::testCSVWatchList()
{

}

void Tests::testFileRepository()
{
	FileRepository r;
	FileRepository r2 = r;
}

void Tests::testHTMLWatchList()
{
	WatchList* w = new HTMLWatchList();
	std::vector<Movie> a = w->getList();
	int r = w->getMaximumPos();
	w->nextPos();
	int pos = w->getPosition("aaa");
	Repository* repo = new FileRepository();

	Movie m1 = Movie("The Other Boleyn Girl", "Drama", 2008, 87802, "https://www.youtube.com/watch?v=NX0LoorqtRM");
	repo->add(m1);
	Movie m2 = Movie("The Other Boleyn Girls", "Drama", 2008, 87802, "https://www.youtube.com/watch?v=NX0LoorqtRM");
	repo->add(m2);
	w->getSuggestions(repo->getItems(), "");
	w->getSuggestions(repo->getItems(), "Drama");
	Movie m = w->getCurrentMovie();
	w->nextPos();
	m = w->getCurrentMovie();

	w->getSuggestions(repo->getItems(), "Drama");
	w->add();
	w->add();
	w->getPosition("The Other Boleyn Girls");
	w->del("a");

	w->del("The Other Boleyn Girl");

}

void Tests::testMemoryRepository()
{
	Repository* repo = new FileRepository();

	Movie m1 = Movie("The Other Boleyn Girl", "Drama", 2008, 87802, "https://www.youtube.com/watch?v=NX0LoorqtRM");
	repo->add(m1);
	int res = repo->getPosition("The Other Boleyn Girl");
	assert(res != -1);
	res = repo->getPosition("The Other Boleyn Girsl");
	m1 = Movie("The Other Boleyn Girl", "Drama", 2008, 878, "https://www.youtube.com/watch?v=NX0LoorqtRM");

	repo->update(m1);
	
	try
	{
		Movie m2 = Movie("The Other Boleyn Girl", "Drama", 2008, 87802, "https://www.youtube.com/watch?v=NX0LoorqtRM");
		repo->add(m2);
	}
	catch (DuplicateMovieException e)
	{
		std::cout << e.what() << '\n';
	}


	Movie m3 = Movie("The Other Boleyn Girls", "Drama", 2008, 87802, "https://www.youtube.com/watch?v=NX0LoorqtRM");
	repo->update(m3);

	std::vector<Movie> movies = repo->getItems();
	assert(movies.size() == 1);

	assert(movies[0].getLikes() == 878);

	repo->del("The Other Boleyn Girl");
	repo->del("The Other Boleyn Girls");

	std::vector<Movie> new_movies = repo->getItems();
	assert(new_movies.size() == 0);

	try
	{
		Movie m01 = Movie("", "Drama", 2008, 87802, "https://www.youtube.com/watch?v=NX0LoorqtRM");
		repo->add(m01);
	}
	catch (MovieException& e)
	{
		for (auto s : e.getErrors())
			std::cout << s;
	}

	try
	{
		Movie m02 = Movie("The Other Boleyn Girl", "drama", 2008, 87802, "https://www.youtube.com/watch?v=NX0LoorqtRM");
		repo->add(m02);
	}
	catch (MovieException& e)
	{
		for (auto s : e.getErrors())
			std::cout << s;
	}

	try
	{
		Movie m03 = Movie("The Other Boleyn Girl", "Drama", 20, 87802, "https://www.youtube.com/watch?v=NX0LoorqtRM");
		repo->add(m03);
	}
	catch (MovieException& e)
	{
		for (auto s : e.getErrors())
			std::cout << s;
	}

	try
	{
		Movie m04 = Movie("The Other Boleyn Girl", "Drama", 2008, 87802, "ahttps://www.youtube.com/watch?v=NX0LoorqtRM");
		repo->add(m04);
	}
	catch (MovieException& e)
	{
		for (auto s : e.getErrors())
			std::cout << s;
	}
	repo->del("The Other Boleyn Girl");
	throw RepositoryException("aaa");
}

void Tests::testMovie()
{
	Movie m1 = Movie();
	Movie m{ "The Other Boleyn Girl", "Drama", 2008, 87802, "https://www.youtube.com/watch?v=NX0LoorqtRM" };
	assert(m.getGenre() == "Drama");
	assert(m.getLikes() == 87802);
	assert(m.getTitle() == "The Other Boleyn Girl");
	assert(m.getTrailer() == "https://www.youtube.com/watch?v=NX0LoorqtRM");
	assert(m.getYear() == 2008);
	Movie m3 = Movie(m);
	m.incLikes();
	assert(m.getLikes() == 87803);
}

void Tests::testRepository()
{}

void Tests::testRepositoryException()
{}

void Tests::testUtil()
{}

void Tests::testValidator()
{}

void Tests::testWatchList()
{}