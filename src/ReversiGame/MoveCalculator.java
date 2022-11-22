package ReversiGame;

public class MoveCalculator {
    /**
     * Рассчитывает все ячейки, куда в данный момент может быть поставлена фишка игрока
     * @return двумерный массив координат возможных ходов
     */
    public int[][] getAllPossibleCellsToMove() {
        return new int[1][1];
    }

    /**
     * Находит вариант лучшего хода в текущий момент, играя на низкой сложности.
     *
     * На этой сложности анализируется только наиболее возможное количество очков,
     * которое будет получено на этом ходе без анализа возможных ходов противника.
     *
     * @return Массив из двух значений: позиция по x и y, куда нужно поставить фишку.
     */
    public int[] getBestMoveWithLightComplexity() {
        return new int[2];
    }

    /**
     * Находит вариант лучшего хода в текущий момент, играя на высокой сложности.
     *
     * На этой сложности анализируется количество LEVEL_OF_COMPLEXITY будущих возможных хода противника,
     * и выдается вариант хода с наибольшим выигрышем по очкам.
     *
     * @return Массив из двух значений: позиция по x и y, куда нужно поставить фишку.
     */
    public int[] getBestMoveWithHardComplexity() {
        return new int[2];
    }
}
