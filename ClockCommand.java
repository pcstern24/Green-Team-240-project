/** The ClockCommand displays the current time to the user */
public class ClockCommand implements UserInputCommand {



    /** Exectus a clock command
      * @return The current in-game time
      */
    public String carryOut() {
        TourStatus status = TourStatus.getInstance();
        return "\nCurrent Time: " + status.getClock().getTime();
    }
}
