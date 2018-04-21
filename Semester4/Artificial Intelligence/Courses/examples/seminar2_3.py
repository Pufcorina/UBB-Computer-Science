"""
Sa se plimbe un cal pe o tabla de sah de dimensiune n x m, odata pe fiecare casuta.
"""

from random import *

class ant:
    def __init__(self, n, m):
        #constructor pentru clasa ant
        self.size = n * m
        self.path = [randint(0, self.size-1)]
        """
        drumul construit de furnica initializat aleator pe prima pozitie
        drumul este o permutare de self.size elemente, fiecare numar
        reprezentand o casuta a tablei de sah:
        pt n=4, m=6
        0  este casuta 0, 0
        1  este casuta 0, 1
        ...
        5  este casuta 0, 5
        6  este casuta 1, 0
        ...
        23 este casuta 3, 5 (ultima din cele 24 de casute)
        """
        self.n = n
        self.m = m
        
    def nextMoves(self, a):
        # returneaza o lista de posibile mutari corecte de la pozitia a
        new = []
        x = int (a / self.m)
        y = a - int (a / self.m) * self.m
        variatiaX = [ 2, 2, -1, -1, -2, -2, 1,  1]
        variatiaY = [-1, 1,  2, -2,  1, -1, 2, -2]
        for i in range(8):
            nextX = x + variatiaX[ i ]
            nextY = y + variatiaY[ i ]
            if (nextX >= 0) and (nextX < self.n) and (nextY >= 0) and (nextY < self.m):
                b = nextX * self.m + nextY
                if (b not in self.path):
                    new.append(b)
        return new.copy()

    def distMove(self, a):
        # returneaza o distanta empirica data de numarul de posibile mutari corecte
        # dupa ce se adauga pasul a in path
        dummy=ant(self.n, self.m)
        dummy.path=self.path.copy()
        dummy.path.append(a)
        return (9-len(dummy.nextMoves(a)))
        
    def addMove(self, q0, trace, alpha, beta):
        # adauga o noua pozitie in solutia furnicii daca este posibil
        p = [0 for i in range(self.size)]
        # pozitiile ce nu sunt valide vor fi marcate cu zero
        nextSteps=self.nextMoves(self.path[len(self.path)-1]).copy()
        # determina urmatoarele pozitii valide in nextSteps
        # daca nu avem astfel de pozitii iesim 
        if (len(nextSteps) == 0):
            return False
        # punem pe pozitiile valide valoarea distantei empirice
        for i in nextSteps:
            p[i] = self.distMove(i)
        # calculam produsul trace^alpha si vizibilitate^beta
        p=[ (p[i]**beta)*(trace[self.path[-1]][i]**alpha) for i in range(len(p))]
        if (random()<q0):
            # adaugam cel mai bun dintre mutarile posibile
            p = [ [i, p[i]] for i in range(len(p)) ]
            p = max(p, key=lambda a: a[1])
            self.path.append(p[0])
        else:
            # adaugam cu o probabilitate un drum posibil (ruleta)
            s = sum(p)
            if (s==0):
                return choice(nextSteps)
            p = [ p[i]/s for i in range(len(p)) ]
            p = [ sum(p[0:i+1]) for i in range(len(p)) ]
            r=random()
            i=0
            while (r > p[i]):
                i=i+1
            self.path.append(i)
        return True
                   
    def fitness(self):
        # un drum e cu atat mai bun cu cat este mai lung
        # problema de minimizare, drumul maxim e n * m
        return (self.size-len(self.path)+2)

def epoca(noAnts, n, m, trace, alpha, beta, q0, rho):
    antSet=[ant(n, m) for i in range(noAnts)]
    for i in range(n * m):
        # numarul maxim de iteratii intr-o epoca este lungimea solutiei
        for x in antSet:
            x.addMove(q0, trace, alpha, beta)
    # actualizam trace-ul cu feromonii lasati de toate furnicile
    dTrace=[ 1.0 / antSet[i].fitness() for i in range(len(antSet))]
    for i in range(n * m):
        for j in range (n * m):
            trace[i][j] = (1 - rho) * trace[i][j]
    for i in range(len(antSet)):
        for j in range(len(antSet[i].path)-1):
            x = antSet[i].path[j]
            y = antSet[i].path[j+1]
            trace[x][y] = trace [x][y] + dTrace[i]
    # return best ant path
    f=[ [antSet[i].fitness(), i] for i in range(len(antSet))]
    f=max(f)
    return antSet[f[1]].path
    
def main(n = 8,m = 8,noEpoch = 100,noAnts = 3,alpha = 1.9,beta = 0.9,rho = 0.05,q0 = 0.5):
    sol=[]
    bestSol=[]
    trace=[[1 for i in range(n * m)] for j in range (n * m)]
    print("Programul ruleaza! Dureaza ceva timp pana va termina!")
    for i in range(noEpoch):
        sol=epoca(noAnts, n, m, trace, alpha, beta, q0, rho).copy()
        if len(sol)>len(bestSol):
            bestSol=sol.copy()
    print ("lungimea celei mai bune solutii depistate la aceasta rulare:", len(bestSol))
    print ("Drumul detectat este:", bestSol)
