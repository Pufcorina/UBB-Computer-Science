package parser.parser_utils;

import java.util.HashMap;
import java.util.Map;

public class ParseTable {
    private Map<Pair<String, String>, Pair<String, Integer>> table = new HashMap<>();

    public ParseTable() {
    }

    public ParseTable(Map<Pair<String, String>, Pair<String, Integer>> table) {
        this.table = table;
    }

    public Map<Pair<String, String>, Pair<String, Integer>> getTable() {
        return table;
    }

    public void setTable(Map<Pair<String, String>, Pair<String, Integer>> table) {
        this.table = table;
    }

    public void put(Pair<String, String> key, Pair<String, Integer> value) {
        table.put(key, value);
    }

    public Pair<String, Integer> get(Pair<String, String> key) {
        for (Map.Entry<Pair<String, String>, Pair<String, Integer>> entry : table.entrySet()) {
            if (entry.getValue() != null) {
                Pair<String, String> currentKey = entry.getKey();
                Pair<String, Integer> currentValue = entry.getValue();

                if (currentKey.getKey().equals(key.getKey()) && currentKey.getValue().equals(key.getValue())) {
                    return currentValue;
                }
            }
        }

        return null;
    }

    public boolean containsKey(Pair<String, String> key) {
        boolean result = false;
        for (Pair<String, String> currentKey : table.keySet()) {
            if (currentKey.getKey().equals(key.getKey()) && currentKey.getValue().equals(key.getValue())) {
                result = true;
            }
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<Pair<String, String>, Pair<String, Integer>> entry : table.entrySet()) {
            if (entry.getValue() != null) {
                Pair<String, String> key = entry.getKey();
                Pair<String, Integer> value = entry.getValue();

                sb.append("M").append(key).append(" = ").append(value).append("   ");
            }
        }

        return sb.toString();
    }
}
