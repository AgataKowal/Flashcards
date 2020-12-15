import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> input = Arrays.stream(scanner.nextLine().trim().split("\\s"))
                .map(Integer::parseInt).collect(Collectors.toList());
        int number = scanner.nextInt();
        int distance = Integer.MAX_VALUE;
        List<Integer> output = new ArrayList<>();
        for (Integer n : input) {
            if (Math.abs(number - n) < distance) {
                output.clear();
                output.add(n);
                distance = Math.abs(number - n);
            } else if (Math.abs(number - n) == distance) {
                output.add(n);
            }
        }
        Collections.sort(output);
        for (Integer n : output) {
            System.out.print(n + " ");
        }
    }
}