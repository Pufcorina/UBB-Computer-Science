import numpy

def generateVectors(poz, lst_vectors, vector, n):
    """
    Generate 2^n vectors possible in Z2

    :param poz: the position in vector ( for backtracking )
    :param lst_vectors: final list of all vectors
    :param vector: an array of elements in Z2
    :param n: dimension of the vector

    :return: None ( but in the end in lst_vectors will be all 2^n vectors )
    """
    if poz == n:
        lst_vectors.append(vector)
        return
    for i in range(0, 2):
        vector[poz] = i
        generateVectors(poz+1, lst_vectors, vector[:], n)


def checkDet(lst_ordered_basis, lst_vectors):
    """
    Check if the given matrix could be a basis

    :param lst_ordered_basis: list with all possible basis
    :param lst_vectors: the list of all 2^n vectors using elements from Z2

    :return: True if the determinant is non-zero and False if it is zero
    """
    aux = [None] * (len(lst_ordered_basis) - 1)
    for i in range(1, len(lst_ordered_basis)):
        aux[i - 1] = lst_vectors[lst_ordered_basis[i]]
    if int(numpy.linalg.det(aux)) != 0:
        return True
    return False


def printBasis(lst_ordered_basis, lst_vectors):
    """
    Print all ordered basis

    :param lst_ordered_basis: the final list with all ordered basis
    :param lst_vectors: the list of all 2^n vectors using elements from Z2

    :return: None
    """
    print("( ", end="")
    for i in range(1, len(lst_ordered_basis) - 1):
        i = lst_vectors[lst_ordered_basis[i]]
        print(tuple(i), end=", ")
    print(tuple(lst_vectors[lst_ordered_basis[len(lst_ordered_basis) - 1]]), " )")


def generateBasis(k, lst_vectors, poz, lst_ordered_basis):
    """
    Generate combination of k vectors whcih will represent all ordered basis

    :param k: dimension of the vector
    :param lst_vectors: the final list with all ordered basis
    :param poz: the position in vector ( for backtracking )
    :param lst_ordered_basis: the list of all 2^n vectors using elements from Z2

    :return: None ( but in the end lst_ordered_basis will contain all ordered basis)
    """
    if poz > k:
        if checkDet(lst_ordered_basis, lst_vectors):
            printBasis(lst_ordered_basis, lst_vectors)
        return
    for i in range(lst_ordered_basis[poz-1] + 1, len(lst_vectors)):
        lst_ordered_basis[poz] = i
        generateBasis(k, lst_vectors, poz + 1, lst_ordered_basis)

def printVectors(lst_vectors):
    """
    Print all 2^n vectors using elements from Z2 separated by new line

    :param lst_vectors: the list of all 2^n vectors using elements from Z2

    :return: None
    """
    print("All possible vectors:")
    print(*lst_vectors, sep="\n")


def runApplication():
    while True:
        n = input("Insert the dimension of the vector space: ")
        try:
            n = int(n)

            lst_vectors = []
            vector = []
            lst_ordered_basis = []

            vector.clear()
            lst_ordered_basis.clear()

            for i in range(0, n):
                vector.append(0)

            for i in range(0, n + 1):
                lst_ordered_basis.append(0)
            lst_ordered_basis[0] = -1

            generateVectors(0, lst_vectors, vector, n)

            printVectors(lst_vectors)

            print("All ordered bases are:")
            generateBasis(n, lst_vectors, 1, lst_ordered_basis)

        except ValueError:
            print("Please insert a valid number!!!")


if __name__ == '__main__':
    runApplication()
