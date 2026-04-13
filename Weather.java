import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Weather {

    private ArrayList<String> conditions;
    private String condition;
    private Random rand;
    private int lastHour;

    public Weather() {
        this.conditions = new ArrayList<>(List.of("Sunny", "Cloudy", "Rainy", "Snowy"));
        this.rand = new Random();
        this.condition = conditions.get(0);
        this.lastHour = -1;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String c) {
        this.condition = c;
    }
    
    public void updateWeather(int currentHour) {

        if (currentHour != lastHour) {
            lastHour = currentHour;
            condition = conditions.get(rand.nextInt(conditions.size()));
        }
    }
}
