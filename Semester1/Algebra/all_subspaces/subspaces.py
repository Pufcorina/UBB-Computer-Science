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


def generateCombinationsBasis(lst_vectors, poz, lst_index, lstcomb, k):
    """
    Generate combination of sums of all possible vectors

    :param lst_vectors: the list of all 2^n vectors using elements from Z2
    :param poz: the position in the vector of indexes ( for backtracking )
    :param lst_index: an array of indexes for generating the combinations
    :param lstcomb: the final list of subspaces
    :param k: dimension of the subspace

    :return: None ( but in the end in lstcomb will be all subspaces of dimension k )
    """
    if poz > k:
        aux = []
        for i in range(0, k):
            aux.append(lst_vectors[lst_index[i+1]])
        if numpy.linalg.matrix_rank(aux) == k:
            lstcomb.append(aux)
            if generateCombinations(0, len(aux), lstcomb, aux, [-1]*(len(aux) + 1)) == True:
                lstcomb.pop(len(lstcomb) - 1)
        return
    for i in range(lst_index[poz-1] + 1, len(lst_vectors)):
        lst_index[poz] = i
        generateCombinationsBasis(lst_vectors, poz + 1, lst_index[:], lstcomb, k)


def sumVectors(poz, vector, lst_index_vector):
    """
    Calculate the sum of a list of vectors

    :param poz: the position in the vector of indexes ( for backtracking )
    :param vector: list of vectors
    :param lst_index_vector: an array of indexes for generating the combinations

    :return: the sum of vectors
    """
    aux = lst_index_vector[:poz]
    sum = [0] * len(vector[0])
    for i in range(0, poz):
        aux[i] = vector[lst_index_vector[i]]
    for i in aux:
        for m in range(0, len(i)):
            sum[m] = (sum[m] + i[m]) % 2
    return sum


def generateCombinations(poz, k, lstcomb, vector, lst_index_vector):
    """

    :param poz: the position in the vector of indexes ( for backtracking )
    :param k: dimension of the subspace
    :param lstcomb: the final list of subspaces
    :param vector:
    :param lst_index_vector: an array of indexes for generating the combinations

    :return:
    """
    bol = False
    if poz >= 2 and poz <= k + 1:
        sum = sumVectors(poz, vector, lst_index_vector)
        for i in lstcomb:
            if sum in i:
                bol = True
                return True
    for i in range(lst_index_vector[poz - 1] + 1, len(vector)):
        if bol != True:
            lst_index_vector[poz] = i
            bol = generateCombinations(poz + 1, k, lstcomb, vector, lst_index_vector[:])
        else:
            return True
            break
    return bol


def printSubspaces(lstcomb, k):
    """
    Print all subspaces of dimension k of vectors of dimension n

    :param lstcomb: the final list of subspaces
    :param k: dimension of the subspace

    :return: None
    """
    print("The number of subspaces with dimension", k, "is: ", len(lstcomb))
    if len(lstcomb) == 0:
        print("The subspaces are: None")
    else:
        print("The subspaces are:")
    for i in range(0, len(lstcomb)):
        print("( ", end="")
        for j in range(0, len(lstcomb[i]) - 1):
            print(tuple(lstcomb[i][j]), ", ", end="")
        print(tuple(lstcomb[i][len(lstcomb[i]) - 1]), " )")


def printVectors(lst_vectors):
    """
    Print all 2^n vectors using elements from Z2 separated by new line

    :param lst_vectors: the list of all 2^n vectors using elements from Z2

    :return: None
    """
    print("All possible vectors:")
    print(*lst_vectors, sep="\n")


def readParameters():
    """
    Read the dimension of the subspace (k)
    Read the dimension of the vector space (n)

    :return: k, n
    """
    k = input("Insert the dimension of the subspace: k = ")
    k = int(k)
    n = input("Insert the dimension of the vector space: n = ")
    n = int(n)

    return k, n


def checkFinalList(lstcomb):
    """
    Check if still exist a sum of vectors which generates the same subspace

    :param lstcomb: the final list of subspaces

    :return: None ( but in the lstcomb will be all the subspaces of dimension k of n dimension vectors
    """
    i = len(lstcomb) - 1
    while i > -1:
        if generateCombinations(0, len(lstcomb[i]), lstcomb, lstcomb[i], [-1] * (len(lstcomb[i]) + 1)) == True:
            lstcomb.pop(i)
            i += 1
        i -= 1


def runApplication():
    lst_vectors = []
    lst_index = []
    vector = []
    lstcomb = []
    while True:
        try:
            k, n = readParameters()

            if k == 0 or n == 0:
                raise ValueError("Numbers cannot be zero")

            vector.clear()
            lst_vectors.clear()
            lst_index.clear()
            lstcomb.clear()

            for i in range(0, n):
                vector.append(0)

            for i in range(0, k + 1):
                lst_index.append(0)
            lst_index[0] = -1

            generateVectors(0, lst_vectors, vector, n)
            lst_vectors.pop(0)
            if k != 1:
                lst_vectors.pop(len(lst_vectors) - 1)

            printVectors(lst_vectors)

            generateCombinationsBasis(lst_vectors, 1, lst_index, lstcomb, k)

            checkFinalList(lstcomb)

            printSubspaces(lstcomb, k)

            print('\n')

        except ValueError as ex:
            print(ex)


if __name__ == '__main__':
    runApplication()