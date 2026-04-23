import java.io.PrintWriter;

public class SaveCommand implements UserInputCommand {

    private String filename;

    public SaveCommand(String filename) {
        this.filename = filename;
    }    

    @Override
    public String carryOut() {
        TourStatus status = TourStatus.getInstance();

        try (PrintWriter out = new PrintWriter(filename)) {

            Campus campus = status.getCampus();

            out.println("SAVE FILE");
            out.println("*****");

            out.println("current location: " + status.getCurrentLocation().getName());

            out.print("backpack:");
            for (Item i : status.getBackpack()) {
                out.print(i.getName() + ",");
            }
            out.println();

            out.println("Location states:");
            for (Location loc : campus.getLocations()) {
                out.println(loc.getName());
                out.println("visited: " + loc.getHaveVisited());

                out.print("items:");
                for (Item i : loc.getItems()) {
                    out.print(i.getName() + ",");
                }
                out.println();

                out.println("+++");

            }

            return "Game saved to " + filename;

        } catch (Exception e) {
            return "Failed to save game.";
        }            
    }

}
