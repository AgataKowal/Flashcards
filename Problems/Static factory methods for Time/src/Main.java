import java.util.Scanner;

class Time {

    int hour;
    int minute;
    int second;

    private Time(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public static Time noon() {
        return Time.of(12, 0, 0);
    }

    public static Time midnight() {
        return Time.of(0, 0, 0);
    }

    public static Time ofSeconds(long seconds) {
        int h = (int) (seconds % 86_400) / 3600;
        int m = (int) ((seconds % 86_400) % 3600) / 60;
        int s = (int) (((seconds % 86_400) % 3600) % 60);
        return Time.of(h, m, s);
    }

    public static Time of(int hour, int minute, int second) {
        if (isInHourRange(hour) && isInMinuteOrSecondRange(minute) && isInMinuteOrSecondRange(second)) {
            return new Time(hour, minute, second);
        } else {
            return null;
        }
    }

    private static boolean isInHourRange(int value) {
        return value >= 0 && value < 24;
    }

    private static boolean isInMinuteOrSecondRange(int value) {
        return value >= 0 && value < 60;
    }
}

/* Do not change code below */
public class Main {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final String type = scanner.next();
        Time time = null;

        switch (type) {
            case "noon":
                time = Time.noon();
                break;
            case "midnight":
                time = Time.midnight();
                break;
            case "hms":
                int h = scanner.nextInt();
                int m = scanner.nextInt();
                int s = scanner.nextInt();
                time = Time.of(h, m, s);
                break;
            case "seconds":
                time = Time.ofSeconds(scanner.nextInt());
                break;
            default:
                time = null;
                break;
        }

        if (time == null) {
            System.out.println(time);
        } else {
            System.out.println(String.format("%s %s %s", time.hour, time.minute, time.second));
        }
    }
}