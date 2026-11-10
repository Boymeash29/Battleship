public class Ship {
	
	private int length;
	private String name;
	private int posRow;
	private int posCol;
	private boolean isHoriz;
	private boolean[] hits;
	
	public Ship(int len, String na, int row, int col, boolean rot) {
		int lenght = len;
		String name = na;
		int posRow = row;
		int posCol = col;
		boolean isHoriz = rot;
		boolean[] hits = new boolean[len];
	}
	
	public int getRow() {
		
	}
	
	public int getCol() {
		
	}
	
	public String toString() {
		return ("Name: " + name);
	}
}
