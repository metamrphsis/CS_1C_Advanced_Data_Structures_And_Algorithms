package utilities;

import java.util.*;
//import java.util.NoSuchElementException;

/**
 * The LazySearchTree class implements lazy deletion in which nodes are
 * marked as deleted, but not actually removed until specified. When a
 * node is marked as deleted, it is only marked for deletion, but not
 * removed. This is known as soft deletion. Hard deletion occurs when we
 * actually remove the node
 *
 * @author Foothill College, Bita Mazloom, Selahittin Saytaş
 * @param <T>   A generic type to represent any type of data
 */
public class LazySearchTree<T extends Comparable<? super T>> implements Cloneable
{
    // Reflects the number of undeleted nodes
    protected int mSize;
    // Tracks the number of hard nodes both deleted & undeleted
    protected int mSizeHard;
    protected LazySTNode mRoot;

    /**
     * The constructor initializes a newLazySearchTree
     * by calling clear() method
     */
    public LazySearchTree()
    {
        clear();
    }

    /**
     * The method checks if the tree is empty
     * @return  A boolean type indicating whether
     *          the tree is empty or not
     */
    public boolean isEmpty()
    {
        return (mSize == 0);
    }

    /**
     * The method returns the number of undeleted
     * and deleted nodes in the tree
     * @return An int type of number of nodes
     */
    public int size()
    {
        return mSize;
    }

    /**
     * The method chops down whatever tree exists
     */
    public void clear()
    {
        mSize = 0;
        mRoot = null;
    }

    /**
     * The method returns the height of the tree
     * using a recursion method
     * @return  An int type of tree height
     */
    public int showHeight()
    {
        return findHeight(mRoot, -1);
    }

    /**
     * The method finds the minimum value in
     * the tree using a recursion method
     * @return A generic type of data that
     *         exist inside the minimum node
     */
    public T findMin()
    {
        if(mRoot == null)
        {
            throw new NoSuchElementException();
        }

        return findMin(mRoot).data;
    }

    /**
     * The method finds the maximum value in
     * the tree using a recursion method
     * @return A generic type of data that
     *         exist inside the maximum node
     */
    public T findMax()
    {
        if(mRoot == null)
        {
            throw new NoSuchElementException();
        }

        return findMax(mRoot).data;
    }

    /**
     * The method finds a node containing the given data
     * If the data is not found, returns null
     * @param item  The data to be searched in the tree
     * @return A generic type of data that
     *         is searched for
     */
    public T find(T item)
    {
        LazySTNode resultNode;
        resultNode = find(mRoot, item);

        if(resultNode == null)
        {
            throw new NoSuchElementException();
        }

        return resultNode.data;
    }

    /**
     * The method implements lazy deletion such that it
     * will ignore any nodes that are marked as deleted
     * @param root  The root of the tree
     * @param item  The node in the tree
     * @return      The root
     */
    protected LazySTNode find(LazySTNode root, T item)
    {
        int compareResult; // Avoid multiple calls to compareTo()

        if(root == null)
        {
            return null;
        }

        compareResult = item.compareTo(root.data);

        if(compareResult < 0)
        {
            return find(root.lftChild, item);
        }
        else if(compareResult > 0)
        {
            return find(root.rghtChild, item);
        }

        return root; // Found
    }

    /**
     * The method checks if the data is contained in
     * the tree using find() method
     * @param item  The data to be searched in the tree
     * @return  A type of boolean depending on whether the
     *          node exist in the tree
     */
    public boolean contains(T item)
    {
        LazySTNode currentNode = mRoot;

        while(currentNode != null && item.compareTo(currentNode.data) != 0)
        {
            if(item.compareTo(currentNode.data) < 0)
            {
                currentNode = currentNode.lftChild;
            }
            else
            {
                currentNode = currentNode.rghtChild;
            }
        }

        // If key is not present in the key
        if(currentNode == null || currentNode.deleted)
        {
            return false;
        }
        return true;
    }

    /**
     * The method inserts a new data
     * @param item  The data to be inserted into the tree
     * @return  A boolean type of true or false depending
     *          on the current size and the updated size
     *          are equal or not to each other
     */
    public boolean insert(T item)
    {
        int oldSize = mSize;
        mRoot = insert(mRoot, item);

        return (mSize != oldSize);
    }

    /**
     * The method removes a node from the tree using
     * lazy deletion. The node will be marked as deleted,
     * but still exist in the tree.
     * @param item  The data to be removed from the tree
     * @return  A boolean type; true if the node has been
     *          marked as deleted, false if not
     */
    public boolean remove(T item)
    {
        int oldSize = mSize;
        remove(mRoot, item);

        return (mSize != oldSize);
    }

