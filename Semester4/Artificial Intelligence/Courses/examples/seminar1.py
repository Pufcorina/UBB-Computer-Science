# -*- coding: utf-8 -*-
"""

jumping frogs:
    find a sequence of valid steps in order to swich places of n red frogs with 
    n brown frogs.
    
    A step is valid if a frog moves a step forward or jumps over one frog 
    if the destination place is empty
    
    Red frogs are moving only in the direction from the begining to the end 
    of the line brown frogs are moving only in the direction from the end of 
    the line towards the begining
    
    example:
    initial configuration: RRR BBB
    final configuration: BBB RRR
    
    
    we observe running this example that the BestFS takes a longer time for n=10
    than BFS (over more 50 times longer) due the fact that the heuristic leads 
    to 'dead paths' also computing the heuristic and sorting the new vertexes 
    consume computing power
    
    """

from time import time

class Configuration:
    '''
    holds a configurations of frogs
    '''
    def __init__(self, positions):
        self.__size = len(positions)
        self.__values = positions[:]
    
    def getSize(self):
        return self.__size
    
    def getValues(self):
        return self.__values[:]


    def nextConfig(self, j):
        '''
        moves the frog from the position j in the proper next position(s)
        in: the position of the moving frog j
        out: the list of the next correct configurations obtained moving this frog
        '''
        nextC = []
        
        
        if self.__values[j] == 'R':
            if j < self.__size - 1:
                if self.__values[j+1] == 0 :
                    aux = self.__values[:]
                    aux[j], aux[j+1] = 0, 'R'
                    nextC.append(Configuration(aux))
                    #one step forward for a red frog
            if j < self.__size - 2:
                if self.__values[j+2] == 0 :
                    aux = self.__values[:]
                    aux[j], aux[j+2] = 0, 'R'
                    nextC.append(Configuration(aux))
                    #twp steps forward for a red frog
        elif self.__values[j] == 'B':
            if j > 0:
                if self.__values[j-1] == 0:
                    aux = self.__values[:]
                    aux[j], aux[j-1] = 0, 'B'
                    nextC.append(Configuration(aux))
                    #one step backward for a brown frog
            if j > 1:
                if self.__values[j-2] == 0:
                    aux = self.__values[:]
                    aux[j], aux[j-2] = 0, 'B'
                    nextC.append(Configuration(aux))
                    #two steps backward for a brown frog
        return nextC
        
    def __eq__(self, other):
        if not isinstance(other, Configuration):
            return False
        if self.__size != other.getSize():
            return False
        for i in range(self.__size):
            if self.__values[i] != other.getValues()[i]:
                return False
        return True
        
    def __str__(self):
        return str(self.__values)

class State:
    '''
    holds a PATH of configurations
    '''
    def __init__(self):
        self.__values = []
    
    def setValues(self, values):
        self.__values = values[:]

    def getValues(self):
        return self.__values[:]

    def __str__(self):
        s=''
        for x in self.__values:
            s+=str(x)+"\n"
        return s

    def __add__(self, something):
        aux = State()
        if isinstance(something, State):
            aux.setValues(self.__values+something.getValues())
        elif isinstance(something, Configuration):
            aux.setValues(self.__values+[something])
        else:
            aux.setValues(self.__values)
        return aux
    
class Problem:
    
    def __init__(self, initial, final):
        self.__initialConfig = initial
        self.__finalConfig = final 
        self.__initialState = State()
        self.__initialState.setValues([self.__initialConfig])
        

    def expand(self, currentState):
        myList = []
        currentConfig = currentState.getValues()[-1]
        for j in range(currentConfig.getSize()):
            for x in currentConfig.nextConfig(j):
                
                myList.append(currentState+x)
        
        return myList
    
    def getFinal(self):
        return self.__finalConfig
    
    def getRoot(self):
        return self.__initialState
    

    def heuristics(self, state, finalC):
        l = finalC.getSize()
        count = 2 * l
        for i in range(l):
            if state.getValues()[-1].getValues()[i] != finalC.getValues()[i]:
                count = count - 1
        return count
        
        
