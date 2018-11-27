from model.finiteAutomata import FiniteAutomata
from model.grammar import Grammar

if __name__ == '__main__':

    # Grammar usage
    # We read the grammar from file 'rg1.txt'

    grammar = Grammar.fromFile('rg1.txt')
    print(grammar)

    # We print the productions for a given non-terminal,
    # A in this case

    try:
        grammar.showProductionsFor('A')
    except Exception as e:
        print(e)

    # We can also read from the console if we'd like,
    # just make sure that the format of the input is the same as in this example

    # grammar2 = Grammar.fromConsole()
    # print('\n' + str(grammar2))

    # Finite Automata usage
    # We read the Finite Automata from the file 'fa1.txt'

    finiteAutomata = FiniteAutomata.fromFile('fa1.txt')
    print(finiteAutomata)

    # We print the transitions for a given state,
    # A in this case

    # finiteAutomata2 = FiniteAutomata.fromConsole()
    # print('\n' + str(finiteAutomata2))


    # Transformations

    # Regular Grammar -> Finite Automata

    grammar = Grammar.fromFile('rg1.txt')

    if grammar.isRegular():
        finiteAutomata = FiniteAutomata.fromRegularGrammar(grammar)
        print(finiteAutomata)
    else:
        print("The grammar is not regular\n")

    # Finite Automata -> Regular Grammar

    finiteAutomata = FiniteAutomata.fromFile('fa1.txt')
    grammar = Grammar.fromFiniteAutomata(finiteAutomata)

    print(grammar)
