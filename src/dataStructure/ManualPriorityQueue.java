package dataStructure;

public class ManualPriorityQueue<T extends Comparable<T>> {

    private LinkedList<T> list = new LinkedList<>();

    public void add(T item) {
        if (list.isEmpty()) {
            list.add(item);
        } else {
            int i = 0;
            while (i < list.size() && item.compareTo(list.get(i)) >= 0) {
                i++;
            }

            // Insert manually
            LinkedList<T> newList = new LinkedList<>();
            for (int j = 0; j < i; j++) {
                newList.add(list.get(j));
            }
            newList.add(item);
            for (int j = i; j < list.size(); j++) {
                newList.add(list.get(j));
            }
            list = newList;
        }
    }

    public T poll() {
        if (isEmpty()) {
            return null;
        }
        return list.remove(0);
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }
}