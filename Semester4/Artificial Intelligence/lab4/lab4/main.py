from random import randint

from knn import KNeighborsClassifier


def readData(filename):
    inputData = []
    outputData = []

    with open(filename) as f:
        lines = f.readlines()
        for line in lines:
            data = line.split(' ')
            if len(data) < 7:
                continue
            inputData.append(
                [float(data[0]), float(data[1]), float(data[2]), float(data[3]), float(data[4]), float(data[5])])
            outputData.append(data[6])
    return inputData, outputData


if __name__ == '__main__':
    inputToLearn, outputToLearn = readData("input.data")

    inputTest = []
    outputTest = []

    for i in range(100):
        index = randint(0, len(inputToLearn) - 1)

        inputTest.append(inputToLearn[index])
        outputTest.append(outputToLearn[index])

        inputToLearn.remove(inputToLearn[index])
        outputToLearn.remove(outputToLearn[index])

    classifier = KNeighborsClassifier()

    classifier.fit(inputToLearn, outputToLearn)

    accuracy = 0

    for i in range(len(inputTest)):
        if classifier.predict(inputTest[i]) == outputTest[i]:
            accuracy += 1

    print("The accuracy is: ", float(accuracy) / len(inputTest))
