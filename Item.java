import java.util.HashMap;
import java.util.Map;
/** An Item class that represents an object that can be found in a location
  * or stored in the user's backpack during the tour.
  * Each item has a name and a message describing it 
  */
public class Item {
    private String name;
    private String message;
    private Map<String, String> commandMessages;
    private Map<String, String> commandActions;
    private Map<String, String> commandTargets;


    /** Default Constructor */
    public Item() {
        commandMessages = new HashMap<>();
        commandActions = new HashMap<>();
        commandTargets = new HashMap<>();
    }
    
    /** Constructs an item with with a name and description 
     * @param n the name of the item
     * @param m the description message of the item
     */
    public Item(String n, String m) {
        name = n;
        message = m;

        commandMessages = new HashMap<>();
        commandActions = new HashMap<>();
        commandTargets = new HashMap<>();
    }

    /** Returns the name of the item
      * @return name of the item
      */
    public String getName() {
        return name;
    }

    /** Returns description of the item
      * @return description message for item
      */
    public String getMessage() {
        return message;
    }
    
    /** Sets the name for an item
      * @param name the new item name
      */
    public void setName(String name) {
        this.name = name;
    }

    /** Sets the description for item
      * @param msg the new description for the item 
      */
    public void setMessage(String msg) {
        message = msg;
    }

    public void addCommand(String command, String action, String target, String msg) {
        commandMessages.put(command, msg);
        commandActions.put(command, action);
        commandTargets.put(command, target);
    }

    public String getCommandMessage(String command) {
        return commandMessages.get(command);
    }

    public String getAction(String command) {
        return commandActions.get(command);
    }

    public String getTarget(String command) {
        return commandTargets.get(command);
    }
}
