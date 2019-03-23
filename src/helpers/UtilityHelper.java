package helpers;

import model.*;

import java.util.ArrayList;

public class UtilityHelper {
    /**
     * Obtain the max expected utility of the different actions of up, down, left, right
     * @param maze maze
     * @param position position of tileState
     * @return Max expected utility
     */
    public static double maxActionExpectedUtility(Maze maze, Position position){
        double up = upActionExpectedUtility(maze, position);
        double down = downActionExpectedUtility(maze, position);
        double left = leftActionExpectedUtility(maze, position);
        double right = rightActionExpectedUtility(maze, position);

        return Math.max(Math.max(up, down), Math.max(left, right));
    }

    /**
     * Sets best model.Action of tileState
     * @param maze maze
     * @param tileState position of tileState
     */
    public static void setBestAction(Maze maze, TileState tileState) {
        double up = upActionExpectedUtility(maze, tileState.position);
        double down = downActionExpectedUtility(maze, tileState.position);
        double left = leftActionExpectedUtility(maze, tileState.position);
        double right = rightActionExpectedUtility(maze, tileState.position);

        if (up >= down && up >= left && up >= right)
            tileState.bestAction = Action.UP;
        else if (down >= up && down >= left && down >= right)
            tileState.bestAction = Action.DOWN;
        else if (left >= up && left >= down && left >= right)
            tileState.bestAction = Action.LEFT;
        else if (right >= up && right >= down && right >= left)
            tileState.bestAction = Action.RIGHT;
    }

    /**
     * Gets the expected utility according to action specified
     * @param action Action
     * @param maze maze
     * @param tileStatePosition position of tileState
     * @return
     */
    public static double getActionExpectedUtility(Action action, Maze maze, Position tileStatePosition){
        if (action == Action.UP)
            return upActionExpectedUtility(maze, tileStatePosition);
        else if (action == Action.DOWN)
            return downActionExpectedUtility(maze, tileStatePosition);
        else if (action == Action.LEFT)
            return leftActionExpectedUtility(maze, tileStatePosition);
        else if (action == Action.RIGHT)
            return rightActionExpectedUtility(maze, tileStatePosition);
        return 0;
    }

    /**
     * Expected utility of the up action according to action probabilities
     * @param maze maze
     * @param tileStatePosition position of tileState
     * @return expected utility of action
     */
    private static double upActionExpectedUtility(Maze maze, Position tileStatePosition){
        return CONSTANTS.CORRECT_ACTION_PROBABILITY * upActionUtility(maze, tileStatePosition) +
                CONSTANTS.WRONG_ACTION_PROBABILITY * leftActionUtility(maze, tileStatePosition) +
                CONSTANTS.WRONG_ACTION_PROBABILITY * rightActionUtility(maze, tileStatePosition);
    }

    /**
     * Expected utility of the down action according to action probabilities
     * @param maze maze
     * @param tileStatePosition position of tileState
     * @return expected utility of action
     */
    private static double downActionExpectedUtility(Maze maze, Position tileStatePosition){
        return CONSTANTS.CORRECT_ACTION_PROBABILITY * downActionUtility(maze, tileStatePosition) +
                CONSTANTS.WRONG_ACTION_PROBABILITY * leftActionUtility(maze, tileStatePosition) +
                CONSTANTS.WRONG_ACTION_PROBABILITY * rightActionUtility(maze, tileStatePosition);
    }

    /**
     * Expected utility of the left action according to action probabilities
     * @param maze maze
     * @param tileStatePosition position of tileState
     * @return expected utility of action
     */
    private static double leftActionExpectedUtility(Maze maze, Position tileStatePosition){
        return CONSTANTS.CORRECT_ACTION_PROBABILITY * leftActionUtility(maze, tileStatePosition) +
                CONSTANTS.WRONG_ACTION_PROBABILITY * upActionUtility(maze, tileStatePosition) +
                CONSTANTS.WRONG_ACTION_PROBABILITY * downActionUtility(maze, tileStatePosition);
    }

    /**
     * Expected utility of the right action according to action probabilities
     * @param maze maze
     * @param tileStatePosition position of tileState
     * @return expected utility of action
     */
    private static double rightActionExpectedUtility(Maze maze, Position tileStatePosition){
        return CONSTANTS.CORRECT_ACTION_PROBABILITY * rightActionUtility(maze, tileStatePosition) +
                CONSTANTS.WRONG_ACTION_PROBABILITY * upActionUtility(maze, tileStatePosition) +
                CONSTANTS.WRONG_ACTION_PROBABILITY * downActionUtility(maze, tileStatePosition);
    }

    /**
     * Obtain the Utility of a up action
     * @param maze maze
     * @param position position of tileState
     * @return utility of action
     */
    private static double upActionUtility(Maze maze, Position position){
        /*
        Utility of the up action
         */
        // Check action if can be done, i.e. tileStates exists and not a wall
        if (maze.canMoveUp(position)){
            return maze.tileStates[position.col][position.row - 1].utility;
        }
        return maze.tileStates[position.col][position.row].utility;
    }

    /**
     * Obtain the Utility of a down action
     * @param maze maze
     * @param position position of tileState
     * @return utility of action
     */
    private static double downActionUtility(Maze maze, Position position){
        /*
        Utility of the down action
         */
        // Check action if can be done, i.e. tileStates exists and not a wall
        if (maze.canMoveDown(position)) {
            return maze.tileStates[position.col][position.row + 1].utility;
        }
        return maze.tileStates[position.col][position.row].utility;
    }

    /**
     * Obtain the Utility of a left action
     * @param maze maze
     * @param position position of tileState
     * @return utility of action
     */
    private static double leftActionUtility(Maze maze, Position position){
        /*
        Utility of the left action
         */
        // Check action if can be done, i.e. tileStates exists and not a wall
        if (maze.canMoveLeft(position)){
            return maze.tileStates[position.col - 1][position.row].utility;
        }
        return maze.tileStates[position.col][position.row].utility;
    }

    /**
     * Obtain the Utility of a right action
     * @param maze maze
     * @param position position of tileState
     * @return utility of action
     */
    private static double rightActionUtility(Maze maze, Position position){
        /*
        Utility of the right action
         */
        // Check action if can be done, i.e. tileStates exists and not a wall
        if (maze.canMoveRight(position)){
            return maze.tileStates[position.col + 1][position.row].utility;
        }
        return maze.tileStates[position.col][position.row].utility;
    }
}
