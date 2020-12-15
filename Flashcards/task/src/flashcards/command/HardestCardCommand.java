package flashcards.command;

import flashcards.Flashcards;

public class HardestCardCommand implements Command {

    private final Flashcards flashcards;

    public HardestCardCommand(Flashcards flashcards) {
        this.flashcards = flashcards;
    }

    @Override
    public void execute() {
        flashcards.getHardestCard();
    }
}
