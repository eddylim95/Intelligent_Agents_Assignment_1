public class Maze {
    public int numCols = 6;
    public int numRows = 6;
    public Tile[][] tiles;

    public Maze(int numCols, int numRows) {
        this.numCols = numCols;
        this.numRows = numRows;

        // Initialize tiles 3D array
        tiles = new Tile[numCols][numRows];
        for (var i=0; i < numCols; i++){
            for (var j=0; j < numRows; j++){
                tiles[i][j] = new Tile();
            }
        }
    }

    public double upActionUtility(int col, int row){
        /*
        Utility of the up action
         */
        // Check action if can be done, i.e. tiles exists and not a wall
        if (row !=0 && !tiles[col][row - 1].wall){
            return tiles[col][row - 1].utility;
        }
        return tiles[col][row].utility;
    }

    public double downActionUtility(int col, int row){
        /*
        Utility of the down action
         */
        // Check action if can be done, i.e. tiles exists and not a wall
        if (row < numRows - 1 && !tiles[col][row + 1].wall) {
            return tiles[col][row + 1].utility;
        }
        return tiles[col][row].utility;
    }

    public double leftActionUtility(int col, int row){
        /*
        Utility of the left action
         */
        // Check action if can be done, i.e. tiles exists and not a wall
        if (col !=0 && !tiles[col - 1][row].wall){
            return tiles[col - 1][row].utility;
        }
        return tiles[col][row].utility;
    }

    public double rightActionUtility(int col, int row){
        /*
        Utility of the right action
         */
        // Check action if can be done, i.e. tiles exists and not a wall
        if (col < numCols - 1 && !tiles[col + 1][row].wall){
            return tiles[col + 1][row].utility;
        }
        return tiles[col][row].utility;
    }
}
