public class Player {
    private String name;
    private GamePiece playerGamePiece;
    private int location;

    /**
     * Player constructor, sets the name and the color of game piece.
     * Sets the player's location to 1 (the first square on board).
     *
     * @param name  player's name.
     * @param color the game piece color of the player.
     */
    public Player(String name, Color color) {
        this.name = name;
        this.playerGamePiece = new GamePiece(color);
        this.location = 1;

    }

    public String getName() {
        return name;
    }

    public GamePiece getGamePiece() {
        return playerGamePiece;
    }

    /**
     * @return the location of the player on the board.
     */
    public int getLocation() {
        return location;
    }

    /**
     * Set the location of the player on the board.
     */
    public void setLocation(int location) {
        this.location = location;
    }
}
