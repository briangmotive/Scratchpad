package main.java;

import java.util.NoSuchElementException;

public class CustomLinkedList<E> {

    private Node headNode;
    private Node tailNode;
    private int size;

    public boolean add(E e) {
        add(size, e);

        return true;
    }
    
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Cannot add index outside of size of list");
        }

        if (size == 0) {
            addFirstNodeEver(element);
            return;
        }

        if (index == 0) {
            addFirst(element);
        } else if (index == size) {
            addLast(element);
        } else {
            Node currentNode = headNode;
            int indexMarker = 0;

            while (currentNode.hasNext()) {
                if (indexMarker == index) {
                    break;
                }
                currentNode = currentNode.getNextNode();
                indexMarker ++;
            }
            Node newNode = new Node(element);
            Node previousNode = currentNode.getPreviousNode();


            previousNode.setNextNode(newNode);
            newNode.setPreviousNode(previousNode);
            newNode.setNextNode(currentNode);
            currentNode.setPreviousNode(newNode);
        }

        size++;
    }

    public void addFirst(E e) {
        if (size == 0) {
            addFirstNodeEver(e);
        } else {
            Node previousHeadNode = headNode;
            headNode = new Node(e);
            headNode.setNextNode(previousHeadNode);
            previousHeadNode.setPreviousNode(headNode);
        }
        size ++;
    }

    public void addLast(E e) {
        if (size == 0) {
            addFirstNodeEver(e);
        } else {
            Node previousTailNode = tailNode;
            tailNode = new Node(e);
            tailNode.setPreviousNode(previousTailNode);
            previousTailNode.setNextNode(tailNode);
        }
        size ++;
    }

    public void clear() {
        headNode = null;
        tailNode = null;
        size = 0;
    }

    public Object clone() {
        return this.clone();
    }

    public boolean contains(Object o) {
        if (size == 0) {
            return false;
        }

        Node currentNode = headNode;
        while (currentNode.hasNext()) {
            if (o == null) {
                if (currentNode.getItem() == null) {
                    return true;
                }
            } else {
                if (currentNode.getItem().equals(o)) {
                    return true;
                }
            }
            currentNode = currentNode.getNextNode();
        }

        return false;
    }

    @SuppressWarnings("unchecked")
    public E element() {
        if (size == 0) {
            throw new NoSuchElementException("the list is empty");
        }
        return (E) headNode.getItem();
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Cannot add index outside of size of list");
        }

        if (size == 0) {
            return null;
        }

        Node currentNode = headNode;
        int indexMarker = 0;

        if (index == 0) {
            return (E) headNode.getItem();
        }

        if (index == size) {
            return (E) tailNode.getItem();
        }

        while (currentNode.hasNext()) {
            currentNode = currentNode.getNextNode();
            indexMarker ++;

            if (indexMarker == index) {
                break;
            }
        }

        return (E) currentNode.getItem();
    }

    @SuppressWarnings("unchecked")
    public E getFirst() {
        if (size == 0) {
            throw new NoSuchElementException("the list is empty");
        }
        return (E) headNode.getItem();
    }

    @SuppressWarnings("unchecked")
    public E getLast() {
        if (size == 0) {
            throw new NoSuchElementException("the list is empty");
        }
        return (E) tailNode.getItem();
    }

    public int indexOf(Object o) {
        if (size == 0) {
           return -1;
        }

        Node currentNode = headNode;
        for (int i = 0; i <= size; i++) {
            if (o == null) {
                if (currentNode.getItem() == null) {
                    return i;
                }
            } else {
                if (currentNode.getItem().equals(o)) {
                    return i;
                }
            }

            if (!currentNode.hasNext()) {
                continue;
            }

            currentNode = currentNode.getNextNode();
        }

        return  -1;
    }

    public int lastIndexOf(Object o) {
        if (size == 0) {
            return -1;
        }

        Node currentNode = tailNode;
        for (int i = size; i >= 0; i--) {
            if (o == null) {
                if (currentNode.getItem() == null) {
                    return i;
                }
            } else {
                if (currentNode.getItem().equals(o)) {
                    return i;
                }
            }

            if (!currentNode.hasPrevious()) {
                continue;
            }

            currentNode = currentNode.getPreviousNode();
        }

        return  -1;
    }

    public boolean offer(E e) {
        return offerLast(e);
    }

    public boolean offerFirst(E e) {
        addFirst(e);
        return true;
    }

    public boolean offerLast(E e) {
        addLast(e);
        return true;
    }

    @SuppressWarnings("unchecked")
    public E peek() {
        return peekFirst();
    }

    @SuppressWarnings("unchecked")
    public E peekFirst() {
        return headNode != null ? (E) this.headNode.getItem(): null;
    }

    @SuppressWarnings("unchecked")
    public E peekLast() {
        return tailNode != null ? (E) this.tailNode.getItem(): null;
    }

    public E poll() {
        return pollFirst();
    }

    public E pollFirst() {
        if (size == 0) {
            return null;
        }
        return removeFirst();
    }

    @SuppressWarnings("unchecked")
    public E pollLast() {
        if (size == 0) {
            return null;
        }

       return removeLast();
    }

    public E pop() {
        return remove();
    }

    public void push(E e) {
        addFirst(e);
    }

    @SuppressWarnings("unchecked")
    public E remove() {
        if (size == 0) {
            throw new NoSuchElementException("the list is empty");
        }

        return removeFirst();
    }

    public E remove(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Cannot remove index outside of size of list");
        }

        E item;
        if (index == 0) {
            item = removeFirst();
        } else if (index == size) {
            item = removeLast();
        } else {
            Node currentNode = headNode;
            int indexMarker = 0;

            while (currentNode.hasNext()) {
                if (indexMarker == index) {
                    break;
                }
                currentNode = currentNode.getNextNode();
                indexMarker ++;
            }

            item = (E) currentNode.getItem();

            removeInPlace(currentNode);
        }

        size --;
        return item;
    }

    public boolean remove(Object o) {
        return removeFirstOccurrence(o);
    }

    @SuppressWarnings("unchecked")
    public E removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException("list is empty");
        }
        Node newHead = headNode.getNextNode();
        newHead.setPreviousNode(null);
        Node oldHead = headNode;
        headNode = newHead;

        size --;

        return (E) oldHead.getItem();
    }

    public boolean removeFirstOccurrence(Object o) {
        Node currentNode = headNode;
        for (int i = 0; i <= size; i++) {
            if (o == null) {
                if (currentNode.getItem() == null) {
                    removeInPlace(currentNode);
                    size --;
                    return true;
                }
            } else {
                if (currentNode.getItem().equals(o)) {
                    removeInPlace(currentNode);
                    size --;
                    return true;
                }
            }

            if (!currentNode.hasNext()) {
                continue;
            }

            currentNode = currentNode.getNextNode();
        }

        return false;
    }

    @SuppressWarnings("unchecked")
    public E removeLast() {
        if (size == 0) {
            throw new NoSuchElementException("list is empty");
        }
        Node newTail = tailNode.getPreviousNode();
        newTail.setNextNode(null);
        Node oldTail = tailNode;
        tailNode = newTail;

        size --;

        return (E) oldTail.getItem();
    }

    public boolean removeLastOccurrence(Object o) {
        Node currentNode = headNode;
        for (int i = size; i >= 0; i--) {
            if (o == null) {
                if (currentNode.getItem() == null) {
                    removeInPlace(currentNode);
                    size --;
                    return true;
                }
            } else {
                if (currentNode.getItem().equals(o)) {
                    removeInPlace(currentNode);
                    size --;
                    return true;
                }
            }

            if (!currentNode.hasNext()) {
                continue;
            }

            currentNode = currentNode.getNextNode();
        }

        return false;
    }

    public E set(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Cannot remove index outside of size of list");
        }

        if (index == 0) {
            Node nextNode = headNode.getNextNode();
            Node newHeadNode = new Node(element);
            newHeadNode.setNextNode(nextNode);
            nextNode.setPreviousNode(newHeadNode);

            Node oldHeadNode = headNode;
            headNode = newHeadNode;

            return (E) oldHeadNode.getItem();
        } else if (index == size) {
            Node previousNode = tailNode.getPreviousNode();
            Node newTailNode = new Node(element);
            newTailNode.setPreviousNode(previousNode);
            previousNode.setNextNode(newTailNode);

            Node oldTail = tailNode;
            tailNode = newTailNode;

            return (E) oldTail.getItem();
        } else {
            Node currentNode = headNode;
            int indexMarker = 0;

            while (currentNode.hasNext()) {
                currentNode = currentNode.getNextNode();
                indexMarker ++;

                if (indexMarker == index) {
                    break;
                }
            }

            Node newNode  = new Node(element);
            Node previousNode = currentNode.getPreviousNode();
            Node nextNode = currentNode.getNextNode();

            previousNode.setNextNode(newNode);
            nextNode.setPreviousNode(newNode);

            newNode.setPreviousNode(previousNode);
            newNode.setNextNode(nextNode);

            return (E) currentNode.getItem();
        }
    }

    public int size() {
        return this.size + 1;
    }

    public Object[] toArray() {
        Object[] array = new Object[size+1];

        Node currentNode = headNode;
        for (int i = 0; i < size ; i++) {
            array[i] = currentNode.getItem();
            currentNode = currentNode.getNextNode();
        }

        return array;
    }

    private void removeInPlace(Node currentNode) {
        Node previousNode = currentNode.getPreviousNode();
        Node nextNode = currentNode.getNextNode();

        previousNode.setNextNode(nextNode);
        nextNode.setPreviousNode(previousNode);
    }

    private void addFirstNodeEver(E e) {
        Node firstNode = new Node(e);
        this.headNode = firstNode;
        this.tailNode = firstNode;

        size ++;
    }

}

class Node {
    private Node nextNode;
    private Node previousNode;
    private Object item;

    <E> Node(E e) {
        this.item = e;
    }

    void setNextNode(Node node) {
        this.nextNode = node;
    }
    
    void setPreviousNode(Node node) {
        this.previousNode = node;
    }

    Node getNextNode() {
        return nextNode;
    }

    Node getPreviousNode() {
        return previousNode;
    }

    public Object getItem() {
        return item;
    }
    
    boolean hasNext() {
        return nextNode != null;
    }

    boolean hasPrevious() {
        return previousNode != null;
    }
    
    
}
