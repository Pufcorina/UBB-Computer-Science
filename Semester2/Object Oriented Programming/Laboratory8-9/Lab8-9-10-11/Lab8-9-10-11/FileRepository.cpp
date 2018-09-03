#include "FileRepository.h"
#include "util.h"
#include "RepositoryException.h"



FileRepository::FileRepository()
{
}

FileRepository::FileRepository(const FileRepository &other) : MemoryRepository(other)
{
	this->filename = other.filename;
	loadFromFile();
}

FileRepository::~FileRepository()
{
}


void FileRepository::loadFromFile()
{
	std::ifstream f(this->filename);

	if (!f.is_open())
		throw FileException("	The file could not be opened!\n");

	std::string temp;
	std::string* args;

	while (!f.eof())
	{
		std::getline(f, temp);
		if (f.eof() || temp == "")
		{
			f.close();
			break;
		}

		args = splitString(temp);
		add(Movie{ args[0], args[1], stoi(args[2]), stoi(args[3]), args[4] });
	}
	f.close();
}

void FileRepository::saveToFile()
{
	std::ofstream f(this->filename);
	for (auto i : this->items)
		f << i << "\n";
	f.close();
}

FileRepository& FileRepository::operator=(const FileRepository& other)
{
	if (this == &other)
		return *this;
	MemoryRepository::operator=(other);
	this->filename = other.filename;

	return *this;
}