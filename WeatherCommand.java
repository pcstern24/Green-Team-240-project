public class WeatherCommand implements UserInputCommand {

    public String carryOut() {
        TourStatus status = TourStatus.getInstance();
        return "\nCurrent weather: " + status.getWeather().getCondition(); 
    }
}
