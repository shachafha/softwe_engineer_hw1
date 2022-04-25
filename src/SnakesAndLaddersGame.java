public class SnakesAndLaddersGame {
    final int MAX_PLAYERS = 5;
    final int NO_LOCATION_TO_PLACE = -1;
    private Die gameDie;
    private Player[] players = new Player[MAX_PLAYERS];
    public SnakesAndLaddersGame(int min, int max){
        this.gameDie = new Die(min,max);
    }
    public SnakesAndLaddersGame(){
        this.gameDie = new Die();
    }
    public void initializeGame(String[] names,Main.Color[] colors, int[] snakes, int[] ladders){

    }
    public void addPlayer(String name, Main.Color color){
        int i=0;
        int emmptyLocation = NO_LOCATION_TO_PLACE;
        boolean nameTaken = false, colorTaken = false;
        for(;i<MAX_PLAYERS;i++) {
            if (this.players[i] != null) {
                if (this.players[i].getName().equals(name))
                    nameTaken = true;
                if (this.players[i].getColor() == color)
                    colorTaken = true;
            } else
                emmptyLocation = i;

        }
        if (emmptyLocation != NO_LOCATION_TO_PLACE && !nameTaken && !colorTaken){
            this.players[emmptyLocation] = new Player(name,color);
            return;
        }
        if (emmptyLocation == NO_LOCATION_TO_PLACE)
            System.out.println("The maximal number of players is five !");
        if (nameTaken && colorTaken)
            System.out.println("The name and the color are already taken!");
        else if (nameTaken)
            System.out.println("The name is already taken!");
        else if (colorTaken)
            System.out.println("The color is already taken!");

    }

    public void addLadder()
}
