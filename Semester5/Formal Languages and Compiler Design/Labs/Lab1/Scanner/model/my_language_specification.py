separators = ['[', ']', '{', '}', '(', ')', ';', ' ', ':']
operators = ['+', '-', '*', '/', '%', '<', '<=', '=', '>=', '>',
             '>>', '<<', '==', '&&', '||', '!', '!=', '&', '~',
             '|', '^', '++', '--', ',']
reservedWords = ['ogoaie_te', 'ogoaie_te', 'musai', 'tup', 'palit', 'drojdera',
                 'bolund', 'mintenas', 'ho', 'ioi', 'da_i', 'tzipa_incoa', 'dara',
                 'oleaca', 'bugat', 'tulai_doamne', 'olecutza', 'sod', 'noah_daca_spui_tu', 'sa_fie_si_asa',
                 'nimicnicie', 'inca_otara', 'zalud']

everything = separators + operators + reservedWords
codification = dict([(everything[i], i + 2) for i in range(len(everything))])
codification['identifier'] = 0
codification['constant'] = 1
