



public class HashObject<T> {
    private T key;
    private int frequency;
    private int probeCount;
    
    public HashObject(T key) {
        this.key = key;
        this.frequency = 1;
        this.probeCount = 0;
    }
    
    public T getKey() {
        return key;
    }
    
    public void incrementFrequency() {
        frequency++;
    }

    public int getProbeCount() {
        return probeCount;
    }
    
    public void setProbeCount(int count) {
        this.probeCount = count;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        HashObject<?> that = (HashObject<?>) obj;
        return key.equals(that.key);
    }
    
    @Override
    public String toString() {
        return key.toString() + " " + frequency + " " + probeCount;
    }


}