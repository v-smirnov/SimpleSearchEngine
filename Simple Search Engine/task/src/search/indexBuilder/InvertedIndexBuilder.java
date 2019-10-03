package search.indexBuilder;

import search.dataSource.DataSourceInterface;

import java.io.IOException;
import java.util.*;

public class InvertedIndexBuilder implements IndexBuilderInterface{
    private DataSourceInterface dataSource;

    public InvertedIndexBuilder(DataSourceInterface dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Map<String, Set<Integer>> buildIndex() throws BuildIndexException {
        try {
            List<String> data = dataSource.findAll();

            HashMap<String, Set<Integer>> index = new HashMap<>();

            int currentLine = 1;

            for (String elem : data) {
                String[] lineItems = elem.toLowerCase().split(" ");

                for (String item : lineItems) {
                    Set<Integer> lineNumbers = index.containsKey(item) ? index.get(item) : new HashSet<>();
                    lineNumbers.add(currentLine);

                    index.put(item, lineNumbers);

                }

                currentLine++;
            }

            return index;
        } catch (IOException e) {
            throw new BuildIndexException("Could not build index. " + e.getMessage());
        }
    }
}
