public class Square {
    private boolean isSnake;
    private boolean isLadder;
    private int squareNumber;
    private Ladder ladder;
    private Snake snake;
    public Square(int squareNumber){
        this.isLadder = false;
        this.isSnake = false;
        this.squareNumber = squareNumber;
    }

    public boolean getIsSnake() {
        return isSnake;
    }
    public boolean getIsLadder() {
        return isLadder;
    }
    public void setLadder(int length){
        this.ladder = new Ladder(length,this.squareNumber);
    }
    public void setSnake(int length){
        this.snake = new Snake(length,this.squareNumber);
    }
}
