package flashcards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyLogger {

    private static final List<String> logs = new ArrayList<>();

    private MyLogger() {
    }

    public static void addToLog(String... text) {
        logs.addAll(Arrays.asList(text));
    }

    public static List<String> getLogs() {
        return logs;
    }
}
