package search;

import search.dataSource.DataSourceInterface;
import search.dataSource.FileDataSource;
import search.indexBuilder.BuildIndexException;
import search.indexBuilder.InvertedIndexBuilder;
import search.strategy.SearchStrategyInterface;
import search.strategy.SearchStrategyResolver;
import search.strategy.StrategyTypeEnum;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        DataSourceInterface dataSource = new FileDataSource(args[1]);

        try {
            Map<String, Set<Integer>> index = new InvertedIndexBuilder(dataSource).buildIndex();

            printMenu();

            performAction(dataSource, index);
        } catch (BuildIndexException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("=== Menu ===");
        System.out.println("1. Find a person");
        System.out.println("2. Print all people");
        System.out.println("0. Exit");
    }

    private static void performAction(DataSourceInterface dataSource, Map<String, Set<Integer>> index) {
        Scanner sc = new Scanner(System.in);

        loop: while (true) {
            int action = Integer.parseInt(sc.nextLine());

            switch (action) {
                case 0:
                    System.out.println("Bye!");
                    break loop;
                case 1:
                    System.out.println("Select a matching strategy: ALL, ANY, NONE");

                    StrategyTypeEnum strategyType;
                    try {
                        strategyType = StrategyTypeEnum.valueOf(sc.nextLine().toUpperCase());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Wrong strategy selected");
                        printMenu();
                        break;
                    }

                    System.out.println("Enter a name or email to search all suitable people.");
                    ArrayList<String> patternList = new ArrayList<>(Arrays.asList(sc.nextLine().toLowerCase().split(" ")));

                    SearchStrategyInterface searchStrategy = SearchStrategyResolver.resolve(strategyType, dataSource);

                    searchStrategy.search(index, patternList).forEach(System.out::println);

                    printMenu();
                    break;
                case 2:
                    printAllPeople(dataSource);
                    printMenu();
                    break;
                default:
                    System.out.println("Incorrect option! Try again.");
                    printMenu();
                    break;
            }
        }

        sc.close();
    }

    private static void printAllPeople(DataSourceInterface dataSource) {
        System.out.println();
        System.out.println("=== List of people ===");

        try {
            dataSource.findAll().forEach(System.out::println);
        } catch (IOException e) {
            /*_*/
        }
    }
}
