import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** Weather class keeps track of current weather for the tour */
public class Weather {

    private ArrayList<String> conditions;
    private String condition;
    private Random rand;
    private int lastHour;

    /** Constructor for weather class that sets the locations */ 

    public Weather() {
        this.conditions = new ArrayList<>(List.of("Sunny", "Cloudy", "Rainy", "Snowy"));
        this.rand = new Random();
        this.condition = conditions.get(0);
        this.lastHour = -1;
    }
    
    /** Returns the current condition on the tour
      * @return the current weather condition
      */

    public String getCondition() {
        return condition;
    }
    
    /** Sets the current weather for the tour
      * @param c the condition to be changed to
      */
    public void setCondition(String c) {
        this.condition = c;
    }
    
    /** Updates the current weather at the top of each hour at random
      * @param currentHour the current time on the tour
      */

    public void updateWeather(int currentHour) {

        if (currentHour != lastHour) {
            lastHour = currentHour;
            condition = conditions.get(rand.nextInt(conditions.size()));
        }
    }

    /** Outputs the message for each weather condition
      * @return the message for each of the weather conditions
      */
    public String getWeatherMessage() {

        switch (condition) {
            case "Sunny":
                return "the sun is shining brightly. It's a perfect day to travel.";

            case "Cloudy":
                return "clouds fill the sky, but the journey continues smoothly.";

            case "Rainy":
                return "rain begins to fall. The path ahead is slippery and slow.";

            case "Snowy":
                return "snow covers the ground. Travel becomes cold and difficult.";

            default:
                return "The weather is unpredictable today.";
        }
    }
}
