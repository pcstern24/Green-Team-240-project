public class LoadCommand implements UserInputCommand {

    private String filename;

    public LoadCommand(String filename) {
        this.filename = filename;
    }

    @Override
    public String carryOut() {
        TourStatus.getInstance().loadGame(filename);
        return "Loaded from " + filename;
    }       
}
