package search.dataSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileDataSource implements DataSourceInterface{
    private String filePath;

    public FileDataSource(String filePath) {
        this.filePath = filePath;
    }

    public List<String> findAll() throws IOException {
        List<String> data = new ArrayList<>();

        Scanner sc = new Scanner(new File(filePath));

        while (sc.hasNext()) {
            data.add(sc.nextLine());
        }

        return data;
    }

    @Override
    public List<String> findByIdList(List<Integer> idList) throws IOException {
        List<String> data = new ArrayList<>();

        Scanner sc = new Scanner(new File(filePath));

        int currentLine = 1;

        while (sc.hasNext()) {
            if (idList.contains(currentLine)) {
                data.add(sc.nextLine());
            } else {
                sc.nextLine();
            }

            currentLine++;
        }

        return data;
    }
}
