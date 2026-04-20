public class OutsideLocation extends Location {
    private boolean hasBench;

    public OutsideLocation(String name, String desc, boolean hasBench) {
        super(name, desc);
        this.hasBench = hasBench;
    }
    public boolean getBench(){
        return hasBench;
    }
}
