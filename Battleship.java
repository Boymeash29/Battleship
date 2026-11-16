import java.util.Scanner;

public class Battleship {

    private GameBoard[] players;
    private Scanner scanner;
    private String[] shipNames = {"Carrier", "Battleship", "Cruiser", "Submarine", "Destroyer"};
    private int[] shipLengths = {5, 4, 3, 3, 2};

    public Battleship() {
        scanner = new Scanner(System.in);
        players = new GameBoard[2];
        players[0] = new GameBoard();
        players[1] = new GameBoard();
        startGame();
    }

    private void startGame() {
        System.out.println("battleship.");
        placeFleet(0);
        passScreen(2);
        placeFleet(1);

        int currentPlayer = 0;
        boolean gameOver = false;
        while (!gameOver) {
            passScreen(currentPlayer + 1);
            gameOver = takeTurn(currentPlayer);
            if (!gameOver) {
                currentPlayer = 1 - currentPlayer;
            }
        }
    }

    private void placeFleet(int playerNum) {
        Board board = players[playerNum].getPlayerBoard();
        System.out.println("player " + (playerNum + 1) + ": place ships.");

        for (int i = 0; i < shipNames.length; i++) {
            boolean placed = false;
            while (!placed) {
                System.out.println("place " + shipNames[i] + " (" + shipLengths[i] + ").");
                int row = readCoord("row (0-9): ");
                int col = readCoord("column (0-9): ");
                String orientation = readOrient();
                boolean horiz = orientation.equals("H");
                Ship ship = new Ship(shipLengths[i], shipNames[i], row, col, horiz);

                placed = board.placeShip(ship);
                if (!placed) {
                    System.out.println("nope. doesn't fit. try again.");
                }
            }

            System.out.println("your board:");
            board.printBoard();
        }
    }

    private int readCoord(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int coord = scanner.nextInt();
                scanner.nextLine();
                if (coord >= 0 && coord < Board.SIZE) {
                    return coord;
                }
            } else {
                scanner.nextLine();
            }
            System.out.println("number from 0 to " + (Board.SIZE - 1) + ", please.");
        }
    }

    private String readOrient() {
        while (true) {
            System.out.print("h or v: ");
            String orientation = scanner.nextLine().trim().toUpperCase();
            if (orientation.equals("H") || orientation.equals("V")) {
                return orientation;
            }
            System.out.println("h or v. try again.");
        }
    }

    private boolean takeTurn(int playerNum) {
        GameBoard current = players[playerNum];
        GameBoard other = players[1 - playerNum];

        while (true) {
            System.out.println("player " + (playerNum + 1) + ", go.");
            System.out.println("your shots:");
            current.getTrackerBoard().printBoard();
            System.out.println("your ships:");
            current.getPlayerBoard().printBoard();

            int row = readCoord("target row (0-9): ");
            int col = readCoord("target column (0-9): ");
            String result = other.getPlayerBoard().fireAt(row, col);

            if (result.equals("already")) {
                System.out.println("already shot there. pick another.");
                continue;
            }

            boolean hit = !result.equals("miss");
            current.getTrackerBoard().recordShot(row, col, hit);
            if (result.equals("miss")) {
                System.out.println("miss.");
            } else if (result.equals("sunk")) {
                System.out.println("sank one.");
            } else {
                System.out.println("hit.");
            }

            if (other.getPlayerBoard().fleetSunk()) {
                System.out.println("player " + (playerNum + 1) + " wins. gg.");
                return true;
            }
            return false;
        }
    }

    private void passScreen(int playerNum) {
        System.out.println("player " + playerNum + ", you're up.");
        System.out.println("other player, look away. press enter.");
        scanner.nextLine();
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }
}
