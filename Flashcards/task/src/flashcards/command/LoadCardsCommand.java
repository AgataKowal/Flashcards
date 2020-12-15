package flashcards.command;

import flashcards.Flashcards;

public class LoadCardsCommand implements Command {

    private final Flashcards flashcards;
    private final String filePath;

    public LoadCardsCommand(Flashcards flashcards, String filePath) {
        this.flashcards = flashcards;
        this.filePath = filePath;
    }

    @Override
    public void execute() {
        flashcards.loadCards(filePath);
    }
}
