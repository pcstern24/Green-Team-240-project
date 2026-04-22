/** ItemCommand class pairs a command with an item */
public class ItemCommand implements UserInputCommand {

private String command;
    private String itemName;

    /** Constructor for ItemCommand
      * @param command the command for a given item
      * @param itemName the name of the item
      */

    public ItemCommand(String command, String itemName) {
        this.command = command;
        this.itemName = itemName;
    }

    /** This method executes a given item command
      * @return a message giving the status of the item
      * @return the result of the item command
      */
    @Override
    public String carryOut() {

        TourStatus status = TourStatus.getInstance();
        Location current = status.getCurrentLocation();

        Item item = current.getItemNamed(itemName);

        if (item == null) {
            return "That item is not here.";
        }

        String message = item.getCommandMessage(command);

        if (message == null) {
            return "You can't do that with this item.";
        }

        String action = item.getAction(command);
        String target = item.getTarget(command);

        String result = message;

        if (action != null) {

            switch (action) {

                case "Transform":
                    current.removeItem(item);

                    if (target != null) {
                        Item template = status.getCampus().getItemByName(target);

                        if (template != null) {

                            Item newItem = new Item(template.getName(), template.getMessage());

                            newItem.copyCommandsFrom(template);

                            current.addItem(newItem);
                        }
                    }
                    break;

                case "Disappear":
                    current.removeItem(item);
                    status.getBackpack().remove(item);
                    break;

                case "Teleport":
                    Location random = status.getCampus().getRandomLocation();
                    
                    if (random != null) {

                        status.setCurrentLocation(random);

                        result += "\nYou have been teleported to " + random.getName();
                    }
                    break;
            }
        }

        return result;
    }
}
