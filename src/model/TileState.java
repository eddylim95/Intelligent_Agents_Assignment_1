package model;

public class TileState {

    // Set default reward to a white tile reward
    public double reward = CONSTANTS.WHITE_TILE_REWARD;
    /**
     * Use setWall method to also set bestAction to NO_ACTION
     */
    public boolean wall = false;
    public double utility = 0;
    public Action bestAction;
    public Position position;

    public TileState(int col, int row){
        this.position = new Position(col, row);
        bestAction = Action.UP;
    }

    public void setWall(boolean wall) {
        this.wall = wall;
        if (wall){
            bestAction = Action.NO_ACTION;
        }
    }
}
