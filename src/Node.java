import java.util.ArrayList;

public class Node {
	
	Node parent;
	int pile;
	boolean mode;
	int generation;
	String[][] board;
	Node[] children;
	
	String symbol;
	String oppSymbol;
	int heuristicValue;
	
	int insertRow, insertCol;
	
	public Node(){
		this.heuristicValue = 100;
	}
	
	public Node(String[][] board, int generation, boolean mode, int pile){
		this.board = new String[board.length][board[0].length];
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				this.board[i][j] = board[i][j];
			}
		}
		this.generation = generation;
		this.mode = mode;
		if(mode){
			symbol = "X";
			oppSymbol = "O";
		}
		else{
			symbol = "O";
			oppSymbol = "X";
		}
		heuristicEval();
		this.pile = pile;
		if(this.pile != 0){
			generateChildren();
		}
		this.pile--;
	}
	
	public Node(Node parent, String[][] board, int generation, boolean mode, int insertRow, int insertCol, int pile){
		this.parent = parent;
		this.board = new String[board.length][board[0].length];
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				this.board[i][j] = board[i][j];
			}
		}
		this.generation = generation;
		this.mode = mode;
		if(mode){
			symbol = "X";
			oppSymbol = "O";
		}
		else{
			symbol = "O";
			oppSymbol = "X";
		}
		this.insertRow = insertRow;
		this.insertCol = insertCol;
		this.pile = pile;
		this.pile--;
		
		if(this.pile > 0){
			generateChildren();
		}
		else{
			heuristicEval();
		}
	}
	
	public void generateChildren(){
		int counter = 0;
		this.children = new Node[64-generation];
		String[][] tmpBoard = new String[board.length][board[0].length];
		for(int s = 0; s < board.length; s++){
			for(int t = 0; t < board[0].length; t++){
				tmpBoard[s][t] = board[s][t];
			}
		}
		
		for(int i = 1; i < board.length; i++){
			for(int j = 1; j < board[0].length; j++){
				if(board[i][j].equals("-")){
					for(int m = 1; m < tmpBoard.length; m++){
						for(int n = 1; n < tmpBoard[0].length; n++){
							if(m == i && n == j)
								tmpBoard[m][n] = symbol;
							else
								tmpBoard[m][n] = board[m][n];
						}
					}
					/*for(int s = 0; s < board.length; s++){
						for(int t = 0; t < board[0].length; t++){
							System.out.print(tmpBoard[s][t]);
						}
						System.out.println();
					}*/
					this.children[counter] = new Node(this, tmpBoard, generation + 1, !mode, i+1, j+1, pile);
					counter++;
					for(int s = 0; s < board.length; s++){
						for(int t = 0; t < board[0].length; t++){
							tmpBoard[s][t] = board[s][t];
						}
					}
				}
			}
		}
	}
	
	//priority:
	//win -> loss -> adjacent opps -> adjacent friendlies -> closer to middle
	
	public void heuristicEval(){
		int tmpValue = 0;
		int playerCounter = 0;
		int oppCounter = 0;
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				if(board[i][j].equals(symbol)){
					playerCounter++;
					tmpValue += playerCounter;
				}
				else{
					playerCounter = 0;
				}
				if(board[i][j].equals(oppSymbol)){
					oppCounter++;
					tmpValue -= oppCounter;
				}
				else{
					oppCounter = 0;
				}
			}
		}
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				if(board[j][i].equals(symbol)){
					playerCounter++;
					tmpValue += playerCounter;
				}
				else{
					playerCounter = 0;
				}
				if(board[j][i].equals(oppSymbol)){
					oppCounter++;
					tmpValue -= oppCounter;
				}
				else{
					oppCounter = 0;
				}
			}
		}
		//tmpValue += Math.abs((8 - (insertCol + insertRow)));
		heuristicValue = tmpValue;
	}
	
	public Node traverseNodes(Node tmp){
		//System.out.println("Generation: " + generation);
		//System.out.println("Pile: " + pile);
		//System.out.println("Children size: " + children.length);
		for(int i = 0; i < children.length; i++){
			if(children[0].children != null){
					tmp = children[i].traverseNodes(tmp);
			}
			else{
				//System.out.println(children[i]);
				//System.out.println(children[i].heuristicValue);
				if(tmp.heuristicValue > children[i].heuristicValue){
					tmp = children[i];
					System.out.println(tmp.heuristicValue);
				}
			}
		}
		return tmp;
	}
	
	public String[][] getBoard(){
		return board;
	}
	
	public String toString(){
		String result = "";
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				result+= board[i][j];
			}
			result += "\n";
		}
		return result;
	}
}
