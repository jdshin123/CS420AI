import java.util.Scanner;

public class Board {
	Legal legal = new Legal();
	
	public String[][] usersTurn(String userMove, String[][] boardOutline, boolean mode) { //example e5
		String symbol;
		if(mode)
			symbol = "X";
		else
			symbol = "O";
		Scanner kb = new Scanner(System.in);	
		int holderRows = 0;
		int holderColumns = 0;
		do{
			for (int i = 0; i < boardOutline.length; i++){ //goes down the columns, a b c d
				if (userMove.toUpperCase().charAt(0) == (boardOutline[i][0].charAt(0))){ // if the E in e5 == a column letter
					holderColumns = i;
				}
			}
			for (int j = 0; j < boardOutline.length; j++){ //goes down the rows, 1 2 3 4
				if (userMove.charAt(1) == (boardOutline[0][j].charAt(0))){ // if the E in e5 == a column letter
					holderRows = j;
				}
			}
			if (legal.legalMove(holderColumns, holderRows, boardOutline) == false){
				System.out.print("Illegal Move! Please choose another move: ");
				userMove = kb.nextLine();
			}
		}while (legal.legalMove(holderColumns, holderRows, boardOutline) == false);
		boardOutline[holderColumns][holderRows] = symbol;
		return boardOutline;
	}
	
	public String[][] createBoard(String[][] board){
		String[] columns = {" ", "A", "B","C","D","E","F","G","H"};
		String[] rows = {" ", "1","2","3","4","5","6","7","8"};
		for (int i = 0; i < board.length; i++){
			board[i][0] = columns[i];
			for (int j = 1; j < board[0].length; j++){
				board[i][j] = rows[j];
				if (i > 0){
					board[i][j] = "-";
				}
			}
		}
		return board;
	}
	
	public void print(String[][] boardOutline){
		for (int i = 0; i < boardOutline.length; i++){
			for (int j = 0; j < boardOutline[0].length; j++){
				System.out.print(boardOutline[i][j]);					
			}
			System.out.println();
		}	
	}

}
