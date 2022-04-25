public class Player {
    private String name;
    private GamePiece playerGamePiece;
    public Player(String name, Main.Color color){
        this.name = name;
        this.playerGamePiece = new GamePiece(color);

    }

    public String getName() {
        return name;
    }

    public Main.Color getColor() {
        return playerGamePiece.getColor();
    }
}
