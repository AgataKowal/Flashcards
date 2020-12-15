package flashcards;

public class Flashcard {

    private String card;
    private String definition;
    private int mistakes;

    public Flashcard(String card, String definition) {
        this.card = card;
        this.definition = definition;
        this.mistakes = 0;
    }

    public Flashcard(String card, String definition, int mistakes) {
        this.card = card;
        this.definition = definition;
        this.mistakes = mistakes;
    }

    public void incrementMistakes() {
        this.mistakes += 1;
    }

    public boolean isAnswerCorrect(String answer) {
        return this.definition.equals(answer);
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public int getMistakes() {
        return mistakes;
    }

    public void setMistakes(int mistakes) {
        this.mistakes = mistakes;
    }

    @Override
    public String toString() {
        return card;
    }
}
