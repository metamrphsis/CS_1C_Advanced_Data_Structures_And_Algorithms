package utilities;

/**
 * Converts duration into a string representation
 * @param <T>   A generic type to represent any type of data
 */
public class PrintObject<T> implements Traverser<T>
{
    public void visit(T item)
    {
        System.out.print(item + " ");
    }
}
