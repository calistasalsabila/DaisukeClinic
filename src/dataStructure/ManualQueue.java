package dataStructure;

public class ManualQueue<T> {
    private Object[] queue;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public ManualQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new Object[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    // Menambahkan elemen ke belakang antrian (enqueue)
    public void enqueue(T item) {
        if (isFull()) {
            System.out.println("Queue is full. Cannot enqueue more elements.");
            return;
        }
        rear = (rear + 1) % capacity; // Handle wraparound
        queue[rear] = item;
        size++;
    }

    // Menghapus dan mengembalikan elemen dari depan antrian (dequeue)
    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty. Cannot dequeue any elements.");
            return null;
        }
        T item = (T) queue[front];
        queue[front] = null; // Avoid loitering
        front = (front + 1) % capacity; // Handle wraparound
        size--;
        return item;
    }

    // Mengembalikan elemen di depan antrian tanpa menghapusnya (peek)
    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty. Cannot peek.");
            return null;
        }
        return (T) queue[front];
    }

    // Memeriksa apakah antrian kosong
    public boolean isEmpty() {
        return size == 0;
    }

    // Memeriksa apakah antrian penuh
    public boolean isFull() {
        return size == capacity;
    }

    // Mengembalikan ukuran antrian
    public int size() {
        return size;
    }

    // Mencetak semua elemen dalam antrian
    public void printQueue() {
        System.out.print("Queue: ");
        if (!isEmpty()) {
            int i = front;
            do {
                System.out.print(queue[i] + " ");
                i = (i + 1) % capacity; // Handle wraparound
            } while (i != (rear + 1) % capacity);
        }
        System.out.println();
    }

    
}