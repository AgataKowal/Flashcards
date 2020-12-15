import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Set<String> dictionary = createDictionary(scanner);
        Set<String> erroneous = findErroneousWords(scanner, dictionary);
        printErroneousWords(erroneous);
    }

    private static Set<String> createDictionary(Scanner scanner) {
        int d = Integer.parseInt(scanner.nextLine().trim());
        Set<String> dictionary = new HashSet<>();
        for (int i = 0; i < d; i++) {
            dictionary.add(scanner.nextLine().toLowerCase().trim());
        }
        return dictionary;
    }

    private static Set<String> findErroneousWords(Scanner scanner, Set<String> dictionary) {
        int l = Integer.parseInt(scanner.nextLine().trim());
        Set<String> erroneous = new HashSet<>();
        for (int i = 0; i < l; i++) {
            String[] words = scanner.nextLine().toLowerCase().trim().split(" ");
            for (String word : words) {
                if (!dictionary.contains(word)) {
                    erroneous.add(word);
                }
            }
        }
        return erroneous;
    }

    private static void printErroneousWords(Set<String> erroneous) {
        for (String word : erroneous) {
            System.out.println(word);
        }
    }
}