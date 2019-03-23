package Collection;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Собственная реализация двусвязного списка, воплощающего всего методы интерфейса java.util.List.
 *
 * @param <E> Обобщитель для типа значения хранимого в листе.
 */
public class MyLinkedList<E> implements List<E> {

//  Ссылка на первый элемент в листе
    private MyListNode head;
//  Ссылка на последний элемент в листе
    private MyListNode tail;

    public MyLinkedList(){
        head = tail = null;
    }

    public MyListNode getNode(int index) {
        if (index < 0|| index >= size())
            throw new IndexOutOfBoundsException();

        MyListNode node = head;
        int count = 0;

        while (node != null) {
            if (count == index)
                return node;
            node = node.next;
            count++;
        }

        return null;
    }

    /**
     * Find and return all nodes with the value.
     *
     * @param value Value for the nodes search.
     * @return MyLinkedList<MyListNode> - all nodes with the value.
     */
    public MyLinkedList<MyListNode> getNode(E value) {
        MyLinkedList<MyListNode> nodes = new MyLinkedList<MyListNode>();
        MyListNode node = head;

        while (node != null) {
            if (node.value.equals(value))
                nodes.add(node);
            node = node.next;
        }
        return nodes;
    }

    public int indexOfNode(MyListNode node) {
        MyListNode buff = head;
        int index = 0;

        while (buff != null) {
            if (node == buff)
                return index;
            index++;
            buff = buff.next;
        }
        return -1;
    }

    /**
     * Внутренний класс для элемента двусвязного списка
     */
    private class MyListNode {
//        The field special for the E type value.
        private E value;
//        The field to link to the next list item.
        private MyListNode next;
//        The field to link to the previous list item.
        private MyListNode prev;

        /**
         * The constructor to this node class.
         *
         * @param value The stored value of this node.
         * @param next The link to the next node is relative to this.
         * @param prev The link to the previous node is relative to this.
         */
        MyListNode(E value, MyListNode next, MyListNode prev){
            this.value = value;
            this.next = next;
            this.prev = prev;
        }

        MyListNode(E value) {
            this(value, null, null);
        }

        MyListNode() {
            this(null);
        }

        void setValue(E value) {
            this.value = value;
        }

        E getValue() {
            return value;
        }

        void setNext(MyListNode next){
            this.next = next;
        }

        MyListNode getNext() {
            return next;
        }

        void setPrev(MyListNode prev) {
            this.prev = prev;
        }

        MyListNode getPrev() {
            return prev;
        }
    }

    /**
     * The iteration class for MyLinkedList.
     */
    private class MyIterator<I extends  E> implements Iterator<I>{

//        The field to link to the next item after the operating item.
        protected MyListNode next;
//        The field to link to the operating item to be returned.
        protected MyListNode curr;

        /**
         * Constructor to MyIterator.
         *
         * At the beginning put the head list as "next" and null as "curr".
         */
        MyIterator() {
            next = head;
            curr = null;
        }

        public boolean hasNext() {
            return next != null;
        }

        /**
         * Set "curr" as "next", then set a new "next" and return value from "curr".
         *
         * @return Value of the next.
         * @throws NullPointerException When "next" is null.
         */
        @SuppressWarnings("unchecked")
        public I next() {
            curr = next;
            next = next.next;

            return (I) curr.value;
        }

        public void remove() {
            MyLinkedList.this.remove(curr);
        }
    }

    public int size() {
        MyListNode node = head;
        int count = 0;
        while (node != null) {
            node = node.next;
            count++;
        }
        return count;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    @SuppressWarnings("unchecked")
    public boolean contains(Object o) {
        Iterator<E> it = iterator();

        while (it.hasNext()){
            if (it.next().equals(o))
                return true;
        }
        return false;
    }

    public Iterator<E> iterator() {
        return new MyIterator<E>();
    }

    public Object[] toArray() {
        Iterator<E> it = iterator();
        Object[] array = new Object[size()];
        int i = 0;

        while (it.hasNext())
            array[i++] = it.next();

        return array;
    }

    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        T[] array = a.length == size() ? a : (T[]) Array.newInstance(a.getClass().getComponentType(), size());
        MyListNode curr = head;

        for (int i = 0; curr != null; i++) {
            array[i] = (T) curr.value;
            curr = curr.next;
        }
        return array;
    }

