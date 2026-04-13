public class InGameClock {
    
    private String time;

    public InGameClock(int hour, int minutes) {
        this.time = String.format("%02d:%02d", hour, minutes);
    }

    public String getTime() {
        return time.toString();
    }

    public void setTime(String t) {
        this.time = t;    
    }

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

    public int getHour() {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]);
    }

    public int getMinute() {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[1]);
    }
} 
