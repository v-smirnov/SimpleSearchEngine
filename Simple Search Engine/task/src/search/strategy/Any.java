package search.strategy;

import search.dataSource.DataSourceInterface;

import java.util.*;

public class Any extends Base {

    public Any(DataSourceInterface dataSource) {
        super(dataSource);
    }

    @Override
    protected Set<Integer> findLines(Map<String, Set<Integer>> index, List<String> patternList) {
        Set<Integer> result = new HashSet<>();

        for (String pattern: patternList) {
            if (index.get(pattern) != null) {
                result.addAll(index.get(pattern));
            }
        }

        return result;
    }
}
