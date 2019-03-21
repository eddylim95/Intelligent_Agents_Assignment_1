import java.util.ArrayList;

public class Agent {
    private Maze maze;
    private Position position;
    private ArrayList<Position> moveSequence = new ArrayList<>();

    public Agent(Maze maze, int col, int row){
        this.maze = maze;
        this.position = new Position(col, row);
    }

    /**
     * Action to move agent up
     */
    public void moveUp() {
        if (maze.canMoveUp(position)){
            position.col--;
        }
        moveSequence.add(position);
    }

    /**
     * Action to move agent down
     */
    public void moveDown() {
        if (maze.canMoveDown(position)){
            position.col++;
        }
        moveSequence.add(position);
    }

    /**
     * Action to move agent left
     */
    public void moveLeft() {
        if (maze.canMoveLeft(position)){
            position.row--;
        }
        moveSequence.add(position);
    }

    /**
     * Action to move agent right
     */
    public void moveRight() {
        if (maze.canMoveRight(position)){
            position.row++;
        }
        moveSequence.add(position);
    }
}
