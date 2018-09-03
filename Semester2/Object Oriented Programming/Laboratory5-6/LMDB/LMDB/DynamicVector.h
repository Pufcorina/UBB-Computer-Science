#pragma once
template <typename T>
class DynamicVector
{
private:
	T* elements;
	int size;
	int capacity;

public:
	// default constructor for a DynamicVector
	DynamicVector(int capacity = 10);

	// copy constructor for a DynamicVector
	DynamicVector(const DynamicVector& v);
	
	// destructor for a DynamicVector
	~DynamicVector();

	// adds an element to the current DynamicVector
	void addItem(const T& e);

	// delete an element from the current DynamicVector
	void deleteItem(const int pos);

	// update an element on a given position from the current DynamicVector
	void updateItem(const int pos, const T e);

	int getSize() const;
	int getCapacity() const { return this->capacity;  }
	T getElement(const int pos) const;

private:
	// resize the current DynamicVector, multiplying its capacity by a given facor (real number)
	void resize(int factor = 2);
};

// constructor for DynamicVector
template <typename T>
DynamicVector<T>::DynamicVector(int capacity = 10)
{
	this->size = 0;
	this->capacity = capacity;
	this->elements = new T[this->capacity];
}

// copy constructor for DynamicVector
template <typename T>
DynamicVector<T>::DynamicVector(const DynamicVector<T>& v)
{
	this->size = v.getSize();
	this->capacity = v.getCapacity();
	this->elements = new T[this->capacity];
	for (int i = 0; i < this->size; i++)
		this->elements[i] = v.getElement(i);
}

// destructor for DynamicVector
template <typename T>
DynamicVector<T>::~DynamicVector()
{
	delete[] this->elements;
}

// add an element to DynamicVector
template <typename T>
void DynamicVector<T>::addItem(const T& e)
{
	if (this->size == this->capacity)
		this->resize();
	this->elements[this->size] = e;
	this->size++;
}

// resize the container and copy the enements in the new one
template <typename T>
void DynamicVector<T>::resize(int factor = 2)
{
	this->capacity *= factor;

	T* els = new T[this->capacity];
	for (int i = 0; i < this->size; i++)
		els[i] = this->elements[i];

	delete[] this->elements;
	this->elements = els;
}


// return the size of the container
template <typename T>
int DynamicVector<T>::getSize() const
{
	return this->size;
}

// delete an element from a certain position
template <typename T>
void DynamicVector<T>::deleteItem(const int pos)
{
	int cnt = 0;
	T* els = new T[this->capacity];
	for (int i = 0; i < this->size; i++)
	{
		if (cnt == pos)
			cnt++;
		els[i] = this->elements[cnt];
		cnt++;
	}

	delete[] this->elements;
	this->elements = els;
	this->size--;
}

// update a position from the container with a specific entity
template <typename T>
void DynamicVector<T>::updateItem(const int pos, const T e)
{
	this->elements[pos] = e;
}

// return the element from the position pos
template <typename T>
T DynamicVector<T>::getElement(const int pos) const
{
	return this->elements[pos];
}