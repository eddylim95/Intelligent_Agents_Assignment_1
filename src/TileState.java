import java.util.ArrayList;

public class TileState {

    // Set default reward to a white tile reward
    public double reward = CONSTANTS.WHITE_TILE_REWARD;
    public boolean wall = false;
    public double utility = 0;
    public ArrayList<Action> bestAction;
    public Position position;

    public TileState(int col, int row){
        this.position = new Position(col, row);
        bestAction = new ArrayList<>();
        bestAction.add(Action.NO_ACTION);
    }
}
