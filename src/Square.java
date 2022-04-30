public class Square {
    private boolean isSnake;
    private boolean isLadder;
    private int squareNumber;
    private Ladder ladder;
    private Snake snake;
    private int squareGoTo;

    /**
     * Square constructor, saves all the squared on the board.
     * It can contain ladder and snake but when creating a square during the game, there are
     * limitations which cause that there can only be one of them.
     * Those limitations are created using the booleans isLadder and isSnake.
     * squareGoTo represents the square which the snake or ladder (if exist) leads to.
     */
    public Square(int squareNumber) {
        this.isLadder = false;
        this.isSnake = false;
        this.squareNumber = squareNumber;
        this.squareGoTo = squareNumber;
    }

    public boolean getIsSnake() {
        return isSnake;
    }

    public boolean getIsLadder() {
        return isLadder;
    }

    /**
     * Create new ladder in the square given the length.
     * Sets the square's ladder status as true.
     * Updates the squareGoTo based on the ladder's length.
     * @param length the length of the ladder
     */
    public void setLadder(int length) {
        this.isLadder = true;
        this.ladder = new Ladder(length, this.squareNumber);
        this.squareGoTo += this.ladder.getLadderLength();
    }
    /**
     * Create new snake in the square given the length.
     * Sets the square's snake status as true.
     * Updates the squareGoTo based on the snake's length.
     * @param length the length of the snake
     */
    public void setSnake(int length) {
        this.isSnake = true;
        this.snake = new Snake(length, this.squareNumber);
        this.squareGoTo -= this.snake.getSnakeLength();
    }

    public int getSquareGoTo() {
        return squareGoTo;
    }
}
