/** Weather command clas allows user to see the current weather on the tour */
public class WeatherCommand implements UserInputCommand {

    /** Executes the command to show the weather
      * @return returns the weather condition along with the associated message.
      */

    public String carryOut() {
        TourStatus status = TourStatus.getInstance();
        
        return "\nCurrent weather: " + status.getWeather().getCondition() + "; " + status.getWeather() .getWeatherMessage();  
    }
}
