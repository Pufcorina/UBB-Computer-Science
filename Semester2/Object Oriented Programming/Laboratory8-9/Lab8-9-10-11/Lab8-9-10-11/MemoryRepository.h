#pragma once
#include "Repository.h"

class MemoryRepository :
	public Repository
{
protected:
	std::vector<Movie> items;
	Validator validator_class;

public:
	MemoryRepository();
	~MemoryRepository();

	MemoryRepository(const MemoryRepository& other) { this->items = other.items; }
	MemoryRepository& operator=(const MemoryRepository& other);

	std::vector<Movie> getItems() { return this->items; }
	int getLength() { return this->items.size(); }

	void add(const Movie& mov);
	int del(const std::string& name);
	int update(const Movie& mov);
	int getPosition(const std::string& name);
	void incLikes(std::string title) { this->items[getPosition(title)].incLikes(); }
	void saveToFile();
};

