package subsetsum;

import cs1c.SongEntry;

import java.util.ArrayList;

/**
 * SubsetSum class keeps track of the price of each item reading the CSV file
 * with GroceriesFileReader class and also keeps track of the duration of
 * the songs reading the data set in JSON format with MillionSongDataSubset class
 *
 * @author Foothill College, Bita M, Selahittin Saytaş
 */
public class SubsetSum //implements Cloneable
{
    /**
     * The static method iterates through the shoppingList and creates
     * a subset of this master list then checks if there are any
     * numbers that are large but smaller than or equal to the
     * sum. If so, the method adds it and and repeates the process
     * until finds the number of subset whose sum is exactly the
     * budget. Eventually, Generates a new subset from the existing
     * subset and returns it
     *
     * @param shoppingList  An ArrayList of shoppinList whose generic type
     *                      is Double
     * @param budget    A int value of the budget
     * @return  An ArrayList of generic type Double
     */
    public static ArrayList<Double> findSubset(ArrayList<Double> shoppingList, double budget)
    {
        // if sum(L) + x <= add the sublist L + x to Collection
        // if sum(L) + x == t, break from both loops

        ArrayList<Double> outputList = new ArrayList<>();
        //ArrayList<Double> subList = new ArrayList<>();

        double minimum = 99999.0; //Double.MAX_VALUE
        double total;
        double sum;

        //System.out.println("THE INITIAL SIZE OF THE LIST: " + outputList.size());

        // Iterate through to print 2^n subset one by one
        // Resources:
        // https://docs.oracle.com/javase/tutorial/java/nutsandbolts/op3.html
        // https://docs.oracle.com/javase/specs/jls/se7/html/jls-15.html#jls-15.19
        // https://stackoverflow.com/questions/10910913/how-do-shift-operators-work-in-java
        // https://www.geeksforgeeks.org/sum-of-bitwise-or-of-all-possible-subsets-of-given-set/
        // Algo: Loop over all elements x in S
        for(int indexOfCurrentElement = 0; indexOfCurrentElement < (1 << shoppingList.size()); indexOfCurrentElement++) //shoppingList.size()
        {
            ArrayList<Double> subList = new ArrayList<>();
            total = 0.0;

            //int shiftLeft = (1 << shoppingList.size());
            //System.out.println("Bitwise Operation :" + shiftLeft);

            // Research for some optimization
//            if(budget < shoppingList.get(i))
//            {
//                continue;
//            }

            int listSize = shoppingList.size();

            // Algo: Loop over all subsets, L, that are already members of Col
            for(int indexOfCurrentSublistInCol = 0; indexOfCurrentSublistInCol < listSize; indexOfCurrentSublistInCol++)
            {
                //System.out.println("I am in the inner loop!!!");
                try
                {
                    // The bitwise & operator performs a bitwise AND operation
                    // (1 << j) is a number with j^th bit 1. When we use '&'
                    // operator with the subList numbers, we can identify which
                    // numbers are exist in the sublist, and which are not
                    // https://docs.oracle.com/javase/tutorial/java/nutsandbolts/op3.html
                    // https://docs.oracle.com/javase/specs/jls/se7/html/jls-15.html#jls-15.19
                    // https://introcs.cs.princeton.edu/java/41analysis/SubsetSum.java.html
                    if((indexOfCurrentElement & (1 << indexOfCurrentSublistInCol)) > 0) // (i & (1 << j))
                    {
                        //System.out.println("(1 << j) number :" + (1 << j));
                        //System.out.println("(i & (1 << j)) number: " + (i & (1 << j)));
                        //System.out.println("Inside IF-STATEMENT");
                        subList.add(shoppingList.get(indexOfCurrentSublistInCol));
                    }
                }
                catch(IndexOutOfBoundsException e)
                {
                    System.out.println(e);
                }
            }

            for(int k = 0; k < subList.size(); k++)
            {
                total += subList.get(k);
                //System.out.println(total);
            }

            //System.out.println(total);

            sum = Math.abs(budget - total);
            //System.out.println("SUM is: " + sum);

            // Checks for the exact match
            if(total == budget)
            {
                outputList.clear();
                outputList.addAll(subList);
                //System.out.println("Another sum is : " + sum);
                return outputList;
            }

            // If no exact match, checks for the closest one
            if((sum < minimum) && (total <= budget))
            {
                minimum = sum;
                //System.out.println("Sum is : " + sum);
                outputList.clear();
                outputList.addAll(subList);
            }

            //outputList.addAll(subList);
        }
        return outputList;
    }


