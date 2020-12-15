package flashcards;

import flashcards.command.*;

import java.io.InputStream;
import java.util.Scanner;

public class Game {

    private static final String CHOOSE_ACTION = "Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):";
    private static final String THE_CARD = "The card:";
    private static final String FILE_NAME = "File name:";
    private static final String TIMES_TO_ASK = "How many times to ask?";

    private final InputStream inputStream;

    public Game(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void play(String[] args) {
        Flashcards flashcards = new Flashcards();
        Controller controller = new Controller();
        Scanner scanner = new Scanner(inputStream);
        if (args.length != 0 && shouldDoTheAction(args, "-import")) {
            String filePath = getFilePath(args, "-import");
            controller.setCommand(new LoadCardsCommand(flashcards, filePath));
            controller.executeCommand();
        }
        while (true) {
            System.out.println(CHOOSE_ACTION);
            String input = scanner.nextLine().trim();
            MyLogger.addToLog(CHOOSE_ACTION, input);
            switch (input) {
                case "add":
                    System.out.println(THE_CARD);
                    String cardToAdd = scanner.nextLine().trim();
                    MyLogger.addToLog(THE_CARD, cardToAdd);
                    controller.setCommand(new AddCardCommand(flashcards, cardToAdd, scanner));
                    break;
                case "remove":
                    System.out.println(THE_CARD);
                    String cardToRemove = scanner.nextLine().trim();
                    MyLogger.addToLog(THE_CARD, cardToRemove);
                    controller.setCommand(new RemoveCardCommand(flashcards, cardToRemove));
                    break;
                case "import":
                    System.out.println(FILE_NAME);
                    String inputFilePath = scanner.nextLine().trim();
                    MyLogger.addToLog(FILE_NAME, inputFilePath);
                    controller.setCommand(new LoadCardsCommand(flashcards, inputFilePath));
                    break;
                case "export":
                    System.out.println(FILE_NAME);
                    String outputFilePath = scanner.nextLine().trim();
                    MyLogger.addToLog(FILE_NAME, outputFilePath);
                    controller.setCommand(new SaveCardsCommand(flashcards, outputFilePath));
                    break;
                case "ask":
                    System.out.println(TIMES_TO_ASK);
                    int timesToPlay = Integer.parseInt(scanner.nextLine().trim());
                    MyLogger.addToLog(TIMES_TO_ASK, String.valueOf(timesToPlay));
                    controller.setCommand(new PlayCardsCommand(flashcards, timesToPlay, scanner));
                    break;
                case "hardest card":
                    controller.setCommand(new HardestCardCommand(flashcards));
                    break;
                case "log":
                    System.out.println(FILE_NAME);
                    String fileName = scanner.nextLine().trim();
                    MyLogger.addToLog(FILE_NAME, fileName);
                    controller.setCommand(new LogCommand(flashcards, fileName));
                    break;
                case "reset stats":
                    controller.setCommand(new ResetStatsCommand(flashcards));
                    break;
                case "exit":
                    controller.setCommand(new ExitGameCommand(flashcards));
                    break;
                default:
                    controller.setCommand(new InvalidInputCommand(flashcards));
                    break;
            }
            controller.executeCommand();
            if ("exit".equals(input)) {
                if (args.length != 0 && shouldDoTheAction(args, "-export")) {
                    String filePath = getFilePath(args, "-export");
                    controller.setCommand(new SaveCardsCommand(flashcards, filePath));
                    controller.executeCommand();
                    MyLogger.addToLog(FILE_NAME, filePath);
                }
                break;
            }
        }
    }

    private String getFilePath(String[] args, String type) {
        String filePath = "";
        for (int i = 0; i < args.length; i++) {
            if (type.equals(args[i])) {
                filePath = args[i + 1];
                break;
            }
        }
        return filePath;
    }

    private boolean shouldDoTheAction(String[] args, String type) {
        boolean shouldDoTheAction = false;
        for (int i = 0; i < args.length; i += 2) {
            if (type.equals(args[i])) {
                shouldDoTheAction = true;
                break;
            }
        }
        return shouldDoTheAction;
    }
}
