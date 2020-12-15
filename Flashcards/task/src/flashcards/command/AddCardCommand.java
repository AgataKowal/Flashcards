package flashcards.command;

import flashcards.Flashcards;

import java.util.Scanner;

public class AddCardCommand implements Command {

    private final Flashcards flashcards;
    private final String cardToAdd;
    private final Scanner scanner;

    public AddCardCommand(Flashcards flashcards, String cardToAdd, Scanner scanner) {
        this.flashcards = flashcards;
        this.cardToAdd = cardToAdd;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        flashcards.addCard(cardToAdd, scanner);
    }
}
