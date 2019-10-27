package stacks;

/**
 * The Navigator class creates two objects of StackList class
 *
 * @author Foothill College, Bita M, Selahittin Saytaş
 */
public class Navigator
{
    private String currentLink;
    private StackList<String> backLinks;
    private StackList<String> forwardLinks;

    /**
     * The constructor initializes the class attributes
     */
    public Navigator()
    {
        currentLink = "";
        backLinks = new StackList<String>("Back ");
        forwardLinks = new StackList<String>("Forward ");
    }

    /**
     * The constructor initializes the class attributes
     * @param currentLink   A String type of currenLink
     * @param backLinks     A StackList<String> tyep of
     *                      backLinks
     * @param forwardLinks  A StackList<String> tyep of
     *                      forwardLinks
     */
    public Navigator(String currentLink, StackList<String> backLinks, StackList<String> forwardLinks)
    {
        this.currentLink = currentLink;
        this.backLinks = backLinks;
        this.forwardLinks = forwardLinks;
    }

    /**
     * The mutator method takes an object of type String
     * for the the current requested link and updates the
     * backLinks and forwardLinks stacks
     * @param linkName  An object of type String
     */
    public void setCurrentLink(String linkName)
    {
        if(currentLink != "")
        {
            backLinks.push(currentLink);
        }

        forwardLinks.clear();
        currentLink = linkName;
    }

    /**
     * The accessor method updates the current link
     * to the link at the top of the backLinks stack
     */
    public void goBack()
    {
        if(backLinks.isEmpty())
        {
            return;
        }
        else
        {
            String tempLink = backLinks.pop();
            forwardLinks.push(currentLink);
            currentLink = tempLink;
        }
    }

    /**
     * The accessor method updates the current link
     * to the link at the top of the forwardLinks stack
     */
    public void goForward()
    {
        if(forwardLinks.isEmpty())
        {
            return;
        }
        else
        {
            String tempLink = forwardLinks.pop();
            backLinks.push(currentLink);
            currentLink = tempLink;
        }
    }

    /**
     * The accessor method returns the current link
     * @return  An object of type String
     */
    public String getCurrentLink()
    {
        return currentLink;
    }

    /**
     * The accessor method returns backLinks of
     * type StackList<String>
     * @return  A StackList<String> type of backLinks
     *          stack
     */
    public StackList<String> getBackLinks()
    {
        return backLinks;
    }

    /**
     * The accessor method returns forward of
     * type StackList<String>
     * @return  A StackList<String> type of forwardLinks
     *          stack
     */
    public StackList<String> getForwardLinks()
    {
        return forwardLinks;
    }
}
