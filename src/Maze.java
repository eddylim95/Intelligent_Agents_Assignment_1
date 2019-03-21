public class Maze {
    public int numCols;
    public int numRows;
    public TileState[][] tileStates;

    public Maze(int numCols, int numRows) {
        this.numCols = numCols;
        this.numRows = numRows;

        // Initialize tileStates 3D array
        tileStates = new TileState[numCols][numRows];
        for (int i=0; i < numCols; i++){
            for (int j=0; j < numRows; j++){
                tileStates[i][j] = new TileState(i, j);
            }
        }

        // Place walls in maze
        tileStates[1][0].wall = true;
        tileStates[4][1].wall = true;
        tileStates[1][4].wall = true;
        tileStates[2][4].wall = true;
        tileStates[3][4].wall = true;

        // Place green tile rewards in maze
        tileStates[0][0].reward = CONSTANTS.GREEN_TILE_REWARD;
        tileStates[2][0].reward = CONSTANTS.GREEN_TILE_REWARD;
        tileStates[5][0].reward = CONSTANTS.GREEN_TILE_REWARD;
        tileStates[3][1].reward = CONSTANTS.GREEN_TILE_REWARD;
        tileStates[4][2].reward = CONSTANTS.GREEN_TILE_REWARD;
        tileStates[5][3].reward = CONSTANTS.GREEN_TILE_REWARD;

        // Place brown tile rewards in maze
        tileStates[1][1].reward = CONSTANTS.BROWN_TILE_REWARD;
        tileStates[5][1].reward = CONSTANTS.BROWN_TILE_REWARD;
        tileStates[2][2].reward = CONSTANTS.BROWN_TILE_REWARD;
        tileStates[3][3].reward = CONSTANTS.BROWN_TILE_REWARD;
        tileStates[4][4].reward = CONSTANTS.BROWN_TILE_REWARD;
    }

    /**
     * Check if movement upward from position is possible.
     * @param position Position of agent
     * @return true if movement is possible
     */
    public boolean canMoveUp(Position position){
        return position.row != 0 && !tileStates[position.col][position.row - 1].wall;
    }

    /**
     * Check if movement downward from position is possible.
     * @param position Position of agent
     * @return true if movement is possible
     */
    public boolean canMoveDown(Position position){
        return position.row != numRows - 1 && !tileStates[position.col][position.row + 1].wall;
    }

    /**
     * Check if movement leftward from position is possible.
     * @param position Position of agent
     * @return true if movement is possible
     */
    public boolean canMoveLeft(Position position){
        return position.col !=0 && !tileStates[position.col - 1][position.row].wall;
    }

    /**
     * Check if movement rightward from position is possible.
     * @param position Position of agent
     * @return true if movement is possible
     */
    public boolean canMoveRight(Position position){
        return position.col != numCols - 1 && !tileStates[position.col + 1][position.row].wall;
    }
}
