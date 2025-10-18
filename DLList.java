import java.io.Serializable; 
public class DLList<E> implements Serializable{
    protected Node<E> head;
    protected Node<E> tail;
    protected int size;

    public DLList() {
        head = new Node<E>(null);
        tail = new Node<E>(null);

        head.setNext(tail);
        tail.setPrev(head);

        size = 0;
    }

    public boolean add(E item) {

        Node<E> newNode = new Node<>(item);

        Node<E> prevLast = tail.prev();
        prevLast.setNext(newNode);
        newNode.setPrev(prevLast);
        newNode.setNext(tail);
        tail.setPrev(newNode);

        size++;

        return true;
    }

    public void add(int i, E item) {

        if (i == size) {
            add(item);
            return;
        }

        Node<E> newNode = new Node<>(item);

        Node<E> nextNode = getNode(i);

        Node<E> prevNode = nextNode.prev();

        prevNode.setNext(newNode);
        newNode.setPrev(prevNode);
        newNode.setNext(nextNode);
        nextNode.setPrev(newNode);

        size++;
    }

    public E get(int i) {
        return getNode(i).get();
    }

    public E remove(int i) {
        Node<E> node = getNode(i);

        Node<E> prevNode = node.prev();
        Node<E> nextNode = node.next();

        prevNode.setNext(nextNode);
        nextNode.setPrev(prevNode);

        size--;

        return node.get();
    }

    public boolean remove(Object o) {
        Node<E> current = head.next();
        for (int i = 0; i < size; i++) {
            if (current.get().equals(o)) {
                Node<E> prev = current.prev();
                Node<E> next = current.next();
                prev.setNext(next);
                next.setPrev(prev);
                size--;
                return true;
            }
            current = current.next();
        }
        return false;
    }

    public void clear() {
        head.setNext(tail);
        tail.setPrev(head);
        size = 0;
    }

    public int size() {
        return size;
    }

    public E set(int i, E item) {
        Node<E> node = getNode(i);
        E old = node.get();
        node.set(item);
        return old;
    }

    public String toString() {
        String nodes = "[";
        Node<E> current = head.next();
        for (int i = 0; i < size; i++) {
            nodes += current.get() + ", ";
            current = current.next();
        }
        nodes += "]";
        return nodes;
    }

    private Node<E> getNode(int i) {

        // forwards
        if (i < size / 2 + 1) {
            Node<E> current = head.next();
            for (int j = 0; j < i; j++) {
                current = current.next();
            }
            return current;
        }

        // backwards
        Node<E> current = tail.prev();
        for (int j = size - 1; j > i; j--) {
            current = current.prev();
        }
        return current;
    }
}
