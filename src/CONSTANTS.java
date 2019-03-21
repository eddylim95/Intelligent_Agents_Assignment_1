public class CONSTANTS {
    public static int MAZE_NUM_COLS = 6;
    public static int MAZE_NUM_ROWS = 6;

    public static int AGENT_START_COL = 2;
    public static int AGENT_START_ROW = 3;
    public static double CORRECT_ACTION_PROBABILITY = 0.8;
    public static double WRONG_ACTION_PROBABILITY = 0.1;

    private static double C = 0.1;
    private static double R_MAX = 1.0;
    public static double MAX_UTILITY_ERROR = C * R_MAX;
    public static double DISCOUNT_FACTOR = .99;

    public static double WHITE_TILE_REWARD = -0.04;
    public static double GREEN_TILE_REWARD = 1;
    public static double BROWN_TILE_REWARD = -1;
}
