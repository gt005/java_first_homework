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

        System.out.println(playingField.renderPlayingFieldIntoString());
        playingField.makeMoveOnPosition(5, 4);
        System.out.println(playingField.renderPlayingFieldIntoString());
        playingField.makeMoveOnPosition(3, 5);
        System.out.println(playingField.renderPlayingFieldIntoString());
        playingField.makeMoveOnPosition(2, 3);
        System.out.println(playingField.renderPlayingFieldIntoString());
    }


}
