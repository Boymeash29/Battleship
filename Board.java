public class Board {
	
	// TODO: add different ships
	public static final int SIZE = 10; // board is 10 by 10
	private String[][] grid; // holds the board values
	
	// create empty board
	public Board() {
		grid = new String[SIZE][SIZE];
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				grid[row][col] = "-"; // w = water
			}
		}
	}
	
	public void placeShip(Ship ship) {
		int row = ship.getRow();
		int col = ship.getCol();
		int length = ship.getLength();
		String letter = ship.getName().substring(0, 1); // first letter of ship name **would it be more memory efficient to make a method for this in ship?**
		
		if (ship.isHorizontal()) {
			for (int i = 0; i < length; i++) {
				grid[row][col + i] = letter; // place ship horizontally
			}
		} else {
			for (int i = 0; i < length; i++) {
				grid[row + i][col] = letter; // place ship vertically
			}
		}
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
			System.out.println();
		}
	}
	
}
