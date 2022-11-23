package ReversiGame;

import java.util.ArrayList;

import static ReversiGame.Constants.*;

/**
 * Движок ReversiGame гарантирует правильную последовательность вызовов методов данного класса¡
 */
public class PlayingField {
    private int[][] playingField;
    private boolean colorOfMove;

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
    }

    /**
     * Переводит двумерный массив из переменной playingField в двумерную строку,
     * а также подставляет в строку возможные ходы
     * @return Строка, содержащая поле с нумерацией строк и столбцов
     */
    public String renderPlayingFieldIntoString() {
        StringBuilder resultRenderedString = new StringBuilder();
        resultRenderedString.append('\t');

        for (int horizontalPositionSymbol = 1; horizontalPositionSymbol < 9; ++horizontalPositionSymbol) {
            resultRenderedString.append(horizontalPositionSymbol);
            resultRenderedString.append('\t');
        }

        resultRenderedString.append('\n');

        ArrayList<ArrayList<Integer>> possibleCellsToMove = MoveCalculator.getAllPossibleCellsToMove(playingField, colorOfMove);

        for (int y = 0; y < 8; ++y) {
            resultRenderedString.append(y + 1);
            for (int x = 0; x < 8; ++x) {
                resultRenderedString.append('\t');
                ArrayList<Integer> arrayToFind = new ArrayList<>();
                arrayToFind.add(x);
                arrayToFind.add(y);

                if (possibleCellsToMove.contains(arrayToFind)) {
                    resultRenderedString.append(POSSIBLE_MOVE_RENDER_SYMBOL);
                    continue;
                }
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
     * @param positionX позиция новой фишки по горизонтали
     * @param positionY позиция новой фишки по вертикали
     */
    public void makeMoveOnPosition(int positionX, int positionY) {
        MoveCalculator.invertChipsDiagonally(playingField, colorOfMove, positionX, positionY);
        playingField[positionY][positionX] = (colorOfMove == REVERSI_BLACK_TURN) ? BLACK_CELL : WHITE_CELL;
        colorOfMove = !colorOfMove;
    }
}
