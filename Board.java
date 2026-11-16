public class Board {
	
	public static final int SIZE = 10; // board is 10 by 10
	private String[][] grid; // holds the board values
	private Ship[] ships;
	private int shipCount;
	
	// create empty board
	public Board() {
		grid = new String[SIZE][SIZE];
		ships = new Ship[5];
		shipCount = 0;
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				grid[row][col] = "-"; // - = water
			}
		}
	}
	
	public boolean placeShip(Ship ship) {
		int row = ship.getRow();
		int col = ship.getCol();
		int length = ship.getLength();
		boolean horiz = ship.isHorizontal();
		if (shipCount >= ships.length || row < 0 || row >= SIZE || col < 0 || col >= SIZE || length <= 0) {
			return false;
		}
		if (horiz && col + length > SIZE) {
			return false;
		}
		if (!horiz && row + length > SIZE) {
			return false;
		}

		for (int i = 0; i < length; i++) {
			if (horiz && !grid[row][col + i].equals("-")) {
				return false;
			}
			if (!horiz && !grid[row + i][col].equals("-")) {
				return false;
			}
		}

		String letter = ship.getName().substring(0, 1);
		for (int i = 0; i < length; i++) {
			if (horiz) {
				grid[row][col + i] = letter;
			} else {
				grid[row + i][col] = letter;
			}
		}
		ships[shipCount] = ship;
		shipCount++;
		return true;
	}
	
	public String fireAt(int row, int col) {
		if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
			return "invalid";
		}
		if (grid[row][col].equals("X") || grid[row][col].equals("O")) {
			return "already";
		}
		if (grid[row][col].equals("-")) {
			grid[row][col] = "O";
			return "miss";
		}
		grid[row][col] = "X";
		for (int i = 0; i < shipCount; i++) {
			Ship ship = ships[i];
			if (ship.isAt(row, col)) {
				ship.hitAt(row, col);
				if (ship.isSunk()) {
					return "sunk";
				}
				return "hit";
			}
		}
		return "hit";
	}

	public void recordShot(int row, int col, boolean hit) {
		if (hit) {
			grid[row][col] = "X";
		} else {
			grid[row][col] = "O";
		}
	}

	public boolean fleetSunk() {
		if (shipCount < ships.length) {
			return false;
		}
		for (int i = 0; i < shipCount; i++) {
			if (!ships[i].isSunk()) {
				return false;
			}
		}
		return true;
	}
	
	public void printBoard() { // there is probably a better way to do this, but this works for now
		System.out.print("  ");
		for (int col = 0; col < SIZE; col++) {
			System.out.print(col + " ");
		}
		System.out.println();
		for (int row = 0; row < SIZE; row++) {
			System.out.print(row + " ");
			for (int col = 0; col < SIZE; col++) {
				System.out.print(grid[row][col] + " ");
			}
			System.out.println(); // I wish I could do this with my head
		}
	}
	
}
