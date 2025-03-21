public class DoubleHashing<T> extends Hashtable<T>{

    public DoubleHashing(int capacity) {
        super(capacity);
    }
    
    private int secondaryHash(T key) {
        return 1 + positiveMod(key.hashCode(), size - 2);
    }
    
    @Override
    public void insert(T key) {
        int hash = positiveMod(key.hashCode(), size);
        int step = secondaryHash(key);
        int probeCount = 0;
        
        while (table[hash] != null) {
            if (table[hash].getKey().equals(key)) {
                table[hash].incrementFrequency();
                return;
            }
            hash = positiveMod(hash + step, size);
            probeCount++;
        }
        
        table[hash] = new HashObject<>(key);
        table[hash].setProbeCount(probeCount);
        numElements++;
    }
    
    @Override
    public boolean search(T key) {
        int hash = positiveMod(key.hashCode(), size);
        int step = secondaryHash(key);
        
        while (table[hash] != null) {
            if (table[hash].getKey().equals(key)) {
                return true;
            }
            hash = positiveMod(hash + step, size);
        }
        return false;
    }

    
}