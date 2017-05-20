#pragma once
#include "repo.h"

class Controller
{
private:
	Repository repo;

public:
	Controller(const Repository r) : repo(r) {}

	Repository getRepo() const { return repo; }


	// Check if the TElem already exists, otherwise insert in the container
	// Input: text - clue text
	//		  inv - investigation status
	//		  truth - truth value
	// Output: NONE
	void addTElemToRepository(std::string text, bool inv, bool truth);

	// Remove all the elements from the container which have false as truth value
	// Input: NONE
	// Output: NONE
	void deleteTElemToRepository();

	void updateTElemToRepository(std::string text, bool inv, bool truth);
	
	std::vector<TElem> getAllElements() const { return this->repo.getElements(); }
	
	bool checkInvestigated(std::string text);
};