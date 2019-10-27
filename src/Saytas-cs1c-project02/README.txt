project folder:
Saytas-cs1c-project02/


Brief description of submitted files:

src/cs1c/
        - MillionSongDataSubset.java
            - One object of class MillionSongDataSubset parses a JSON data set and stores each entry in an array

        - SongEntry.java
            - One object of class SongEntry stores a simplified version of the genre data set from
              the Million Song Dataset
              http://labrosa.ee.columbia.edu/millionsong/blog/11-2-28-deriving-genre-dataset

        - TimeConverter.java
            - Converts duration into a string representation

src/subsetsum/
        - FoothillTunesStore.java
            - An object of type FoothillTunesStore creates an object of type MillionSongDataSubset,
              which in turn parses a JSON data set with a given file path. The parsed data set
              is stored in an array of SongEntry objects
              Next, it reads prompts the user for a play list duration
              Uses an object of type SubsetSum to make a play list of SongEntry objects
              that best match the given duration

        - GroceriesFileReader.java
            - GroceriesFileReader class reads input from a CSV file one line at a time and
              adds the grocery item prices into the double type of ArrayList called itemPrices

        - ShoppingBag.java
            - An object of type ShoppingBag class creates an object of type subset sum to find a best
              possible grocery shopping list within the given budget.

        - Sublist.java
            - The class Sublist stores a reference to the master list from
              which we are forming a sublist and also stores the indices
              and the sum of the objects. The class uses an ArrayList<> of
              integers which are indices of each elements in the master set
              to represent the actual sublist that is derived from the master
              set

        - SubsetSum.java
            - SubsetSum class keeps track of the price of each item reading the CSV file
              with GroceriesFileReader class and also keeps track of the duration of
              the songs reading the data set in JSON format with MillionSongDataSubset class

            - Includes the extra credit portion findSubsetOfSongs() static method

resources/
    - Three CSV (Comma Separated Value) file
        - groceries.txt
        - myGroceries.txt
        - myGroceries.01.txt
    - One JSON (JavaScript Object Notation) file
        - music_genre_subset.json

    RUN.txt
    - Console output of ShoppingBag class

    RUN2.txt
        - Console output of FoothillTunesStore class

README.txt
- Description of submitted files