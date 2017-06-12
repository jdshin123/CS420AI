
public class AI {
	int time;
	
	public AI(int time){
		this.time = time;
	}
	
	public String[][] evaluateAI(String[][] board,int generation, boolean mode){
		Node head = new Node(board, generation, mode, 3);
		Node tmp = head.traverseNodes(new Node()).parent.parent;
		return tmp.board;
	}
}
