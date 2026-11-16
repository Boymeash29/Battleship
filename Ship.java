public class Ship {
	
	private int length;
	private String name;
	private int posRow;
	private int posCol;
	private boolean isHoriz;
	private boolean[] hits;
	
	public Ship(int len, String na, int row, int col, boolean rot) {
		length = len;
		name = na;
		posRow = row;
		posCol = col;
		isHoriz = rot;
		hits = new boolean[len];
	}
	
	public int getRow() {
		return posRow;
	}
	
	public int getCol() {
		return posCol;
	}
	
	public int getLength() {
		return length;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isHorizontal() {
		return isHoriz;
	}

	public boolean isAt(int row, int col) {
		if (isHoriz) {
			return row == posRow && col >= posCol && col < posCol + length;
		}
		return col == posCol && row >= posRow && row < posRow + length;
	}

	public void hitAt(int row, int col) {
		if (isAt(row, col)) {
			int index;
			if (isHoriz) {
				index = col - posCol;
			} else {
				index = row - posRow;
			}
			hit(index);
		}
	}
	
	public void hit(int index) {
		if (index >= 0 && index < hits.length) {
			hits[index] = true;
		}
	}
	
	public boolean isHit(int index) {
		if (index >= 0 && index < hits.length) { // Check if the index is valid
			return hits[index]; // Return status
		}
		return false;
	}
	
	public boolean isSunk() {
		for (int i = 0; i < hits.length; i++) {
			if (!hits[i]) {
				return false;
			}
		}
		return true;
	}
	
	public String toString() {
		return "Name: " + name;
	}
}
