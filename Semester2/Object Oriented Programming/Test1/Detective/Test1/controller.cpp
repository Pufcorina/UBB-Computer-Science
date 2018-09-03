#include "controller.h"

void Controller::addTElemToRepository(std::string text, bool inv, bool truth)
{
	TElem e(text, inv, truth);
	this->repo.addEntity(e);
}

void Controller::deleteTElemToRepository()
{
	std::vector<TElem> v;
	for (int i = 0; i < this->repo.getElements().size(); i++)
	{
		if (this->repo.getElements()[i].getTruth() == false)
			v.push_back(this->repo.getElements()[i]);
	}
	for(int i = 0; i < v.size(); i++)
		this->repo.deleteEntity(v[i]);
}

void Controller::updateTElemToRepository(std::string text, bool inv, bool truth)
{
	TElem e(text, inv, truth);
	this->repo.updateEntity(e);
}

bool Controller::checkInvestigated(std::string text)
{
	TElem e(text, false, false);
	int pos = getRepo().findTElem(e);

	if (pos == -1)
		std::cout << "No such Clue\n";
	return this->repo.getElements()[pos].getInvestigation();
}