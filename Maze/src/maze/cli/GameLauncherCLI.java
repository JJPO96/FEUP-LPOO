package maze.cli;

import java.util.Scanner;
import maze.logic.*;
import maze.logic.Maze.Direction;
import maze.logic.Maze.Mode;

public class GameLauncherCLI {

	private static boolean done = false;
	
	/**
	 * Prints the maze in text format
	 * 
	 * @param game instance of Maze
	 */
	public static void printMaze(Maze game){
		System.out.println("\n"+game.toString());
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

	/**
	 * Selects game mode
	 * 
	 * @param scan to be used to get input from user
	 * @return game mode selected
	 */
	public static Mode menuGameMode(Scanner scan){
		Mode mode = null;

		System.out.println("\n<< SELECT GAME MODE >>\n");
		System.out.println("> 1 - STATIC DRAGONS");
		System.out.println("> 2 - MOVING DRAGONS");
		System.out.println("> 3 - MOVING AND SLEEPING DRAGONS");
		System.out.println();

		int input = getUserInput(scan, 1, 3);	

		switch (input){
		case 1:
			mode = Mode.STATIC;
			break;
		case 2:
			mode = Mode.MOVING;
			break;
		case 3:
			mode = Mode.MOVINGSLEEPING;
			break;
		}

		return mode;
	}

	public static int getUserInput(Scanner scan, int min, int max){

		int input = 0;

		do {

			try{
				input = scan.nextInt();
				if (input < min || input > max)
					throw new IllegalArgumentException();
			}

			catch(Exception e) {
				scan.nextLine();
				System.err.println("ERROR:: Invalid option! Please try again!");
			} 
		} while (input < min || input > max);

		scan.nextLine();
		
		return input;
	}
	
	public static Direction getMoveInput(Scanner scan){

		char input = ' ';
		Direction move = null;

		do {
			try{
				input = scan.nextLine().charAt(0);
				input = Character.toUpperCase(input);
				if(!validMove(input))
					throw new IllegalArgumentException();
			}

			catch(Exception e) {
				System.err.println("ERROR:: Invalid option! Please try again!");
			}			
		} while (!validMove(input));
		
		switch (input){
		case 'L':
			move = Direction.LEFT;
			break;
		case 'R':
			move = Direction.RIGHT;
			break;
		case 'U':
			move = Direction.UP;
			break;
		case 'D':
			move = Direction.DOWN;
			break;
		}
		
		return move;
	}
	
	public static int getNumberDragons(Scanner scan){
		
		// TODO: COLOCAR MINIMO VALOR E MAXIMO VALOR CONSOANTE O TAMANHO DO TABULEIRO

		System.out.println("\nChoose number of Dragons!\n");
		int ret;
		ret = scan.nextInt();
		
		return ret;
	}

	public static int getMazeSize(Scanner scan){
		
		// TODO: verificar se o valor dado é ímpar
		System.out.println("\nChoose Maze Size!\n");
		int ret;
		ret = scan.nextInt();
		
		return ret;
	}
	
	public static void runGame(Scanner scan, int dragons, int mazeSize){

		Maze game = new Maze(menuGameMode(scan),dragons,mazeSize);		
		printMaze(game);

		while(game.isRunning()){
			game.update(getMoveInput(scan));
			printMaze(game);
		}

		gameEnd(game);
	}
	
	public static void displayMainMenu(){
		System.out.println("\n<< STARTING MENU >>\n");
		System.out.println("> 1 - Start game");
		System.out.println("> 2 - Exit");
		System.out.println();
	}
	
	/**
	 * Prints game's end message
	 * 
	 * @param game
	 */
	public static void gameEnd(Maze game){
		if (game.isMazeOpen())
			System.out.println("VICTORY!");
		else
			System.out.println("DEFEAT!");
	}

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int input = 0;
		int dragons;
		int mazeSize;
		System.out.println("<< WELCOME TO THE MAZE >>\n");

		while (!done){			
			displayMainMenu();
			input = getUserInput(scan, 1, 2);	
			
			switch(input){
			case 1:		
				dragons = getNumberDragons(scan);
				mazeSize = getMazeSize(scan);
				runGame(scan,dragons,mazeSize);
				break;
			case 2:
				done = true;
				break;			
			}
		}

		System.out.println("EXITING...");
		scan.close();
	}
}



