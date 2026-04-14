import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;
import java.util.Enumeration;
/**
  * The Campus class stores all locations
  * and loads the campus map from a file.
  * @author Patrick Stern
  * @version 23.02.2026
  */
public class Campus {

    private String campusName;
    private Hashtable<String, Location> locations;
    private Hashtable<String, Item> allItems;
    private Location startingLocation;
    private String filename;
    /** Constructs an empty Campus. */
    public Campus() {
        locations = new Hashtable<>();
    }
    /** Constructs a Campus with file.
     * @param name the name of the  file you'd like to make into a Campus
     */
    public Campus(String name) {
        this.filename = name;
        locations = new Hashtable<>();
        allItems = new Hashtable<>();
    }
    /** Adds a location to the Campus. 
     * @param location Location to add
     */
    public void addLocation(Location location) {
        locations.put(location.getName(), location);
    }
    /** Retrieves a Location by name. 
     * @param name Location name
     * @return matching Location
     */
    public Location getLocation(String name) {
        return locations.get(name);
    }
    /** Sets the name of Campus. 
     * @param n name of Campus
     */
    public void setCampusName(String n) {
       campusName = n;
    }
    /** Returns the name of Campus. 
     * @return the name of the desired Campus
     */
    public String getCampusName() {
        return campusName;
    }
    /** Sets the starting Location. 
     * @param start the Location you wish to start at on Campus
     */
    public void setStartingLocation(Location start) {
        startingLocation = start;
    }
    /** Returns the starting Location 
     * @return the starting Locaiton on Campus
     */
    public Location getStartingLocation() {
        return startingLocation;
    }
    
    public Location getRandomLocation() {
    
        if (locations == null || locations.isEmpty()) {
            return null;
        }

        Random rand = new Random();

        Enumeration<Location> values = locations.elements();

        int index = rand.nextInt(locations.size());

        for (int i = 0; i < index; i++) {
            values.nextElement();
        }

        return values.nextElement();
    }
    
    public Item getItemByName(String name) {
        return allItems.get(name.toLowerCase());
    }


    /** Sets the filename 
     * @param f the name of the file being passed
     */
    public void setFilename(String f) {
        filename = f;
    }
    /** Returns the name of the file 
     * @return the name of the file
     */
    public String getFilename() {
        return filename;
    }
    /** Loads the file from user input 
     * @throws FileNotFoundException if file cannot be read
     */
    public void loadFile() throws FileNotFoundException {

        Scanner stdin = new Scanner(new File(filename));

        campusName = stdin.nextLine();
        stdin.nextLine();
        stdin.nextLine();

        while (stdin.hasNextLine()) {

            String line = stdin.nextLine();
            if (line.equals("*****")) {
                break;
            }
            
            String locName = line;
            String description = "";

            while (!(line = stdin.nextLine()).equals("+++")) {
                description += line + "\n";
            }
            
            addLocation(new Location(locName, description.trim()));
        } 
       
        stdin.nextLine();        
        
        while (stdin.hasNextLine()) {
            
            String line = stdin.nextLine().trim();
            if (line.equals("*****")) {
                break;
            }       

            String fromName = line;
            String dir = stdin.nextLine().trim();
            String toName = stdin.nextLine();
            stdin.nextLine();

            Location from = getLocation(fromName);
            Location to = getLocation(toName);

            if (from != null && to != null) {
                Door d = (new Door(dir, from, to));
                from.addDoor(d);
            }
        } 

        stdin.nextLine();

        while (stdin.hasNextLine()) {
            String line = stdin.nextLine();
            if (line.equals("*****")) {
                break;
            }

            String itemName = line;
            String locName = stdin.nextLine();

            Item item = new Item(itemName, "");

            allItems.put(itemName.toLowerCase(), item);
            
            while (!(line = stdin.nextLine()).equals("+++")) {
                if (line.contains(":")) {

                    String[] parts = line.split(":");
                    String left = parts[0];
                    String message = parts[1];

                    String command;
                    String action = null;
                    String target = null;

                    if (left.contains("[")) {
                        command = left.substring(0, left.indexOf("["));

                        String inside = left.substring(left.indexOf("[") + 1, left.indexOf("]"));

                        if (inside.contains("(")) {
                            action = inside.substring(0, inside.indexOf("("));
                            target = inside.substring(inside.indexOf("(") + 1, inside.indexOf(")"));
                        } else {
                            action = inside;
                        }
                    } else {
                        command = left;
                    }

                    item.addCommand(command, action, target, message);
                } else {
                    item.setMessage(item.getMessage() + line + "\n");
                }
            }

            item.setMessage(item.getMessage().trim());

            Location loc = getLocation(locName);
            if (loc != null && !locName.equals("none")) {
                loc.addItem(item);
            }
        }

        setStartingLocation(getLocation("Double Drive"));

    }
}

