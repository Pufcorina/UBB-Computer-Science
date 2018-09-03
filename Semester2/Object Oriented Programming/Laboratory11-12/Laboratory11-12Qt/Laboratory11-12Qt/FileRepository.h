#pragma once
#include "MemoryRepository.h"

class FileRepository :
	public MemoryRepository
{
private:
	std::string filename;
	void loadFromFile();

public:
	FileRepository();
	FileRepository(const std::string& filename) : MemoryRepository() { this->filename = filename; loadFromFile(); }
	FileRepository(const FileRepository& other);
	~FileRepository();


	FileRepository& operator=(const FileRepository& other);
	void saveToFile() override;
};

