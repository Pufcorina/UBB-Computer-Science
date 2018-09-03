#include "Exceptions.h"
using namespace std;


const string& ValidationException::getMsg() const {
	return this->msg;
}



const string & RepoException::getMsg() const
{
	return this->msg;
}
