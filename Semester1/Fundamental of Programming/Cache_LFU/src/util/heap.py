class BinHeap(object):
    def __init__(self, size):
        self.__heapList = []
        self.__indexes = {}
        self.__maxSize = size

    def __getitem__(self, item):
        return self.__heapList[item]

    def __len__(self):
        return len(self.__heapList)

    def full(self):
        '''
        :return: True if the heap reached the maximum size, False otherwise
        '''
        return len(self.__heapList) == self.__maxSize

    def up(self, i):
        '''
        Method that swap the element i with it's parent until it is the root of it's own sub-heap
        :param i: the index of the element from listHeap
        :return: None.
        '''
        if i in self.__indexes:
            i = self.__indexes[i]
        while i > 0:
            if self.__heapList[i] < self.__heapList[(i - 1) // 2]:
                self.__heapList[(i - 1) // 2], self.__heapList[i] = self.__heapList[i], self.__heapList[(i - 1) // 2]
                self.__indexes[self.__heapList[i].entity_ID] = i
                self.__indexes[self.__heapList[(i - 1) // 2].entity_ID] = (i - 1) // 2
            i = (i - 1) // 2

    def insert(self, elem):
        '''
        Insert in heap the element elem at the end of the array
        :param elem: a Cache_Entity object
        :return: None.
        '''
        if not elem.entity_ID in self.__indexes:
            self.__heapList.append(elem)
            self.__indexes[elem.entity_ID] = len(self.__heapList) - 1
            self.up(len(self.__heapList) - 1)
        else:
            self.update(elem.entity_ID)

    def delete(self):
        '''
        Interchange the root with the last element and remove(the previous root).
        After find the right position in heap for the new root
        :return: None.
        '''
        self.__heapList[0], self.__heapList[len(self.__heapList) - 1] = self.__heapList[len(self.__heapList) - 1], self.__heapList[0]
        del self.__indexes[self.__heapList[len(self.__heapList) - 1].entity_ID]
        self.__heapList.pop(len(self.__heapList) - 1)
        self.up(len(self.__heapList) - 1)

    def update(self, index):
        '''
        Update the frequency for the heapList element with entity_ID equal with index
        :param index: entity_ID for the heapList element
        :return: None.
        '''
        if index in self.__indexes:
            index = self.__indexes[index]
        self.__heapList[index] += 1
        self.down(index)

    def down(self, i):
        '''
        Method that swap the element i with the minimum of it's sons until both sons are greater than it
        :param i: the index of the element from listHeap
        :return: None.
        '''
        if i in self.__indexes:
            i = self.__indexes[i]
        while 2 * i + 1 < len(self.__heapList):
            left = self.__heapList[2 * i + 1]

            if 2 * (i + 1) < len(self.__heapList):
                right = self.__heapList[2 * (i + 1)]
            else:
                right = 0x3f3f3f3f

            current = self.__heapList[i]

            if right != 0x3f3f3f3f and right <= left and right < current:
                self.__heapList[i], self.__heapList[2 * (i + 1)] = self.__heapList[2 * (i + 1)], self.__heapList[i]
                self.__indexes[self.__heapList[i].entity_ID] = i
                self.__indexes[self.__heapList[2 * (i + 1)].entity_ID] = 2 * (i + 1)
                i = 2 * (i + 1)
            elif left < current:
                self.__heapList[i], self.__heapList[2 * i + 1] = self.__heapList[2 * i + 1], self.__heapList[i]
                self.__indexes[self.__heapList[i].entity_ID] = i
                self.__indexes[self.__heapList[2 * i + 1].entity_ID] = 2 * i + 1
                i = 2 * i + 1
            else:
                break