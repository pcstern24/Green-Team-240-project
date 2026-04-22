/** Implements UserInputCommand in order to move the user if they input a distance command */

public class DistanceCommand implements UserInputCommand {

    public String carryOut() {
        TourStatus status = TourStatus.getInstance();
        int distance = status.getDistance().getDistance();
        String rank;

        if (status.getDistance().getDistance() <= 5) {
            rank = "New Traveler";
        } else if (status.getDistance().getDistance() <= 10) {
            rank = "Seasoned Traveler";
        } else if (status.getDistance().getDistance() <= 15) {
            rank = "Experienced Traveler";
        } else if (status.getDistance().getDistance() <= 20) {
            rank = "Veteran Traveler";
        } else if (status.getDistance().getDistance() <= 25) {
            rank = "Elite Traveler";
        } else {
            rank = "Legendary Traveler";
        } 

        return "\nYour current distance is " + distance + " placing you at " + rank + " status!";
    }
}
