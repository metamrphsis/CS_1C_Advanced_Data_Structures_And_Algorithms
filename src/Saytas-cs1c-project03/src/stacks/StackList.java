package stacks;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The generic type StackList is a Singly-LinkedList class. Objects
 * of type StackList manage items in a singly linked list where
 * we can only push() and pop() items from one end of the stack
 * We will call this at the top of the stack
 *
 * @author Foothill College, Bita M, Selahittin Saytaş
 *
 * @param <T>   A generic type
 */
public class StackList<T> implements Iterable<T>
{
    private String name;
    private Node<T> top;

    /**
     * The constructor initializes the class attribute
     * @param name  A String type for the name of the
     *              instance
     */
    public StackList(String name)
    {
        this.name = name;
        this.top = null;
    }

    /**
     * The method takes a generic item as the argument
     * and adds the item to the top of the stack
     * @param item  A generic type of argument
     */
    public void push(T item)
    {
        // Creating a temporary new node and allocating memory
        Node<T> tempItem = new Node<>(item);

        // Putting temporary reference into tempItem.next
        tempItem.next = top;

        // Updating the top reference
        top = tempItem;
    }

    /**
     * The method receives no arguments and removes the
     * item from the top of the stack
     * @return  A generic type of popped item
     */
    public T pop()
    {
        // Check for stack Underflow
        // Reference:
        // https://stackoverflow.com/questions/35071931/java-stack-underflow
        if(top == null)
        {
            throw new NoSuchElementException();
        }

        T item = top.data;

        // Update the top reference to point to the next node
        top = top.next;
        return item;
    }

    /**
     * The method looks at the top of the stack and returns
     * a generic type for the data seen at the top of the
     * stack. If the stack is empty, the method returns null
     * @return  A generic type
     */
    public T peek()
    {
        if(isEmpty())
        {
            System.out.println("The Stack is empty!");
            return null;
        }

        return top.data;
    }

    /**
     * The method discards all object references from the
     * linked-list to "empty" this StackList instance
     */
    public void clear()
    {
        top = null;
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

        output.append(name + "with " + size() + " links:\n" + "[");

        Node<T> nodeReference = top;
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
     * The method checks if the top of the stack is
     * pointing to anything
     * @return  A boolean value of true or false
     */
    public boolean isEmpty()
    {
        return top == null;
    }

    /**
     * The method returns the number of elements in the
     * stack
     * @return  An int type of number of elements
     */
    public int size()
    {
        Node<T> nodeReference = top;
        int count = 0;
        while(nodeReference != null)
        {
            count++;
            nodeReference = nodeReference.next;
        }

        return count;
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
        return new StackIterator();
    }

    /**
     * The inner StackIterator parameterized class implements
     * an iterator interface that provides an iterator over the
     * outer generic StackList
     */
    private class StackIterator implements Iterator<T>
    {
        private int sizeOfStackList = size();
        private Node<T> firstNode = top;

//        public StackIterator()
//        {
//        }

        /**
         * The method hecks if there is an item in the next position
         * in a list
         * @return  A boolean value of true or false
         */
        public boolean hasNext()
        {
            return (sizeOfStackList > 0);
        }

        /**
         * The method moves to the next element in a list
         * @return  A generic type of node
         */
        public T next()
        {
            T item = (T)firstNode.data;
            firstNode = firstNode.next;
            sizeOfStackList--;
            return item;
        }

//        public void remove(){}
    }
}
