package flashcards;

import java.io.InputStream;

public class Main {

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        Game game = new Game(inputStream);
        game.play(args);
    }
}
