project folder:
Saytas-cs1c-project07/


Brief description of submitted files:

src/cs1c/

        - MillionSongDataSubset.java
            - One object of class MillionSongDatSubset parses a JSON data set with a given file path.
              Stores each entry into a SongEntry object.

              One object of class MillionSongDataSubset parses a JSON data set and stores each entry in an array
              One object of class FoothillTunesStore parses a JSON data set with a given json file
              and stores each entry in an array 'tunes' and acts as a iTune song store. It provides
              methods showing all the songs in our personal tunes library, adding new songs in our
              tunes collection by title selection, making a play list of a certain duration.

        - SongEntry.java
            - One object of class SongEntry stores a simplified version of the genre data set
              from the Million Song Dataset
              http://labrosa.ee.columbia.edu/millionsong/blog/11-2-28-deriving-genre-dataset

        - TimeConverter.java
            - The class converts duration into a String representation
              seconds into format: hours : minutes : seconds

src/hashTables/
        - FHhashQP.java
            - FHhashQP class --------------------------------------------

        - FHhashQPwFind.java
            - The FHhashQPwFind class extends FHhashQP and provides modifications to
              find(),myHashKey(), and findPosKey() methods

        - HashEntry.java
            - HashEntry class for use by FHhashQP -----------------------

        - MyTunes.java
            - Tests the functionality of FHhashQPwFind.java. Specifically, checks for
              implementation of find() function to return an object associated with a
              given key input

        - SongCompInt.java
            - The SongCompInt class is a Wrapper class for a SongEntry object
              We will use this to compare objects based on the songs int id field

        - SongsCompGenre.java
            - The SongsCompGenre is Wrapper class for a SongEntry object
              We will use this to compare objects based on the songs String genre field
              We will use this to determine the number of songs in each genre

        - TableGenerator.java
            - The TableGenerator class creates and populates two hash tables of type
              FHhashQPwFind class, one for each wrapper class SongCompInt and SongCompGenre

        // Extra Credit Portion test File
        - TestBenchMarking.java
            - The TestBenchmarking class compares the search time of the two data structures
              by testing the FHhashQPwFind and wrapper class SongsCompGenre and SongCompInt

resources/
    - Two CSV (Comma Separated Value) file
        - findGenres.txt
        - findIDs.txt

    - One JSON (JavaScript Object Notation) file
        - music_genre_subset.json

    RUN.txt
        - Console output of MyTunes.java class

    Extra_Credit_Portion_RUN.txt
        - Console output of TestBenchmarking.java class

README.txt
- Description of submitted files