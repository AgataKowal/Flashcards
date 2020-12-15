package flashcards.command;

import flashcards.Flashcards;

public class ExitGameCommand implements Command {

    private final Flashcards flashcards;

    public ExitGameCommand(Flashcards flashcards) {
        this.flashcards = flashcards;
    }

    @Override
    public void execute() {
        flashcards.exitGame();
    }
}
