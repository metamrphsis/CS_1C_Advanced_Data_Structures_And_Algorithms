package utilities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Random;

/**
 * The QuickSortTest class investigates the recursion limit of arrays of
 * various sizes. It investigates recursion limits from 2 to 300 by
 * incrementing by 2 and runs them on different size arrays from 20.000 to
 * a larger array size.
 *
 * @author Bita Mazloom, Selahittin Saytaş
 */
public class QuickSortTest
{
    private static double[][] runningTime;
    private static int count = 0;

    /**
     * The method creates a random array of Integers[]
     * and fills up the runningTime[][] double array
     * including the recursion limit and array size
     * @param limit The recursion limit
     * @param size  The array size
     */
    public static void callSort(int limit, int size)
    {
        int[] array = new int[size];

        Random randomGenerator = new Random();

        array = randomGenerator.ints(size, 0,size + 1).toArray();

        Integer[] randomNumbers = Arrays.stream(array).boxed().toArray(Integer[]::new);

        runningTime[count][0] = size;

        double average = 0.0;
        long startTime;
        long estimatedTime;

        for(int i = 0; i < 3; i++)
        {
            startTime = System.nanoTime();
            FHsort.quickSort(randomNumbers);
            estimatedTime = System.nanoTime() - startTime;
            BigDecimal bigDecimal = new BigDecimal(Double.toString(estimatedTime / 1000000.0));
            bigDecimal = bigDecimal.setScale(3, RoundingMode.HALF_UP);
            average += bigDecimal.doubleValue();
        }

        runningTime[count][limit / 2] = average / 3.0;

//        long startTime = System.nanoTime();
//        FHsort.quickSort(randomNumbers);
//
//        long estimatedTime = System.nanoTime() - startTime;
//        runningTime[count][0] = size;
//        //runningTime[count][limit / 2] = estimatedTime / 1000000.0;
//        BigDecimal bigDecimal = new BigDecimal(Double.toString(estimatedTime / 1000000.0));
//        bigDecimal = bigDecimal.setScale(3, RoundingMode.HALF_UP);
//
//        runningTime[count][limit / 2] = bigDecimal.doubleValue();
    }

    /**
     * The main method refers to array of the
     * type String by the name args this will
     * be used as input during usage of command
     * line argument
     * @param args  An array of String objects
     *              contains the supplied
     *              command-line arguments
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {
        //runningTime = new double[20][151];
        runningTime = new double[21][151];
        int size = 20000;
        int row = 0;

        do
        {
            for(int limit = 2; limit <= 300; limit += 2)
            {
                FHsort.QS_RECURSION_LIMIT = limit;
                callSort(limit, size);
            }

            count++;
            // To set the size of the array
            size += 100000; //100000 - 500000 - 1000000
            row++;
        }
        while(row <= 20);

        System.out.println("The table with its size of the array in rows and its recursion " +
                "limit is represented in columns\n" +
                "Table[i][j] will be the running time\n");
        System.out.format("%-14s", "size");

        for(int j = 1; j < runningTime[0].length; j++)
        {
            System.out.format("%-14d", (2 * j));
        }

        System.out.println("\n\n");

        for(int i = 0; i < runningTime.length; i++)
        {
            System.out.format("%-14s", "Running Time");

            for(int j = 0; j < runningTime[0].length; j++)
            {
                System.out.format("%-14.3f", runningTime[i][j]);
            }

            System.out.println("\n");
        }


        // Writing to a CSV file
        // In each line, the first entry is the size of the array
        // And the remaining entries are the running time in miliseconds
        // for every recursion limit starting from 2 to 300
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("resources/RUN_1.csv"));
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("resources/RUN_2.csv"));
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("resources/RUN_3.csv"));
        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0; i < runningTime.length; i++)
        {
            for(int j = 0; j < runningTime[0].length; j++)
            {
                stringBuilder.append("" + runningTime[i][j]);
                stringBuilder.append(",");
            }

            bufferedWriter.write(stringBuilder.toString());
            bufferedWriter.newLine();
            stringBuilder.setLength(0);
        }

        bufferedWriter.close();
    }
}