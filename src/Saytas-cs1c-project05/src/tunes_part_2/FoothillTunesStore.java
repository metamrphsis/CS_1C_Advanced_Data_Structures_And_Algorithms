package tunes_part_2;

import utilities.LazySearchTree;
import utilities.PrintObject;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The FoothillTunesStore class creates and initalizes an object of LazySearchTree
 * whose generic type is SongEntry. In the main method, creates an array of songs
 * and adds them in to the FoothillTunesStore object of store. The method sort the
 * songs based on their titles and experiments with removing the songs from the tree
 *
 * @author Foothill College, Bita Mazloom, Selahittin Saytaş
 */
public class FoothillTunesStore
{
    public static final boolean SHOW_DETAILS = true;

    PrintObject<SongEntry> printObject = new PrintObject<SongEntry>();

    // The data structure, which we use to add and remove items
    private LazySearchTree<SongEntry> tunes;

    /**
     * The constructor method initializes the
     * printObject SongEntry generic type of PrintObject
     */
    public FoothillTunesStore()
    {
        tunes = new LazySearchTree<SongEntry>();
    }

    /**
     * Add a new item with the name as in parameter into inventory. If there is
     * already a product with the same name, increase amount by one, if not create
     * a new object.
     * @param item  The item to be added to the inventory tree
     */
    public void addToTunes(SongEntry item)
    {
        // Check if the item is in the inventory tree
        boolean isFound = tunes.contains(item);

        // If the item is not found, add the temporary
        // object as another node (category) to the tree
        if(!isFound)
        {
            tunes.insert(item);
            return;
        }

    }

    /**
     * If the item is in the inventory, decrease the count by one
     * If only one item is left, remove it from the inventory
     * @param item  The item to be removed to the inventory tree
     */
    public void removeFromTunes(SongEntry item)
    {
        // Check if the item is in the inventory tree
        boolean isFound = tunes.contains(item);

        if(!isFound)
        {
            throw new NoSuchElementException();
        }

        tunes.remove(item);
    }

    /**
     * The method checks if the song exist in the
     * LazySearchTree
     * @param title A String type of the song title
     * @return      A boolean value true or false
     *              whether the song title exist
     *              or not
     */
    public boolean search(String title)
    {
        SongEntry tmp = new SongEntry(title, 0, "", "");
        SongEntry.setSortType(0);

        return(tunes.contains(tmp));
    }

    /**
     * Display the first item and last time of the soft tree in lexical order
     * @param message   The message to display
     */
    public void showFirstAndLastItem(String message)
    {
        System.out.println("\n" + message);

        try
        {
            SongEntry min = tunes.findMin();
            System.out.println("The first item: " + min.toString());
        }
        catch (Exception NoSuchElementException)
        {
            System.out.println("Warning: Minimum element is not found!");
        }

        try
        {
            SongEntry max = tunes.findMax();
            System.out.println("The last item: " + max.toString());
        }
        catch(Exception NoSuchElementException)
        {
            System.out.println("Warning: Maximum element is not found!");
        }
    }

    /**
     * The method displays the songs in the tree
     * if the boolean value is true
     * @param message   A String value of message
     * @param showTree  A boolean value of true or false
     *                  Depending on the value, the method
     *                  displays the songs in the tree
     */
    protected void displayTunesState(String message, boolean showTree)
    {
        System.out.println("\n" + message);
        System.out.println("\"Hard\" number of unique items (i.e. mSizeHard) = " + tunes.sizeHard());
        System.out.println("\"Soft\" number of unique items (i.e. mSize) = " + tunes.size());

        if(!showTree)
        {
            return;
        }

        System.out.println("\nTesting traversing \"hard\" tree:");
        tunes.traverseHard(printObject);

        System.out.println("\n\nTesting traversing \"soft\" inventory:");
        tunes.traverseSoft(printObject);
        System.out.println("\n");
    }







    /**
     * Main method
     * @param args  The supplied command-line arguments
     *              as an array of String objects
     */
    public static void main(String[] args)
    {
        final String FILEPATH = "resources/music_genre_subset.json";

        MillionSongDataSubset msd = new MillionSongDataSubset(FILEPATH);

        // Retrieves the parsed objects
        SongEntry[] allSongs = msd.getArrayOfSongs();

        FoothillTunesStore store = new FoothillTunesStore();

        for(int i = 0; i < 10; i++)
        {
            store.addToTunes(allSongs[i]);
        }

        // Sorting by title
        SongEntry.setSortType(0);

        if(SHOW_DETAILS)
        {
            store.displayTunesState("\nSong list", true);
        }

        Scanner keyboard = new Scanner(System.in);

        do
        {
            System.out.println("Enter the song title to be searched: \n");
        }
        while(!keyboard.hasNextLine());

        String title = keyboard.nextLine();

        if(store.search(title))
        {
            System.out.println("\nThe song title is found: " + title);
        }
        else
        {
            System.out.println("\nThe song title '" + title + "' is not found!");
        }

        if(SHOW_DETAILS)
        {
            store.showFirstAndLastItem("\nThe first and the last songs: ");
        }

        System.out.println("\nDeleting one song from the store: ");
        store.removeFromTunes(allSongs[0]);
        if(SHOW_DETAILS)
        {
            store.displayTunesState("\nThe songs list after the deletion: ", true);
        }

        System.out.println("\nDeleting one more song from the store: ");
        store.removeFromTunes(allSongs[1]);
        if(SHOW_DETAILS)
        {
            store.displayTunesState("\nThe songs list after one more deletion: ", true);
        }

        if(SHOW_DETAILS)
        {
            store.showFirstAndLastItem("\nLastly displaying the first and the last songs: ");
        }

        System.err.flush();
        System.out.println("\n");
        System.out.println("\"Done with FoothillTunesStore.\"");
    }
}