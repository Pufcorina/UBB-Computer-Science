#pragma once
#include <vector>
#include <algorithm>
#include <iostream>
#include <string>
#include "Exceptions.h"
using namespace std;

template <class T>
class Repo
{
private:
	vector<T> elems;
public:
	void add(T el);
	void rem(T el);
	void upd(T el);
	int size();
	vector<T> getAll();
};

template<class T>
void Repo<T>::add(T el)
{
	auto it = find_if(elems.begin(), elems.end(), [&](const T& crt) {return  crt == el; });
	if (it != elems.end())
		throw RepoException{ "iezicsta deja!\n" };
	elems.push_back(el);
}

template<class T>
void Repo<T>::rem(T el)
{
	auto it = std::find_if(elems.begin(), elems.end(), [&](const T& crt) {return  crt == el; });
	if (it == elems.end())
		throw RepoException{ "iezicsta deja!\n" };
	elems.erase(it);
}

template<class T>
void Repo<T>::upd(T el)
{
	auto it = find_if(elems.begin(), elems.end(), [&](const T& crt) {return  crt == el; });
	if (it == elems.end())
		throw RepoException{ "iezicsta deja!\n" };
	elems[it - elems.begin()] = el;
}

template<class T>
int Repo<T>::size()
{
	return elems.size();
}

template<class T>
vector<T> Repo<T>::getAll() {
	return elems;
}
