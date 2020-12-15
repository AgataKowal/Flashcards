package flashcards.command;

import flashcards.Flashcards;

public class RemoveCardCommand implements Command {

    private final Flashcards flashcards;
    private final String cardToRemove;

    public RemoveCardCommand(Flashcards flashcards, String cardToRemove) {
        this.flashcards = flashcards;
        this.cardToRemove = cardToRemove;
    }

    @Override
    public void execute() {
        flashcards.removeCard(cardToRemove);
    }
}
