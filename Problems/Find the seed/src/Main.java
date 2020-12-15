import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        Map<Integer, Integer> maximums = new HashMap<>();

        for (int i = a; i < b + 1; i++) {
            int max = Integer.MIN_VALUE;
            Random r = new Random(i);
            int current;
            for (int j = 0; j < n; j++) {
                current = r.nextInt(k);
                if (current < k && current > max) {
                    max = current;
                }
            }
            maximums.put(i, max);
        }

        int min = Integer.MAX_VALUE;
        int seed = 0;
        for (Integer key : maximums.keySet()) {
            int currentValue = maximums.get(key);
            if (currentValue < min) {
                min = currentValue;
                seed = key;
            }
        }
        System.out.println(seed);
        System.out.println(min);
    }
}