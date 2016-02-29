package maze.cli;

import java.util.Scanner;
import maze.logic.*;

public class GameLauncher {

	public static String convertString(Maze game){

		Position pos = new Position(0, 0);

		String maze = "";

		for (int i = 0; i < game.getgameBoard().getBoard().length; i++){
			for ( int j = 0; j < game.getgameBoard().getBoard()[i].length; j++){
				pos.setX(j);
				pos.setY(i);

				if (game.getHero().getPos().equals(pos))
					maze+= game.getHero().getSymbol();
				else if (game.getDragon().getPos().equals(pos))
					maze+= game.getDragon().getSymbol();
				else if (game.getSword().getPos().equals(pos) && !game.getSword().isPicked())
					maze+= game.getSword().getSymbol();
				else
					maze += game.getgameBoard().getBoard()[i][j];
			}

			maze += "\n";
		}		

		return maze;		
	}

	public static void printMaze(Maze game){	
		System.out.println(convertString(game));
	}

	public static boolean validMove(char input){

		// Movement - (L)eft, (R)ight, (U)p, (D)own		
		switch (input){
		case 'L':
			return true;
		case 'R':
			return true;
		case 'U':
			return true;
		case 'B':
			return true;
		default:
			return false;
		}
	}

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		char input = ' ';

		Maze game = new Maze();

		// TODO - Quando se criar varios modos de jogo, a função init() irá receber um parâmeto
		//  que irá dizer o modo de jogo escolhido
		game.init();		
		printMaze(game);

		while(game.isRunning()){			
			try{
				input = scan.nextLine().charAt(0);
			}

			catch(Exception e) {
				input = ' ';
				System.err.println("ERRO:: Input inválido! Tente novamente!");
			}

			input = Character.toUpperCase(input);

			if (validMove(input)){
				game.update(input);
				printMaze(game);
			}
		}		

		scan.close();
	}
}


