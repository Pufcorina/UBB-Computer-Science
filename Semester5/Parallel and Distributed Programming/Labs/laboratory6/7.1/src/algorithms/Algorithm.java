package algorithms;

import java.util.List;

public interface Algorithm {
    List<Integer> getPrefixes(List<Integer> input, int threadCount);
}