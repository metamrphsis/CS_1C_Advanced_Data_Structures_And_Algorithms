package hashTables;

import java.util.NoSuchElementException;

/**
 * The FHhashQPwFind class extends FHhashQP and provides modifications to
 * find(),myHashKey(), and findPosKey() methods
 *
 * @author Selahittin Saytaş
 *
 * @param <KeyType> A generic type
 * @param <E>       A generic type
 */
public class FHhashQPwFind<KeyType, E extends Comparable<KeyType>> extends FHhashQP<E>
{
    /**
     * The method returns the found object or
     * throws a java.util.NoSuchElementException
     * @param idKey The key value to be hashed
     * @return      The data for the corresponding key
     */
    public E find(KeyType idKey)
    {
        HashEntry<E> hashEntryArray;
        int keyPosIndex = findPosKey(idKey);

        hashEntryArray = super.mArray[keyPosIndex];

        if(hashEntryArray.state != ACTIVE)
        {
            throw new NoSuchElementException();
        }

        return hashEntryArray.data;
    }

    /**
     * The method provides a trivial modification to myHash()
     * which uses the key rather than the object, to hash
     * @param key Hash key
     * @return  Index
     */
    private int myHashKey(KeyType key)
    {
        int keyHashValue = key.hashCode();
        int index = keyHashValue % mTableSize;

        if(index < 0)
        {
            index += mTableSize;
        }

        return index;
    }

    /**
     * The method provides trivial modification to findPos()
     * which uses the key rather than the object, to get a position
     * @param key   The key value to be hashed
     * @return      Hash key
     */
    private int findPosKey(KeyType key)
    {
        int bucket = myHashKey(key);
        int number = 1;

        while(mArray[bucket].state != EMPTY && mArray[bucket].data.compareTo(key) != 0)
        {
            bucket += number;

            // To compute the next odd number
            number += 2;

            if(bucket >= mTableSize)
            {
                bucket -= mTableSize;
            }
        }

        return bucket;

        /*
        int kthOddNum = 1;
        int index = myHash(x);

        while(mArray[index].state != EMPTY
                && !mArray[index].data.equals(x))
        {
            index += kthOddNum; // k squared = (k-1) squared + kth odd #
            kthOddNum += 2;     // compute next odd #
            if (index >= mTableSize)
                index -= mTableSize;
        }

        return index;
         */
    }
}
