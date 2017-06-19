#pragma once
#include <iostream>

template <typename TElement>
class Bag
{
protected:
	int length;
	int m;
	TElement* T;
	int* next;
	int firstFree;

public:
	//Constructor
	Bag();

	//Copy constructor
	Bag(const Bag<TElement>& b);

	/*
		Initialise the bag, capacity = 50 (default)
			params:: None.
			return:: None.
			throws:: No exception.
	*/
	void init();

	//Destructor
	~Bag();

	//Assignment overload operator
	Bag<TElement>& operator=(const Bag<TElement>& b);

	/*
		Return the number of elements from Bag
			params:: None.
			return:: length - Bag length
			throws:: No exception.
	*/
	int size();


	/*
		Add the element e to the Bag, resize and rehash if the Bag is full
			params:: e - TElement
			return:: None.
			throws:: No exception.
	*/
	void add(TElement e);


	/*
		Search the element e in Bag
			params:: e - TElement
			return:: True if the element is in Bag, False otherwise
			throws:: No exception.
	*/
	bool search(TElement e);


	/*
		Remove the element e from the Bag if it is in the Bag
			params:: e - TElement
			return:: None.
			throws:: Inexistence exception.
	*/
	void remove(TElement e);

private:
	int hashFunction(TElement e);
	void resize();
	void rehash(TElement* lst);
	TElement* copyElements();

public:
	class Iterator
	{
	private:
		Bag<TElement>* bag;
		int currentPos;
	public:
		//Constructor
		Iterator()
		{
			this->bag = new Bag();
			this->currentPos = 0;
		}

		//Copy Constructor
		Iterator(const Iterator& it)
		{
			this->bag = it.bag;
			this->currentPos = it.currentPos;
		}

		//Destructor
		~Iterator()
		{
		}


		/*
			Initialise the bag iterator
				params:: b - Bag with TElement as elements
				return:: None.
				throws:: No exception.
		*/
		void init(Bag<TElement>* b)
		{
			this->bag = b;
			this->currentPos = 0;
		}


		/*
			Validate the current iterator. 
			A valide iterator has the bag length grater than 0 
			and the currentPos smaller than the capacity m
				params:: None.
				return:: True if the iterator is valid, False otherwise
				throws:: No exception.
		*/
		bool valid()
		{
			if (this->bag->length == 0)
				return false;
			if (this->currentPos == this->bag->m)
				return false;
			return true;
		}


		/*
			Get the next iterator, finding a valid position in the Bag, 
			which value is different form TElement() and next position is different from -1
				params:: None.
				return:: None.
				throws:: No exception.
		*/
		void next()
		{
			do
			{
				this->currentPos++;
			} while (this->currentPos != this->bag->m && this->bag->next[this->currentPos] == 0 && this->bag->T[this->currentPos] == TElement());
		}


		/*
			Return the current element to which the iterator is pointing
				params:: None.
				return:: TElement - the element of the currentPos from the Bag
				throws:: No exception.
		*/
		TElement getCurrent()
		{
			return this->bag->T[this->currentPos];
		}
	};
};

template <typename TElement>
Bag<TElement>::Bag()
{
	this->m = 50;
	this->length = 0;
	this->firstFree = 0;
	this->T = new TElement[this->m + 2];
	this->next = new int[this->m + 2];
}


template <typename TElement>
Bag<TElement>::~Bag()
{
	delete[] T;
	//delete[] next;
}


template <typename TElement>
Bag<TElement>::Bag(const Bag<TElement>& other)
{
	this->m = other.m;
	this->length = other.length;
	this->firstFree = other.firstFree;

	this->T = new TElement[this->m + 2];
	this->next = new int[this->m + 2];
	for (int i = 0; i < this->m; i++)
	{
		this->T[i] = other.T[i];
		this->next[i] = other.next[i];
	}
}


template <typename TElement>
Bag<TElement>& Bag<TElement>::operator=(const Bag<TElement>& other)
{
	if (this == &other)
		return *this;

	this->m = other.m;
	this->length = other.length;
	this->firstFree = other.firstFree;
	delete[] this->T;
	delete[] this->next;
	this->T = new TElement[this->m + 2];
	this->next = new int[this->m + 2];
	for (int i = 0; i < this->m; i++)
	{
		this->T[i] = other.T[i];
		this->next[i] = other.next[i];
	}

	return *this;
}


template <typename TElement>
void Bag<TElement>::init()
{
	this->m = 50;
	this->length = 0;
	this->firstFree = 0;
	for (int i = 0; i < this->m; i++)
	{
		this->T[i] = TElement();
		this->next[i] = -1;
	}
}


template <typename TElement>
int Bag<TElement>::size()
{
	return this->length;
}


template <typename TElement>
void Bag<TElement>::add(TElement e)
{
	if (this->length == this->m)
		this->resize();
	int hashCode = hashFunction(e);
	if (this->T[hashCode] == TElement())
		this->T[hashCode] = e;
	else
	{
		this->T[this->firstFree] = e;
		int i = hashCode;
		while (this->next[i] != -1)
			i = this->next[i];
		this->next[i] = this->firstFree;
		while (this->T[this->firstFree] != TElement())
			this->firstFree++;
	}
	this->length++;
}


template <typename TElement>
bool Bag<TElement>::search(TElement e)
{
	int hashCode = hashFunction(e);
	int i = hashCode;
	if (this->T[i] == e)
		return true;
	while (this->next[i] != -1)
	{
		i = this->next[i];
		if (this->T[i] == e)
			return true;
	}
	return false;
}


template <typename TElement>
void Bag<TElement>::remove(TElement e)
{
	if (!search(e))
		throw std::exception("not found!");

	int hashCode = hashFunction(e);
	int i = hashCode;
	int previous = -1;
	int nextPos = this->next[i];
	while (this->T[i] != e)
	{
		previous = i;
		i = nextPos;
		nextPos = this->next[i];
	}

	if (nextPos == -1)
	{
		this->next[previous] = -1;
		this->T[i] = TElement();
		if (this->firstFree > i)
			this->firstFree = i;
	}
	else
	{
		if (hashFunction(this->T[nextPos]) == nextPos)
		{
			this->next[previous] = nextPos;
			this->T[i] = TElement();
			this->next[i] = -1;
			if (this->firstFree > i)
				this->firstFree = i;
		}
		else
		{
			this->T[i] = this->T[nextPos];
			this->next[i] = this->next[nextPos];
			this->T[nextPos] = TElement();
			this->next[nextPos] = -1;
			if (this->firstFree > nextPos)
				this->firstFree = i;
		}
	}

	this->length--;
}


template <typename TElement>
int Bag<TElement>::hashFunction(TElement e)
{
	return (int)(e % this->m);
}

template <typename TElement>
void Bag<TElement>::resize()
{
	TElement* listBackup = copyElements();

	this->length = 0;
	this->m = this->m * 2;

	delete[] this->T;
	delete[] this->next;

	this->T = new TElement[this->m + 2];
	this->next = new int[this->m + 2];
	for (int i = 0; i < this->m; i++)
		this->T[i] = TElement();
	for (int i = 0; i < this->m; i++)
		this->next[i] = -1;

	this->firstFree = 0;

	rehash(listBackup);
	delete[] listBackup;
}


template <typename TElement>
void Bag<TElement>::rehash(TElement* lst)
{
	for (int i = 0; i < this->m / 2; i++)
		this->add(lst[i]);
}


template <typename TElement>
TElement* Bag<TElement>::copyElements()
{
	TElement* lst = new TElement[this->m + 2];
	for (int i = 0; i < m; i++)
		lst[i] = this->T[i];
	return lst;
}