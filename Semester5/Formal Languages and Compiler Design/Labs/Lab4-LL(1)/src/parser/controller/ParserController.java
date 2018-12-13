package parser.controller;

import parser.parser_utils.LLParser;
import parser.parser_utils.Pair;
import parser.parser_utils.ParseTable;
import parser.utils.Grammar;
import parser.utils.Production;

import java.util.*;

public class ParserController {
    private GrammarController grammarController = new GrammarController();
    private Grammar grammar;
    private LLParser llParser = new LLParser();
    private ParseTable parseTable = new ParseTable();

    public ParserController() {
    }

    public ParserController(GrammarController grammarController) {
        this.grammarController = grammarController;
        grammar = grammarController.getGrammar();
    }

    public void parse(String w) {
        String startingSymbol = grammar.getStartingSymbol();
        createParseTable();

        boolean result = llParser.parse(parseTable, w, startingSymbol);

        if (result) {
            System.out.println("Sequence " + w + " accepted.");
            Stack<String> pi = llParser.getPi();
            System.out.println(pi);
            System.out.println(displayPiProductions(pi));
        } else {
            System.out.println("Sequence " + w + " is not accepted.");
        }
    }

    private String displayPiProductions(Stack<String> pi) {
        StringBuilder sb = new StringBuilder();
        List<Pair<Integer, Production>> indexedProductions = getIndexedProductions();

        for (String productionIndexString : pi) {
            if (productionIndexString.equals("ε")) {
                continue;
            }
            Integer productionIndex = Integer.parseInt(productionIndexString);
            for (Pair<Integer, Production> indexedProduction : indexedProductions) {
                if (indexedProduction.getKey().equals(productionIndex)) {
                    sb.append(indexedProduction.getKey()).append(": ").append(indexedProduction.getValue()).append("  ");
                }
            }
        }

        return sb.toString();
    }

    public Map<String, List<String>> getFirstSet() {
        Map<String, List<String>> firstSet = new HashMap<>();
        for (String nonTerminal : grammar.getNonTerminals()) {
            String firstsString = this.first(nonTerminal);
            List<String> firsts = Arrays.asList(firstsString.split(""));
            firstSet.put(nonTerminal, firsts);
        }

        return firstSet;
    }

    public Map<String, List<String>> getFollowSet() {
        Map<String, List<String>> followSet = new HashMap<>();
        for (String nonTerminal : grammar.getNonTerminals()) {
            String firstsString = this.follow(nonTerminal);
            List<String> firsts = Arrays.asList(firstsString.split(""));
            followSet.put(nonTerminal, firsts);
        }

        return followSet;
    }

    private String first(String nonTerminal) {
        int found = 0;
        StringBuilder temp = new StringBuilder();

        // for each production of the nonTerminal symbol given
        for (Production production : grammarController.getProductionsForNonterminal(nonTerminal)) {
            // for each rule of the production
            for (String rule : production.getRules()) {
                temp.append(first(rule, found));
            }
        }

        return removeDuplicates(temp.toString());
    }

    private String first(String rule, Integer found) {
        StringBuilder temp = new StringBuilder();

        // for each symbol in the rule
        for (String symbol : rule.split("")) {
            if (grammar.getNonTerminals().contains(symbol)) {
                String str = first(symbol);
                boolean isEpsilonOnlyNonterminalProduction = str.length() == 1 && str.charAt(0) == 'ε';
                if (!isEpsilonOnlyNonterminalProduction) {
                    temp.append(str);
                    found = 1;
                }
            } else {
                temp.append(symbol);
                break;
            }

            if (found == 1) {
                break;
            }
        }

        return temp.toString();
    }

    public String firstByRule(String rule) {
        int found = 0;
        return first(rule, found);
    }

    private String follow(String nonTerminal) {
        int found = 0;
        StringBuilder temp = new StringBuilder();

        if (nonTerminal.equals(grammar.getStartingSymbol())) {
            temp = new StringBuilder("ε");
        }

        List<String> nonTerminalsList = grammar.getNonTerminals();
        int index = nonTerminalsList.indexOf(nonTerminal);

        // for each non-terminal
        for (int j = 0; j < nonTerminalsList.size(); j++) {
            // for each production of the non-terminal
            String currentNonTerminal = (String) nonTerminalsList.get(j);
            List<Production> productionsForNonTerminal = grammarController.getProductionsForNonterminal(currentNonTerminal);
            for (Production production : productionsForNonTerminal) {
                // get rules in the production
                List<String> productionRules = production.getRules();
                for (String rule : productionRules) {
                    temp.append(follow(rule, found, currentNonTerminal, nonTerminal, j, index));
                }
            }
        }

        return removeDuplicates(temp.toString());
    }

