package flashcards.command;

import flashcards.Flashcards;

public class ResetStatsCommand implements Command {

    private final Flashcards flashcards;

    public ResetStatsCommand(Flashcards flashcards) {
        this.flashcards = flashcards;
    }

    @Override
    public void execute() {
        flashcards.resetStats();
    }
}
