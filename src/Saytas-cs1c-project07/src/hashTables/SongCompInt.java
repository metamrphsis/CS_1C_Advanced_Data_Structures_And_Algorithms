package hashTables;

/**
 * The SongCompInt class is a Wrapper class for a SongEntry object
 * We will use this to compare objects based on the songs int id field
 */
public class SongCompInt implements Comparable<Integer>
{
    private Integer songData;
    private int songID;
    //private HashEntry<SongEntry> songData;

    /**
     * The constructor method initializes the songData
     * @param songData  An int for the song ID
     */
    public SongCompInt(int songData)
    {
        this.songData = songData;
        //this.songID = songData.getID();
        //songData = new HashEntry<>(songData, FHhashQP.ACTIVE);
    }

    @Override
    /**
     * The method compares two strings
     */
    public int compareTo(Integer songKey)
    {
        return songData - songKey;
        //return songData.getID() - songKey;
        //return songData.data.getID- songKey;
    }

    /**
     * The method compares the two given strings based on
     * the content of the string
     * @param songID A int type of song genre
     * @return  A boolean value of true or false
     */
    public boolean equals(SongCompInt songID)
    {
        return this.songData.equals(songID.songData);
        //return songData.data.equals(songID.songData.data);
    }

    @Override
    /**
     * The method returns the integer hash code value of the object
     */
    public int hashCode()
    {
        return songData.hashCode();
        //return songData.data.getID();
    }

    @Override
    /**
     * The toString() method prints the songData
     */
    public String toString()
    {
        return songData.toString();
        //return songData.data.toString)();
    }
}
