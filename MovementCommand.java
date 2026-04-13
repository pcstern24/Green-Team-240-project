/** The movement command handles movement between locations
  * based on direction provided by a user
  */
public class MovementCommand implements UserInputCommand {
    private String dir;

    /** Constructs a Movement Command
      * @param dir direction to move
      */
    public MovementCommand(String dir) {
        this.dir = dir;
    }

    /** Executes the movement command and updates the user's location.
      * @return a description of the new location
      */
    public String carryOut() {
        TourStatus status = TourStatus.getInstance();
        Location current = status.getCurrentLocation();
            
        Door chosenDoor = null;
        for (Door door : current.getDoors()) {
            if (door.getDirection().equalsIgnoreCase(dir)) {
            chosenDoor = door;
            break;
            }
        }    
            
        if (chosenDoor == null) {
            return "You cannot go that way";
        }

        status.setCurrentLocation(chosenDoor.getEntering());
        status.getDistance().addDistance(); 
        status.getClock().addTime(15);   
        status.getWeather().updateWeather(status.getClock().getHour());
        return ""; 
    }
}
