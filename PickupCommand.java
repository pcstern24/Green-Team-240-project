/** The PickupCommand handles picking up an item from 
  * the current location and adding it to the backpack.
  */
public class PickupCommand implements UserInputCommand {
    private String itemName;

    /** Constructs a PickupCommand
      * @param itemName the name of the item
      */
    public PickupCommand(String itemName) {
        this.itemName = itemName;
    }

    /** Executes the pickup command 
      * @return a message indicating success or failure
      * note TourStatus status: holds current tour status
      */
    public String carryOut() {
        TourStatus status = TourStatus.getInstance();
        Location current = status.getCurrentLocation();
        Item foundItem = null;
        
        for (Item item : current.getItems()) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                foundItem = item;
                break;
            }
        } 

        if (foundItem == null) {
          return "Item not found";
        }

        current.getItems().remove(foundItem);
        status.addToBackpack(foundItem);
        return "Picked up: " + foundItem.getName() + "\n" + foundItem.getMessage();  
    }
}
