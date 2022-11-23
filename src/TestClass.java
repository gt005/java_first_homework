import ReversiGame.PlayingField;
/**
 * Пример запуска игры
 */
public class TestClass {
    public static void main(String[] args) {
        PlayingField test = new PlayingField();
        test.createNewGame();

        System.out.println(test.renderPlayingFieldIntoString());

    }
}