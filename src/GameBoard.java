public class GameBoard {
    final int BOARD_HEIGHT = 10;
    final int BOARD_WIDTH = 10;
    static final int BOARD_FIRST_SQUARE = 1;
    static final int BOARD_LAST_SQUARE = 100;
    private Square[] board;

    public GameBoard(){
        board = new Square[BOARD_HEIGHT*BOARD_WIDTH+1];
        for (int i=1;i<=BOARD_HEIGHT*BOARD_WIDTH+1;i++)
            board[i] = new Square(i);
    }

    public Square[] getBoard() {
        return board;
    }
}
