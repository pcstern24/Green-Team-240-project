import java.util.ArrayList;
/**
  * The Location class represents a place on campus
  * It has a name, description, door, and tracks
  * if it has been previously visited
  * @author Patrick Stern
  * @version 23.02.2026
  */
public class Location {

    private String name;
    private String description;
    private Boolean haveVisited;
    private ArrayList<Door> doors;
    private ArrayList<Item> items;
    /** Constructs a new location. */
    public Location() {
        this.name = name;
        this.description = ""; 
        this.haveVisited = false;
        doors = new ArrayList<>();
    
    }
    /** Constructs a new location. 
     * @param name name of the Location
     * @param description text description of the Location
     */
    public Location(String name, String desc) {
        this.name = name;
        this.description = desc;
        this.haveVisited = false;
        this.doors = new ArrayList<>();
        this.items = new ArrayList<>();
    }
    /** Returns location name. 
     * @return Location name
     */
    public String getName() {
        return name;
    }
    /** Returns a description. 
     * @return description text
     */
    public String getDescription() {
        return description;
    }
    /** Returns if the Room has been visited.
     * @return true if visited
     */
    public boolean getHaveVisited() {
        return haveVisited;    
    }
    /** Returns a list of doors for a location
      * @return ArrayList of doors
      */
    public ArrayList<Door> getDoors() {
        return doors;
    }
    /** Returns a list of items for a location
      * @return ArrayList of items
      */
    public ArrayList<Item> getItems() {
        return items;
    }
    /** Sets the name of a Location. 
     * @param n name of Location
     */
    public void setName(String n) {
        this.name = n;
    }
    /** Sets description of the Location.
     * @param d description of Location
     */
    public void setDescription(String d) {
        this.description = d;
    }
    /** Sets if a Location has been visited. 
     * @param v truth statement of if a Room has been visited
     */
    public void setHaveVisited(boolean v) {
        this.haveVisited = v;
    }
    /** Returns a formatted list of Doors.
     * @return string describing all exits 
     */
    public String describeDoors() {
        String result = "";
        for (Door d : doors) {
            result += d.describe() + "\n";
        }
        return result;
    }

    /** Returns a full description of the location including name,
      * description, available doors, and items present    
      * @return a formatted description of the location
      */
    public String describeLocation() {
        String result = "You are at: " + name + "\n";

        if (!haveVisited) {
            result += description + "\n";
            haveVisited = true;
        }

        result += "Doors:\n" + describeDoors();
        result += "Items:\n" + getItemsInLocation();
    
        return result;
    }

    /** Attempts to leave in a direction. 
     * @param dir direction entered by user
     * @return destination if valid, otherwise null 
     */ 
    public Location leaveLocation(String dir) {
        for (Door d : doors) {
            if (d.getDirection().equalsIgnoreCase(dir)) {
                return d.getEntering();
            }
        }
        return null;
    }
    /** Attempts to enter a room. */
    public void enter() { 

        if (!haveVisited) {
            if (isOutside) {
                System.out.println(description + "Additionally this is an Outdoor location! ");
            } else {
                System.out.println(description);
            }
            haveVisited = true;
        } else {
            System.out.println(name);
        }
    }

    /** Adds a door to a location
     * @param door door to add
     */
    public void addDoor(Door door) {
        doors.add(door);
    }
    
    /** Adds an item to backpack
      * @param item item to add
      */
    public void addItem(Item item) {
        this.items.add(item);
    }

    /** Removes an item from backpack
      * @param item item to be removed
      */
    public void removeItem(Item item) {
        items.removeIf(i -> i.getName().equalsIgnoreCase(item.getName()));        
    }

    /** Returns item with the given name
      * @param name name of item 
      * @return the matching item, or null if not found
      */
    public Item getItemNamed(String name) {
        for (Item i: items) {
            if (i.getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return null;
    }
    
    /** Returns a list of item names in this location
      * @return a seperated list of all the items
      */
    public String getItemsInLocation() {
        String result = "";
        for (Item i : items) {
            result += i.getName() + "\n";
        } 
        return result;
    }
}
