package search.strategy;

import search.dataSource.DataSourceInterface;

import java.util.*;

public class All extends Base {
    public All(DataSourceInterface dataSource) {
        super(dataSource);
    }

    @Override
    protected Set<Integer> findLines(Map<String, Set<Integer>> index, List<String> patternList) {
        Set<Integer> result = new HashSet<>();

        for (String pattern: patternList) {
            if (index.get(pattern) != null) {
                if (result.isEmpty()) {
                    result.addAll(index.get(pattern));
                } else {
                    result.retainAll(index.get(pattern));
                }
            }
        }

        return result;
    }
}
