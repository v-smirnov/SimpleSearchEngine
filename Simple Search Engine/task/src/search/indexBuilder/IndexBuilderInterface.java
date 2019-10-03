package search.indexBuilder;

import java.util.Map;
import java.util.Set;

public interface IndexBuilderInterface {
    Map<String, Set<Integer>> buildIndex() throws BuildIndexException;
}
