package queues;

import cs1c.SongEntry;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The Singly-LinkedList parameterized class implements
 * Iterable<T> interface. Objects of type Queue manage
 * items in a singly linked list where we can enqueue()
 * items to the end and dequeue() items from the front
 * of the queue
 * @param <T>   A parameterized class generic type
 */
public class Queue<T> implements Iterable<T>
{
    private String name;
    private Node<T> head;
    private Node<T> tail;
    private int size;

    /**
     * The constructor method takes user assigned name
     * and initializes the instance variables
     * @param name  A String type for the instance
     *              variable name
     */
    public Queue(String name)
    {
        this.name = name;
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * The accessor method returns the name
     * @return  A String type of name
     */
    public String getName()
    {
        return name;
    }

    /**
     * The accessor method returns the head node
     * which points to the front of the queue
     * @return  A generic node type of head
     */
    public Node<T> getHead()
    {
        return head;
    }

    /**
     * The accessor method returns the tail node
     * which points to the end of the queue
     * @return  A generic node type of tail
     */
    public Node<T> getTail()
    {
        return tail;
    }

    /**
     * The method returns the number of elements in the
     * stack
     * @return  An int type of number of elements
     */
    public int size()
    {
        Node<T> nodeReference = head;
        int count = 0;
        while(nodeReference != null)
        {
            count++;
            nodeReference = nodeReference.next;
        }

        return count;
    }

    /**
     * The method checks if the top of the stack is
     * pointing to anything
     * @return  A boolean value of true or false
     */
    public boolean isEmpty()
    {
        return head == null;
    }

    /**
     * The method takes a generic item as the argument
     * and adds the item to the end of the queue
     * @param songItem  A generic type of parameter
     */
    public void enqueue(T songItem)
    {
        Node<T> tempItem = new Node<>(songItem);

        if(isEmpty())
        {
            head = tempItem;
            tail = tempItem;
        }
        else
        {
            tail.next = tempItem;
            tail = tempItem;
        }
    }

    /**
     * The method receives no arguments and removes
     * the item from the front of the queue. The method
     * also throws a NoSuchElementException() if the queue
     * is empty
     * @return  A generic type of dequeue-ed item
     */
    public T dequeue()
    {
        if(size() == 0) //isEmpty()
        {
            throw new NoSuchElementException();
        }

        Node<T> removedFirstSong = head;
        head = head.next;
        return removedFirstSong.data;
    }

    /**
     * The method looks at the least recently added
     * item of the queue and returns an object of the
     * generic type for the data seen at the front of
     * the queue. If the queue is empty, it returns null
     * @return  An object of generic type for the data
     *          seen in the front of the queue
     */
    public SongEntry peek()
    {
        if(isEmpty())
        {
            return null;
        }

        //Node<T> returnNode = new Node<T>(head);

        return (SongEntry)tail.data;
    }

    /**
     * The toString() method includes the name of the stack
     * passed in by the Navigator class in addition to the
     * number of links in the stack
     * @return A string representation of the StackList class
     */
    @Override
    public String toString()
    {
        StringBuilder output = new StringBuilder();

        output.append(name + ":\n" + "["); //" with " + size() + " links:\n" + "["

        Node<T> nodeReference = head;
        while(nodeReference != null)
        {
            if(nodeReference == null)
            {
                output.append(nodeReference.data);
            }
            else
            {
                output.append(nodeReference.data + ", ");
            }

            nodeReference = nodeReference.next;
        }

        output.append("]");

        return output.toString();
    }




    /**
     * Definition of inner Node class
     */
    public class Node<T>
    {
        private Node next;
        T data;

        /**
         * The constructor
         * @param nodeData  A generic type
         */
        public Node(T nodeData)
        {
            this.data = nodeData;
            this.next = null;
        }
    }






    /**
     * Iterator method
     * @return  An object of StackIterator
     */
    public Iterator<T> iterator()
    {
        return new QueueIterator();
    }

    /**
     * The inner StackIterator parameterized class implements
     * an iterator interface that provides an iterator over the
     * outer generic StackList
     */
    private class QueueIterator implements Iterator<T>
    {
        private int sizeOfQueue = size();
        private Node<T> firstNode = head;

        /**
         * The method hecks if there is an item in the next position
         * in a list
         * @return  A boolean value of true or false
         */
        public boolean hasNext()
        {
            return (sizeOfQueue > 0);
        }

        /**
         * The method moves to the next element in a list
         * @return  A generic type of node
         */
        public T next()
        {
            T item = (T)firstNode.data;
            firstNode = firstNode.next;
            sizeOfQueue--;
            return item;
        }
    }
}
