package ReversiGame;

public final class Constants {
    public static final boolean REVERSI_BLACK_TURN = false;
    public static final boolean REVERSI_WHITE_TURN = true;
    public static final String SUCCESSFUL_FUNCTION_COMPLETION = "success";

    public static final String GAME_RULES = "Выберите режим игры, введя цифру режима: \n1) Игрок - компьютер(слабый)\t2) Игрок - компьютер(продвинутый)\t3) Игрок - Игрок";
    public static final int EMPTY_CELL = 0;
    public static final int WHITE_CELL = 1;
    public static final int BLACK_CELL = 2;
    public static final int POSSIBLE_MOVE_CELL = 3;

    public static final String EMPTY_CELL_RENDER_SYMBOL = new String(Character.toChars(0xb7));
    public static final String WHITE_CELL_RENDER_SYMBOL = new String(Character.toChars(0x26AA));
    public static final String BLACK_CELL_RENDER_SYMBOL = new String(Character.toChars(0x26AB));
    public static final String POSSIBLE_MOVE_RENDER_SYMBOL = new String(Character.toChars(0x2705));

    public static final boolean PLAYER_PLAYER_GAME_MODE = true;
    public static final boolean PLAYER_COMPUTER_GAME_MODE = false;

    public static final boolean EASY_COMPUTER_COMPLEXITY = true;
    public static final boolean HARD_COMPUTER_COMPLEXITY = true;
}
