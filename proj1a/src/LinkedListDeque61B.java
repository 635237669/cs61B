import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T>{
    private final Node sentinel;
    private int size;

    public LinkedListDeque61B() {
        size = 0;
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }
//是双端队列，比较的时候应当注意元素相同与顺序相同
    @Override
    public boolean equals(Object o) {
        if (o instanceof LinkedListDeque61B oas) {
            if (this.size() != oas.size()) return false;
            for (int i = 0; i < this.size(); i++) {
                Object thisElem = this.get(i);
                Object otherElem = oas.get(i);
                if (!thisElem.equals(otherElem)) return false;
            }
            return true;
        }
        return false;
    }

    public String toString(){return toList().toString();}
//deepseek版，使用了类型转换和<?>
//@Override
//public boolean equals(Object o) {
//    if (this == o) return true;
//    if (!(o instanceof Deque61B<?>)) return false;
//    return dequeEquals((Deque61B<?>) o);
//}

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new LinkedListDeque61BIterator();
    }

    /**
     * Add {@code x} to the front of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addFirst(T x) {
        Node oldFirst = sentinel.next;//先找到原来的first节点，给它命名
        Node newNode = new Node(sentinel, x, sentinel.next);//再设置新节点及其前后关系
        sentinel.next = newNode;//再更改sentinel的next和oldfirst的prev
        oldFirst.prev = newNode;
        size++;
    }

    /**
     * Add {@code x} to the back of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addLast(T x) {
        Node oldLast = sentinel.prev;
        Node newNode = new Node(oldLast, x, sentinel);
        oldLast.next = newNode;
        sentinel.prev = newNode;
        size++;
    }

    /**
     * Returns a List copy of the deque. Does not alter the deque.
     *
     * @return a new list copy of the deque.
     */
    @Override
    public List<T> toList() {
        List<T> result = new ArrayList<>();//不放到实例变量里因为不应该同时维护数组列表和双链表
        Node current = sentinel.next;

        while (current != sentinel) {
            result.add(current.item);
            current = current.next;
        }
        return result;
    }

    /**
     * Returns if the deque is empty. Does not alter the deque.
     *
     * @return {@code true} if the deque has no elements, {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the size of the deque. Does not alter the deque.
     *
     * @return the number of items in the deque.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Remove and return the element at the front of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeFirst() {
        if(size==0){
            return null;
        }else{
            Node firstNode = sentinel.next;      // 保存要移除的节点
            T item = firstNode.item;
            sentinel.next=sentinel.next.next;
            sentinel.next.prev=sentinel;
            size--;
            return item;
        }
    }

    /**
     * Remove and return the element at the back of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeLast() {
        if(size==0){
            return null;
        }else{
            Node firstNode = sentinel.prev;      // 保存要移除的节点
            T item = firstNode.item;
            sentinel.prev=sentinel.prev.prev;
            sentinel.prev.next=sentinel;
            size--;
            return item;
        }
    }

    /**
     * The Deque61B abstract data type does not typically have a get method,
     * but we've included this extra operation to provide you with some
     * extra programming practice. Gets the element, iteratively. Returns
     * null if index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public T get(int index) {
        if (index<0){
            return null;
        }else if(index>=size){
            return null;
        }else{
            Node a=sentinel;
            for(int i=0;i<=index;i++) {
                 a = a.next;
            }
            return a.item;
        }

    }

    private T getRecursiveHelper(Node currentNode, int index) {
        // 这个辅助函数的意思：从currentNode开始，它对应的是第0个节点（相对位置），然后我们要找的是当前链中的第index个节点。
        // 但是，我们也可以理解为：我们要找的是从当前节点开始的第index个节点（0就是当前节点，1就是下一个节点，等等）
        if (index == 0) {
            return currentNode.item;
        }
        return getRecursiveHelper(currentNode.next, index-1);
    }

    /**
     * This method technically shouldn't be in the interface, but it's here
     * to make testing nice. Gets an element, recursively. Returns null if
     * index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public T getRecursive(int index) {
        if (index<0){
            return null;
        }else if(index>=size){
            return null;
        }else {//这里需要一个增加节点作为参数的辅助方法
            return getRecursiveHelper(sentinel.next,index);
        }
    }

private class LinkedListDeque61BIterator implements Iterator<T> {
        private int wizPos;

        public LinkedListDeque61BIterator() {
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < size;
        }
        public T next() {
            Node returnItem =sentinel;
            returnItem=returnItem.next;
            wizPos += 1;
            return returnItem.item;
        }
    }

    private class Node {
        private Node prev;
        private final T item;
        private Node next;
        public Node(Node a,T b,Node c){
            prev=a;
            item=b;
            next=c;
        }
    }
}
