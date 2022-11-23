package ReversiGame;

public final class Constants {
    public static final boolean REVERSI_BLACK_TURN = false;
    public static final boolean REVERSI_WHITE_TURN = true;
    public static final String SUCCESSFUL_FUNCTION_COMPLETION = "success";

    // Число ходов, которые будут просчитываться AI наперед
    public static final int LEVEL_OF_COMPLEXITY = 3;
    public static final String GAME_RULES = "Тут описать правила игры";
    public static final int EMPTY_CELL = 0;
    public static final int WHITE_CELL = 1;
    public static final int BLACK_CELL = 2;
    public static final int POSSIBLE_MOVE_CELL = 3;

    public static final String EMPTY_CELL_RENDER_SYMBOL = new String(Character.toChars(0xb7));
    public static final String WHITE_CELL_RENDER_SYMBOL = new String(Character.toChars(0x26AA));
    public static final String BLACK_CELL_RENDER_SYMBOL = new String(Character.toChars(0x26AB));
    public static final String POSSIBLE_MOVE_RENDER_SYMBOL = new String(Character.toChars(0x2705));
}
