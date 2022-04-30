
public class SnakesAndLaddersGame {
    final int MAX_PLAYERS = 5;
    final int NO_LOCATION_TO_PLACE = -1;
    private Die gameDie;
    private Player[] players = new Player[MAX_PLAYERS];
    private GameBoard gameBoard = new GameBoard();

    public SnakesAndLaddersGame(int min, int max) {
        this.gameDie = new Die(min, max);
    }

    public SnakesAndLaddersGame() {
        this.gameDie = new Die();
    }

    public void initializeGame() {
        System.out.println("Initializing the game...");
        String userInput = Main.scanner.nextLine();
        int countPlayers = 0;
        while (userInput.equals("end")) {
            System.out.println("Cannot start the game, there are less than two players!");
            userInput = Main.scanner.nextLine();
        }
        while (!userInput.equals("end")) {
            if (userInput.startsWith("add player ")) {
                userInput = userInput.substring(11);
                if (addPlayer(userInput.substring(0, userInput.indexOf(" ")),
                        Color.valueOf(userInput.substring(userInput.indexOf(" ") + 1).toUpperCase()))) {
                    countPlayers++;
                }
            } else if (userInput.startsWith("add ladder ")) {
                userInput = userInput.substring(11);
                addLadder(Integer.parseInt(userInput.substring(0, userInput.indexOf(" "))),
                        Integer.parseInt(userInput.substring(userInput.indexOf(" ") + 1)));
            } else if (userInput.startsWith("add snake ")) {
                userInput = userInput.substring(10);
                addSnake(Integer.parseInt(userInput.substring(0, userInput.indexOf(" "))),
                        Integer.parseInt(userInput.substring(userInput.indexOf(" ") + 1)));
            }
            userInput = Main.scanner.nextLine();
            while (userInput.equals("end") && countPlayers < 2) {
                System.out.println("Cannot start the game, there are less than two players!");
                userInput = Main.scanner.nextLine();
            }
        }

    }

    public boolean addPlayer(String name, Color color) {
        int i = 0;
        int emptyLocation = NO_LOCATION_TO_PLACE;
        boolean nameTaken = false, colorTaken = false;
        for (; i < MAX_PLAYERS; i++) {
            if (this.players[i] != null) {
                if (this.players[i].getName().equals(name))
                    nameTaken = true;
                if (this.players[i].getGamePiece().getColor() == color)
                    colorTaken = true;
            } else {
                emptyLocation = i;
                break;
            }

        }
        if (emptyLocation != NO_LOCATION_TO_PLACE && !nameTaken && !colorTaken) {
            this.players[emptyLocation] = new Player(name, color);
            return true;
        }
        if (emptyLocation == NO_LOCATION_TO_PLACE)
            System.out.println("The maximal number of players is five !");
        if (nameTaken && colorTaken)
            System.out.println("The name and the color are already taken!");
        else if (nameTaken)
            System.out.println("The name is already taken!");
        else if (colorTaken)
            System.out.println("The color is already taken!");
        return false;

    }

    public void addLadder(int length, int squareNumber) {
        if (squareNumber > GameBoard.BOARD_LAST_SQUARE || squareNumber < GameBoard.BOARD_FIRST_SQUARE) {
            System.out.println("The square is not within the board's boundaries!");
        } else if (squareNumber + length > GameBoard.BOARD_LAST_SQUARE) {
            System.out.println("The ladder is too long!");
        } else if (gameBoard.getBoard()[squareNumber].getIsLadder()) {
            System.out.println("This square already contains a bottom of a ladder!");
        } else if (gameBoard.getBoard()[squareNumber].getIsSnake()) {
            System.out.println("This square contains a head of a snake!");
        } else {
            gameBoard.getBoard()[squareNumber].setLadder(length);
        }

    }

    public void addSnake(int length, int squareNumber) {
        if (squareNumber > GameBoard.BOARD_LAST_SQUARE || squareNumber < GameBoard.BOARD_FIRST_SQUARE) {
            System.out.println("The square is not within the board's boundaries!");
        } else if (squareNumber == GameBoard.BOARD_LAST_SQUARE) {
            System.out.println("You cannot add a snake in the last square!");
        } else if (squareNumber - length < GameBoard.BOARD_FIRST_SQUARE) {
            System.out.println("The snake is too long!");
        } else if (gameBoard.getBoard()[squareNumber].getIsSnake()) {
            System.out.println("This square already contains a head of a snake !");
        } else if (gameBoard.getBoard()[squareNumber].getIsLadder()) {
            System.out.println("This square contains a bottom of a ladder !");
        } else {
            gameBoard.getBoard()[squareNumber].setSnake(length);
        }

    }

    public int numOfPlayers() {
        int i = 0;
        for (; i < MAX_PLAYERS; i++) {
            if (players[i] == null)
                break;
        }
        return i;
    }

    public String start() {
        int round = 1;
        String winner = "";
        int roll;
        while (winner.equals("")) {
            System.out.println("------------------------- Round number " + round + " -------------------------");

            for (int i = 0; i < this.numOfPlayers(); i++) {
                roll = this.gameDie.Roll();
                if (updateMove(players[i], roll)) {
                    winner = players[i].getName();
                }
            }
            System.out.println("\nPlayers positions on the board:");
            for (int i = 0; i < this.numOfPlayers(); i++) {
                System.out.println(players[i].getName() + " is in square number " + players[i].getLocation());

            }
            round++;
        }
        return winner;

    }

    public boolean updateMove(Player player, int roll) {
        int currentLocation = player.getLocation();
        int newLocation = 0;
        if (roll + currentLocation > GameBoard.BOARD_LAST_SQUARE) {
            newLocation = (2 * GameBoard.BOARD_LAST_SQUARE) - (roll + currentLocation);
        } else if (roll + currentLocation < GameBoard.BOARD_FIRST_SQUARE) {
            newLocation = GameBoard.BOARD_FIRST_SQUARE;
        } else {
            newLocation = roll + currentLocation;
        }
        System.out.print(player.getName() + " rolled " + roll + ". The path to the next square: " + currentLocation+" -> " + newLocation);
        while (gameBoard.getBoard()[newLocation].getIsLadder() || gameBoard.getBoard()[newLocation].getIsSnake()) {
            newLocation = gameBoard.getBoard()[newLocation].getSquareGoTo();
            System.out.print(" -> " + newLocation);
        }
        System.out.println();
        player.setLocation(newLocation);
        return newLocation == GameBoard.BOARD_LAST_SQUARE;


    }
}
