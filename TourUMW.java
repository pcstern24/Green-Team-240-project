import java.util.Scanner;
/** 
  * TourUMW runs the interactive campus tour and
  * handles user input and screen output
  */
public class TourUMW {
    /** Main for running the program. 
     * @param args command-line arguments
     * @throws Exception if Campus file fails to load
     */
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        Campus umw = setUpCampus(stdin);
        
        TourStatus status = TourStatus.getInstance();
        status.setCampus(umw);

        while (true) {

            Location current = status.getCurrentLocation(); 
           
            System.out.println("\n" + current.describeLocation());

            System.out.print("Enter direction (or q to quit): ");
            String input = stdin.nextLine();

            if (input.equalsIgnoreCase("q")) {
                break;
            }
                
            UserInputCommand cmd = parseInput(input);
            String result = cmd.carryOut();

            if (!result.equals("")) {
                System.out.println(result);
            }
        }
    }

    /** Parses user input and returns the appropriate command object
      * @param input the user input string
      * @return a UserInputCommand object
      */

    public static UserInputCommand parseInput(String input) {
        String[] parts = input.split(" ");
        TourStatus status = TourStatus.getInstance();

        String command = parts[0].toLowerCase();
        String argument = null;

        if (parts.length > 1) {
            argument = input.substring(command.length()).trim();
        }

        switch (command) {
            case "n": case "s": case "e": case "w":
                return new MovementCommand(command);
            case "pickup": case "p":
                if (argument != null) return new PickupCommand(argument);
                return new InvalidCommand("Pickup requires an item name.");
            case "drop": case "d":
                if (argument != null) return new DropCommand(argument);
                return new InvalidCommand("Drop requires an item name.");
            case "backpack": case "b":
                return new BackpackCommand();
            case "distance": 
                return new DistanceCommand();
            case "clock": case "time":
                return new ClockCommand();
            case "weather":
                return new WeatherCommand();    
            case "save":
                if (argument != null) return new SaveCommand(argument);
                return new SaveCommand("save.txt");
            case "load": 
                if (argument != null) return new LoadCommand(argument);
                return new LoadCommand("save.txt");
            default:
                if (argument != null) {
                    return new ItemCommand(command, argument);
                }
                return new InvalidCommand(input);
        }
    }

    /** Creates Campus and loads it in from file 
     * @param stdin scanner for user input 
     * @return initialized Campus
     * @throws Exception if file cannot be opened
     */
    public static Campus setUpCampus(Scanner stdin) throws Exception {
       System.out.print("Enter campus file name: ");
       String fileName = stdin.nextLine();

       Campus umw = new Campus(fileName);

       try {
           umw.loadFile();
       }
       catch (Exception e) {
           e.printStackTrace();
       }

       return umw;
    }
}
