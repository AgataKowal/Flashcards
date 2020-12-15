package flashcards.command;

import flashcards.Flashcards;

public class InvalidInputCommand implements Command {

    private final Flashcards flashcards;

    public InvalidInputCommand(Flashcards flashcards) {
        this.flashcards = flashcards;
    }

    @Override
    public void execute() {
        flashcards.printInvalidMessage();
    }
}
