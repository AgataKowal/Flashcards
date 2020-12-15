package flashcards.command;

import flashcards.Flashcards;

public class LogCommand implements Command {

    private final Flashcards flashcards;
    private final String fileName;

    public LogCommand(Flashcards flashcards, String fileName) {
        this.flashcards = flashcards;
        this.fileName = fileName;
    }

    @Override
    public void execute() {
        flashcards.generateLog(fileName);
    }
}