    /**
     * The method uses recursion to insert data into the tree
     * @param root  The node that the insert method is on
     * @param item  The data to be inserted into the tree
     * @return      The location of a node
     */
    protected LazySTNode insert(LazySTNode root, T item)
    {
        LazySTNode newNode = new LazySTNode(item, null, null);
        LazySTNode rootNode = mRoot;
        LazySTNode nullNode = null;

        while(rootNode != null)
        {
            nullNode = rootNode;

            if(item.compareTo(rootNode.data) < 0)
            {
                rootNode = rootNode.lftChild;
            }
            else if(item.compareTo(rootNode.data) > 0)
            {
                rootNode = rootNode.rghtChild;
            }
            else
            {
                rootNode.deleted = false;
                mSize++;
                return mRoot;
            }
        }

        if(nullNode == null)
        {
            mRoot = newNode;
        }
        else if(item.compareTo(nullNode.data) < 0)
        {
            nullNode.lftChild = newNode; //nullNode.lftChild;
        }
        else //if(item.compareTo(nullNode.data) > 0)
        {
            nullNode.rghtChild = newNode; //nullNode.rghtChild;
        }

        mSize++;
        mSizeHard++;
        return mRoot;
    }

    /**
     * The method uses recursion to remove data from the tree
     * @param root  The node that the insert method is on
     * @param item  The data to be removed from the tree
     */
    protected void remove(LazySTNode root, T item) // protected LazySTNode remove(LazySTNode root, T item)
    {
        int compareResult; // Avoid multiple calls to compareTo()

        if(root == null)
        {
            return;
        }

        compareResult = item.compareTo(root.data);

        if(compareResult < 0)
        {
            //root.lftChild = remove(root.lftChild, item);
            remove(root.lftChild, item);
        }
        else if(compareResult > 0)
        {
            //root.rghtChild = remove(root.rghtChild, item);
            remove(root.rghtChild, item);
        }
        else
        {
            if(!root.deleted)
            {
                root.deleted = true;
                mSize--;
            }
        }
    }

    /**
     * The method goes through the tree from low to highest value
     * executing a specified action at each node
     * @param func      func an object whose data type is defined by the client
     * @param <F>       func an object whose data type is defined by the client
     *                  This func object needs to come from a class that implements
     *                  Traverser. This func object contains the action the client
     *                  wants executed at each node
     */
    public <F extends Traverser<? super T>> void traverseHard(F func)
    {
        traverseHard(func, mRoot);
    }

    /**
     * The method creates a deep copy of the specified tre
     * @return  A clonned new object
     * @throws CloneNotSupportedException   Thrown to indicate that
     *                                      the clone method in class
     *                                      Object has been called to
     *                                      clone an object, but that
     *                                      the object's class does not
     *                                      implement the Cloneable interface
     */
    public Object clone() throws CloneNotSupportedException
    {
        LazySearchTree<T> newObject = (LazySearchTree)super.clone();
        newObject.clear();

        newObject.mRoot = cloneSubTree(mRoot);
        newObject.mSize = mSize;

        return newObject;
    }

    // Private helper methods

    /**
     * The method implements lazy deletion by using recursion
     * such that it starts searching from the root and return
     * the minimum and maximum node that has not been deleted
     * @param root  The root of node the recursion is currently on
     * @return      Null if the given root is null. Otherwise it goes
     *              to the minimum node and returns a reference of it
     *              to the caller
     */
    protected LazySTNode findMin(LazySTNode root)
    {
        if(root == null)
        {
            return null;
        }

        LazySTNode result = findMin(root.lftChild);

        if(result != null)
        {
            return result;
        }

        if(!root.deleted)
        {
            return root;
        }

        return findMin(root.rghtChild);
    }

    /**
     * The method implements lazy deletion by using recursion
     * such that it starts searching from the root and return
     * the minimum and maximum node that has not been deleted
     * @param root  The root of node the recursion is currently on
     * @return      Null if the given root is null. Otherwise it goes
     *              to the maximum node and returns a reference of it
     *              to the caller
     */
    protected LazySTNode findMax(LazySTNode root)
    {
        if(root == null)
        {
            return null;
        }

        if(root.deleted && root.rghtChild != null)
        {
            return findMax(root.lftChild);
        }

        if(!root.deleted && root.rghtChild == null)
        {
            return root;
        }

        if(!root.deleted && root.rghtChild != null)
        {
            return findMax(root.rghtChild);
        }

        if(root.deleted && root.rghtChild == null)
        {
            return null;
        }

        return null;
    }

    /**
     * The method traverses nodes that has not been
     * marked as deleted in the LazySearchTree
     * @param func  An object containing the action the
     *              client wants executed on each object
     * @param treeNode  The tree node the recursion is currently
     *                  on
     * @param <F>       func an object whose data type is defined by the client.
     *                  This func object needs to come from a class that implements
     *                  Traverser. This func object contains the action the client
     *                  wants executed at each node
     */
    protected <F extends Traverser<? super T>> void traverseHard(F func, LazySTNode treeNode)
    {
        if(treeNode == null)
        {
            return;
        }

        traverseHard(func, treeNode.lftChild);
        func.visit(treeNode.data);
        traverseHard(func, treeNode.rghtChild);
    }

