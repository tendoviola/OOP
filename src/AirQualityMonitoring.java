  import java.util.Arrays;
import java.util.Random;

public class AirQualityMonitoring {
    public static void main(String[] args) {
        int[] aqiReadings = new int[30];
        Random rand = new Random();

        // i) Generate 30 random AQI readings between 1 and 300
        for (int i = 0; i < aqiReadings.length; i++) {
            aqiReadings[i] = rand.nextInt(300) + 1;
        }

        // Display AQI readings
        System.out.println("Generated AQI readings: " + Arrays.toString(aqiReadings));

        // ii) Compute and display the median AQI value
        double median = calculateMedian(aqiReadings);
        System.out.printf("Median AQI value: %.2f%n", median);

        // iii) Identify and count hazardous days (AQI > 200)
        int hazardousCount = 0;
        for (int aqi : aqiReadings) {
            if (aqi > 200) {
                hazardousCount++;
            }
        }
        System.out.println("Number of hazardous days (AQI > 200): " + hazardousCount);
    }

    private static double calculateMedian(int[] data) {
        int[] copy = Arrays.copyOf(data, data.length);
        Arrays.sort(copy);

        int middle = copy.length / 2;
        if (copy.length % 2 == 0) {
            return (copy[middle - 1] + copy[middle]) / 2.0;
        } else {
            return copy[middle];
        }
    }
}