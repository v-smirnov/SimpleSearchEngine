package search.strategy;

import search.dataSource.DataSourceInterface;

import java.util.*;

public class None extends Base {
    public None(DataSourceInterface dataSource) {
        super(dataSource);
    }

    @Override
    protected Set<Integer> findLines(Map<String, Set<Integer>> index, List<String> patternList) {
        Set<Integer> setForMissingElements = new HashSet<>();
        Set<Integer> setForPresentedElements = new HashSet<>();

        for (Map.Entry<String, Set<Integer>> entry : index.entrySet()) {
            if (!patternList.contains(entry.getKey())) {
                setForMissingElements.addAll(entry.getValue());
            } else {
                setForPresentedElements.addAll(entry.getValue());
            }
        }

        setForMissingElements.removeAll(setForPresentedElements);

        return setForMissingElements;
    }
}
