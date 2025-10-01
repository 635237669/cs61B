import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Stack;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node root;//设置树根，其实也是树

    private class Node {
        private final K key; //
        private V val; //
        private Node left, right;
        private int N; //

        public Node(K key, V val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    //注意这里的二叉树与讲义上的很类似
    @Override
    public void put(K key, V val) {
        root = put(root, key, val);//需要递归辅助函数，注意put与remove都要更新，就是赋值root
    }

    public Node put(Node x, K key, V val) {
        if (x == null) return new Node(key, val, 1);
        int c = key.compareTo(x.key);
        if (c < 0) x.left = put(x.left, key, val);
        else if (c > 0) x.right = put(x.right, key, val);
        else x.val = val;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node x, K key) {
        if (x == null) return null;
        int c = key.compareTo(x.key);
        if (c < 0) return get(x.left, key);
        else if (c > 0) return get(x.right, key);
        else return x.val;//事实上是找对应的x
    }

    @Override
    public boolean containsKey(K key) {
        return containsKey(root, key);
    }

    private boolean containsKey(Node x, K key) {
        if (x == null) return false;//和get类似
        int c = key.compareTo(x.key);
        if (c < 0) return containsKey(x.left, key);
        else if (c > 0) return containsKey(x.right, key);
        else return true;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public Set<K> keySet() {
        Set<K> a = new java.util.HashSet<>(Set.of());
        for (K i : this) {//this指迭代器
            a.add(i);
        }
        return a;
    }

    @Override
    public V remove(K key) {
        V v = get(key);//先拿这个结点的值避免删去以后找不到
        if (v != null) root = remove(root, key);//更新root
        return v;
    }

    private Node removeMin(Node x) {
        if (x.left == null) return x.right;//remove+left变right足以删掉节点
        x.left = removeMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    private Node remove(Node x, K key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = remove(x.left, key);
        else if (cmp > 0) x.right = remove(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = removeMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTMapIterator();
    }

    //这个迭代器使用了栈
    private class BSTMapIterator implements Iterator<K> {
        private final Stack<Node> stack;

        public BSTMapIterator() {
            stack = new Stack<>();
            pushLeft(root);
        }

        private void pushLeft(Node x) {
            while (x != null) {
                stack.push(x);
                x = x.left;
            }
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node current = stack.pop();
            // 将右子树的左路径全部入栈
            pushLeft(current.right);
            return current.key;
        }
    }

    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(Node x) {
        if (x == null) return;
        printInOrder(x.left);
        StdOut.println(x.key + " -> " + x.val);
        printInOrder(x.right);
    }

}


