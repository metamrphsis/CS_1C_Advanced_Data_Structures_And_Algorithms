project folder:
Saytas-cs1c-project03/


Brief description of submitted files:

src/stacks/
        - BrowserNavigation.java
            - Simulates a browser's back and forward buttons by recording links that are visited
              and then keeping a stack of "back" links and a stack of "forward" links.
              Creates an object of type BrowserNavigation that manages the history of web-links
              the user navigates to. Tests the implementation of your StackList and Navigator classes

        - Navigator.java
            - The Navigator class creates two objects of StackList class

        - StackList.java
            - The generic type StackList is a Singly-LinkedList class. Objects
              of type StackList manage items in a singly linked list where
              we can only push() and pop() items from one end of the stack
              We'll call this at the top of the stack

                    - Node class
                        - Definition of inner Node class

                    - StackIterator class
                        - The inner StackIterator parameterized class implements
                          an iterator interface that provides an iterator over the
                          outer generic StackList

resources/
    - Two CSV (Comma Separated Value) file
        - links.txt
        - popEmptyStackOfLinks.txt

    RUN.txt
    - Console output of  class

README.txt
- Description of submitted files