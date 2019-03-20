public class ValueIteration {
    private double correctActionProbability = 0.8;
    private double wrongActionProbability = 0.1;

    public double evaluate(Maze maze, double actions, double transitionModel, double reward,
                         double discountFactor, double maxUtilityError){
        /*
        Perform value iteration algorithm
         */
        var currentUtility = 0.0;
        var nextUtility = 0.0;
        var maxStateUtilityChange = 0.0;

        while(maxStateUtilityChange < maxUtilityError*(1-discountFactor)/discountFactor) {
            currentUtility = nextUtility;
            maxStateUtilityChange = 0;
            for (var i = 0; i < maze.numCols; i++) {
                for (var j = 0; j < maze.numRows; j++) {
                    bellmanUpdate(maze, i, j, discountFactor);
                    var whatever = Math.abs(maze.tiles[i][j].utility - maze.tiles[i][j].utility);
                    if (whatever > maxStateUtilityChange){
                        maxStateUtilityChange = whatever;
                    }
                }
            }
        }
        return currentUtility;
    }

    private void bellmanUpdate(Maze maze, int currentCol, int currentRow, double discountFactor){
        /*
        updates the utility of current tile
         */
        var currentTile = maze.tiles[currentCol][currentRow];
        currentTile.utility = currentTile.reward + discountFactor * maxExpectedUtility(
                upExpectedUtility(maze, currentCol, currentRow), downExpectedUtility(maze, currentCol, currentRow),
                leftExpectedUtility(maze, currentCol, currentRow), rightExpectedUtility(maze, currentCol, currentRow));
    }

    private double maxExpectedUtility(double up, double down, double left, double right){
        /*
        Max expected utility of the different actions of up, down, left, right
         */
        return Math.max(Math.max(up, down), Math.max(left, right));
    }

    private double upExpectedUtility(Maze maze, int currentCol, int currentRow){
        /*
        Expected Utility of the up action according to action probabilities
         */
        return correctActionProbability * maze.upActionUtility(currentCol, currentRow) +
                wrongActionProbability * maze.leftActionUtility(currentCol, currentRow) +
                wrongActionProbability * maze.rightActionUtility(currentCol, currentRow);
    }

    private double downExpectedUtility(Maze maze, int currentCol, int currentRow){
        /*
        Expected Utility of the down action according to action probabilities
         */
        return correctActionProbability * maze.downActionUtility(currentCol, currentRow) +
                wrongActionProbability * maze.leftActionUtility(currentCol, currentRow) +
                wrongActionProbability * maze.rightActionUtility(currentCol, currentRow);
    }

    private double leftExpectedUtility(Maze maze, int currentCol, int currentRow){
        /*
        Expected Utility of the left action according to action probabilities
         */
        return correctActionProbability * maze.leftActionUtility(currentCol, currentRow) +
                wrongActionProbability * maze.upActionUtility(currentCol, currentRow) +
                wrongActionProbability * maze.downActionUtility(currentCol, currentRow);
    }

    private double rightExpectedUtility(Maze maze, int currentCol, int currentRow){
        /*
        Expected Utility of the right action according to action probabilities
         */
        return correctActionProbability * maze.rightActionUtility(currentCol, currentRow) +
                wrongActionProbability * maze.upActionUtility(currentCol, currentRow) +
                wrongActionProbability * maze.downActionUtility(currentCol, currentRow);
    }
}
