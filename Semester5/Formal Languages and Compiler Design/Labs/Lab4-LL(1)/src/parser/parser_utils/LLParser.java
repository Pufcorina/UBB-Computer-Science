package parser.parser_utils;

import java.util.Stack;

public class LLParser {
    private Stack<String> alpha = new Stack<>();
    private Stack<String> beta = new Stack<>();
    private Stack<String> pi = new Stack<>();

    public LLParser() {
    }

    public LLParser(Stack<String> alpha, Stack<String> beta, Stack<String> pi) {
        this.alpha = alpha;
        this.beta = beta;
        this.pi = pi;
    }

    public boolean parse(ParseTable parseTable, String w, String startingSymbol) {
        initializeStacks(w, startingSymbol);
        boolean go = true;
        boolean result = true;

        while (go) {
            String betaHead = beta.peek();
            String alphaHead = alpha.peek();

            if (betaHead.equals("$") && alphaHead.equals("$")) {
                return result;
            }

            Pair<String, String> heads = new Pair<>(betaHead, alphaHead);
            Pair<String, Integer> parseTableEntry = parseTable.get(heads);

            if (parseTableEntry == null) {
                go = false;
                result = false;
            } else {
                String production = parseTableEntry.getKey();
                Integer productionPos = parseTableEntry.getValue();

                if (productionPos == -1 && production.equals("acc")) {
                    go = false;
                } else if (productionPos == -1 && production.equals("pop")) {
                    beta.pop();
                    alpha.pop();
                } else {
                    beta.pop();
                    if (!production.equals("ε")) {
                        pushAsChars(production, beta);
                    }
                    pi.push(productionPos.toString());
                }
            }
        }

        return result;
    }

    private void initializeStacks(String w, String startingSymbol) {
        alpha.push("$");
        pushAsChars(w, alpha);

        beta.push("$");
        beta.push(startingSymbol);

        pi.push("ε");
    }

    private void pushAsChars(String w, Stack<String> stack) {
        for (int i = w.length() - 1; i >= 0; i--)
            stack.push(String.valueOf(w.charAt(i)));
    }

    public Stack<String> getAlpha() {
        return alpha;
    }

    public void setAlpha(Stack<String> alpha) {
        this.alpha = alpha;
    }

    public Stack<String> getBeta() {
        return beta;
    }

    public void setBeta(Stack<String> beta) {
        this.beta = beta;
    }

    public Stack<String> getPi() {
        return pi;
    }

    public void setPi(Stack<String> pi) {
        this.pi = pi;
    }
}

