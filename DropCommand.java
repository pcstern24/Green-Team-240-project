/** The DropCommand class handles removing an item from the backpack    
  * and placing it in the current location.
  */
import java.util.ArrayList;
public class DropCommand implements UserInputCommand {
    private String itemName;

    /** Constructs a DropCommand
      * @param itemName the name of the item to drop
      * @param status the current tourStaus
      */
    public DropCommand(String itemName) {
        this.itemName = itemName;
    }

    /** Executes the drop command
      * @return a message indicating success or failure
      */
    public String carryOut() {
        TourStatus status = TourStatus.getInstance();
        Location current = status.getCurrentLocation();
        ArrayList<Item> backpack = status.getBackpack();

        Item foundItem = null;
        for (Item item : backpack) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                foundItem = item;
                break;
            }
        }

        if (foundItem == null) {
            return "Cannot drop: " + itemName;
        }

        backpack.remove(foundItem);
        current.addItem(foundItem);

        return "You dropped: " + foundItem.getName();
    }
}
