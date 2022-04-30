public class Player {
    private String name;
    private GamePiece playerGamePiece;
    private int location;

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

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }
}
