public class LinearProbing<T> extends Hashtable<T>{
    public LinearProbing(int capacity) {
        super(capacity);
    }
    
    @Override
    public void insert(T key) {
        int hash = positiveMod(key.hashCode(), size);
        int probeCount = 0;
        
        while (table[hash] != null) {
            if (table[hash].getKey().equals(key)) {
                table[hash].incrementFrequency();
                return;
            }
            hash = (hash + 1) % size;
            probeCount++;
        }
        
        table[hash] = new HashObject<>(key);
        table[hash].setProbeCount(probeCount);
        numElements++;
    }
    
    @Override
    public boolean search(T key) {
        int hash = positiveMod(key.hashCode(), size);
        
        while (table[hash] != null) {
            if (table[hash].getKey().equals(key)) {
                return true;
            }
            hash = (hash + 1) % size;
        }
        return false;
    }





}