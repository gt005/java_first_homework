package ReversiGame;

public final class Constants {
    public static final boolean REVERSI_BLACK_TURN = false;
    public static final boolean REVERSI_WHITE_TURN = true;
    public static final String SUCCESSFUL_FUNCTION_COMPLETION = "success";

    public static final int EMPTY_CELL = 0;
    public static final int WHITE_CELL = 1;
    public static final int BLACK_CELL = 2;

    public static final String EMPTY_CELL_RENDER_SYMBOL = new String(Character.toChars(0xb7));
    public static final String WHITE_CELL_RENDER_SYMBOL = new String(Character.toChars(0x26AA));
    public static final String BLACK_CELL_RENDER_SYMBOL = new String(Character.toChars(0x26AB));
    public static final String POSSIBLE_MOVE_RENDER_SYMBOL = new String(Character.toChars(0x2705));

    public static final boolean PLAYER_PLAYER_GAME_MODE = true;
    public static final boolean PLAYER_COMPUTER_GAME_MODE = false;

    public static final boolean EASY_COMPUTER_COMPLEXITY = true;
    public static final boolean HARD_COMPUTER_COMPLEXITY = true;

    public static  final String GAME_RULES = "В начале игры в центр доски выставляются 4 фишки: чёрные на d5 и e4, белые на d4 и e5.\n" +
            "Первый ход делают чёрные. Далее игроки ходят по очереди.\n" +
            "Делая ход, игрок должен поставить свою фишку на одну из клеток доски таким образом," +
            " чтобы между этой поставленной фишкой и одной из имеющихся уже на доске фишек его цвета находился " +
            "непрерывный ряд фишек соперника, горизонтальный, вертикальный или диагональный (другими словами, " +
            "чтобы непрерывный ряд фишек соперника оказался «закрыт» фишками игрока с двух сторон). " +
            "Все фишки соперника, входящие в «закрытый» на этом ходу ряд, переворачиваются на другую сторону (меняют цвет)" +
            " и переходят к ходившему игроку.\n" +
            "Если в результате одного хода «закрывается» одновременно более одного ряда фишек противника, " +
            "то переворачиваются все фишки, оказавшиеся на тех «закрытых» рядах, которые идут от поставленной фишки.\n" +
            "Игрок вправе выбирать любой из возможных для него ходов. Если игрок имеет возможные ходы, " +
            "он не может отказаться от хода. Если игрок не имеет допустимых ходов, то ход передаётся сопернику.\n" +
            "Игра прекращается, когда на доску выставлены все фишки или когда ни один из игроков не может сделать хода." +
            " По окончании игры проводится подсчёт фишек каждого цвета, и игрок, чьих фишек на доске выставлено больше," +
            " объявляется победителем. В случае равенства количества фишек засчитывается ничья.\n";
    public static final String POSSIBLE_GAME_MODES_SELECTION_DESCRIPTION = "Выберите режим игры, введя цифру режима: \n1) Игрок - компьютер(слабый)\t2) Игрок - компьютер(продвинутый)\t3) Игрок - Игрок";
    public static final String USER_REMOTE_GAME_COMMANDS = "Неизвестная команда, Чтобы начать новую игру, напишите \"start\"\n" +
            "Чтобы получить информацию о лучшем счете за сессию, напишите \"score\".\n" +
            "Чтобы выйти из игры, напишите \"quit\"\n";
}
