package algorithms;

import java.util.ArrayList;
import java.util.List;

public class NormalPrefixes implements Algorithm{

    @Override
    public List<Integer> getPrefixes(List<Integer> input, int threadCount) {
        List<Integer> result = new ArrayList<>(input.size());

        result.add(input.get(0));

        for (int i = 1; i < input.size(); i++) {
            result.add(input.get(i) + result.get(i - 1));
        }

        return result;
    }
}