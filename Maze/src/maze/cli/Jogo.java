package maze.cli;

import java.util.Scanner;
import maze.logic.*;

public class Jogo {
	
	public static String convertString(Maze game){
		
		String maze = "";

		for (int i = 0; i < game.getLab().getTab().length; i++){
			for ( int j = 0; j < game.getLab().getTab()[i].length; j++){
				
				if (game.getHero().getCoords().getX() ==j && game.getHero().getCoords().getY() == i)
					maze+= game.getHero().getSimbolo();
				else if (game.getDragon().getCoords().getX() ==j && game.getDragon().getCoords().getY() == i)
					maze+= game.getDragon().getSimbolo();
				else if (game.getSword().getCoords().getX() ==j && game.getSword().getCoords().getY() == i && !game.getSword().isPicked())
					maze+= game.getSword().getSimbolo();
				else
					maze += game.getLab().getTab()[i][j];
			}
			
			maze += "\n";
		}		
		
		return maze;		
	}
	
	public static void printMaze(Maze game){	
		System.out.println(convertString(game));
	}
	
	public static boolean validMove(char input){
		
		//char a = Character.toLowerCase(input);
		
		// Movimento - E ou e (Esquerda), D ou d (Direita), C ou c (Cima), B ou b (Baixo)
		
		switch (input){
		case 'E':
			return true;
		case 'e':
			return true;
		case 'D':
			return true;
		case 'd':
			return true;
		case 'C':
			return true;
		case 'c':
			return true;
		case 'B':
			return true;
		case 'b':
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
			
			input = Character.toLowerCase(input);
			
			if (validMove(input)){
				game.update(input);
				printMaze(game);
			}
		}		
		
		scan.close();
	}
}

