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
        promptForShip(playerBoard, "Carrier", 5);
        promptForShip(playerBoard, "Battleship", 4);
        promptForShip(playerBoard, "Cruiser", 3);
        promptForShip(playerBoard, "Submarine", 3);
        promptForShip(playerBoard, "Destroyer", 2);
        playerBoard.printBoard();
    }

    private void promptForShip(Board board, String name, int length) {
        int really = 0;
        while (true) {
            System.out.println("Place your " + name + " (length " + length + ").");
            System.out.print("Starting row (0-9): ");
            int row = scanner.nextInt();
            System.out.print("Starting column (0-9): ");
            int col = scanner.nextInt();
            System.out.print("Orientation (H/V): ");
            String orientation = scanner.next().toUpperCase();

            if (!orientation.equals("H") && !orientation.equals("V")) {
                really++;
                if (really == 1) {
                    System.out.println("That is not H or V");
                } else if (really == 2) {
                    System.out.println("Really? Please enter H or V");
                } else {
                    System.out.println("That's it, you are not allowed to play this game anymore");
                    System.exit(0);
                }
                continue;
            }

            really = 0;
            boolean horizontal = orientation.equals("H");
            Ship ship = new Ship(length, name, row, col, horizontal);
            if (board.placeShip(ship)) {
                return;
            }
            System.out.println("no");
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
