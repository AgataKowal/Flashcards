package flashcards.command;

import flashcards.Flashcards;

public class SaveCardsCommand implements Command {

    private final Flashcards flashcards;
    private final String filePath;

    public SaveCardsCommand(Flashcards flashcards, String filePath) {
        this.flashcards = flashcards;
        this.filePath = filePath;
    }

    @Override
    public void execute() {
        flashcards.saveCards(filePath);
    }
}
