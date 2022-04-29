public class Square {
    private boolean isSnake;
    private boolean isLadder;
    private int squareNumber;
    private Ladder ladder;
    private Snake snake;
    private int squareGoTo;

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

    public void setLadder(int length) {
        this.isLadder = true;
        this.ladder = new Ladder(length, this.squareNumber);
        this.squareGoTo += length;
    }

    public void setSnake(int length) {
        this.isSnake = true;
        this.snake = new Snake(length, this.squareNumber);
        this.squareGoTo -= length;
    }

    public int getSquareGoTo() {
        return squareGoTo;
    }
}