    private String follow(String rule, Integer found, String currentNonTerminal, String nonTerminal, Integer j, Integer index) {
        StringBuilder temp = new StringBuilder();
        List<String> nonTerminalsList = grammar.getNonTerminals();

        // for each char in the ruleChars
        String[] ruleSymbols = rule.split("");
        for (int i = 0; i < ruleSymbols.length; i++) {
            // entering each symbol in rule
            // finding the non-terminal whose follow set is to be found

            // if the current symbol is equal to the non-terminal given
            if (ruleSymbols[i].equals(nonTerminal)) {
                // if it is the last terminal/non-terminal then follow of current non-terminal
                if (i == ruleSymbols.length - 1) {
                    if (j < index) {
                        temp.append(follow(currentNonTerminal));
                    }
                } else {
                    // for each non-terminal
                    for (String nonTerminal2 : nonTerminalsList) {
                        // first of next non-terminal otherwise (else later…)
                        if (ruleSymbols[i + 1].equals(nonTerminal2)) {
                            String[] firsts = first(nonTerminal2).split("");
                            // for each char if first array
                            for (String first : firsts) {
                                if (first.equals("ε")) //if first includes epsilon
                                {
                                    if (i + 1 == ruleSymbols.length - 1) {
                                        temp.append(follow(currentNonTerminal)); //when non-terminal is second last
                                    } else {
                                        temp.append(follow(nonTerminal2));
                                    }
                                } else {
                                    temp.append(first); //include whole first set except epsilon
                                }
                            }
                            found = 1;
                        }
                    }
                    if (found != 1)
                        temp.append(ruleSymbols[i + 1]); //follow set will include terminal (else is here)
                }
            }
        }


        return temp.toString();
    }

    private String removeDuplicates(String str) {
        char[] chars = str.toCharArray();
        Set<Character> charSet = new LinkedHashSet<>();
        for (char c : chars) {
            charSet.add(c);
        }

        StringBuilder sb = new StringBuilder();
        for (Character character : charSet) {
            sb.append(character);
        }

        return sb.toString();
    }

    public ParseTable createParseTable() {
        List<String> rowSymbols = new LinkedList<>();
        rowSymbols.addAll(grammar.getNonTerminals());
        rowSymbols.addAll(grammar.getTerminals());
        rowSymbols.add("$");

        List<String> columnSymbols = new LinkedList<>(grammar.getTerminals());
        columnSymbols.add("$");

        for (String rowSymbol : rowSymbols) {
            for (String columnSymbol : columnSymbols) {
                // M(a, a) = pop
                // M($, $) = acc

                if (rowSymbol.equals(columnSymbol)) {
                    Pair<String, String> key = new Pair<>(rowSymbol, columnSymbol);
                    Pair<String, Integer> value;

                    if (rowSymbol.equals("$")) {
                        value = new Pair<>("acc", -1);
                    } else {
                        value = new Pair<>("pop", -1);
                    }

                    parseTable.put(key, value);
                } else if (grammar.getNonTerminals().contains(rowSymbol)) {
                    /* 1) M(A, a) = (α, i), if:
                            a) a ∈ first(α)
                            b) a != ε
                            c) A -> α production with index i

                       2) M(A, b) = (α, i), if:
                            a) ε ∈ first(α)
                            b) whichever b ∈ follow(A)
                            c) A -> α production with index i
                    */

                    Pair<String, String> key = new Pair<>(rowSymbol, columnSymbol);
                    Pair<String, Integer> value = null;

                    List<Pair<Integer, Production>> indexedProductionsForNonTerminal = getIndexedProductionsForNonTerminal(rowSymbol);
                    for (Pair<Integer, Production> indexedProduction : indexedProductionsForNonTerminal) {
                        Integer index = indexedProduction.getKey();
                        Production production = indexedProduction.getValue();

                        String rule = production.getRules().get(0);
                        if (firstByRule(rule).contains(columnSymbol) && !columnSymbol.equals("ε")) {
                            key = new Pair<>(rowSymbol, columnSymbol);
                            value = new Pair<>(rule, index);
                            parseTable.put(key, value);

                        } else if (firstByRule(rule).contains("ε")) {
                            String followRowSymbol = follow(rowSymbol);
                            for (int i = 0; i < followRowSymbol.length(); i++) {
                                String b = String.valueOf(followRowSymbol.charAt(i));
                                if (b.equals("ε")) {
                                    key = new Pair<>(rowSymbol, "$");
                                } else {
                                    key = new Pair<>(rowSymbol, b);
                                }
                                value = new Pair<>(rule, index);

                                if (!parseTable.containsKey(key)) {
                                    parseTable.put(key, value);
                                }
                            }

                        }
                    }
                }
            }
        }

        return parseTable;
    }

    private List<Pair<Integer, Production>> getIndexedProductionsForNonTerminal(String nonTerminal) {
        List<Pair<Integer, Production>> indexedProductionsForNonTerminal = new LinkedList<>();

        for (Pair<Integer, Production> indexedProduction : getIndexedProductions()) {
            Production production = indexedProduction.getValue();
            if (production.getStart().equals(nonTerminal)) {
                indexedProductionsForNonTerminal.add(indexedProduction);
            }
        }

        return indexedProductionsForNonTerminal;
    }

    private List<Pair<Integer, Production>> getIndexedProductions() {
        List<Pair<Integer, Production>> indexedProductions = new LinkedList<>();

        int i = 1;
        for (Production production : grammar.getProductions()) {
            for (String rule : production.getRules()) {
                Production newProduction = new Production(production.getStart(), rule);
                indexedProductions.add(new Pair<>(i, newProduction));
                i++;
            }
        }

        return indexedProductions;
    }
}

