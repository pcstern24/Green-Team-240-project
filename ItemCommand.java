public class ItemCommand implements UserInputCommand {

private String command;
    private String itemName;

    public ItemCommand(String command, String itemName) {
        this.command = command;
        this.itemName = itemName;
    }

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
                        current.addItem(new Item(target, "A newly transformed item."));
                    }
                    break;

                case "Disappear":
                    current.removeItem(item);
                    status.getBackpack().remove(item);
                    break;

                case "Teleport":
                    Location random = status.getCampus().getRandomLocation();
                    status.setCurrentLocation(random);

                    result += "\nYou have been teleported to " + random.getName();
                    break;
            }
        }

        return result;
    }
}
