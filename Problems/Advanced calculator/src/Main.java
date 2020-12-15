/* Please, do not rename it */
class Problem {

    public static void main(String[] args) {
        String operator = args[0];
        int result = 0;
        switch (operator) {
            case "MAX":
                int max = Integer.MIN_VALUE;
                for (int i = 1; i < args.length; i++) {
                    int currentValue = Integer.parseInt(args[i]);
                    if (currentValue > max) {
                        max = currentValue;
                    }
                }
                result = max;
                break;
            case "MIN":
                int min = Integer.MAX_VALUE;
                for (int i = 1; i < args.length; i++) {
                    int currentValue = Integer.parseInt(args[i]);
                    if (currentValue < min) {
                        min = currentValue;
                    }
                }
                result = min;
                break;
            case "SUM":
                for (int i = 1; i < args.length; i++) {
                    int currentValue = Integer.parseInt(args[i]);
                    result += currentValue;
                }
                break;
            default:
                System.out.println("Invalid input");
                break;
        }
        System.out.println(result);
    }
}