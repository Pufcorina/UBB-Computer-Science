from system import FuzzySystem


class Controller:
    def __init__(self, temperature, humidity, time, rules):
        self.system = FuzzySystem(rules)
        self.system.add_description('temperature', temperature)
        self.system.add_description('humidity', humidity)
        self.system.add_description('time', time, out=True)

    def compute(self, inputs):
        return "If we have the humidity: " + str(inputs['humidity']) + \
               " and temperature: " + str(inputs['temperature']) + \
               " will probably have the operating time: " + str(self.system.compute(inputs))
