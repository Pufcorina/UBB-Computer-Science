package parser.controller;

import parser.parser_utils.ParseTable;
import parser.utils.Production;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Program {

    private GrammarController grammarController = new GrammarController();
    private ParserController parserController = new ParserController();

    public Program() {
    }

    public Program(GrammarController grammarController, ParserController parserController) {
        this.grammarController = grammarController;
        this.parserController = parserController;
    }


    public Map<String, List<String>> getFirstSet() {
        return parserController.getFirstSet();
    }

    public Map<String, List<String>> getFollowSet() {
        return parserController.getFollowSet();
    }

    public ParseTable createParseTable() {
        return parserController.createParseTable();
    }

    public void parse(String w) {
        parserController.parse(w);
    }

    public List<String> getNonTerminals() {
        return grammarController.getGrammar().getNonTerminals();
    }

    public Set<String> getTerminals() {
        return grammarController.getGrammar().getTerminals();
    }

    public List<Production> getProductions() {
        return grammarController.getGrammar().getProductions();
    }

    public List<Production> getProductionsForNonterminal(String nonTerminal) {
        return grammarController.getProductionsForNonterminal(nonTerminal);
    }

    public String getStartingSymbol() {
        return grammarController.getGrammar().getStartingSymbol();
    }

    public boolean isGrammarRegular() {
        return grammarController.isGrammarRegular();
    }
}
