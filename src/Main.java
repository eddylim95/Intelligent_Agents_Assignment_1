public class Main {
    public static void main(String[] args) {
        Maze maze = new Maze(CONSTANTS.MAZE_NUM_COLS, CONSTANTS.MAZE_NUM_ROWS);
        ValueIteration valueIteration = new ValueIteration(maze);
        valueIteration.updateUtilities();

        for (int i=0; i < CONSTANTS.MAZE_NUM_COLS; i++){
            for (int j=0; j < CONSTANTS.MAZE_NUM_ROWS; j++){
                System.out.print(String.format("%22s", maze.tileStates[j][i].utility + "|"));
            }
            System.out.println();
        }

        for (int i=0; i < CONSTANTS.MAZE_NUM_COLS; i++){
            for (int j=0; j < CONSTANTS.MAZE_NUM_ROWS; j++){
                System.out.print(String.format("%22s", maze.tileStates[j][i].bestAction + "|"));
            }
            System.out.println();
        }
    }
}
