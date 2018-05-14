from math import sqrt


def euclidian_distance(a, b):
    return sqrt((a[0] - b[0]) ** 2 + (a[1] - b[1]) ** 2 + (a[2] - b[2]) ** 2 + (a[3] - b[3]) ** 2 + (a[4] - b[4]) ** 2 + (a[5] - b[5]) ** 2)


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

