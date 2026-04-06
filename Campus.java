import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Hashtable;
/**
  * The Campus class stores all locations
  * and loads the campus map from a file.
  * @author Patrick Stern
  * @version 23.02.2026
  */
public class Campus {

    private String campusName;
    private Hashtable<String, Location> locations;
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

            String description = "";
            while (!(line = stdin.nextLine()).equals("+++")) {
                description += line + "\n";
            }

            Location loc = getLocation(locName);
            if (loc != null) {
                loc.addItem(new Item(itemName, description.trim()));
            }
        }

        setStartingLocation(getLocation("Double Drive"));

    }
}