    /**
     * Add element in tail of list.
     *
     * @param e Element that will be appended.
     * @return Result of the operation.
     */
    public boolean add(E e) {
        MyListNode newNode = new MyListNode(e, null, tail);

        if (tail == null)
            head = tail = newNode;
        else{
            tail.next = newNode;
            tail = newNode;
        }

        return true;
    }

    public boolean remove(Object o) {
        MyListNode node = head;

        while (node != null){
            if (node.value.equals(o)){
                if (node.prev == null && node.next == null)
                    head = tail = null;
                else if (node.prev == null){
                    head = head.next.prev = null;
                }
                else if (node.next == null) {
                    tail = node.prev.next = null;
                }
                else{
                    node.prev.next = node.next;
                    node.next.prev = node.prev;
                }

                return true;
            }
            node = node.next;
        }
        return false;
    }

    public boolean containsAll(Collection<?> c) {
        Iterator it = c.iterator();
        while (it.hasNext()) {
            if (!contains(it.next()))
                return false;
        }
        return true;
    }

    public boolean addAll(Collection<? extends E> c) {
        Iterator<? extends E> it = c.iterator();

        while (it.hasNext()) {
            add(it.next());
        }
        return true;
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        Iterator<? extends E> it = c.iterator();

        while (it.hasNext()) {
            add(index++, it.next());
        }
        return true;
    }

    public boolean removeAll(Collection<?> c) {
        Iterator it = c.iterator();

        while (it.hasNext()) {
            Object el = it.next();
            while(contains(el)){
                remove(el);
            }
        }
        return true;
    }

    public boolean retainAll(Collection<?> c) {
        MyListNode node = head;

        while (node != null) {
            MyListNode buff = node;
            if (!c.contains(node)){
                remove(node);
            }
            node = buff.next;
        }
        return true;
    }

    public void clear() {
        MyListNode node = head;

        while (node != null) {
            MyListNode buff = node;
            remove(node);
            node = buff.next;
        }
    }

    public E get(int index) {
        if (index < 0|| index >= size())
            throw new IndexOutOfBoundsException();

        MyListNode node = head;
        int count = 0;

        while (node != null) {
            if (count == index)
                return node.value;
            node = node.next;
            count++;
        }

        return null;
    }

    public E set(int index, E element) {
        if (index < 0|| index >= size())
            throw new IndexOutOfBoundsException();

        MyListNode node = head;
        int count = 0;

        while (node != null) {
            if (count == index) {
                E prevVal = node.value;
                node.value = element;
                return prevVal;
            }
            node = node.next;
            count++;
        }

        return null;
    }

    public void add(int index, E element) {
        if (index < 0|| index >= size())
            throw new IndexOutOfBoundsException();

        MyListNode node = head,
                    newNode = new MyListNode(element);
        int count = 0;

        while (node != null) {
            if (count == index){
                node.prev.next = newNode;
                newNode.prev = node.prev;
                newNode.next = node;
                node.prev = newNode;
                return;
            }
            node = node.next;
            count++;
        }
    }

    public E remove(int index) {
        MyListNode node = getNode(index);
        remove(node);

        return node.value;
    }

    public int indexOf(Object o) {
        MyListNode node = head;
        int index = 0;

        while (node != null) {
            if (node.value.equals(o))
                return index;

            node = node.next;
            index++;
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        MyListNode node = tail;
        int index = size()-1;

        while (node != null) {
            if (node.value.equals(o))
                return index;

            node = node.prev;
            index--;
        }
        return -1;
    }

    private class MyListIterator<I extends E> extends MyIterator<I> implements ListIterator<I>{

        MyListIterator() {

        }

        MyListIterator(int index){
            curr = getNode(index);
        }

        public boolean hasPrevious() {
            return curr.prev != null;
        }

        public I previous() {
            next = curr;
            curr = curr.prev;

            return (I) curr.value;
        }

        public int nextIndex() {
            return indexOfNode(next);
        }

        public int previousIndex() {
            if (curr == null)
                return -1;

            return indexOfNode(curr.prev);
        }

        public void set(I i) {
            curr.value = i;
        }

        public void add(I i) {
            MyListNode node = new MyListNode(i, next, next.prev);
            curr = node;
        }
    }

    public ListIterator<E> listIterator() {
        return new MyListIterator<E>();
    }

    public ListIterator<E> listIterator(int index) {
        return new MyListIterator<E>(index);
    }

    public List<E> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex >= size())
            throw new IndexOutOfBoundsException();
        List<E> list = new MyLinkedList<E>();

        for (int i = fromIndex; i < toIndex; i++) {
            list.add(get(i));
        }
        return list;
    }
}