    /**
     * The method recursively clones each node in a tree
     * returning the pointer to the root node of the cloned tree
     * @param root  The root node the method is currently on
     * @return      Null if the given root is null. Otherwise a pointer
     *              to the node that was cloned. In the end of recursion
     *              returns the node of the cloned tree
     */
    protected LazySTNode cloneSubTree(LazySTNode root)
    {
        LazySTNode newNode;

        if(root == null)
        {
            return null;
        }

        newNode = new LazySTNode(root.data, cloneSubTree(root.lftChild), cloneSubTree(root.rghtChild));

        return newNode;
    }

    /**
     * The method uses recursion to find the height of the tree
     * @param treeNode  The tree node whose height is being calculated
     * @param height    The height of the current node
     * @return          An int type representing the height of the node
     *                  in the tree
     */
    protected int findHeight(LazySTNode treeNode, int height)
    {
        int leftHeight, rightHeight;

        if(treeNode == null)
        {
            return height;
        }

        height++;

        leftHeight = findHeight(treeNode.lftChild, height);
        rightHeight = findHeight(treeNode.rghtChild, height);

        return (leftHeight > rightHeight) ? leftHeight : rightHeight;
    }

    /**
     * The method returns the number of undeleted
     * and deleted nodes in the tree
     * @return  An int value representing the number of
     *          undeleted and deleted nodes in the tree
     */
    public int sizeHard()
    {
        return mSizeHard;
    }

    /**
     * The method traverses the tree by recursively going
     * to each element of the tree from lowest value to highest
     * value
     * @param printObject   Func an object whose data type
     *                      is defined by the client. This
     *                      func object needs to come from
     *                      a class that implements Traverser
     *                      The func object contains the action
     *                      the client wants executed at each node
     */
    public void traverseHard(PrintObject<T> printObject)
    {
        traverseHard(printObject, mRoot);
    }

    /**
     * The method traverses the tree by recursively going
     * to each element of the tree from lowest value to highest
     * value
     * @param printObject   Func an object whose data type
     *                      is defined by the client. This
     *                      func object needs to come from
     *                      a class that implements Traverser
     *                      The func object contains the action
     *                      the client wants executed at each node
     * @param treeNode      The root of node the recursion is currently on
     */
    private void traverseHard(PrintObject<T> printObject, LazySTNode treeNode)
    {
        if(treeNode == null)
        {
            return;
        }

        traverseHard(printObject, treeNode.lftChild);
        printObject.visit(treeNode.data);
        traverseHard(printObject, treeNode.rghtChild);
    }

    /**
     * The method goes through the tree from left to the
     * right (from low to highest value) executing a
     * specified action at each node
     * @param printObject   Func an object whose data type
     *                      is defined by the client. This
     *                      func object needs to come from
     *                      a class that implements Traverser
     *                      The func object contains the action
     *                      the client wants executed at each node
     */
    public void traverseSoft(PrintObject<T> printObject)
    {
        traverseSoft(printObject, mRoot);
    }

    /**
     * The method traverses nodes that has not been
     * marked as deleted in the LazySearchTree
     * @param func  An object containing the action the
     *              client wants executed on each object
     * @param treeNode  The tree node the recursion is currently
     *                  on
     * @param <F>       func an object whose data type is defined by the client.
     *                  This func object needs to come from a class that implements
     *                  Traverser. This func object contains the action the client
     *                  wants executed at each node
     */
    protected <F extends Traverser<? super T>> void traverseSoft(F func, LazySTNode treeNode)
    {
        if(treeNode == null)
        {
            return;
        }

        traverseSoft(func, treeNode.lftChild);

        if(!treeNode.deleted)
        {
            func.visit(treeNode.data);
        }

        traverseSoft(func, treeNode.rghtChild);
    }







    /**
     * A nested class for node objects. LazySTNodes are for search trees that use
     * lazy deletion
     */
    private class LazySTNode
    {
        // Use public access so the tree or other
        // classes can access members
        public LazySTNode lftChild, rghtChild;
        public T data;
        public LazySTNode myRoot; // Needed to test for certain error
        public boolean deleted;

        /**
         * The constructor creates a new binary tree node using
         * the given parameters
         * @param data  The data contained in the node
         * @param lft   The left child node
         * @param rght  The right child node
         */
        public LazySTNode(T data, LazySTNode lft, LazySTNode rght)
        {
            this.lftChild = lft;
            this.rghtChild = rght;
            this.data = data;
            this.deleted = false;
        }

        /**
         * The method creates a new binary tree node with null data,
         * null left and right children
         */
        public LazySTNode()
        {
            this(null, null, null);
        }

        // Function stubs -- for use only with AVL Trees when we extend

        /**
         * The accessor method return the height
         * @return  An int value
         */
        public int getHeight()
        {
            return 0;
        }

        /**
         * The mutator method sets the height
         * @param height An int value height
         * @return  A boolean value
         */
        public boolean setHeight(int height)
        {
            return true;
        }
    }
}
