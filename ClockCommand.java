public class ClockCommand implements UserInputCommand {

    public String carryOut() {
        TourStatus status = TourStatus.getInstance();
        return "\nCurrent Time: " + status.getClock().getTime();
    }
}
