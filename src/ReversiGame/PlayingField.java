package ReversiGame;

import java.util.ArrayList;
import java.util.Arrays;

import static ReversiGame.Constants.*;

/**
 * Движок ReversiGame гарантирует правильную последовательность вызовов методов данного класса¡
 */
public class PlayingField {
    private int[][] playingField; // Само поле 8 на 8 клеток
    private boolean colorOfMove;  // Цвет фишек текущего хода

    private int[][] previousPlayingField;  // Хранит предыдущее игровое поле для возможности отмены хода

    private ArrayList<ArrayList<Integer>> possibleCellsToMove;  // Хранит все возможные клетки для текущего хода

    private int[] gameScore;  // Хранит два значения: количество черных и белых фишек соответственно

    private int blackBestScore;  // Максимальное количество черных фишек в конце игры за сессию
    private int whiteBestScore;  // Максимальное количество черных фишек в конце игры за сессию

    {
        blackBestScore = 0;
        whiteBestScore = 0;
        previousPlayingField = null;
    }

    /**
     * Возвращает счет игры
     * @return Массив из двух элементов: количества черных и белых фишек на поле соответственно
     */
    public int[] getGameScore() {
        return gameScore;
    }

    /**
     * Проверяет, может ли игрок сделать ход.
     */
    public boolean checkPossibilityOfMove() {
        return !possibleCellsToMove.isEmpty();
    }

    /**
     * Передает ход следующему игроку
     */
    public void changeTurn() {
        colorOfMove = !colorOfMove;
        possibleCellsToMove = MoveCalculator.getAllPossibleCellsToMove(playingField, colorOfMove);
    }

    /**
     * Возвращает цвет текущего хода
     */
    public boolean getColorOfMove() {
        return colorOfMove;
    }

    public int getBlackBestScore() {
        return blackBestScore;
    }

    public int getWhiteBestScore() {
        return whiteBestScore;
    }

    /**
     * Изменяет лучший счет для черных фишек, если пришедшее значение больше имеющегося
     */
    public void setBlackBestScore(int blackBestScore) {
        if (this.blackBestScore < blackBestScore) {
            this.blackBestScore = blackBestScore;
        }
    }

    /**
     * Изменяет лучший счет для черных фишек, если пришедшее значение больше имеющегося
     */
    public void setWhiteBestScore(int whiteBestScore) {
        if (this.whiteBestScore < whiteBestScore) {
            this.whiteBestScore = whiteBestScore;
        }
    }

    /**
     * Полностью очищает игровое поле, оставляя только 4 начальные фишки.
     */
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
     * Создает новую игру с чистым полем.
     */
    public void createNewGame() {
        colorOfMove = REVERSI_BLACK_TURN;
        clearPlayingField();
        gameScore = new int[]{2, 2};
        possibleCellsToMove = MoveCalculator.getAllPossibleCellsToMove(playingField, colorOfMove);
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
     * Выполняет ход на данные координаты, переворачивает все "замыкающие" фишки противника и передает ход следующему игроку.
     * @param positionX позиция новой фишки по горизонтали
     * @param positionY позиция новой фишки по вертикали
     * @return Строка, которая содержит сообщение об ошибки или строку-константу SUCCESSFUL_FUNCTION_COMPLETION, если ход успешно выполнен.
     */
    public String makeMoveOnPosition(int positionX, int positionY, boolean isComputerTurn) {
        ArrayList<Integer> arrayToFind = new ArrayList<>();
        arrayToFind.add(positionX);
        arrayToFind.add(positionY);
        if (!possibleCellsToMove.contains(arrayToFind)) {
            return "На эту клетку нельзя ходить по правилам игры. Все возможные ходы указаны галочкой.";
        }

        int[][] tmpPlayingField = null;

        if (!isComputerTurn) { // Нужно сохранять только если ходит человек
            tmpPlayingField = new int[8][];
            for (int i = 0; i < 8; ++i) {
                tmpPlayingField[i] = Arrays.copyOf(playingField[i], 8);
            }
        }

        int scoreChange = MoveCalculator.invertChipsDiagonally(playingField, colorOfMove, positionX, positionY);
        if (colorOfMove == REVERSI_BLACK_TURN) {
            gameScore[0] += scoreChange + 1;
            gameScore[1] -= scoreChange;
        } else {
            gameScore[0] -= scoreChange;
            gameScore[1] += scoreChange + 1;
        }

        playingField[positionY][positionX] = (colorOfMove == REVERSI_BLACK_TURN) ? BLACK_CELL : WHITE_CELL;
        colorOfMove = !colorOfMove;
        possibleCellsToMove = MoveCalculator.getAllPossibleCellsToMove(playingField, colorOfMove);

        if (!isComputerTurn) {
            previousPlayingField = new int[8][];
            for (int i = 0; i < 8; ++i) {
                previousPlayingField[i] = Arrays.copyOf(tmpPlayingField[i], 8);
            }
        }
        return SUCCESSFUL_FUNCTION_COMPLETION;
    }

    /**
     * Проверяет, может ли игрок отменить свой ход и отменяет если это возможно.
     * Например, в начале игры это невозможно, а также нельзя отменить ход второй раз.
     * @param gameMode ожидает константу из класса Constants.
     * @return Строка с ошибкой, либо константная строка SUCCESSFUL_FUNCTION_COMPLETION в случае успеха отмены хода.
     */
    public String cancelLastPlayerMove(boolean gameMode) {
        if (this.previousPlayingField == null) {
            return "Этот ход отменить нельзя";
        }

        if (gameMode == PLAYER_PLAYER_GAME_MODE) {
            this.changeTurn();
        }

        playingField = previousPlayingField;
        previousPlayingField = null;

        possibleCellsToMove = MoveCalculator.getAllPossibleCellsToMove(playingField, colorOfMove);

        return SUCCESSFUL_FUNCTION_COMPLETION;
    }

    /**
     * Рассчитывает оптимальный ход компьютера, учитывая сложность игры и совершает нужный ход.
     */
    public void calculateAndMakeComputerMove(boolean levelOfComplexity) {
        int[] moveCoordinates = MoveCalculator.getBestMoveWithComplexity(levelOfComplexity, playingField, colorOfMove);

        makeMoveOnPosition(moveCoordinates[0], moveCoordinates[1], true);
    }
}
