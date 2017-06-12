
import java.util.Scanner;

public class Project3 {
	
	public static void main(String[] args){
		int generation = 0;
		boolean mode;
		boolean first;
		Scanner sc = new Scanner(System.in);
		Board bor = new Board();
		Legal leg = new Legal();
		
		String[][] boardRep = new String[9][9];
		boolean done = false;
		String userMove;
		
		System.out.println("Would you like to go first? (y/n)");
		String userFirst = sc.nextLine();
		if(userFirst.equals("y"))
			first = true; 
		else
			first = false;
		System.out.println();
		System.out.println("How long would you like the computer to think?");
		int thinkTime = sc.nextInt();
		AI xD = new AI(thinkTime);
		String bufferVar = sc.nextLine();
		System.out.println("Would you like to be O or X?");
		String symbol = sc.nextLine();
		if(symbol.equals("O"))
			mode = true;
		else
			mode = false;
		System.out.println();
		bor.createBoard(boardRep);
		bor.print(boardRep);
		System.out.println();
		
		//player's turn
		if(first){														//if player is first
			generation++;
			System.out.print("\nChoose your next move:");
			userMove = sc.nextLine();									//take input
			while (!leg.outOfBoundsMove(userMove,boardRep)){			//Checks if input is valid, repeat if not
				System.out.print("Invalid Move, Please try another: ");	
				userMove = sc.nextLine();
			}
			boardRep = bor.usersTurn(userMove, boardRep, !mode);				//changes board rep
			System.out.println();								
			bor.print(boardRep);						
			if (leg.connectFour(boardRep) == true){ 					//Checks if game is won
				bor.print(boardRep);
				System.out.println();
				done = true;
				return;
			}
		}
		
		while (!done){
			//Ai's turn
			System.out.println("AI turn");
			if (leg.connectFour(boardRep)){ 				//Checks if game is won
					bor.print(boardRep);
					System.out.println();
					done = true;
					return;
			}
			else{
					boardRep = xD.evaluateAI(boardRep,generation,mode);				//chooses next move
					bor.print(boardRep);
					System.out.println();
					generation++;
			}
			//player's turn
			System.out.print("\nChoose your next move:");
			userMove = sc.nextLine();									// take input
			while (!leg.outOfBoundsMove(userMove,boardRep)){			//Checks if input is valid, repeat if not
				System.out.print("Invalid Move, Please try another: ");	
				userMove = sc.nextLine();
			}
			boardRep = bor.usersTurn(userMove, boardRep,!mode);				//changes board rep
			generation++;
			System.out.println();							
			bor.print(boardRep);
			if (leg.connectFour(boardRep)){ 					//Checks if game is won
				bor.print(boardRep);
				System.out.println();
				done = true;
				return;
			}
			
		}
		
	}
}