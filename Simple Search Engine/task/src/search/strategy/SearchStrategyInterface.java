package search.strategy;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SearchStrategyInterface {
    List<String> search(Map<String, Set<Integer>> index, List<String> patternList);
}
