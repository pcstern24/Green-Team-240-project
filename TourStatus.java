import java.util.ArrayList;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

    public void saveGame(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {

            writer.println("SAVE FILE");
            writer.println("*****");

            writer.println("Location states:");

            for (Location loc : campus.getLocations()) {
                writer.println(loc.getName());

                if (loc.getHaveVisited()) {
                    writer.println("visited:true");
                }

                if (!loc.getItems().isEmpty()) {
                    writer.print("items:");
                    for (int i = 0; i < loc.getItems().size(); i++) {
                        writer.print(loc.getItems().get(i).getName());
                        if (i < loc.getItems().size() - 1) {
                            writer.print(",");
                        }
                    }
                    writer.println();
                }

                writer.println("+++");
            }

            writer.println("*****");

            writer.println("User:");
            writer.println("current location:" + currentLocation.getName());

            writer.print("backpack:");
            for (int i = 0; i < backpack.size(); i++) {
                writer.print(backpack.get(i).getName());
                if (i < backpack.size() - 1) {
                    writer.print(",");
                }
            }
            writer.println();

            writer.println("*****");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadGame(String filename) {

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {

            String line;
            Location currentLoc = null;

            backpack.clear();
            for (Location loc : campus.getLocations()) {
                loc.getItems().clear();
                loc.setHaveVisited(false);
            }

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.equals("SAVE FILE") ||
                    line.equals("*****") || 
                    line.equals("Location states:") || 
                    line.equals("User:")) {
                    continue; 
                }

                if (!line.contains(":") && !line.equals("+++")) {
                   currentLoc = campus.getLocation(line);
                   continue;
                }

                if (line.equals("+++")) {
                   currentLoc = null;
                   continue;
                }

                if (currentLoc != null) {

                    if (line.startsWith("visited:")) {
                        currentLoc.setHaveVisited(true);    
                    }
                     
                    else if (line.startsWith("items:")) {
                        String[] items = line.substring(6).split(",");
                        for (String item : items) {
                            Item realItem = campus.getItemByName(item.trim().toLowerCase());    
                            if (realItem != null) {
                                currentLoc.getItems().add(realItem);
                            }
                        }
                    }
                }
                                
                if (line.startsWith("current location:")) {
                    String name = line.substring(17).trim();
                    
                    Location loc = campus.getLocation(name); 
                        
                    if (loc != null) {
                        setCurrentLocation(loc);
                    }
                }    

                if (line.startsWith("backpack:")) {
                    String[] items = line.substring(9).split(",");
                    for (String item : items) {
                        if (!item.isEmpty()) {
                            Item realItem = campus.getItemByName(item.trim().toLowerCase());
                            if (realItem != null) {
                                backpack.add(realItem);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            this.currentLocation = loc;
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
         * @param dir direction entered by user
         * @return an error message if user goes in a void direction
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

        /** Determines if the user has a key in their backpack
         * @return if the user has a key it returns true if not it returns false
         */

        public boolean hasKey(){
            for (Item item : backpack) {
                if (item.getName().equalsIgnoreCase("Key")) {
                    return true;
                }
            }
            return false;
        }

        /** Lists all items in backpack
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
