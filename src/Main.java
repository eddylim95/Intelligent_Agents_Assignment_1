import model.CONSTANTS;
import model.Maze;
import helpers.Logger;

public class Main {
    public static void main(String[] args) {
        // Value Iteration
        Maze maze1 = new Maze(CONSTANTS.MAZE_NUM_COLS, CONSTANTS.MAZE_NUM_ROWS);

        ValueIteration valueIteration = new ValueIteration(maze1);
        valueIteration.updateUtilities();
        maze1.printMazeState();

        // Log to csv to plot
        Logger.logUtilityChangesToCsv("ValueIterationUtilityChanges.csv");
        Logger.logPolicyStatesToCsv(maze1, "ValueIterationPolicy.csv");
        Logger.clearLogger();

        // Policy Iteration
        Maze maze2 = new Maze(CONSTANTS.MAZE_NUM_COLS, CONSTANTS.MAZE_NUM_ROWS);

        PolicyIteration policyIteration = new PolicyIteration(maze2);
        policyIteration.updatePolicies();
        maze2.printMazeState();

        // Log to csv to plot
        Logger.logUtilityChangesToCsv("PolicyIterationUtilityChanges.csv");
        Logger.logPolicyStatesToCsv(maze2, "PolicyIterationPolicy.csv");
        Logger.clearLogger();

        // More states
        Maze maze3 = new Maze(10, 10);

        ValueIteration valueIteration1 = new ValueIteration(maze3);
        valueIteration1.updateUtilities();
        maze3.printMazeState();
        Logger.clearLogger();

        // More states
        Maze maze4 = new Maze(10, 10);

        PolicyIteration policyIteration1 = new PolicyIteration(maze4);
        policyIteration1.updatePolicies();
        maze4.printMazeState();
        Logger.clearLogger();

        // More complex
        Maze maze5 = new Maze(CONSTANTS.MAZE_NUM_COLS, CONSTANTS.MAZE_NUM_ROWS);
        maze5.tileStates[1][0].reward = CONSTANTS.GREEN_TILE_REWARD;
        maze5.tileStates[5][2].reward = CONSTANTS.GREEN_TILE_REWARD;
        maze5.tileStates[2][1].reward = CONSTANTS.BROWN_TILE_REWARD;
        maze5.tileStates[3][2].reward = CONSTANTS.BROWN_TILE_REWARD;
        maze5.tileStates[4][3].setWall(true);
        ValueIteration valueIteration2 = new ValueIteration(maze5);
        valueIteration2.updateUtilities();
        maze5.printMazeState();
        Logger.clearLogger();

        // More complex
        Maze maze6 = new Maze(CONSTANTS.MAZE_NUM_COLS, CONSTANTS.MAZE_NUM_ROWS);
        maze6.tileStates[1][0].reward = CONSTANTS.GREEN_TILE_REWARD;
        maze6.tileStates[5][2].reward = CONSTANTS.GREEN_TILE_REWARD;
        maze6.tileStates[2][1].reward = CONSTANTS.BROWN_TILE_REWARD;
        maze6.tileStates[3][2].reward = CONSTANTS.BROWN_TILE_REWARD;
        maze6.tileStates[4][3].setWall(true);
        PolicyIteration policyIteration2 = new PolicyIteration(maze6);
        policyIteration2.updatePolicies();
        maze6.printMazeState();
        Logger.clearLogger();

    }
}
