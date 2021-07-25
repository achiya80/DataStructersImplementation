package DataStructures;

public interface IDeque<T> {

    public void push(T value);

    public T pop();

    public void enqueue(T value);

    public T dequeue();

    public boolean isEmpty();
}
