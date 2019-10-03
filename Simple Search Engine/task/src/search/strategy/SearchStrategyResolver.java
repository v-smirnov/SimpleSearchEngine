package search.strategy;

import search.dataSource.DataSourceInterface;

import java.util.HashMap;
import java.util.Map;

public class SearchStrategyResolver {
    public static SearchStrategyInterface resolve(StrategyTypeEnum strategyType, DataSourceInterface dataSource) {
        SearchStrategyInterface strategy = getMap(dataSource).get(strategyType);

        return strategy == null ? new Any(dataSource) : strategy;
    }

    private static Map<StrategyTypeEnum, SearchStrategyInterface> getMap(DataSourceInterface dataSource) {
        Map<StrategyTypeEnum, SearchStrategyInterface> map = new HashMap<>();

        map.put(StrategyTypeEnum.ALL, new All(dataSource));
        map.put(StrategyTypeEnum.ANY, new Any(dataSource));
        map.put(StrategyTypeEnum.NONE, new None(dataSource));

        return map;
    }
}
