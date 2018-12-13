package parser.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Production {
    private String start;
    private List<String> rules;

    public Production(String start, String rules) {
        this.start = start;
        this.rules = new ArrayList<>();

        String[] splittedRules = rules.split("\\|");

        this.rules.addAll(Arrays.asList(splittedRules));
    }

    public List<String> getRules() {
        return rules;
    }

    public String getStart() {
        return start;
    }

    public String toString() {
        return start + "->" + rules.toString();
    }
}
