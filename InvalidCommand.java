/** The InvalidCommand class represents an unrecognized user command. 
  * It returns an error message indicating the command is invalid.
  */
public class InvalidCommand implements UserInputCommand {
    private String invalidCommand;

    /** Constructs an InvalidCommand
      * @param invalidCommand the invalid input
      */
    public InvalidCommand(String invalidCommand) {
        this.invalidCommand = invalidCommand;
    } 

    /** Executes the invalid command 
      * @return an error message 
      */
    public String carryOut() {
        return "Invalid command: " + invalidCommand;
    }

}
