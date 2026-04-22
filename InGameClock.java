/** InGameClock keeos track of the current time */
public class InGameClock {
    
    private String time;
    
    /** Constructor for in game clock that sets the starting time to 8:00
      *
      */
    public InGameClock(int hour, int minutes) {
        this.time = String.format("%02d:%02d", hour, minutes);
    }
    /** Returns the current time
      * @return the time in game
      */
    public String getTime() {
        return time.toString();
    }
    
    /** Sets the current time for the tour
      *
      */
    public void setTime(String t) {
        this.time = t;    
    }

    /** Increments the time whena user does an action
      *
      */
    public void addTime(int minsAdded) {
        String[] parts = time.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);

        minute += minsAdded;
        hour += minute / 60;
        minute = minute % 60;
        hour = hour % 24;

        this.time = String.format("%02d:%02d", hour, minute);
    }

    /** Returns the current hour of the tour
      * @return an integer at the first part of the time 
      */
    public int getHour() {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]);
    }

    /** Returns the current minute of the tour
      * @return an integer with the second part of the time 
      */
    public int getMinute() {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[1]);
    }
} 
