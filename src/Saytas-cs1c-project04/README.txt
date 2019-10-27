project folder:
Saytas-cs1c-project04/


Brief description of submitted files:

src/cs1c/
        - MillionSongDataSubset.java
            - One object of class MillionSongDataSubset parses a JSON data set and stores each
              entry in an array

        - SongEntry.java
            - One object of class SongEntry stores a simplified version of the genre data set
              from the Million Song Dataset
              http://labrosa.ee.columbia.edu/millionsong/blog/11-2-28-deriving-genre-dataset

        - TimeConverter.java
            - Converts duration into a string representation

src/queues/
        - Jukebox.java
            - The class creates and manages three objects of type
              Queue<SongEntry> class. An instance of the class reads
              a file which includes the user's requests for the name
              of a song to be added to a specific playlist then then
              adds songs to the three playlists "favorites", "lounge",
              and "road trip" accordingly

        - MyTunes.java
            - Creates an object of type MyTunes which simulates a playlist queue
              Enqueues and dequeues SongEntry objects from each playlist
              Simulates playing each song and finally checks the state of each playlist

        - Queue.java
            - The Singly-LinkedList parameterized class implements
              Iterable<T> interface. Objects of type Queue manage
              items in a singly linked list where we can enqueue()
              items to the end and dequeue() items from the front
              of the queue

resources/
    - One JSON (JavaScript Object Notation) file
        - music_genre_subset.json

    - Two CSV (Comma Separated Value) file
        - tunes.txt
        - tunes_truncated.txt

    RUN.txt
        - Console output of test file: resources/tunes.txt

    RUN2.txt
        - Console output of test file: resources/tunes_truncated.txt

    RUN3.txt
        - Console output of an empty test file: resources/empty_tunes.txt

README.txt
- Description of submitted files