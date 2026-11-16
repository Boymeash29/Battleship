public class Board {
	
	public static final int SIZE = 10; // board is 10 by 10
	private String[][] grid; // holds the board values
	
	// create empty board
	public Board() {
		grid = new String[SIZE][SIZE];
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
		boolean horizontal = ship.isHorizontal();
		if (row < 0 || row >= SIZE || col < 0 || col >= SIZE || length <= 0) {
			return false;
		}
		if (horizontal && col + length > SIZE) {
			return false;
		}
		if (!horizontal && row + length > SIZE) {
			return false;
		}

		for (int i = 0; i < length; i++) {
			if (horizontal && !grid[row][col + i].equals("-")) {
				return false;
			}
			if (!horizontal && !grid[row + i][col].equals("-")) {
				return false;
			}
		}

		String letter = ship.getName().substring(0, 1);
		for (int i = 0; i < length; i++) {
			if (horizontal) {
				grid[row][col + i] = letter;
			} else {
				grid[row + i][col] = letter;
			}
		}
		return true;
	}
	
	public String fireAt(int row, int col) {
		if (grid[row][col].equals("-")) {
			grid[row][col] = "O";
			return "miss";
		}
		grid[row][col] = "X";
		return "hit";
	}
	
	public void printBoard() { // there is probably a better way to do this, but this works for now
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				System.out.print(grid[row][col] + " ");
			}
			System.out.println(); // I wish I could do this with my head
		}
	}
	
}
