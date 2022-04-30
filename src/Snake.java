public class Snake {
    private int snakeLength;
    private int snakeSquareNumber;

    /**
     * Snake constructor.
     */
    public Snake(int snakeLength, int snakeSquareNumber) {
        this.snakeLength = snakeLength;
        this.snakeSquareNumber = snakeSquareNumber;
    }

    public int getSnakeLength() {
        return snakeLength;
    }
}
