package subsetsum;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * GroceriesFileReader class reads input from a CSV file one line at a time and
 * adds the grocery item prices into the double type of ArrayList called itemPrices
 *
 * @author Foothill College, Bita M, Selahittin Saytaş
 */
public class GroceriesFileReader
{
    private ArrayList<String> groceryItems = new ArrayList<>();
    private ArrayList<Double> itemPrices = new ArrayList<>();

    /**
     * The method reads the CSV (Comma Separated Value)
     * file of groceries and creates and adds the prices of
     * grocery items into a specified ArrayList type of double
     * @param filePath  The relative path of the file
     * @return  An ArrayList of doubles containing the price
     *          of grocery items
     */
    public ArrayList<Double> readFile(String filePath) //throws FileNotFoundException
    {
        File fileName = new File(filePath);
        Scanner input;
        int count = 0;

        try
        {
            input = new Scanner(fileName);

            while(input.hasNextLine())
            {
                String line = input.nextLine();
                String[] tokens = line.split(",", -1);
                String groceryItem = tokens[0];
                double itemPrice = Double.parseDouble(tokens[1]);

                groceryItems.add(groceryItem);
                itemPrices.add(itemPrice);
            }
            input.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File " + fileName + "not found!");
        }

        return itemPrices;
    }

    /**
     * The method prints the grocery items
     */
    public void printGroceryItems()
    {
        System.out.println("Grocery Items: ");
        for(String item : groceryItems)
        {
            System.out.println(item);
        }
    }

    /**
     * The method prints the prices of grocery items
     */
    public void printItemPrices()
    {
        System.out.println("Item Prices: ");
        for(double itemPrice : itemPrices)
        {
            System.out.println(itemPrice);
        }
    }
}
