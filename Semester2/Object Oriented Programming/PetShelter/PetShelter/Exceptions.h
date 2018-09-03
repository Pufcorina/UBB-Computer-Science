#pragma once
#include <string>
using namespace std;


class ValidationException {
private:
	string msg;
public:
	ValidationException(const string& msg) :msg{ msg } {}
	const string& getMsg() const;
};

class RepoException {
private:
	string msg;
public:
	RepoException(const string& msg) :msg{ msg } {}
	const string& getMsg() const;
};
