import ReversiGame.PlayingField;
import ReversiGame.MoveCalculator;

import java.util.ArrayList;

import static ReversiGame.Constants.REVERSI_BLACK_TURN;

/**
 * Пример запуска игры
 */
public class TestClass {
    public static void main(String[] args) {
        PlayingField test = new PlayingField();
        test.createNewGame();

        System.out.println(test.renderPlayingFieldIntoString());

        int[][] playingField = {
            {0, 0, 0, 2, 0, 0, 0, 0},
            {0, 0, 2, 2, 0, 0, 1, 0},
            {0, 0, 0, 2, 1, 1, 0, 0},
            {0, 0, 2, 1, 1, 2, 0, 0},
            {0, 0, 1, 1, 1, 2, 0, 0},
            {0, 1, 0, 1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
        };

//        System.out.println(PlayingField.renderPlayingFieldIntoString(playingField));

//        System.out.println();
//        for (ArrayList<Integer> x : MoveCalculator.getAllPossibleCellsToMove(playingField, REVERSI_BLACK_TURN)) {
//            playingField[x.get(1)][x.get(0)] = 3;
//        }
//        System.out.println(PlayingField.renderPlayingFieldIntoString(playingField));
    }
}