package search.strategy;

import search.dataSource.DataSourceInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

abstract public class Base implements SearchStrategyInterface {
    private DataSourceInterface dataSource;

    public Base(DataSourceInterface dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<String> search(Map<String, Set<Integer>> index, List<String> patternList) {
        try {
            return dataSource.findByIdList(new ArrayList<>(findLines(index, patternList)));
        } catch (IOException e) {
            /*_*/
        }

        return new ArrayList<>();
    }


    abstract protected Set<Integer> findLines(Map<String, Set<Integer>> index, List<String> patternList);
}
