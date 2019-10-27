package queues;

import cs1c.SongEntry;

import java.io.File;
import java.io.FileNotFoundException;
//import java.util.Queue;
import java.util.Scanner;

/**
 * The class creates and manages three objects of type
 * Queue<SongEntry> class. An instance of the class reads
 * a file which includes the user's requests for the name
 * of a song to be added to a specific playlist then then
 * adds songs to the three playlists "favorites", "lounge",
 * and "road trip" accordingly
 */
public class Jukebox
{
    private Queue<SongEntry> favoritePL;
    private Queue<SongEntry> roadTripPL;
    private Queue<SongEntry> loungePL;

    /**
     * The constructor method initializes playlists; the
     * Queue<SongEntry> type field variables favoritePL,
     * roadTripPL, and loungePL, respectively
     */
    public Jukebox()
    {
        this.favoritePL = new Queue<>("favorites");
        this.roadTripPL = new Queue<>("road trip");
        this.loungePL = new Queue<>("lounge");
    }

    /**
     * The method reads the test file and then adds songs
     * to one of the three queues. Then the first song found
     * that equals the title will be placed in the favorites
     * playlist
     * @param requestFile   The path of the file
     * @param allSongs      An array of type SongEntry[]
     */
    public void fillPlaylists(String requestFile, SongEntry[] allSongs)
    {
        File fileName = new File(requestFile);
        Scanner input;
        int count = 0;

        try
        {
            input = new Scanner(fileName);

            while(input.hasNextLine())
            {
                String line = input.nextLine();
                String[] tokens = line.split(",", -1);
                String queuePlayListType = tokens[0];
                String songType = tokens[1];
                SongEntry song = null;

                for(int i = 0; i < allSongs.length; i++)
                {
                    if(allSongs[i].getTitle().equals(songType))
                    {
                        song = allSongs[i];
                        break;
                    }
                }

                if(song != null)
                {
                    switch(queuePlayListType)
                    {
                        case "favorites":
                            favoritePL.enqueue(song);
                            break;

                        case "road trip":
                            roadTripPL.enqueue(song);
                            break;

                        case "lounge":
                            loungePL.enqueue(song);
                            break;
                    }
                }
            }
            input.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File " + fileName + " is not found!");
        }
    }

    /**
     * The accessor method returns an object of
     * Queue<SongEntry> type
     * @return  A Queue<SongEntry> object of
     *          favoritePL
     */
    public Queue<SongEntry> getFavoritePL()
    {
        return favoritePL;
    }

    /**
     * The accessor method returns an object of
     * Queue<SongEntry> type
     * @return  A Queue<SongEntry> object of
     *          roadTripPL
     */
    public Queue<SongEntry> getRoadTripPL()
    {
        return roadTripPL;
    }

    /**
     * The accessor method returns an object of
     * Queue<SongEntry> type
     * @return  A Queue<SongEntry> object of
     *          loungePL
     */
    public Queue<SongEntry> getLoungePL()
    {
        return loungePL;
    }
}
