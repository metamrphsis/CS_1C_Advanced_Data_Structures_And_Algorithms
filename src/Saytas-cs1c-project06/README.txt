project folder:
Saytas-cs1c-project06/


Brief description of submitted files:

src/market_app/

        - SuperMarket.java
            - Simulates the market scenario of buying and adding items to a store's inventory.
              Implements BST with lazy deletion to keep track of total inventory ("deleted" + non deleted)
              and current inventory (non deleted only).

src/tunes_part_2/
        - FoothillTunesStore.java
            - The FoothillTunesStore class creates and initializes an object of LazySearchTree
              whose generic type is SongEntry. In the main method, creates an array of songs
              and adds them in to the FoothillTunesStore object of store. The method sorts the
              songs based on their titles. The class experiments with removing the songs from the tree

        - MillionSongDataSubset.java
            - One object of class MillionSongDataSubset parses a JSON data set and stores each entry in an array
              One object of class FoothillTunesStore parses a JSON data set with a given json file
              and stores each entry in an array 'tunes' and acts as a iTune song store. It provides
              methods showing all the songs in our personal tunes library, adding new songs in our
              tunes collection by title selection, making a play list of a certain duration.

              One object of class MillionSongDatSubset parses a JSON data set with a given file path.
              Stores each entry into a SongEntry object.

        - SongEntry.java
            - One object of class SongEntry stores a simplified version of the genre data set
              from the Million Song Dataset
              http://labrosa.ee.columbia.edu/millionsong/blog/11-2-28-deriving-genre-dataset

        - TimeConverter.java
            - The class method that converts seconds into format: hours : minutes : seconds

src/utilities/
        - Item.java
            - One object of Item class represents one item in the inventory, with two class members

        - LazySearchTree.java
            - The LazySearchTree class enable removal of nodes that have been marked for lazy deletion.
              Rather than disconnecting a soft deleted node every single time the user decides to remove
              an element, we will provide new functionality that cleans up the tree via one or more explicit
              hard deletions whenever a call to our tree's collectGarbage() is made

              - LazySTNode.java
                - A nested class for node objects. LazySTNodes are for search trees that use
                  lazy deletion

        - PrintObject.java
            - Converts duration into a string representation

        - Traverser.java
            - Traverser extends Object. Java Object Graph traverser. It will visit
              all Java object reference fields and call the passed in Visitor instance
              with each object encountered, including the root. It will properly detect
              cycles within the graph and not hang

resources/
    - Three CSV (Comma Separated Value) file
        - inventory_invalid_removal.txt
        - inventory_log.txt
        - inventory_short.txt

    - One JSON (JavaScript Object Notation) file
        - music_genre_subset.json

    RUN.txt
        - Console output of

README.txt
- Description of submitted files