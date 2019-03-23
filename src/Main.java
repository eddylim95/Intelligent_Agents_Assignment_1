import model.CONSTANTS;
import model.Maze;
import helpers.Logger;
import sun.rmi.runtime.Log;

public class Main {
    public static void main(String[] args) {
        Maze maze1 = new Maze(CONSTANTS.MAZE_NUM_COLS, CONSTANTS.MAZE_NUM_ROWS);

        ValueIteration valueIteration = new ValueIteration(maze1);
        valueIteration.updateUtilities();
        maze1.printMazeState();

        // Log to csv to plot
        Logger.logUtilityChangesToCsv("ValueIterationUtilityChanges.csv");
        Logger.logPolicyStatesToCsv(maze1, "ValueIterationPolicy.csv");
        Logger.clearLogger();

        Maze maze2 = new Maze(CONSTANTS.MAZE_NUM_COLS, CONSTANTS.MAZE_NUM_ROWS);

        PolicyIteration policyIteration = new PolicyIteration(maze2);
        policyIteration.updatePolicies();
        maze2.printMazeState();

        // Log to csv to plot
        Logger.logUtilityChangesToCsv("PolicyIterationUtilityChanges.csv");
        Logger.logPolicyStatesToCsv(maze2, "PolicyIterationPolicy.csv");
        Logger.clearLogger();
    }
}
