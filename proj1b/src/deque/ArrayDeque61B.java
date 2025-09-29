package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ArrayDeque61B<T> implements Deque61B<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private int number;//必须区分number和size，number是底层数组的大小，size是队列的大小
    //number其实就是items.length

    public ArrayDeque61B(){
        items=(T[]) new Object[8];
        size=0;//最开始是0-7
        number=8;
        nextFirst=6;//nextfirst总是队列的第1个元素的前一个元素
        nextLast=7;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ArrayDeque61B oas) {
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
    public Iterator<T> iterator() {return new ArrayDeque61BIterator();}

    //辅助函数
    public int length(){
        return number;
    }

    //辅助函数
    public void reSize(int capacity){
        T[] a=(T[]) new Object[capacity];
        for(int i=0;i<size;i++){
            a[i]=get(i);
        }
        items=a;
        nextFirst=capacity-1;
        nextLast=size;
        number=capacity;
    }

       /**
     * Add {@code x} to the front of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addFirst(T x) {//暂时只考虑items[nextFirst]和items[nextLast]没有元素的情况
        if(size == number) {
            reSize(2*number);//2可以改成别的数
        }
        items[nextFirst] = x;
        nextFirst = Math.floorMod(nextFirst - 1, number);
        size++;
    }

    /**
     * Add {@code x} to the back of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addLast(T x) {
        if(size == number) {
            reSize(2*number);
        }
        items[nextLast]=x;
        nextLast=Math.floorMod(nextLast+1,number);
        size++;
    }

    /**
     * Returns a List copy of the deque. Does not alter the deque.
     *
     * @return a new list copy of the deque.
     */
    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for(int i=0;i<size;i++){
            returnList.add(get(i));
        }
        return returnList;
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
        if(number > 8 && size <= 0.25*number) {
            reSize(number/2);
        }
        int b=Math.floorMod(nextFirst+1,number);//首项的物理位置
        T a=items[b];
        items[b]=null;
        nextFirst=b;
        size--;
        return a;
    }

    /**
     * Remove and return the element at the back of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeLast() {
        if(number > 8 && size <= 0.25*number) {
            reSize(number/2);
        }
        int b=Math.floorMod(nextLast-1,number);//首项的物理位置
        T a=items[b];
        items[b]=null;
        nextLast=b;
        size--;
        return a;
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
        if(index>=size||index<0){
            return null;
        }else{
            int c=Math.floorMod(index+nextFirst+1,number);//因为nextFirst+1与队列的第0个元素在数组中的位置总是相同的
            return items[c];
        }
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
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    private class ArrayDeque61BIterator implements Iterator<T> {
        private int wizPos;

        public ArrayDeque61BIterator() {
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < size;
        }
        public T next() {
            T returnItem = items[nextFirst+wizPos+1];
            wizPos += 1;
            return returnItem;
        }
    }


}
