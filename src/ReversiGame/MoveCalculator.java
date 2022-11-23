package ReversiGame;

import java.util.ArrayList;
import java.util.Arrays;

import static ReversiGame.Constants.*;

/**
 * Класс для помощи в просчете возможных ходов
 */
public final class MoveCalculator {
    /**
     * Проверяет, не вышли ли координаты за доску размером 8 на 8
     */
    private static boolean isPositionOnTheBoard(int positionX, int positionY) {
        return (positionX >= 0) && (positionX < 8) && (positionY >= 0) && (positionY < 8);
    }

    private MoveCalculator() {
    }

    /**
     * Рассчитывает все ячейки, куда в данный момент может быть поставлена фишка игрока
     *
     * @param playingField задает игровое поле, для которого рассчитывать ходы.
     * @param colorOfMove задает цвет фишек, для которых делать рассчеты.
     * @return двумерный массив из координат возможных фишек
     */
    public static ArrayList<ArrayList<Integer>> getAllPossibleCellsToMove(int[][] playingField, boolean colorOfMove) {
        ArrayList<ArrayList<Integer>> resultPossibleCells = new ArrayList<ArrayList<Integer>>();

        int unionChipColor = (colorOfMove == REVERSI_BLACK_TURN) ? BLACK_CELL : WHITE_CELL;
        int enemyChipColor = (colorOfMove == REVERSI_BLACK_TURN) ? WHITE_CELL : BLACK_CELL;

        // просматриваем все возможные ячейки поля
        for (int xAvailablePosition = 0; xAvailablePosition < 8; ++xAvailablePosition) {
            for (int yAvailablePosition = 0; yAvailablePosition < 8; ++yAvailablePosition) {
                // Зачем просматривать ячейку, на которой и так кто-то стоит
                if (playingField[yAvailablePosition][xAvailablePosition] != EMPTY_CELL) {
                    continue;
                }

                boolean atLeastOneDirectionFound = false;
                // просмотр во все стороны
                for (int xDirection = -1; xDirection <= 1; ++xDirection) {
                    for (int yDirection = -1; yDirection <= 1; ++yDirection) {
                        if ((xDirection == yDirection) && (yDirection == 0)) {
                            continue;
                        }
                        int tmpXPosition = xAvailablePosition + xDirection;
                        int tmpYPosition = yAvailablePosition + yDirection;

                        // Пока не выходит за поле и просматриваемый ряд содержит только вражеский цвет
                        while (isPositionOnTheBoard(tmpXPosition, tmpYPosition) &&
                                (playingField[tmpYPosition][tmpXPosition] == enemyChipColor)) {

                            tmpYPosition += yDirection;
                            tmpXPosition += xDirection;
                        }

                        if (isPositionOnTheBoard(tmpXPosition, tmpYPosition) &&
                                playingField[tmpYPosition][tmpXPosition] == unionChipColor &&
                                !((tmpXPosition == xAvailablePosition + xDirection) &&
                                (tmpYPosition == yAvailablePosition + yDirection))
                        ) {
                            resultPossibleCells.add(new ArrayList<>(Arrays.asList(xAvailablePosition, yAvailablePosition)));
                            atLeastOneDirectionFound = true;
                            break;
                        }
                    }
                    if (atLeastOneDirectionFound) {
                        break;
                    }
                }
            }
        }

        return resultPossibleCells;
    }

    /**
     * Получить количество очков, которое прибавится при размещении фишки на конкретной позиции.
     * Важно: Валидация данных должна происходить извне.
     * @param playingField задает игровое поле, для которого посчитать ход
     * @param colorOfMove  указывает на то, чей ход
     * @param positionX    позиция фишки, которую поставить по горизонтали
     * @param positionY    позиция фишки, которую поставить по вертикали
     * @return
     */
    private int getIncreaseInPointsOfCertainMove(int[][] playingField, boolean colorOfMove, int positionX, int positionY) {
        return -1;
    }

    /**
     * Находит вариант лучшего хода в текущий момент, играя на высокой сложности.
     * <p>
     * На этой сложности анализируется количество LEVEL_OF_COMPLEXITY будущих возможных хода противника,
     * и выдается вариант хода с наибольшим выигрышем по очкам.
     * <p>
     * @param levelOfComplexity задает количество шагов, которое нужно просматривать наперед с оценкой
     *                          возможных ответных ходов противника.
     *                          0 означает отсутствие оценки возможных ответов противника.
     * @param playingField задает игровое поле, для которого рассчитывать ходы.
     * @param colorOfMove задает цвет фишек, для которых делать рассчеты.
     * @return Массив из трех значений: позиция по x, позиция по y, куда нужно поставить фишку,
     *         а также количество очков, на которое увеличется отрыв от противника.
     */
    public static int[] getBestMoveWithComplexity(int levelOfComplexity, int[][] playingField, boolean colorOfMove) {
        return new int[2];
    }
}