    /**
     * The static method iterates through the songList and creates
     * a subset of this master list then checks if there are any
     * music durations that are large but smaller than or equal to the
     * duration. If so, the method adds it and and repeats the process
     * until finds the number of subset whose duration is exactly the
     * the same as the argument. Eventually, Generates a new subset from
     * the existing subset and returns it
     *
     * @param songList  An ArrayList of songList whose generic type
     *                  SongEntry
     * @param duration  A double value of the musicDuration
     * @return  An ArrayList of generic type SongEntry
     */
    public static ArrayList<SongEntry> findSubsetOfSongs(ArrayList<SongEntry> songList, double duration)
    {
        ArrayList<SongEntry> outputList = new ArrayList<>();

        int musicDuration = (int)(duration * 60);
        // To avoid the large input size, the
        // actual problem size is reduced
        int indexOfSongs = isSubsetSum(songList, musicDuration);

        long setPowerSize = (long)Math.pow(2, indexOfSongs);
        long minimum = setPowerSize;
        long total;
        long sum;

        for(long i = 0; i < setPowerSize; i++)
        {
            //ArrayList<SongEntry> subList = new ArrayList<>();
            ArrayList<SongEntry> subList = new ArrayList<>();

            total = 0;

            for(long j = 0; j < indexOfSongs; j++)
            {
                if((i & (1 << j)) > 0)
                {
                    subList.add(songList.get((int)j));
                }
            }

            for(int k = 0; k < subList.size(); k++)
            {
                total += subList.get(k).getDuration();
            }

            sum = (long)Math.abs(musicDuration - total);

            // Checks for the exact match
            if(total == musicDuration)
            {
                outputList.clear();
                outputList.addAll(subList);

                int returnedDurationSize = 0;

                for(int j = 0; j < outputList.size(); j++)
                {
                    returnedDurationSize += outputList.get(j).getDuration();
                }

                System.out.println("The duration of the returned list is :" + returnedDurationSize +
                        "\nThe needed duration is : " + musicDuration);

                return outputList;
            }

            // If no exact match, checks for the closest one
            if((sum < minimum) && (!subList.isEmpty()) && (total < musicDuration))
            {
                minimum = sum;
                outputList.clear();
                outputList.addAll(subList);
            }
        }

        return outputList;
    }


    /**
     * The static method constructs a 2D boolean table[][] and then checks
     * whether there is a subset of songList[] with sum equal to sumreturns
     * true if a subset of songList[] with sum equal to the given sum
     *
     * @param songList  An ArrayList of songList whose generic type
     *                  is SongEntry
     * @param sum   An integer of the sum of musicDuration
     * @return  An integer of the subset
     */
    public static int isSubsetSum(ArrayList<SongEntry> songList, int sum)
    {
        // The value of the subset[i][j] will be
        // true there is a subset of [0 ... j - 1]
        // with sum equal to i
        int songListSize = songList.size();
        boolean subset[][] = new boolean[sum + 1][songListSize + 1];

        // If the sum is equal to 0 then then the
        // set is not empty - boolean value is true
        for(int i = 0; i <= songListSize; i++)
        {
            subset[0][i] = true;
        }

        // If the sum is not equal to 0 then
        // the set is empty - boolean value is false
        for(int i = 1; i <= sum; i++)
        {
            subset[i][0] = false;
        }

        // Filling the subset table in botton manner
        for(int i = 1; i <= sum; i++)
        {
            for(int j = 1; j <= songListSize; j++)
            {
                subset[i][j] = subset[i][j - 1];
                if(i >= songList.get(j - 1).getDuration())
                {
                    subset[i][j] = subset[i][j] || subset[i - songList.get(j - 1).getDuration()][j - 1];
                }
            }
        }

        for(int i = sum; i > 0; i--)
        {
            for(int j = 0; j <= songListSize; j++)
            {
                if(subset[i][j])
                {
                    System.out.println("Index returned : " + j);
                    return j;
                }
            }
        }
        return 0;
    }
}