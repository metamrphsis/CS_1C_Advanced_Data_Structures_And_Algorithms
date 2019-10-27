package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * The GetGraphData class reads the data from the CSV file
 *
 * @author Bita Mazloom, Selahittin Saytaş
 */
public class GetGraphData
{
    private int rows;
    private int columns;
    private int numPlots;
    private int[] limit;
    private double[] arrayLength;
    private int[][] data;

    /**
     * The constructor initializes the instance
     * variables
     */
    public GetGraphData()
    {
        rows = 21;
        columns = 150;
        numPlots = rows;
        limit = new int[columns];
        arrayLength = new double[rows];

        for(int i = 0; i < rows; i++)
        {
            arrayLength[i] = 20000.0 + i * 500000.0;
        }

        for(int i = 0; i < columns; i++)
        {
            limit[i] = 2 + i * 2;
        }

        data = new int[rows][columns];
        //readAndParseInputFile("resources/RUN_1.csv");
        //readAndParseInputFile("resources/RUN_2.csv");
        readAndParseInputFile("resources/RUN_3.csv");
    }

    /**
     * The method calls the getReader method to
     * get the file reader, and then to parse the
     * input lines which are already split into strings
     * @param name  The path of the file CSV file
     */
    public void readAndParseInputFile(String name)
    {
        BufferedReader bufferedReader = getReader(name);
        String input = "";
        int i = 0;
        double temp = 0.0;

        while(true)
        {
            try
            {
                input = bufferedReader.readLine();

                if(input == null)
                {
                    bufferedReader.close();
                    break;
                }
            }
            catch(IOException e)
            {
                System.out.println(e.getMessage());
                e.printStackTrace(System.err);
                System.out.println(e.toString());
                System.exit(0);
            }

            String[] tokens = input.split(",");

            for(int j = 1; j < tokens.length; j++)
            {
                temp = Double.parseDouble(tokens[j]);
                data[i][j - 1] = Math.round((float) temp);
            }

            i++;
        }
    }

    /**
     * The method checks whether the stream is ready to be read
     * text from a character-input stream
     * @param name The path of the file CSV file
     * @return  Returns null if no data to read from the reader
     *          Otherwise, returns a textual line read from the
     *          BufferedReader
     */
    public BufferedReader getReader(String name)
    {
        BufferedReader bufferedReader = null;

        try
        {
            File file = new File(name);
            bufferedReader = new BufferedReader(new FileReader(file));
        }
        catch(FileNotFoundException e)
        {
            System.out.println("The file is not found!");
            System.exit(0);
        }

        return bufferedReader;
    }

    /**
     * The accessor method returns the
     * int type of number of rows
     * @return the rows
     */
    public int getRows()
    {
        return rows;
    }

    /**
     * The accessor method returns the
     * int type of number of columns
     * @return the columns
     */
    public int getColumns()
    {
        return columns;
    }

    /**
     * The accessor method returns the
     * int type of number of plots which
     * is the row of part of the data
     * @return the numPlots
     */
    public int getNumPlots()
    {
        return numPlots;
    }

    /**
     * The accessor method returns the
     * int[] type of 1D array
     * @return the limit
     */
    public int[] getLimit()
    {
        return limit;
    }

    /**
     * The accessor method returns the
     * double[] type of 1D array
     * @return the arrayLength
     */
    public double[] getArrayLength()
    {
        return arrayLength;
    }

    /**
     * The accessor method returns the
     * int[][] type of 2D array
     * @return the data
     */
    public int[][] getData()
    {
        return data;
    }
}