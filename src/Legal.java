
public class Legal {
	public boolean connectFour(String[][] boardOutline) {
		int human = 1;
		int ai = 1;
		for (int i = 0; i < boardOutline.length; i++){ 
			for (int j = 0; j < boardOutline.length; j++){
				if (boardOutline[i][j] == "X"){ //Checks if the player has four of a kind
					int checkingRight = j + 1;
					try{ //Tries and catches if index out of bounds rightwards
						while (boardOutline[i][checkingRight] == "X"){ //checks to the right for X
							human += 1;
							if (human == 4){
								System.out.println("Player Wins!");
								return true;
							}
							checkingRight +=1;
						}
						human = 1; //Resets count since not 4 in a row
					}catch(IndexOutOfBoundsException e){
						human = 1; //Resets count since not 4 in a row
					}
					int checkingDown = i + 1;
					try{ //Tries and catches if index out of bounds downwards
						while (boardOutline[checkingDown][j] == "X"){ //checks to the bottom for X
							human += 1;
							if (human == 4){
								System.out.println("Player Wins!");
								return true;
							}
							checkingDown +=1;
						}
						human = 1; //Resets count since not 4 in a row
					}catch(IndexOutOfBoundsException e){
						human = 1; //Resets count since not 4 in a row
					}
				}else if (boardOutline[i][j] == "O"){ //Checks if the computer has four of a kind
					int checkingRight = j + 1;
					try{ //Tries and catches if index out of bounds rightwards
						while (boardOutline[i][checkingRight] == "O"){ //checks to the right for O
							ai += 1;
							if (ai == 4){
								System.out.println("Computer Wins!");
								return true;
							}
							checkingRight +=1;
						}
						ai = 1; //Resets count since not 4 in a row
					}catch(IndexOutOfBoundsException e){
						ai = 1; //Resets count since not 4 in a row
					}
					int checkingDown = i + 1;
					try{ //Tries and catches if index out of bounds downwards
						while (boardOutline[checkingDown][j] == "O"){ //checks to the bottom for O
							ai += 1;
							if (ai == 4){
								System.out.println("Computer Wins!");
								return true;
							}
							checkingDown +=1;
						}
						ai = 1; //Resets count since not 4 in a row
					}catch(IndexOutOfBoundsException e){
						ai = 1; //Resets count since not 4 in a row
					}
				}
			}
		}
		return false;		
	}

	
	/**
	 * This method checks if the next move is legal
	 */
	public boolean legalMove(String[][] boardOutline, int row, int col) {
		if (!boardOutline[row][col].equals("-")){
			return false;
		}
		return true;
	}
	
	/**
	 * This method checks if the next move is legal with specific parameters
	 * from another method
	 */
	public boolean legalMove(int holderColumns, int holderRows, String[][] boardOutline) {
		if (!boardOutline[holderColumns][holderRows].equals("-")){
			return false;
		}
		return true;
	}
	
	
	/**
	 * This method checks if the input entered from the user is not within
	 * the bounds of the board.
	 */
	public boolean outOfBoundsMove(String userMove, String[][] boardOutline) {
			boolean letter = false;
			boolean number = false;
			if (userMove.length() == 0 || userMove.length() > 2){
				return false;
			}
			for (int i = 0; i < boardOutline.length; i++){ //goes down the columns, a b c d
				if (userMove.toUpperCase().charAt(0) == (boardOutline[i][0].charAt(0))){ // if the E in e5 == a column letter
					letter = true;
				}
				if (userMove.charAt(1) == (boardOutline[0][i].charAt(0))){ // if the E in e5 == a column letter
					number = true;
				}
			}
			if (letter == true && number == true){
				return true;
			}else{
				return false;
			}	
	}
}
