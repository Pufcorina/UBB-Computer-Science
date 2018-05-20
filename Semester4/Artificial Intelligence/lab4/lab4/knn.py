from math import sqrt


def euclidian_distance(a, b):
    sum = 0
    for i in range(len(a)):
        sum += (a[i] - b[i]) ** 2
    return sqrt(sum)


class KNeighborsClassifier:
    def __init__(self):
        self.__input = []
        self.__output = []

    def fit(self, inputToLearn, outputToLearn):
        self.__input = inputToLearn
        self.__output = outputToLearn

    def predict(self, data):
        return self.__get_closest(data)

    def __get_closest(self, data):
        best_dist = euclidian_distance(data, self.__input[0])
        best_index = 0

        for i in range(1, len(self.__input)):
            dist = euclidian_distance(data, self.__input[i])
            if dist < best_dist:
                best_dist = dist
                best_index = i

        return self.__output[best_index]

