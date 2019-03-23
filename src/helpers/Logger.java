package helpers;

import model.CONSTANTS;
import model.Maze;
import model.TileState;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Logger {
    private static ArrayList<TileState[][]> allStateInfo = new ArrayList<>();

    public static void logStates(TileState[][] tileStates){
        TileState[][] deepCopy = new TileState[CONSTANTS.MAZE_NUM_COLS][CONSTANTS.MAZE_NUM_ROWS];
        for (int i = 0; i < CONSTANTS.MAZE_NUM_COLS; i++) {
            for (int j = 0; j < CONSTANTS.MAZE_NUM_ROWS; j++) {
                deepCopy[i][j] = new TileState(i, j);
                deepCopy[i][j].utility = tileStates[i][j].utility;
                deepCopy[i][j].bestAction = tileStates[i][j].bestAction;
                deepCopy[i][j].wall = tileStates[i][j].wall;
                deepCopy[i][j].reward = tileStates[i][j].reward;
            }
        }

        allStateInfo.add(deepCopy);
    }

    public static void logUtilityChangesToCsv(String logDir) {
        StringBuilder sb = new StringBuilder();
        initializeCsvHeaders(sb);

        for (TileState[][] states: allStateInfo){
            for (int i = 0; i < CONSTANTS.MAZE_NUM_COLS; i++) {
                for (int j = 0; j < CONSTANTS.MAZE_NUM_ROWS; j++) {
                    sb.append(states[j][i].utility);
                    sb.append(",");
                }
            }
            removeTrailingComma(sb);
            sb.append("\n");
        }

        writeToFile(sb.toString(), logDir);
    }

    public static void logPolicyStatesToCsv(Maze maze, String logDir){
        StringBuilder sb = new StringBuilder();
        initializeCsvHeaders(sb);

        for (int i=0; i < CONSTANTS.MAZE_NUM_COLS; i++){
            for (int j=0; j < CONSTANTS.MAZE_NUM_ROWS; j++){
                sb.append(maze.tileStates[j][i].bestAction);
                sb.append(",");
            }
        }
        removeTrailingComma(sb);

        writeToFile(sb.toString(), logDir);
    }

    /**
     * Clear state information from logger
     */
    public static void clearLogger(){
        allStateInfo = new ArrayList<>();
    }

    private static void writeToFile(String data, String logDir) {
        File file = new File(logDir);
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(data);
            writer.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Sets up csv header
     * @param sb String Builder
     */
    private static void initializeCsvHeaders(StringBuilder sb) {
        for (int i = 0; i < CONSTANTS.MAZE_NUM_COLS; i++) {
            for (int j = 0; j < CONSTANTS.MAZE_NUM_ROWS; j++) {
                sb.append("\"(");
                sb.append(j);
                sb.append(", ");
                sb.append(i);
                sb.append(")\",");
            }
        }
        removeTrailingComma(sb);
        sb.append("\n");
    }

    private static void removeTrailingComma(StringBuilder sb) {
        // remove trailing comma(,)
        if (sb.charAt(sb.length() - 1) == ',') {
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
