package subsetsum;


import java.util.ArrayList;
import java.util.ListIterator;

/**
 * The class Sublist stores a reference to the master list from
 * which we are forming a sublist and also stores the indices
 * and the sum of the objects. The class uses an ArrayList<> of
 * integers which are indices of each elements in the master set
 * to represent the actual sublist that is derived from the master
 * set
 */
public class Sublist implements Cloneable
{
    private ArrayList<Double> originalObjects;
    private ArrayList<Integer> indices;
    private double sum; // Sum might need to be an int

    /**
     * The constructor creates an empty sublist
     * (No indices)
     * @param originalObjects   An ArrayList of generic
     *                          type Integer
     */
    public Sublist(ArrayList<Double> originalObjects)
    {
        this.originalObjects = originalObjects;
        indices = new ArrayList<Integer>();
        sum = 0;
    }

    /**
     * The accessor method returns the sum of
     * the ArrayList
     * @return  The sum of the ArrayList
     */
    public double getSum()
    {
        return sum;
    }

    /**
     * The accesso method returns an ArrayList of
     * type doubel
     * @return  A double type of ArrayList
     */
    public ArrayList<Double> getAsArrayList()
    {
        return originalObjects;
    }

    /**
     * The clone method creates and returns an exact
     * copy of the new object
     * @return The copy of Sublist object
     * @throws CloneNotSupportedException
     */
    public Object clone() throws CloneNotSupportedException
    {
        // Shallow copy
        Sublist newObject = (Sublist)super.clone();

        // Deep copy
        newObject.indices = (ArrayList<Integer>)indices.clone();

        return newObject;
    }

    /**
     * The method takes an item from master set and forms
     * an augmented sublist which has items added calling the
     * clone() method. The method also adjusts the sum of the
     * newly created sublist
     *
     * @param indexOfItemToAdd  Index of the item from the master
     *                          list that is to be added to the
     *                          sublist
     * @return  A new Sublist
     * @throws CloneNotSupportedException
     */
    public Sublist addItem(int indexOfItemToAdd) throws CloneNotSupportedException
    {
        Sublist newSublistObject = (Sublist)this.clone();
        newSublistObject.sum += this.originalObjects.get(indexOfItemToAdd);
        newSublistObject.indices.add(indexOfItemToAdd);
        return newSublistObject;
    }

    /**
     * The void method iterates through the ArrayList
     * and displays the elements of the sublist represented
     * by the object with their corresponding indices
     */
    public void showSublist()
    {
        ListIterator<Integer> iter = indices.listIterator();
        int eachIndices;
        double eachValue;

        while(iter.hasNext())
        {
            eachIndices = iter.next();
            eachValue = originalObjects.get(eachIndices);
            System.out.println("Index position: " + eachIndices + "\tThe value: " + eachValue);
        }
    }
}