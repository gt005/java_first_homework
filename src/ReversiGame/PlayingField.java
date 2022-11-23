package ReversiGame;

import java.util.ArrayList;

import static ReversiGame.Constants.*;

/**
 * Движок ReversiGame гарантирует правильную последовательность вызовов методов данного класса¡
 */
public class PlayingField {
    private int[][] playingField;
    private boolean colorOfMove;
    private ArrayList<ArrayList<Integer>> possibleCellsToMove;

    private Player player1;
    private Player player2;  // Для режима игры игрок-игрок

    private boolean gameStarted;

    public boolean isGameStarted() {
        return gameStarted;
    }

    public int[][] getPlayingField() {
        return playingField;
    }

    public boolean getColorOfMove() {
        return colorOfMove;
    }

    private void clearPlayingField() {
        playingField = new int[8][8];
        for (int y = 0; y < 8; ++y) {
            for (int x = 0; x < 8; ++x) {
                playingField[y][x] = EMPTY_CELL;
            }
        }

        playingField[3][3] = WHITE_CELL;
        playingField[4][4] = WHITE_CELL;
        playingField[3][4] = BLACK_CELL;
        playingField[4][3] = BLACK_CELL;
    }

    /**
     * Создает новую игру с чистым полем и инициализирует нужные переменные
     */
    public void createNewGame() {
        colorOfMove = REVERSI_BLACK_TURN;
        clearPlayingField();
        possibleCellsToMove = MoveCalculator.getAllPossibleCellsToMove(playingField, colorOfMove);
    }

    /**
     * Переводит двумерный массив из переменной playingField в двумерную строку
     */
    public String renderPlayingFieldIntoString() {
        StringBuilder resultRenderedString = new StringBuilder();
        resultRenderedString.append('\t');

        for (int horizontalPositionSymbol = (int)'A'; horizontalPositionSymbol < (int)'I'; ++horizontalPositionSymbol) {
            resultRenderedString.append((char)horizontalPositionSymbol);
            resultRenderedString.append('\t');
        }

        resultRenderedString.append('\n');

        for (int y = 0; y < 8; ++y) {
            resultRenderedString.append(y + 1);
            for (int x = 0; x < 8; ++x) {
                resultRenderedString.append('\t');
                switch (playingField[y][x]) {
                    case EMPTY_CELL:
                        resultRenderedString.append(EMPTY_CELL_RENDER_SYMBOL);
                        break;
                    case WHITE_CELL:
                        resultRenderedString.append(WHITE_CELL_RENDER_SYMBOL);
                        break;
                    case BLACK_CELL:
                        resultRenderedString.append(BLACK_CELL_RENDER_SYMBOL);
                        break;
                    case POSSIBLE_MOVE_CELL:
                        resultRenderedString.append(POSSIBLE_MOVE_RENDER_SYMBOL);
                        break;
                    default:
                        resultRenderedString.append('?');
                        break;
                }
            }
            resultRenderedString.append('\n');
        }
        return resultRenderedString.toString();
    }

    /**
     * Ставит на позицию новую фишку цвета текущего хода и меняет ход.
     * @param posX позиция новой фишки по горизонтали
     * @param posY позиция новой фишки по вертикали
     * @return возвращает строку ошибки или константу SUCCESSFUL_FUNCTION_COMPLETION при
     *         успешном ходе
     */
    public String makeMoveOnPosition(int posX, int posY) {


        return "function 'makeMoveOnPosition' hadn't implemented yet";
    }
}
