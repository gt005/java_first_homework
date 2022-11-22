package ReversiGame;

/**
 * легкий вариант - это на каждом ходе он будет наибольшее количество фишек отсматривать
 * сложный - это то же самое, но на 3 хода вперед и отсматривать где больше выгода при каждом из ходов игрока
 */
public class PlayingField {
    private int[][] playingField;
    private boolean colorOfMove;

    public int[][] getPlayingField() {
        return playingField;
    }

    public boolean getColorOfMove() {
        return colorOfMove;
    }

    /**
     * Создает новую игру с чистым полем и инициализирует нужные переменные
     */
    public void createNewGame() {
    }

    /**
     * Переводит двумерный массив из переменной playingField в двумерную строку
     */
    public String renderPlayingFieldIntoString() {
        return "function 'renderPlayingFieldIntoString' hadn't implemented yet";
    }

    /**
     * Ставит на позицию новую фишку цвета текущего хода и меняет ход.
     * @param pos_x позиция новой фишки по горизонтали
     * @param pos_y позиция новой фишки по вертикали
     * @return возвращает строку ошибки или константу SUCCESSFUL_FUNCTION_COMPLETION при
     *  успешном ходе
     */
    public String makeMoveOnPosition(int pos_x, int pos_y) {
        return "function 'makeMoveOnPosition' hadn't implemented yet";
    }
}
