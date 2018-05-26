class FuzzyRule:
    """
        Define a conjunctive fuzzy rule
        X and Y and ... => Z
    """

    def __init__(self, inputs, out):
        """
            Receives the set of inputs and expected output
        """
        self.out_var = out  # the name of the output variable
        self.inputs = inputs

    def evaluate(self, inputs):
        """
            Receives a dictionary of all the input values and returns the conjunction
            of their values
        """
        return [self.out_var, min(
            [inputs[descr_name][var_name]
             for descr_name, var_name in self.inputs.items()
             ])]
