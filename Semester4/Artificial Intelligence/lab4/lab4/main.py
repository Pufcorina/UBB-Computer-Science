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
            inp = []
            for i in range(20):
                inp.append(float(data[i]))
            inputData.append(inp)
            outputData.append(data[20])
    return inputData, outputData


if __name__ == '__main__':
    inputToLearn, outputToLearn = readData("file.txt")

    inputTest = []
    outputTest = []

    for i in range(10):
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
