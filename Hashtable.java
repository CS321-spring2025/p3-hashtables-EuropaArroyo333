
import java.io.IOException;
import java.io.PrintWriter;

public abstract class Hashtable<T>{
    protected HashObject<T>[] table;
    protected int size;
    protected int numElements;
    
    @SuppressWarnings("unchecked")
    public Hashtable(int capacity) {
        this.size = capacity;
        this.numElements = 0;
        this.table = new HashObject[size];
    }
    
    protected int positiveMod(int dividend, int divisor) {
        int quotient = dividend % divisor;
        return (quotient < 0) ? quotient + divisor : quotient;
    }
    
    public abstract void insert(T key);
    public abstract boolean search(T key);
    
    public void dumpToFile(String fileName) {
        try (PrintWriter out = new PrintWriter(fileName)) {
            for (int i = 0; i < size; i++) {
                if (table[i] != null) {
                    out.println("table[" + i + "]: " + table[i].toString());
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }



}