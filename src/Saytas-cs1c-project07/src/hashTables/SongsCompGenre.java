package hashTables;

import cs1c.SongEntry;

import java.util.ArrayList;

/**
 * The SongsCompGenre is Wrapper class for a SongEntry object
 * We will use this to compare objects based on the songs String genre field
 * We will use this to determine the number of songs in each genre
 */
public class SongsCompGenre implements Comparable<String>
{
    private String nameOfTheGenre = "";
    private String songData;
    private ArrayList<SongEntry> allSongs;
    //private HashEntry<String> songData;

    /**
     * The constructor initializes field attributes
     * @param songGenreData A String for the song genre
     */
    public SongsCompGenre(String songGenreData)
    {
        super();
        this.nameOfTheGenre = songGenreData;
        this.songData = songGenreData;
        this.allSongs = new ArrayList<>();
        //songData = new HashEntry<String>(songData, FHhashQP.ACTIVE);
    }

    @Override
    /**
     * The method compares two strings
     */
    public int compareTo(String songKey)
    {
        return this.getNameOfTheGenre().compareTo(songKey);
        //return this.getNameOfTheGenre().compareTo(songKey);
    }

    /**
     * The method compares the two given strings based on
     * the content of the string
     * @param songGenre A String type of song genre
     * @return  A boolean value of true or false
     */
    public boolean equals(SongsCompGenre songGenre)
    {
        return this.getNameOfTheGenre().equals(songGenre.getNameOfTheGenre());
        //return this.getNameOfTheGenre().equals(songGenre.getNameOfTheGenre());
    }

    @Override
    /**
     * The method returns the integer hash code value of the object
     */
    public int hashCode()
    {
        return this.getNameOfTheGenre().hashCode();
        //return this.getNameOfTheGenre().hashCode();
    }

    @Override
    /**
     * The toString() method prints the song data
     */
    public String toString()
    {
        return songData.toString();
    }

    /**
     * The method adds a song to the list of songs
     * @param song  A SongEntry object type of song
     */
    void addSong(SongEntry song)
    {
        allSongs.add(song);
    }

    /**
     * The accessor method returns the name of the genre
     * @return  A String type of name of the genre
     */
    public String getNameOfTheGenre()
    {
        return nameOfTheGenre;
    }

    /**
     * The accessor method returns a SongEntry
     * type ArrayList of all songs
     * @return  A SongEntry type ArrayList of songs
     */
    public ArrayList<SongEntry> getAllSongs()
    {
        return allSongs;
    }

    /**
     * The accessor method returns the name of the genre
     * @return  A String type of name of the genre
     */
    public String getName()
    {
        return nameOfTheGenre;
    }

    /**
     * The accessor method returns a SongEntry
     * type ArrayList of all songs
     * @return  A SongEntry type ArrayList of songs
     */
    public ArrayList<SongEntry> getData()
    {
        return allSongs;
    }
}
