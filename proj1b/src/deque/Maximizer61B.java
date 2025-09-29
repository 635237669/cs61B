package deque;
import java.util.Comparator;
import java.util.Iterator;

public class Maximizer61B {
    /**
     * Returns the maximum element from the given iterable of comparables.
     * You may assume that the iterable contains no nulls.
     *
     * @param iterable  the Iterable of T
     * @return          the maximum element
     *///就是做一个调用iterable的Comparable
    public static <T extends Comparable<T>> T max(Iterable<T> iterable) {
        Iterator<T> seer = iterable.iterator();//设置Iterator
        if(seer.hasNext()){
            T max=seer.next();
            while (seer.hasNext()){
                T a=seer.next();
                int cmp=a.compareTo(max);
                if (cmp > 0) {
                    max = a;
                }
            }
            return max;
        } else {
            return null;
        }
    }

    /**
     * Returns the maximum element from the given iterable according to the specified comparator.
     * You may assume that the iterable contains no nulls.
     *
     * @param iterable  the Iterable of T
     * @param comp      the Comparator to compare elements
     * @return          the maximum element according to the comparator
     */
    public static <T> T max(Iterable<T> iterable, Comparator<T> comp) {
        Iterator<T> seer = iterable.iterator();//设置Iterator
        if(seer.hasNext()){
            T max=seer.next();
            while (seer.hasNext()){
                T a=seer.next();
                int cmp=comp.compare(a,max);
                if (cmp > 0) {
                    max = a;
                }
            }
            return max;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
     ArrayDeque61B<Integer> ad = new ArrayDeque61B<>();
     ad.addLast(5);
     ad.addLast(12);
     ad.addLast(17);
     ad.addLast(23);
     System.out.println(max(ad));
    }
}
