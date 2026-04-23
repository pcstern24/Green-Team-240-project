/** The Load Command handles loading in a .txt save file */
public class LoadCommand implements UserInputCommand {

    private String filename;

    /** Constructor for LoadFile
      * @param filename the name of the file to be loaded in
      */
    public LoadCommand(String filename) {
        this.filename = filename;
    }

    @Override

  /** Executes the load command while user is already in the tour
    * @return a version of the game that was previously saved
    */
    public String carryOut() {
        TourStatus.getInstance().loadGame(filename);
        return "Loaded from " + filename;
    }       
}
