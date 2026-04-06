/** The BackpackCommand displays the users contents of the user's backpack. */
public class BackpackCommand implements UserInputCommand {
    
    /** Constructs a BackpackCommand */
    public BackpackCommand() {
    
    }
    
    /** Executes a backpack command
      * @return a list of items in the backpack
      */
    public String carryOut() {
        return "Backpack:\n" + TourStatus.getInstance().listBackpackItems();
    }
}
