public class DistanceCommand implements UserInputCommand {

    public String carryOut() {
        TourStatus status = TourStatus.getInstance();
        return "\nYour current distance is: " + status.getDistance(); 
    }
}
