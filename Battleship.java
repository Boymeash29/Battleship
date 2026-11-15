public class Battleship {
	
	// main method starts the game
	public static void main(String[] args) {
		GameBoard game = new GameBoard();
		
		System.out.println("Player Board");
		game.getPlayerBoard().printBoard();
		System.out.println();
		System.out.println("Tracking Board");
		game.getTrackerBoard().printBoard();
	}
	
}
