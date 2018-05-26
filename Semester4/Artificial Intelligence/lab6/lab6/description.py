class FuzzyDescriptions:
    """
        Encapsulate a description of a fuzzy variable
        It contains a set of functions for each fuzzy region
    """
    def __init__(self):
        self.regions = {}
        self.inverse = {}

    def add_region(self, var_vame, membership_function, inverse=None):
        """
            Adds a region with a given membership function, optionally
            an inverse function for the Sugeno or Tsukamoto models
        """
        self.regions[var_vame] = membership_function
        self.inverse[var_vame] = inverse

    def fuzzify(self, value):
        """
            Return the fuzzified values for each region
        """
        return {name: membership_function(value)
                for name, membership_function in self.regions.items()
                }

    def defuzzify(self, var_name, value):
        return self.inverse[var_name](value)