class Controller:
    
    def __init__(self, problem):
        self.__problem = problem
    
    def BFS(self, root):
        
        q = [root]

        while len(q) > 0 :
            currentState = q.pop(0)
            
            if currentState.getValues()[-1] == self.__problem.getFinal():
                return currentState
            q = q + self.__problem.expand(currentState)

    def BestFS(self, root):
        
        visited = []
        toVisit = [root]
        while len(toVisit) > 0:
            node = toVisit.pop(0)
            visited = visited + [node]
            if node.getValues()[-1] == self.__problem.getFinal():
                return node
            aux = []
            for x in self.__problem.expand(node):
                if x not in visited:
                    aux.append(x)
            aux = [ [x, self.__problem.heuristics(x,self.__problem.getFinal())] for x in aux]
            aux.sort(key=lambda x:x[1])
            aux = [x[0] for x in aux]
            toVisit = aux[:] + toVisit 

class UI:
    
    def __init__(self):
        self.__iniC = Configuration(['R','R','R', 0, 'B','B', 'B'])
        self.__finC = Configuration(['B','B', 'B', 0, 'R','R','R'])
        self.__p = Problem(self.__iniC,self.__finC)
        self.__contr = Controller(self.__p)
    
    def printMainMenu(self):
        s = ''
        s += "[RRR BBB] and [BBB RRR] are the default initial and final config.\n"
        s += "0 - exit \n"
        s += "1 - read the number of frogs \n"
        s += "2 - find a path with BFS \n"
        s += "3 - find a path with BestFS\n"
        print(s)
    
    def readConfigSubMenu(self):
        n = 3
        try:
            print("Input the number of red frogs (implicit n=3)")
            n = int(input("n = "))
        except :
            print("invalid number, the implicit value is still 3")
            n=3
        self.__iniC = Configuration(['R']*n+[0]+['B']*n)
        self.__finC = Configuration(['B']*n+[0]+['R']*n)
        self.__p = Problem(self.__iniC, self.__finC)
        self.__contr = Controller(self.__p)
        
    def findPathBFS(self):
        startClock = time()
        print(str(self.__contr.BFS(self.__p.getRoot())))    
        print('execution time = ',time()-startClock, " seconds" )
        
    def findPathBestFS(self):
        startClock = time()
        print(str(self.__contr.BestFS(self.__p.getRoot())))
        print('execution time = ',time()-startClock, " seconds" )
   
    def run(self):
        runM=True
        self.printMainMenu()
        while runM:        
            try: 
                command = int(input(">>"))
                if command == 0:
                    runM = False
                elif command == 1:
                    self.readConfigSubMenu()
                elif command == 2:
                    self.findPathBFS()
                elif command == 3:
                    
                    self.findPathBestFS()            
            except:
                print('invalid command')
    

    
def tests():
    c1 = Configuration(['R','R','R', 0, 'B','B', 'B'])
    c2 = Configuration(['B','B', 'B', 0, 'R','R','R'])
    s = State()
    p = Problem(c1,c2)
    contr = Controller(p)
    
    #Configuration
    assert(c1.getSize()==7)
    assert(c1.getValues()==['R','R','R', 0, 'B','B', 'B'])
    assert(c1.nextConfig(1) == [Configuration(['R', 0, 'R', 'R', 'B', 'B', 'B'])])
    assert(c1.nextConfig(0) == [])
    
    #State
    
    assert(s.getValues() == [])
    s = s + 'ceva aiurea'
    assert(s.getValues() == [])
    s = s + c1
    assert(s.getValues() == [c1])
    
    
    #Problem
    aux = p.expand(s)
    assert(len(aux) == 4)
    assert(aux[1].getValues()[-1] == Configuration(['R', 'R',0, 'R', 'B', 'B', 'B']))
    
    #...
    
    print('tests passed')
    
def main():
    tests()
    ui = UI()
    ui.run()
    
    
    
main()    
        
        
    
        