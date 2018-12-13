package parser.controller;

import parser.utils.Grammar;
import parser.utils.Production;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class GrammarController {
    private Grammar grammar = new Grammar();

    public GrammarController() {
        getGrammarFromFile();
    }

    public void getGrammarFromFile() {
        try {
            int i = 0;
            for (String line : Files.readAllLines(Paths.get("data/grammar.txt"))) {
                String[] tokens = line.split(" ");
                for (int j = 0; j < tokens.length; j++) {
                    if (i == 0) {
                        grammar.getNonTerminals().add(tokens[j]);
                    }
                    if (i == 1) {
                        grammar.getTerminals().add(tokens[j]);
                    }
                    if (i == 2) {
                        grammar.getProductions().add(new Production(tokens[j], tokens[j + 1]));
                        j++;
                    }
                    if (i == 3) {
                        grammar.setStartingSymbol(tokens[j]);
                    }
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(grammar.toString());
    }


    public boolean isGrammarRegular() {
        boolean result = true;

        // verify first if the grammar is right-linear
        if (!isGrammarRightLinear()) {
            result = false;
        }

        // get the list of production from the grammar and iterate them
        List<Production> productions = grammar.getProductions();
        for (Production currentProduction : productions) {
            List<String> rules = currentProduction.getRules();
            String productionStart = currentProduction.getStart();

            for (String rule : rules) {
                // check that a rule is accepted in a regular grammar, concerning epsilon
                if (!isRuleEpsilonValid(productionStart, rule)) {
                    result = false;
                }
            }
        }

        return result;
    }

    private boolean isRuleEpsilonValid(String start, String rule) {
        boolean result = true;
        List<String> ruleParts = Arrays.asList(rule.split(""));

        // if ε is a rule, the productionStart can be none other than the starting symbol
        String startingSymbol = grammar.getStartingSymbol();
        if (rule.equals("ε") && !start.equals(startingSymbol)) {
            result = false;
        }

        // if S->ε is a production, then S cannot appear in the RHS of any production
        if (ruleParts.size() == 2 && ruleParts.get(1).equals(grammar.getStartingSymbol()) && grammarContainsStartToEpsilon()) {
            result = false;
        }

        return result;
    }


    /**
     * A right-linear grammar must satisfy the following conditions:
     * 1. every character from the productions to belong to the alphabet (terminal, nonterminal or ε)
     * 2. productions must only be of one of the following forms:
     * i)  A->a (terminal)
     * ii) A->aB (terminal + nonterminal)
     *
     * @return - true -> if the grammar is right linear (satisfies above conditions)
     * - false -> otherwise
     */
    public boolean isGrammarRightLinear() {
        boolean result = true;

        List<Production> productions = grammar.getProductions();
        for (Production currentProduction : productions) {
            // check if the productionStart is a non-terminal defined by the grammar
            String productionStart = currentProduction.getStart();
            if (!grammar.getNonTerminals().contains(productionStart)) {
                result = false;
            }

            // iterate rules resulting from the current start, and check their validity
            List<String> rules = currentProduction.getRules();
            for (String rule : rules) {
                List<String> ruleParts = Arrays.asList(rule.split(""));

                // a valid rule in a regular grammar can only be formed of maximum 2 characters
                if (ruleParts.size() > 2) {
                    result = false;
                }

                // check that every character is in the alphabet
                for (String rulePart : ruleParts) {
                    boolean isTerminalOrNonterminal = grammar.getTerminals().contains(rulePart) || grammar.getNonTerminals().contains(rulePart);
                    boolean isInAlphabet = isTerminalOrNonterminal || rulePart.equals("ε");
                    if (!isInAlphabet) {
                        result = false;
                    }
                }

                //  if the rule is just a character, it must be a terminal or ε
                if (ruleParts.size() == 1 && (!grammar.getTerminals().contains(ruleParts.get(0)) && !ruleParts.get(0).equals("ε"))) {
                    result = false;
                }

                // ε can ONLY appear alone in the rule
                if (ruleParts.size() > 1 && ruleParts.contains("ε")) {
                    result = false;
                }

                // check that the order of characters is valid => terminal + nonterminal
                if (ruleParts.size() == 2 &&
                        !(grammar.getTerminals().contains(ruleParts.get(0)) && grammar.getNonTerminals().contains(ruleParts.get(1)))) {
                    result = false;
                }
            }
        }

        return result;
    }

    public boolean grammarContainsStartToEpsilon() {
        boolean result = false;

        List<Production> productions = grammar.getProductions();
        for (Production production : productions) {
            String startingSymbol = grammar.getStartingSymbol();
            if (production.getStart().equals(startingSymbol)) {
                for (String rule : production.getRules()) {
                    if (rule.equals("ε")) {
                        result = true;
                    }
                }
            }
        }

        return result;
    }

    public List<Production> getProductionsForNonterminal(String nonterminal) {
        List<Production> productionsForNonterminal = new LinkedList<>();

        List<Production> productions = grammar.getProductions();
        for (Production production : productions) {
            if (production.getStart().equals(nonterminal)) {
                productionsForNonterminal.add(production);
            }
        }

        return productionsForNonterminal;
    }


    public Grammar getGrammar() {
        return grammar;
    }
}

