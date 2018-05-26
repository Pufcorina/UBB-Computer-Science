from controller import Controller
from description import FuzzyDescriptions
from ruler import FuzzyRule


def trap_region(a, b, c, d):
    """
        Returns a higher order function for a trapezoidal fuzzy region
    """
    return lambda x: max(0, min((x - a) / (b - a), 1, (d - x) / (d - c)))


def tri_region(a, b, c):
    """
        Returns a higher order function for a triangular fuzzy region
    """
    return trap_region(a, b, b, c)


def inverse_line(a, b):
    return lambda val: val * (b - a) + a


def inverse_tri(a, b, c):
    return lambda val: (inverse_line(a, b)(val) + inverse_line(c, b)(val)) / 2


if __name__ == '__main__':
    temperature = FuzzyDescriptions()
    humidity = FuzzyDescriptions()
    time = FuzzyDescriptions()
    rules = []

    temperature.add_region('very cold', trap_region(-1000, -30, -20, 5))
    temperature.add_region('cold', tri_region(-5, 0, 10))
    temperature.add_region('normal', trap_region(5, 10, 15, 20))
    temperature.add_region('warm', tri_region(15, 20, 25))
    temperature.add_region('hot', trap_region(25, 30, 35, 1000))

    humidity.add_region('dry', tri_region(-1000, 0, 50))
    humidity.add_region('normal', tri_region(0, 50, 100))
    humidity.add_region('wet', tri_region(50, 100, 1000))

    time.add_region('short', tri_region(-1000, 0, 50), inverse_line(50, 0))
    time.add_region('medium', tri_region(0, 50, 100), inverse_tri(0, 50, 100))
    time.add_region('long', tri_region(50, 100, 1000), inverse_line(50, 100))

    rules.append(FuzzyRule({'temperature': 'very cold', 'humidity': 'wet'},
                           {'time': 'short'}))
    rules.append(FuzzyRule({'temperature': 'cold', 'humidity': 'wet'},
                           {'time': 'short'}))
    rules.append(FuzzyRule({'temperature': 'normal', 'humidity': 'wet'},
                           {'time': 'short'}))
    rules.append(FuzzyRule({'temperature': 'warm', 'humidity': 'wet'},
                           {'time': 'short'}))
    rules.append(FuzzyRule({'temperature': 'hot', 'humidity': 'wet'},
                           {'time': 'medium'}))

    rules.append(FuzzyRule({'temperature': 'very cold', 'humidity': 'normal'},
                           {'time': 'short'}))
    rules.append(FuzzyRule({'temperature': 'cold', 'humidity': 'normal'},
                           {'time': 'medium'}))
    rules.append(FuzzyRule({'temperature': 'normal', 'humidity': 'normal'},
                           {'time': 'medium'}))
    rules.append(FuzzyRule({'temperature': 'warm', 'humidity': 'normal'},
                           {'time': 'medium'}))
    rules.append(FuzzyRule({'temperature': 'hot', 'humidity': 'normal'},
                           {'time': 'long'}))

    rules.append(FuzzyRule({'temperature': 'very cold', 'humidity': 'dry'},
                           {'time': 'medium'}))
    rules.append(FuzzyRule({'temperature': 'cold', 'humidity': 'dry'},
                           {'time': 'long'}))
    rules.append(FuzzyRule({'temperature': 'normal', 'humidity': 'dry'},
                           {'time': 'long'}))
    rules.append(FuzzyRule({'temperature': 'warm', 'humidity': 'dry'},
                           {'time': 'long'}))
    rules.append(FuzzyRule({'temperature': 'hot', 'humidity': 'dry'},
                           {'time': 'long'}))

    ctrl = Controller(temperature, humidity, time, rules)

    print(ctrl.compute({'humidity': 65, 'temperature': 17}))
    print(ctrl.compute({'humidity': 10, 'temperature': 17}))
    print(ctrl.compute({'humidity': 10, 'temperature': 30}))
    print(ctrl.compute({'humidity': 75, 'temperature': 20}))
