package ReversiGame;

import static ReversiGame.Constants.*;

/**
 * Управляет ходом игры, взаимодействуя с пользователем, взаимодействует с классами Player и ReversiBoard
 */
public class ReversiGame {
    /**
     * Создает новую игру, а также выводит правила
     */
    public void startGame() {
        System.out.println(GAME_RULES);
        PlayingField playingField = new PlayingField();
        playingField.createNewGame();

    }


}
