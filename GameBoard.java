public class GameBoard {
	
	private Board playerBoard;
	private Board trackerBoard;
	
	// creates both boards
	public GameBoard() {
		playerBoard = new Board();
		trackerBoard = new Board();
	}
	
	public Board getPlayerBoard() {
		return playerBoard;
	}
	
	public Board getTrackerBoard() {
		return trackerBoard;
	}
	
}
