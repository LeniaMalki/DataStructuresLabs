package exercises;

public class FixedSizeArrayQueue<T> {

    int head;
    int tail;
    T[] list;

    @SuppressWarnings("unchecked")
    public FixedSizeArrayQueue(int length) {
        this.list = (T[]) new Object[length];
    }

    void enqueue(T elem){
        if (tail == head-1) {
            System.out.println("stack is full, Some data will dissapear at the bottom of the stack");
            head++;
        }
        tail = tail++;
        list[tail] = elem;
    }

    T dequeue(){
        int i = head;
        head = (head+1)%list.length;
        return list[i];
    }
}
