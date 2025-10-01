import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    private class Node {
        private Node prev;//可以在嵌套类里调用同样类型的东西作为它的属性，类似自指
        private final T item;
        private Node next;

        public Node(Node a, T b, Node c) {
            prev = a;
            item = b;
            next = c;
        }
    }

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

    public String toString() {
        return toList().toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDeque61BIterator();
    }

    @Override
    public void addFirst(T x) {
        Node oldFirst = sentinel.next;//先找到原来的first节点，给它命名
        Node newNode = new Node(sentinel, x, sentinel.next);//再设置新节点及其前后关系
        sentinel.next = newNode;//再更改sentinel的next和oldfirst的prev
        oldFirst.prev = newNode;
        size++;
    }

    @Override
    public void addLast(T x) {
        Node oldLast = sentinel.prev;
        Node newNode = new Node(oldLast, x, sentinel);
        oldLast.next = newNode;
        sentinel.prev = newNode;
        size++;
    }

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

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            Node firstNode = sentinel.next;      // 保存要移除的节点
            T item = firstNode.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size--;
            return item;
        }
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            Node firstNode = sentinel.prev;      // 保存要移除的节点
            T item = firstNode.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size--;
            return item;
        }
    }

    @Override
    public T get(int index) {
        if (index < 0) {
            return null;
        } else if (index >= size) {
            return null;
        } else {
            Node a = sentinel;
            for (int i = 0; i <= index; i++) {
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
        return getRecursiveHelper(currentNode.next, index - 1);
    }

    @Override
    public T getRecursive(int index) {
        if (index < 0) {
            return null;
        } else if (index >= size) {
            return null;
        } else {//这里需要一个增加节点作为参数的辅助方法
            return getRecursiveHelper(sentinel.next, index);
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
            Node returnItem = sentinel;
            returnItem = returnItem.next;
            wizPos += 1;
            return returnItem.item;
        }
    }
}
