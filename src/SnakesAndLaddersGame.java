
public class SnakesAndLaddersGame {
    final int MAX_PLAYERS = 5;
    final int NO_LOCATION_TO_PLACE = -1;
    final int ADD_PLAYER_WORD_LENGTH = 11;
    final int ADD_LADDER_WORD_LENGTH = 11;
    final int ADD_SNAKE_WORD_LENGTH = 10;
    final int MIN_NUM_OF_PLAYERS = 2;
    private Die gameDie;
    private Player[] players = new Player[MAX_PLAYERS];
    private GameBoard gameBoard = new GameBoard();

    /**
     * SnakesAndLaddersGame constructor with parameters.
     *
     * @param min minimum die range.
     * @param max maximum die range.
     */
    public SnakesAndLaddersGame(int min, int max) {
        this.gameDie = new Die(min, max);
    }

    /**
     * SnakesAndLaddersGame constructor with default die parameters.
     */
    public SnakesAndLaddersGame() {
        this.gameDie = new Die();
    }

    /**
     * Scans from the user the players names and colors, the ladders and snakes.
     * Prints error messages, in according to given conditions.
     * Creates the players and updated the board with the snakes and ladders.
     * And finally, it arranges the players alphabetically.
     */
    public void initializeGame() {
        System.out.println("Initializing the game...");
        String userInput = Main.scanner.nextLine();
        int countPlayers = 0;

        // make sure that there are at least two players in the game.
        while (userInput.equals("end")) {
            System.out.println("Cannot start the game, there are less than two players!");
            userInput = Main.scanner.nextLine();
        }

        while (!userInput.equals("end")) {
            if (userInput.startsWith("add player ")) {
                // cuts the negligible beginning of the string.
                userInput = userInput.substring(ADD_PLAYER_WORD_LENGTH);

                // cuts the userInput using substring and sends to addplayer the given name and color
                // if the player is successfully added, then it updated the player's counter.
                if (addPlayer(userInput.substring(0, userInput.indexOf(" ")),
                        Color.valueOf(userInput.substring(userInput.indexOf(" ") + 1).toUpperCase()))) {
                    countPlayers++;
                }
            } else if (userInput.startsWith("add ladder ")) {
                // cuts the negligible beginning of the string.
                userInput = userInput.substring(ADD_LADDER_WORD_LENGTH);

                // cuts the userInput using substring and sends to addLadder the given length and location.
                addLadder(Integer.parseInt(userInput.substring(0, userInput.indexOf(" "))),
                        Integer.parseInt(userInput.substring(userInput.indexOf(" ") + 1)));
            } else if (userInput.startsWith("add snake ")) {
                // cuts the negligible beginning of the string
                userInput = userInput.substring(ADD_SNAKE_WORD_LENGTH);

                // cuts the userInput using substring and sends to addSnake the given length and location.
                addSnake(Integer.parseInt(userInput.substring(0, userInput.indexOf(" "))),
                        Integer.parseInt(userInput.substring(userInput.indexOf(" ") + 1)));
            }
            userInput = Main.scanner.nextLine();

            // make sure that there are at least two players in the game.
            while (userInput.equals("end") && countPlayers < MIN_NUM_OF_PLAYERS) {
                System.out.println("Cannot start the game, there are less than two players!");
                userInput = Main.scanner.nextLine();
            }
        }
        this.arrangePlayers();
    }

