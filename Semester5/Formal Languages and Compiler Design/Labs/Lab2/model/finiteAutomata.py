class FiniteAutomata:
    @staticmethod
    def parseLine(line):
        return [value.strip() for value in line.strip().split('=')[1].strip()[1:-1].strip().split(',')]

    @staticmethod
    def parseConsole(line):
        return [value.strip() for value in line.strip()[1:-1].strip().split(',')]

    @staticmethod
    def fromFile(fileName):
        with open(fileName) as file:
            Q = FiniteAutomata.parseLine(file.readline())
            E = FiniteAutomata.parseLine(file.readline())
            q0 = file.readline().split('=')[1].strip()
            F = FiniteAutomata.parseLine(file.readline())

            S = FiniteAutomata.parseTransitions(FiniteAutomata.parseLine(''.join([line for line in file])))

            return FiniteAutomata(Q, E, S, q0, F)

    @staticmethod
    def fromConsole():
        Q = FiniteAutomata.parseConsole(input('Q = '))
        E = FiniteAutomata.parseConsole(input('E = '))
        q0 = input('q0 = ')
        F = FiniteAutomata.parseConsole(input('F = '))

        S = FiniteAutomata.parseTransitions(FiniteAutomata.parseConsole(input('S = ')))

        return FiniteAutomata(Q, E, S, q0, F)

    @staticmethod
    def parseTransitions(parts):
        result = []
        transitions = []
        index = 0

        while index < len(parts):
            transitions.append(parts[index] + ',' + parts[index + 1])
            index += 2

        for transition in transitions:
            lhs, rhs = transition.split('->')
            state2 = rhs.strip()
            state1, route = [value.strip() for value in lhs.strip()[1:-1].split(',')]

            result.append(((state1, route), state2))

        return result

    @staticmethod
    def fromRegularGrammar(rg):
        Q = rg.N + ['K']
        E = rg.E
        q0 = rg.S
        F = ['K']

        S = []

        for production in rg.P:
            state2 = 'K'
            state1, rhs = production
            if state1 == q0 and rhs[0] == 'E':
                F.append(q0)
                continue

            route = rhs[0]

            if len(rhs) == 2:
                state2 = rhs[1]

            S.append(((state1, route), state2))

        return FiniteAutomata(Q, E, S, q0, F)

    def __init__(self, Q, E, S, q0, F):
        self.Q = Q
        self.E = E
        self.S = S
        self.q0 = q0
        self.F = F

    def isState(self, value):
        return value in self.Q

    def getTransitionsFor(self, state):
        if not self.isState(state):
            raise Exception('Can only get transitions for states')

        return [trans for trans in self.S if trans[0][0] == state]

    def showTransitionsFor(self, state):
        transitions = self.getTransitionsFor(state)

        print('{ ' + ' '.join([' -> '.join([str(part) for part in trans]) for trans in transitions]) + ' }')

    def __str__(self):
        return 'Q = { ' + ', '.join(self.Q) + ' }\n' \
               + 'E = { ' + ', '.join(self.E) + ' }\n' \
               + 'F = { ' + ', '.join(self.F) + ' }\n' \
               + 'S = { ' + ', '.join([' -> '.join([str(part) for part in trans]) for trans in self.S]) + ' }\n' \
               + 'q0 = ' + str(self.q0) + '\n'
