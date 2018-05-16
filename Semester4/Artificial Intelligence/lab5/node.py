from random import random

from numpy.random.mtrand import choice


ITER = 100
T = ["value" + str(x) for x in range(384)] + \
    ["c" + str(x) for x in range(10)]
P = [0.332, 0.332, 0.332, 0.002, 0.002]
F = ['+', '-', '*', 'sin', 'cos']
C = [random() for _ in range(10)]


class Node:
    def __init__(self):
        self.val = None
        self.left = None
        self.right = None
        self.size = 1

    def deepcopy(self):
        copy = Node()
        copy.val = self.val
        copy.size = self.size
        if self.left:
            copy.left = self.left.deepcopy()
        if self.right:
            copy.right = self.right.deepcopy()
        return copy

    '''
    The set of terminals (e.g., the independent variables of the problem,
    zero-argument functions, and random constants) 
    for each branch of the to-be-evolved program
    '''

    def init(self, d):
        if d == 0:
            self.val = choice(T)
            return
        '''
        The probabilities associated with each entry in a.
        If not given the sample assumes a uniform distribution 
        over all entries in a.'''
        self.val = choice(F, p=P)
        self.left = Node()
        self.left.init(d - 1)
        self.size += self.left.size
        if self.val != 'sin' and self.val != 'cos':
            self.right = Node()
            self.right.init(d - 1)
            self.size += self.right.size

    '''
    Koza mutation -> replace a node(leaf or internal) by a sub-tree
    '''
    def mutate(self, pos, prob):
        if pos <= 0:
            assert False
        if pos > self.size:
            assert False
        if self.left and pos <= self.left.size:
            self.left.mutate(pos, prob)
        else:
            leftSize = 0
            if self.left:
                leftSize = self.left.size
            if leftSize + 1 == pos:
                if random() < prob:
                    if self.val in T:
                        self.val = choice(T)
                    else:
                        if self.val == 'sin':
                            self.val = 'cos'
                        elif self.val == 'cos':
                            self.val = 'sin'
                        else:
                            self.val = choice(F[:3])
            else:
                self.right.mutate(pos - leftSize - 1, prob)

    def change(self, root, node1, node2):
        self.val = root.val
        if root.left:
            if root.left == node1:
                self.left = node2.deepcopy()
            else:
                self.left = Node()
                self.left.change(root.left, node1, node2)
        if root.right:
            if root.right == node1:
                self.right = node2.deepcopy()
            else:
                self.right = Node()
                self.right.change(root.right, node1, node2)
        self.size = 1
        if self.left:
            self.size += self.left.size
        if self.right:
            self.size += self.right.size

    def getNodes(self):
        ret = []
        if self.left:
            ret += self.left.getNodes()
        ret.append(self)
        if self.right:
            ret += self.right.getNodes()
        return ret

    def __str__(self):
        if self.val in T:
            return str(C[int(self.val[1])]) if self.val[0] == 'c' else str(self.val)
        if self.val == "sin" or self.val == "cos":
            return self.val + "(" + str(self.left) + ")"
        return str(self.left) + self.val + str(self.right)

    def __repr__(self):
        return self.__str__()
