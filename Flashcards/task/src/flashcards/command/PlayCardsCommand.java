package flashcards.command;

import flashcards.Flashcards;

import java.util.Scanner;

public class PlayCardsCommand implements Command {

    private final Flashcards flashcards;
    private final int timesToPlay;
    private final Scanner scanner;

    public PlayCardsCommand(Flashcards flashcards, int timesToPlay, Scanner scanner) {
        this.flashcards = flashcards;
        this.timesToPlay = timesToPlay;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        flashcards.playCards(timesToPlay, scanner);
    }
}
