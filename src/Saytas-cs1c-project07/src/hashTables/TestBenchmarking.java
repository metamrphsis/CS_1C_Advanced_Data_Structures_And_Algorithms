package hashTables;

import cs1c.MillionSongDataSubset;
import cs1c.SongEntry;
import cs1c.TimeConverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The TestBenchmarking class compares the search time of the two data structures
 * by testing the FHhashQPwFind and wrapper class SongsCompGenre and SongCompInt
 *
 * @author Selahittin Saytaş
 */
public class TestBenchmarking
{
    public static final boolean SHOW_DETAILS = false;
    private SongEntry[] allSongs;
    private FHhashQPwFind<Integer, SongCompInt> tableOfSongIDs;

    private TestBenchmarking(SongEntry[] allSongs)
    {
        TableGenerator tableGenerator = new TableGenerator();
        this.allSongs = new SongEntry[allSongs.length];
        tableOfSongIDs = tableGenerator.populateIDtable(allSongs);
        this.allSongs = allSongs;
    }

    /**
     * Uses MillionSongDataSubset to read all songs from data file.
     * @param jsonFile		A JSON file to parse
     * @return				The array of SongEntry objects
     */
    public static SongEntry[] readSongsFromDataFile(String jsonFile)
    {
        MillionSongDataSubset msd = new MillionSongDataSubset(jsonFile);
        SongEntry[] allSongs = msd.getArrayOfSongs();
        System.out.printf("Total number of songs read %d \n", allSongs.length);
        return allSongs;
    }

    /**
     * Basic file reader which reads a file with format:
     * [value to find]
     * [value to find]
     * etc.
     * And stores each value into a list.
     * @param fileName	The input file to read.
     * @return Read lines as a list.
     */
    public ArrayList<String> readFindRequests(String fileName)
    {
        ArrayList<String> requests = new ArrayList<>();
        Scanner input = null;

        try
        {
            File file = new File(fileName);
            input = new Scanner(file);

            String line = "";

            while(input.hasNextLine())
            {
                line = input.nextLine();
                requests.add("" + line);
            }

        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(input != null)
            {
                input.close();
            }
        }

        return requests;
    }

    /**
     * Tests the FHhashQPwFind and wrapper class SongCompInt.
     * @param fileName The input file to read.
     */
    public void testIDTableSequential(String fileName)
    {
        System.out.printf("Test file for ID table: %s \n", fileName);

        ArrayList<String> IDsToFind = readFindRequests(fileName);

        for(String ID : IDsToFind)
        {
            int id;

            try
            {
                id = Integer.parseInt(ID);
            }
            catch(NumberFormatException e)
            {
                System.out.printf("\nWarning: Input \"%s\" is not a valid number. Skipping...\n", ID);
                continue;
            }

            System.out.printf("\nFinding song ID: %d\n", id);

            try
            {
                //TODO: Define the wrapper class for a SongEntry object such that
                //it compares objects based on a song's integer id field
                boolean compareResult = findSequential(id);

                if(compareResult != false)
                {
                    System.out.printf("Song ID %d found in tableOfSongIDs:\n", id);
                }
            }
            catch(NoSuchElementException e)
            {
                System.out.printf("Song ID %d NOT found in tableOfSongIDs:\n", id);
            }
        }

        System.out.println("Done with testing table of IDs!\n");
    }

    /**
     * The method looks if the id's match
     * @param id An int type of id
     * @return  A boolean value of true or false
     */
    public boolean findSequential(int id)
    {
        for(int i = 0; i < allSongs.length; i++)
        {
            if(allSongs[i].getID() == id)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Tests the FHhashQPwFind and wrapper class SongCompInt.
     * @param fileName The input file to read.
     */
    public void testIDTable(String fileName)
    {
        System.out.printf("Test file for ID table: %s \n" + fileName);

        ArrayList<String> IDsToFind = readFindRequests(fileName);

        for(String ID : IDsToFind)
        {
            int id;

            try
            {
                id = Integer.parseInt(ID);
            }
            catch(NumberFormatException e)
            {
                System.out.printf("\nWarning: Input \"%s\" is not a valid number. Skipping...\n" + ID);
                continue;
            }

            System.out.printf("\nFinding song ID: %d\n" + id);

            try
            {
                //TODO: Define the wrapper class for a SongEntry object such that
                //it compares objects based on a song's integer id field
                SongCompInt compareResult = tableOfSongIDs.find(id);

                if(compareResult != null)
                {
                    System.out.printf("Song ID %d found in tableOfSongIDs:\n" + id);
                }
            }
            catch(NoSuchElementException e)
            {
                System.out.printf("Song ID %d NOT found in tableOfSongIDs:\n" + id);
            }
        }

        System.out.println("Done with testing table of IDs!\n");
    }


    /**
	 * Creates an object of type MyTunes class that, which reads the song information from a JSON input file
	 * and stores all entries in an array of SongEntry objects.
	 * Constructs an object of MyTunes, which populates two hash tables.
	 * Each tables uses different attribute of SongEntry class as the key to find.
	 * Tests finding keys in each hash table and reports whether requested key is found
     *
     * @param args  Command line arguments as an array of String objects
     */
    public static void main(String[] args)
    {
        final String DATAFILE = "resources/music_genre_subset.json";//Directory path for JSON file
        final String TESTFILE01 = "resources/findIDs.txt"; // Example test file for hashing based on IDs
        final String TESTFILE02 = "resources/findGenres.txt"; // Example test file for hashing based on genres names

        int length = 0;

        SongEntry[] allSongsOriginal = readSongsFromDataFile(DATAFILE);
        SongEntry[] allSongs = null;
        length = allSongsOriginal.length;
        long[][] runningTime = new long[(length / 5000) + 1][3];

        int counter = 0;
        do
        {
            allSongs = new SongEntry[length];
            System.arraycopy(allSongsOriginal, 0, allSongs, 0, length);

            MyTunes theStore = new MyTunes(allSongs);

            long startTime = System.nanoTime();
            theStore.testIDtable(TESTFILE01);
            long estimatedTime = System.nanoTime() - startTime;
            runningTime[counter][0] = length;
            runningTime[counter][1] = estimatedTime;

            System.err.flush();

            startTime = System.nanoTime();
            TestBenchmarking benchMarking = new TestBenchmarking(allSongs);
            benchMarking.testIDTableSequential(TESTFILE01);
            estimatedTime = System.nanoTime() - startTime;
            runningTime[counter][2] = estimatedTime;
            length = length - 5000;
            counter++;
        }
        while(length > 0);

        System.out.println("Time Comparison for Find Operation Quadratic VS Sequential:\n");
        System.out.format("%-50s%-50s%-50s", "Number of elements", "Quadratic", "Sequential\n\n");

        for(int i = 0; i < runningTime.length; i++)
        {
            System.out.println();
            System.out.format("%-50s%-50s%-50s", runningTime[i][0], TimeConverter.convertTimeToString(runningTime[i][1]),
                    TimeConverter.convertTimeToString(runningTime[i][2]));
            System.out.println();
        }

        System.out.println("\nDone with MyTunes!");
    }
}
