#include "pch.h"
#include "Matrix.h"
#include <string>	

Matrix::Matrix()
{
	this->rows = 0;
	this->cols = 0;
}

Matrix::~Matrix()
{
}


std::string Matrix::printMatrix()
{
	std::string out = "";

	for (int i = 0; i < this->rows; i++)
	{
		for (int j = 0; j < this->cols; j++)
		{
			out += std::to_string(this->content[i][j]) + ' ';
		}
		out += '\n';
	}

	return out;
}

int Matrix::index(int row, int col)
{
	return row * this->cols + col;
}

void Matrix::set(int row, int col, int value)
{
	this->content[row][col] = value;
}

int Matrix::get(int row, int col)
{
	return this->content[row][col];
}

int Matrix::getRowsNumber()
{
	return this->rows;
}

int Matrix::getColsNumber()
{
	return this->cols;
}
