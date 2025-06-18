package dataStructure;

public class ManualHashMap<K, V> implements Map<K, V> {

    private static final int DEFAULT_CAPACITY = 16;
    private LinkedList<Entry<K, V>>[] table; // Array of linked lists for handling collisions
    private int size; // Number of entries in the map

    public ManualHashMap() {
        this(DEFAULT_CAPACITY); // Default constructor
    }

    @SuppressWarnings("unchecked")
    public ManualHashMap(int capacity) {
        this.table = new LinkedList[capacity]; // Initialize the hash table
        this.size = 0; // Initialize size counter
    }

    @Override
    public void put(K key, V value) {
        int index = hash(key) % table.length; // Hash index
        if (table[index] == null) { // Initialize list if needed
            table[index] = new LinkedList<>();
        }

        LinkedList<Entry<K, V>> list = table[index];
        for (Entry<K, V> entry : list) {
            if (entry.key.equals(key)) { // Update existing key's value
                entry.value = value;
                return;
            }
        }
        list.add(new Entry<>(key, value)); // Add new entry if key not found
        size++;
    }

    @Override
    public V get(K key) {
        int index = hash(key) % table.length; // Hash index to retrieve
        if (table[index] != null) {
            LinkedList<Entry<K, V>> list = table[index];
            for (Entry<K, V> entry : list) {
                if (entry.key.equals(key)) {
                    return entry.value; // Return value if key is found
                }
            }
        }
        return null; // Return null if key is not found
    }

    @Override
    public void remove(K key) {
        int index = hash(key) % table.length; // Hash index for removal
        if (table[index] != null) {
            LinkedList<Entry<K, V>> list = table[index];
            boolean removed = false;

            // Create a new list for non-removed entries
            LinkedList<Entry<K, V>> newList = new LinkedList<>();
            for (Entry<K, V> entry : list) {
                if (entry.key.equals(key)) {
                    removed = true; // Mark as removed
                    size--; // Decrement size
                } else {
                    newList.add(entry); // Keep this entry
                }
            }
            table[index] = newList; // Set updated list back
            if (table[index].isEmpty()) {
                table[index] = null; // Clean up empty list in the hash table
            }
        }
    }

    @Override
    public boolean containsKey(K key) {
        int index = hash(key) % table.length; // Hash index for key lookup
        if (table[index] != null) {
            LinkedList<Entry<K, V>> list = table[index];
            for (Entry<K, V> entry : list) {
                if (entry.key.equals(key)) {
                    return true; // Key found
                }
            }
        }
        return false; // Key not found
    }

    @Override
    public int size() {
        return size; // Return number of entries
    }

    @Override
    public boolean isEmpty() {
        return size == 0; // Check if the map is empty
    }

    // Method to get all keys from the map
    public K[] keys() {
        @SuppressWarnings("unchecked")
        K[] keyArray = (K[]) new Object[size]; // Pastikan array sesuai ukuran
        int index = 0;

        for (LinkedList<Entry<K, V>> list : table) {
            if (list != null) {
                for (Entry<K, V> entry : list) {
                    if (index < size) {
                        keyArray[index++] = entry.key;
                    }
                }
            }
        }
        return keyArray;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()); // Hash function for keys
    }

    // Inner class for key-value pairs
    private static class Entry<K, V> {
        K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}