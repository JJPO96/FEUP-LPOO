package maze.cli;

import java.util.Scanner;
import maze.logic.*;

public class GameLauncher {
	
	private static boolean done = false;

	public static String convertString(Maze game){

		Position pos = new Position(0, 0);

		String maze = "";

		for (int i = 0; i < game.getgameBoard().getBoard().length; i++){
			for ( int j = 0; j < game.getgameBoard().getBoard()[i].length; j++){
				pos.setX(j);
				pos.setY(i);

				if (game.getHero().getPos().equals(pos))
					maze+= game.getHero().getSymbol();
				else if (game.getDragon().isAlive() && game.getDragon().getPos().equals(pos) && game.getDragon().getPos().equals(game.getSword().getPos()))
					maze += 'F';
				else if (game.getDragon().isAlive() && game.getDragon().getPos().equals(pos)&& game.getDragon().isSleeping())
					maze+= 'd';
				else if (game.getDragon().isAlive() && game.getDragon().getPos().equals(pos)&& !game.getDragon().isSleeping())
					maze+= 'D';
				else if (game.getSword().getPos().equals(pos) && !game.getSword().isPicked())
					maze+= game.getSword().getSymbol();
				else
					maze += game.getgameBoard().getBoard()[i][j];
			}

			maze += "\n";
		}		

		return maze;		
	}

	/**
	 * Prints game's end message
	 * 
	 * @param game
	 */
	public static void gameEnd(Maze game){
		if (game.getHero().isAlive())
			System.out.println("VICTORY!");
		else
			System.out.println("DEFEAT!");
	}
	
	public static void printMaze(Maze game){	
		System.out.println(convertString(game));
	}

	/**
	 * Verifies if the move command is valid
	 * 
	 * @param input which is the move command made by the user
	 * @return if the move was valid or not
	 */
	public static boolean validMove(char input){

		// Movement - (L)eft, (R)ight, (U)p, (D)own		
		switch (input){
		case 'L':
			return true;
		case 'R':
			return true;
		case 'U':
			return true;
		case 'D':
			return true;
		default:
			return false;
		}
	}

	public static void runGame(Scanner scan){
		
		char input = ' ';
		
		Maze game = new Maze();		
		System.out.println("Select game mode. 0 to 2");
		int mode = scan.nextInt();
		scan.nextLine();
		game.init(mode);
		printMaze(game);
		
		while(game.isRunning()){			
			try{
				input = scan.nextLine().charAt(0);
			}

			catch(Exception e) {
				input = ' ';
				System.err.println("ERROR:: Invalid option! Please try again.!");
			}

			input = Character.toUpperCase(input);

			if (validMove(input)){
				game.update(input);
				printMaze(game);
			}
		}
		
		gameEnd(game);
	}
		
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		char input = ' ';
		
		System.out.println("<< WELCOME TO THE MAZE >>\n");
		
		while (!done){			
			System.out.println("\n<< STARTING MENU >>\n");
			System.out.println("> 1 - Start game");
			System.out.println("> 2 - Exit");
			
			// TODO - Corrigir a leitura de inpu de char para inteiro
			
			try{
				input = scan.nextLine().charAt(0);
			}

			catch(Exception e) {
				input = ' ';
				System.err.println("ERROR:: Invalid option! Please try again.!");
			}
			
			switch(input){
			case '1':
				runGame(scan);
				break;
			case '2':
				done = true;
				break;			
			}
		}
		
		System.out.println("EXITING...");
		scan.close();
	}
}


