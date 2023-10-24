package traffic_signal2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    private static final String LOG_FILE_PATH = "traffic_log.txt";

    // Log road sensor data
    public static void logRoadSensor(int[] rs) {
        String timestamp = getTimestamp();
        String logMessage = String.format("%s - Road Sensor Data: %s%n", timestamp, arrayToString(rs));
        appendToLog(logMessage);
    }

    // Log light state changes
    public static void logLightStateChange(int[] lightStates) {
        String timestamp = getTimestamp();
        String logMessage = String.format("%s - Light State Change: %s%n", timestamp, arrayToString(lightStates));
        appendToLog(logMessage);
    }

    // Helper method to append log messages to the log file
    private static void appendToLog(String logMessage) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE_PATH, true))) {
            writer.write(logMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method to format the current timestamp
    private static String getTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    // Helper method to convert an array to a string
    private static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
