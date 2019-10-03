package search.dataSource;

import java.io.IOException;
import java.util.List;

public interface DataSourceInterface {
    List<String> findAll() throws IOException;

    List<String> findByIdList(List<Integer> idList) throws IOException;
}
