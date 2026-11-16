import java.util.Scanner;

public class Battleship {

    private GameBoard gameBoard;
    private Scanner scanner;

    public Battleship() {
        gameBoard = new GameBoard();
        scanner = new Scanner(System.in);
        startGame();
    }

    private void startGame() {
        placeShips();
    }

    private void placeShips() {
        Board playerBoard = gameBoard.getPlayerBoard();
        Ship carrier = promptForShip("Carrier", 5);
        Ship battleship = promptForShip("Battleship", 4);
        Ship cruiser = promptForShip("Cruiser", 3);
        Ship submarine = promptForShip("Submarine", 3);
        Ship destroyer = promptForShip("Destroyer", 2);

        playerBoard.placeShip(carrier);
        playerBoard.placeShip(battleship);
        playerBoard.placeShip(cruiser);
        playerBoard.placeShip(submarine);
        playerBoard.placeShip(destroyer);
        playerBoard.printBoard();
    }

    private Ship promptForShip(String name, int length) {
        while (true) {
            System.out.println("Place your " + name + " (length " + length + ").");
            System.out.print("Starting row (0-9): ");
            int row = scanner.nextInt();
            System.out.print("Starting column (0-9): ");
            int col = scanner.nextInt();
            System.out.print("Orientation (H/V): ");
            String orientation = scanner.next().toUpperCase();

            boolean horizontal = orientation.equals("H");
            boolean vertical = orientation.equals("V");
            // Check if the ship fits within the board boundaries *this took almost an hour lol (did ask for help from dad)
            boolean fits = row >= 0 && row < Board.SIZE && col >= 0 && col < Board.SIZE && ((horizontal && col + length <= Board.SIZE) || (vertical && row + length <= Board.SIZE));

            if (fits) {
                return new Ship(length, name, row, col, horizontal);
            }

            System.out.println("Out of bounds, try again");
        }
    }

    private void takeTurn() {
        // TODO: add logic for taking a turn in the game
    }

    private void checkForWinner() {
        // TODO: add logic to check if there is a winner
    }

    private void endGame() {
        // TODO: add logic to end the game
    }
	
}
