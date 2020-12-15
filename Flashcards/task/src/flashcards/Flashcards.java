package flashcards;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Flashcards {

    private static final List<Flashcard> flashcards = new ArrayList<>();
    private static final List<Flashcard> hardestCards = new ArrayList<>();


    public void addCard(String cardToAdd, Scanner scanner) {
        String text;
        if (flashcards.stream().anyMatch(card -> card.getCard().equals(cardToAdd))) {
            text = String.format("The card \"%s\" already exists.", cardToAdd);
            System.out.println(text);
            MyLogger.addToLog(text);
        } else {
            text = "The definition of the card:";
            System.out.println(text);
            String definition = scanner.nextLine().trim();
            MyLogger.addToLog(text, definition);
            if (flashcards.stream().anyMatch(card -> card.getDefinition().equals(definition))) {
                text = String.format("The definition \"%s\" already exists.", definition);
                System.out.println(text);
                MyLogger.addToLog(text);
            } else {
                flashcards.add(new Flashcard(cardToAdd, definition));
                text = String.format("The pair (\"%s\":\"%s\") has been added.", cardToAdd, definition);
                System.out.println(text);
                MyLogger.addToLog(text);
            }
        }
    }

    public void exitGame() {
        System.out.println("Bye bye!");
    }

    public void getHardestCard() {
        int maxErrors = 1;
        for (Flashcard card : flashcards) {
            if (card.getMistakes() > maxErrors) {
                hardestCards.clear();
                hardestCards.add(card);
                maxErrors = card.getMistakes();
            } else if (card.getMistakes() == maxErrors) {
                hardestCards.add(card);
            }
        }
        String text;
        if (hardestCards.size() == 0) {
            text = "There are no cards with errors.";
            System.out.println(text);
            MyLogger.addToLog(text);
        } else if (hardestCards.size() == 1) {
            Flashcard hardestOne = hardestCards.get(0);
            text = String.format("The hardest card is \"%s\". You have %d errors answering it.",
                    hardestOne.getCard(), hardestOne.getMistakes());
            System.out.println(text);
            MyLogger.addToLog(text);
        } else {
            int size = hardestCards.size();
            StringBuilder sb = new StringBuilder("The hardest cards are");
            for (int i = 0; i < size - 1; i++) {
                sb.append(" \"").append(hardestCards.get(i).getCard()).append("\",");
            }
            Flashcard lastCard = hardestCards.get(size - 1);
            sb.append(" \"").append(lastCard.getCard()).append("\". You have ").append(lastCard.getMistakes())
                    .append(" errors answering them.");
            System.out.println(sb.toString());
            MyLogger.addToLog(sb.toString());
        }
    }

    public void loadCards(String filePath) {
        String text;
        try {
            Path path = Paths.get(filePath);
            List<String> lines = Files.readAllLines(path);
            String[] triangle;
            String card;
            String definition;
            int mistakes = 0;
            for (String line : lines) {
                triangle = line.trim().split(":");
                card = triangle[0];
                definition = triangle[1];
                if (triangle.length == 3) {
                    mistakes = Integer.parseInt(triangle[2]);
                }
                Flashcard existing = getCardByName(card);
                //TODO verify
                if (existing != null) {
                    flashcards.remove(existing);
                }
                existing = new Flashcard(card, definition, mistakes);
                flashcards.add(existing);
            }
            text = String.format("%d cards have been loaded.", lines.size());
            System.out.println(text);
            MyLogger.addToLog(text);
        } catch (IOException e) {
            text = "File not found.";
            System.out.println(text);
            MyLogger.addToLog(text);
        }
    }

    public void generateLog(String fileName) {
        List<String> logs = MyLogger.getLogs();
        File file = new File(fileName);
        try (FileWriter writer = new FileWriter(file)) {
            for (String line : logs) {
                writer.write(line + "\r\n");
            }
        } catch (IOException e) {
            System.out.println("Error while writing to file.");
        }
        System.out.println("The log has been saved.");
    }

    public void playCards(int timesToPlay, Scanner scanner) {
        int counter = 0;
        String text;
        while (counter != timesToPlay) {
            for (Flashcard flashcard : flashcards) {
                text = "Print the definition of \"" + flashcard.getCard() + "\":";
                System.out.println(text);
                String answer = scanner.nextLine().trim();
                MyLogger.addToLog(text, answer);
                if (flashcard.isAnswerCorrect(answer)) {
                    text = "Correct!";
                    System.out.println(text);
                    MyLogger.addToLog(text);
                } else if (flashcards.stream().anyMatch(card -> card.getDefinition().equals(answer))) {
                    text = "Wrong. The right answer is \"" + flashcard.getDefinition() +
                            "\", but your definition is correct for \"" + getCardFromDescription(answer) +
                            "\".";
                    System.out.println(text);
                    MyLogger.addToLog(text);
                    flashcard.incrementMistakes();
                } else {
                    text = "Wrong. The right answer is \"" + flashcard.getDefinition() + "\".";
                    System.out.println(text);
                    MyLogger.addToLog(text);
                    flashcard.incrementMistakes();
                }
                counter += 1;
                if (counter == timesToPlay) {
                    break;
                }
            }
        }
    }

    public void removeCard(String cardToRemove) {
        String text;
        if (flashcards.stream().anyMatch(card -> card.getCard().equals(cardToRemove))) {
            Flashcard toRemove = flashcards.stream().filter(card -> card.getCard().equals(cardToRemove))
                    .findFirst().orElseThrow();
            flashcards.remove(toRemove);
            text = "The card has been removed.";
            if (hardestCards.stream().anyMatch(card -> card.getCard().equals(cardToRemove))) {
                hardestCards.remove(toRemove);
            }
        } else {
            text = String.format("Can't remove \"%s\": there is no such card.", cardToRemove);
        }
        System.out.println(text);
        MyLogger.addToLog(text);
    }

    public void resetStats() {
        for (Flashcard card : flashcards) {
            card.setMistakes(0);
        }
        hardestCards.clear();
        String text = "Card statistics has been reset.";
        System.out.println(text);
        MyLogger.addToLog(text);
    }

    public void saveCards(String filePath) {
        File file = new File(filePath);
        String text;
        try (FileWriter writer = new FileWriter(file)) {
            for (Flashcard card : flashcards) {
                writer.write(card.getCard() + ":" + card.getDefinition() + ":" + card.getMistakes() + "\r\n");
            }
            text = String.format("%d cards have been saved.", flashcards.size());
            System.out.println(text);
            MyLogger.addToLog(text);
        } catch (IOException e) {
            text = "Error while writing to file.";
            System.out.println(text);
            MyLogger.addToLog(text);
        }
    }

    public void printInvalidMessage() {
        String text = "Invalid command";
        System.out.println(text);
        MyLogger.addToLog(text);
    }

    private Flashcard getCardFromDescription(String answer) {
        return flashcards.stream().filter(card -> answer.equals(card.getDefinition())).findFirst().orElseThrow();
    }

    private Flashcard getCardByName(String name) {
        return flashcards.stream().filter(card -> card.getCard().equals(name)).findFirst().orElse(null);
    }
}
