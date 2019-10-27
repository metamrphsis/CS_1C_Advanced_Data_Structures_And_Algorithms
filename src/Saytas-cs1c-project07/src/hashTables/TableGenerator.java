package hashTables;

import cs1c.SongEntry;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * The TableGenerator class creates and populates two hash tables of type
 * FHhashQPwFind class, one for each wrapper class SongCompInt and SongCompGenre
 *
 * @author Selahittin Saytaş
 */
public class TableGenerator
{
    FHhashQPwFind<Integer, SongCompInt> tableOfSongIDs;
    FHhashQPwFind<String, SongsCompGenre> tableOfSongGenres;
    ArrayList<String> genreNames;

    /**
     * The constructor initializes the field attributes
     */
    public TableGenerator()
    {
        this.tableOfSongIDs = new FHhashQPwFind<>();
        this.tableOfSongGenres = new FHhashQPwFind<>();
        this.genreNames = new ArrayList<>();
    }

    /**
     * The method populates the tableOfIDs hash table
     * @param allSongs  A SongEntry array of all songs
     * @return  A hash tables of type FHhashQPwFind class
     *          for SongCompInt wrapper class
     */
    public FHhashQPwFind<Integer, SongCompInt> populateIDtable(SongEntry[] allSongs)
    {
        for(int i = 0; i < allSongs.length; i++)
        {
            Integer songID = null;
            SongCompInt songIntID = null;

            try
            {
                songID = allSongs[i].getID();
                songIntID = tableOfSongIDs.find(songID);
            }
            catch(NoSuchElementException e)
            {}

            if(songIntID == null)
            {
                songIntID = new SongCompInt(songID);
                tableOfSongIDs.insert(songIntID);
            }
        }

        return tableOfSongIDs;
    }

    /**
     * The method populates the tableOfGenres
     * hash table and ArrayList of genre names
     * @param allSongs  A SongEntry array of all songs
     * @return  A hash tables of type FHhashQPwFind class
     *          for SongCompGenre wrapper class
     */
    public FHhashQPwFind<String, SongsCompGenre> populateGenreTable(SongEntry[] allSongs)
    {
        for(int i = 0; i < allSongs.length; i++)
        {
            String songGenre = null;
            SongsCompGenre songStringGenre = null;

            try
            {
                songGenre = allSongs[i].getGenre();
                songStringGenre = tableOfSongGenres.find(songGenre);
            }
            catch(NoSuchElementException e)
            {}

            if(songStringGenre == null)
            {
                genreNames.add(songGenre);
                songStringGenre = new SongsCompGenre(songGenre);
                tableOfSongGenres.insert(songStringGenre);
            }

            songStringGenre.addSong(allSongs[i]);
        }

        return tableOfSongGenres;
    }

    /**
     * The accessor method returns a String
     * type ArrayList for genre Names
     * @return  A String type ArrayList of song
     *          genre names
     */
    public ArrayList<String> getGenreNames()
    {
        return this.genreNames;
    }
}
