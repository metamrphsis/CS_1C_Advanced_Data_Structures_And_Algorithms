package utilities;

/**
 * Traverser class extends Object. Java Object Graph traverser.
 * It will visit all Java object reference fields and call the
 * passed in Visitor instance with each object encountered,
 * including the root. It will properly detect cycles within
 * the graph and not hang
 *
 * @param <E>   A generic type to represent any type of data
 */
public interface Traverser<E>
{
    public void visit(E x);
}