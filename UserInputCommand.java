/** The UserInputCommand interface represents a command entered by the user.
  * Each command much implement the carryOut method, which performs the action
  * and returns a result message.
  */
public interface UserInputCommand {
    /** Executes the command 
      * @return a message describing the result of the command
      */
    String carryOut();
}
