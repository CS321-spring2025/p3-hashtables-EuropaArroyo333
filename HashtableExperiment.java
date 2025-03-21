import java.io.*;
import java.util.*;

public class HashtableExperiment {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java HashtableExperiment <dataSource> <loadFactor> [<debugLevel>]");
            return;
        }
        
        int dataSource = Integer.parseInt(args[0]);
        double loadFactor = Double.parseDouble(args[1]);
        int debugLevel = (args.length > 2) ? Integer.parseInt(args[2]) : 0;
        
        int tableSize = TwinPrimeGenerator.generateTwinPrime(95500, 96000);
        int numElements = (int) Math.ceil(loadFactor * tableSize);
        
        LinearProbing<Object> linearTable = new LinearProbing<>(tableSize);
        DoubleHashing<Object> doubleTable = new DoubleHashing<>(tableSize);
        
        List<Object> data = generateData(dataSource, numElements);
        
        for (Object obj : data) {
            linearTable.insert(obj);
            doubleTable.insert(obj);
        }
        
        printSummary(dataSource, numElements, linearTable, doubleTable);
        
        if (debugLevel == 1) {
            linearTable.dumpToFile("linear-dump.txt");
            doubleTable.dumpToFile("double-dump.txt");
        }
    }
    
    private static List<Object> generateData(int dataSource, int numElements) {
        List<Object> data = new ArrayList<>();
        Random random = new Random();
        
        switch (dataSource) {
            case 1: 
                for (int i = 0; i < numElements; i++) {
                    data.add(random.nextInt());
                }
                break;
            case 2:
                long current = new Date().getTime();
                for (int i = 0; i < numElements; i++) {
                    data.add(new Date(current));
                    current += 1000;
                }
                break;
            case 3:
                try (BufferedReader reader = new BufferedReader(new FileReader("word-list.txt"))) {
                    String word;
                    while ((word = reader.readLine()) != null && data.size() < numElements) {
                        data.add(word);
                    }
                } catch (IOException e) {
                    System.err.println("Error reading word-list.txt");
                }
                break;
        }
        return data;
    }
    
    private static void printSummary(int dataSource, int numElements, Hashtable<?> linearTable, Hashtable<?> doubleTable) {
        System.out.println("Data Source: " + dataSource);
        System.out.println("Total Inserted: " + numElements);
        System.out.println("Linear Probing Avg Probes: " + calculateAverageProbes(linearTable));
        System.out.println("Double Hashing Avg Probes: " + calculateAverageProbes(doubleTable));
    }
    
    private static double calculateAverageProbes(Hashtable<?> table) {
        int totalProbes = 0;
        for (HashObject<?> obj : table.table) {
            if (obj != null) {
                totalProbes += obj.getProbeCount();
            }
        }
        return (double) totalProbes / table.numElements;
    }
}
