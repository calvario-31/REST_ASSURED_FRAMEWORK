package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
    public static Logger Log = LogManager.getLogger("[LOG]");
    private static final String separator = "------------------------------------------------------------------------------------------";


    public static void startTest(String testName) {
        System.out.println();
        Log.info(separator);
        Log.info("Test: " + testName);
        Log.info(separator);
    }

    public static void endTest(String status, String testName) {
        Log.info(separator);
        Log.info(status + " " + testName);
        Log.info(separator);
        System.out.println();
    }

    public static void printRequest(String url, String method, String contentType, String token, String payload,
                                    int statusCode, long time, String responseBody) {
        String newLine = System.lineSeparator();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(newLine).append(newLine)
                .append(separator).append(newLine)
                .append("Request:").append(newLine)
                .append(separator).append(newLine)
                .append("Url:\t\t\t").append(url).append(newLine)
                .append("Method:\t\t\t").append(method).append(newLine)
                .append("Content-Type:   ").append(contentType).append(newLine)
                .append("Authorization:\t").append(token).append(newLine)
                .append("Payload: ").append(newLine).append(payload).append(newLine)
                .append(separator).append(newLine)
                .append("Response:").append(newLine)
                .append(separator).append(newLine)
                .append("Status Code:\t").append(statusCode).append(newLine)
                .append("Response Time:\t").append(time).append(" ms").append(newLine)
                .append("Response Body:").append(newLine).append(responseBody).append(newLine);
        Log.debug(stringBuilder);
    }

    public static void info(String message) {
        Log.info(message);
    }

    public static void warn(String message) {
        Log.warn(message);
    }

    public static void error(String message) {
        Log.error(message);
    }

    public static void fatal(String message) {
        Log.fatal(message);
    }

    public static void debug(String message) {
        Log.debug(message);
    }
}
