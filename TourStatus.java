import java.util.ArrayList;
/**
  * TourStatus class tracks the user's
  * current position during the tour.
  * @author Patrick Stern
  * @version 23.02.2026
 */
public class TourStatus {

    private Campus campus;
    private Location currentLocation;
    private ArrayList<Item> backpack;
    private static TourStatus theInstance;
    private Weather weather;
    private InGameClock clock;
    private Distance distance;

    /** Empty constructor. */ 
    private TourStatus() {
        backpack = new ArrayList<>();
        weather = new Weather();
        clock = new InGameClock(8, 00);
        distance = new Distance();
    }
    
    /** The single instance of TourStatus */
    public static TourStatus getInstance() {
        if (theInstance == null) {
            theInstance = new TourStatus();
        }
        return theInstance;
    }

    /** Sets the campus and initializes start Location. 
     * @param campus Campus being toured
     */
    public void setCampus(Campus campus) {
        this.campus = campus;
        currentLocation = campus.getStartingLocation();
        currentLocation.enter();
    }
    /** Sets the current Location. 
     * @param loc the current Location
     */
    public void setCurrentLocation(Location loc) {
        currentLocation = loc;
    }
    /** Returns a Campus. 
     * @return the Campus object
     */
    public Campus getCampus() {
        return campus;
    }
    /** Returns the current Location. 
     * @return the current Location
     */
    public Location getCurrentLocation() {
        return currentLocation;
    }
    /** Returns all items in backpack
      * @return items in backpack
      */
    public ArrayList<Item> getBackpack() {
        return backpack;
    }
    
    /** Returns the current weather condition
      * @return weather as a string
      */
    public Weather getWeather() {
        return weather;
    }

    /** Returns the time as a string
      * @return the current time
      */
    public InGameClock getClock() {
        return clock;
    }

    /** Returns the current distance for user
      * @return the current distance as an int
      */
    public Distance getDistance() {
        return distance;
    }

    /** Updates the tour location 
     * @param dir direciton entered by user
     * @return an error message if user goes in a void direcion
     */
    public Location updateTourLocation(String dir) {
        
        Location next = currentLocation.leaveLocation(dir);

        if (next == null) {
            System.out.println("You cannot go that way");
        } 
        currentLocation = next;
        return currentLocation;
    }
    /** Adds an item to the backpack
     * @param item the item to add
     * @return the item added
     */
    public void addToBackpack(Item item) {
        backpack.add(item);
    }

    /** Drops an item from backpack
      * @param name name of the item to drop
      * @return the item removed, null if not
      */
    public Item dropItemFromBackpack(String name) {
        for (Item i : backpack) {
            if (i.getName().equalsIgnoreCase(name)) {
                backpack.remove(i);
                return i;
            }
        }
        return null;
    }

    /** Picks up item from current location and adds it to backpack
      * @param name the name of the item to pick up
      * @return the item picked up, null if not
      */
    public Item pickupItemFromLocation(String name) {
        Item item = currentLocation.getItemNamed(name);
        
        if (item != null) {
            backpack.add(item);
            currentLocation.removeItem(item);
        }
        return item;
    }

    public boolean hasKey(){
        for (Item item : backpack) {
            if (item.getName().equalsIgnoreCase("Key")) {
                return true;
            }
        }
        return false;
    }

    /** Lisrs all items in backpack
      * @return a list of all items in backpack
      */

    public String listBackpackItems() {
        String result = "";
        for (Item i : backpack) {
            result += i.getName() + "\n";
        }
        return result;
    }
}
