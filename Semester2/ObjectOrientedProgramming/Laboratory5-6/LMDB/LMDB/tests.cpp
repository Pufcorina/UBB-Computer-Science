#include "tests.h"
#include <assert.h>
#include "repo.h"
#include "DynamicVector.h"
#include "watchList.h"
#include "controller.h"

void Tests::testAll()
{
	testMovie();
	testDynamicVector();
	testRepository();
	testController();
	testWatchList();
}

void Tests::testMovie()
{
	Movie m{ "The Other Boleyn Girl", "Drama", 2008, 87802, "https://www.youtube.com/watch?v=NX0LoorqtRM" };
	assert(m.getGenre() == "Drama");
	assert(m.getLikes() == 87802);
	assert(m.getTitle() == "The Other Boleyn Girl");
	assert(m.getTrailer() == "https://www.youtube.com/watch?v=NX0LoorqtRM");
	assert(m.getYear() == 2008);
}

void Tests::testDynamicVector()
{
	DynamicVector<int> v1{ 2 };
	v1.addItem(10);
	v1.addItem(20);
	assert(v1.getSize() == 2);
	assert(v1.getElement(1) == 20);
	v1.addItem(30);
	assert(v1.getSize() == 3);
	DynamicVector<int> v2 = v1;
	assert(v2.getSize() == 3);

	DynamicVector<int> v3 = v1;
	assert(v3.getElement(0) == 10);

	v3.deleteItem(1);
	assert(v3.getElement(1) == 30);

	v2.updateItem(2, 13);
	assert(v2.getElement(2) == 13);
}

void Tests::testRepository()
{
	Repository repo = Repository();
	Movie m1 = Movie("The Other Boleyn Girl", "Drama", 2008, 87802, "https://www.youtube.com/watch?v=NX0LoorqtRM");
	repo.addMovie(m1);
	int res = repo.findMovie("The Other Boleyn Girl", "Drama", 2008);
	assert(res != -1);

	m1 = Movie("The Other Boleyn Girl", "Drama", 2008, 878, "https://www.youtube.com/watch?v=NX0LoorqtRM");

	repo.updateMovie(m1);

	DynamicVector<Movie> movies = repo.getMovies();
	assert(movies.getSize() == 1);

	assert(movies.getElement(0).getLikes() == 878);

	repo.deleteMovie(m1);

	DynamicVector<Movie> new_movies = repo.getMovies();
	assert(new_movies.getSize() == 0);
}

void Tests::testController()
{}

void Tests::testWatchList()
{}