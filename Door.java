/**
  * The Door class is a connection between two locaitons
  * It has a leaving and entering direction 
  * @author Patrick Stern
  * @version 23.02.2026
  */

public class Door {

    private String direction;
    private Location leavingLocation;
    private Location enteringLocation;
    /** Constructs an empty door object. */
    public Door() {}
    /** Constructs a Door connecting two locations. 
     * @param dir direciton of travel (n,s,e,w)
     * @param leave the location being exited
     * @param enter the destination location   
     */
    public Door(String dir, Location leave, Location enter) {
        this.direction = dir;
        this.leavingLocation = leave;
        this.enteringLocation = enter;
    }
    /** Returns description of door. 
     * @return formatted direction description
     */
    public String describe() {
        return "Go " + direction + " to " + enteringLocation.getName(); 
    }
    /** Returns starting loaction. 
     * @return leaving Location
     */
    public Location getLeaving() {
        return leavingLocation;
    }
    /** Returns destination location. 
     * @return entering Location
     */
    public Location getEntering() {
        return enteringLocation;
    }
    /** Returns direction of the door. 
     * @return direciton string
     */
    public String getDirection() {
        return direction;
    }
    /** Sets levaing location. 
     * @param leave new leaving Location
     */
    public void setLeaving(Location leave) {
        this.leavingLocation = leave;
    }
    /** Sets entering loacation. 
     * @param enter new entering Location
     */
    public void setEntering(Location enter) {
        this.enteringLocation = enter;
    }
    /** Sets direction. 
     * @param dir direction string
     */
    public void setDirection(String dir) {
        this.direction = dir;
    }
}
