#pragma once
#include <vector>
#include "entity.h"

typedef Clue TElem;

class Repository
{
private:
	std::vector<TElem> elements;
	
public:
	Repository() {}

	// Check if the TElem already exists, otherwise insert in the container
	// Input: e - TElem entity
	// Output: NONE
	void addEntity(const TElem e);

	// Check if the TElem exists and remove it from the container
	// Input: e - TElem entity
	// Output: NONE
	void deleteEntity(const TElem e);

	// Check if the TElem exists and update with the new Movie entity
	// Input: e - new Telem entity
	// Output: NONE
	void updateEntity(const TElem e);

	// Find a TElem in our container
	// Input: e - TElem entity
	// Output: 1 - find, 0 otherwise
	int findTElem(const TElem e);

	// Return TElem collection
	std::vector<TElem> getElements() const { return elements; }
};