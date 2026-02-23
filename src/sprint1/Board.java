package sprint1;

public class Board {
	private static Board myBoard;
	private Object[][] squares;
	private int length = 100;
	private int width = 60;
	
	private Board() {
		squares = new Object[length][width];
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < width; j++) {
				squares[i][j] = new Object();
			}
		}
	}
	
	public static Board getMyBoard() {
		if(myBoard == null) {
			myBoard = new Board();
		}
		
		return myBoard;
	}
	
	
}