    /**
     * creates the player if the name or the color are not already taken,
     * and there are less the maximum player number.
     * Prints an error message correspondingly.
     *
     * @param name  the name of the player
     * @param color the color of the player
     * @return true if the player is created, and false otherwise.
     */
    private boolean addPlayer(String name, Color color) {
        int i = 0;
        int emptyLocation = NO_LOCATION_TO_PLACE;
        boolean nameTaken = false, colorTaken = false;
        // compares current player to existing players and sets the params nameTaken and color taken
        // if they are already exits.
        for (; i < MAX_PLAYERS; i++) {
            if (this.players[i] != null) {
                if (this.players[i].getName().equals(name))
                    nameTaken = true;
                if (this.players[i].getGamePiece().getColor() == color)
                    colorTaken = true;
            } else {
                // saves the empty location for putting the next player.
                emptyLocation = i;
                break;
            }

        }
        // creates the player only if the name and the color is not taken and there are less than 5 players.
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

    /**
     * creates the ladder if its within the game boundaries, its length is valid,
     * and the square is not taken.
     * Prints an error message correspondingly.
     *
     * @param length       the length of the ladder
     * @param squareNumber the square number of the ladder
     */
    private void addLadder(int length, int squareNumber) {
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

    /**
     * creates the snake if its within the game boundaries, its length is valid,
     * and the square is not taken.
     * Prints an error message correspondingly.
     *
     * @param length       the length of the snake
     * @param squareNumber the square number of the snake
     */
    private void addSnake(int length, int squareNumber) {
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

    /**
     * @return the active number of players in the current game.
     */
    public int numOfPlayers() {
        int i = 0;
        for (; i < MAX_PLAYERS; i++) {
            if (players[i] == null)
                break;
        }
        return i;
    }

    /**
     * Starts the game, runs the game rounds.
     * In each round, it rolls the dice for all the players and update its location.
     * Print messages according to the game progress.
     *
     * @return the name of the winning player.
     */
    public String start() {
        int round = 1;
        String winner = "";
        int roll;
        // loop runs until there is a winner.
        while (winner.equals("")) {
            System.out.println("------------------------- Round number " + round + " -------------------------");

            for (int i = 0; i < this.numOfPlayers(); i++) {
                roll = this.gameDie.Roll();
                if (updateMove(players[i], roll)) {
                    {
                        winner = players[i].getName();
                        break;
                    }
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

    /**
     * Updates the current player's move, according to the given conditions.
     * Checks and updates according to deviation from the game board or if it lands on snake or ladder.
     *
     * @return true if the current player wins, and false otherwise.
     */
    private boolean updateMove(Player player, int roll) {
        int currentLocation = player.getLocation();
        int newLocation = 0;

        // when the new location is bigger than the last square number then it returns the remain steps.
        if (roll + currentLocation > GameBoard.BOARD_LAST_SQUARE) {
            newLocation = (2 * GameBoard.BOARD_LAST_SQUARE) - (roll + currentLocation);
            // when the new location is lower than the first square number then it returns to the first square.
        } else if (roll + currentLocation < GameBoard.BOARD_FIRST_SQUARE) {
            newLocation = GameBoard.BOARD_FIRST_SQUARE;
        } else {
            newLocation = roll + currentLocation;
        }
        System.out.print(player.getName() + " rolled " + roll + ". The path to the next square: " + currentLocation + " -> " + newLocation);
        // updates the location if it lands on a ladder or a snake, until it doesn't land on either one.
        while (gameBoard.getBoard()[newLocation].getIsLadder() || gameBoard.getBoard()[newLocation].getIsSnake()) {
            newLocation = gameBoard.getBoard()[newLocation].getSquareGoTo();
            System.out.print(" -> " + newLocation);
        }
        System.out.println();
        player.setLocation(newLocation);
        return newLocation == GameBoard.BOARD_LAST_SQUARE;


    }

    /**
     * Arrange the players alphabetic order and updates the array.
     * Uses max sort algorithm, and swap function.
     */
    private void arrangePlayers() {
        int num = this.numOfPlayers();
        for (int i = num - 1; i > 0; i--) {
            swapPlayers(i, findMax(i));
        }
    }

    /**
     * Finds the alphabetic maximum string in a given array.
     *
     * @return the location of the alphabetic maximum string.
     */
    private int findMax(int end) {
        String max = this.players[0].getName();
        int location = 0;
        for (int i = 1; i <= end; i++) {
            if (this.players[i].getName().compareTo(max) > 0) {
                max = this.players[i].getName();
                location = i;
            }
        }
        return location;

    }

    /**
     * Swaps two player's places in an array.
     *
     * @param player1Location location in the array of the first player
     * @param player2Location location in the array of the second player
     */
    private void swapPlayers(int player1Location, int player2Location) {
        Player playerTemp = this.players[player1Location];
        this.players[player1Location] = this.players[player2Location];
        this.players[player2Location] = playerTemp;
    }
}
