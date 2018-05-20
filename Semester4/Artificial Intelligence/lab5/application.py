'''
3. Build a system that approximate the relative position on an axis of
some patiences’ tomographic images (id, structure’s bone histogram,
regions filled with air histogram).
The training data will be taken from:
http://archive.ics.uci.edu/ml/datasets/Relative+location+of+CT+slices+on+a
xial+axis.
'''
from gpalgorithm import GPAlgorithm

'''
The set of functions will contain at least operators 
+, -, *, sin, cos, 
and the terminal set will contain the input of the problem and 10 constants from [0,1]. 
The training of the algorithm ends when the mean
square error is less than e (given as a parameter of the problem).
'''
if __name__ == '__main__':
    algorithmGP = GPAlgorithm("slice_localization_data\slice_localization_data.csv", 10)
    algorithmGP.run()
