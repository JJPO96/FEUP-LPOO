package maze.logic;

public class Labirinto {
	private char[][] tabuleiro = {
			{'X','X','X','X','X','X','X','X','X','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ','X',' ','S'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ',' ',' ',' ',' ','X'},
			{'X','X','X','X','X','X','X','X','X','X'}};
	
	public Labirinto(){};
	
	public char[][] getTab(){
		return tabuleiro;
	}
	
	public boolean setObjeto(Objeto elemento){
		
		// Verifica se é possível colocar um elemento do jogo numa posição, caso ela esteja vazia
		if(tabuleiro[elemento.getY()][elemento.getX()] == ' '){
			tabuleiro[elemento.getY()][elemento.getX()] = elemento.getSimbolo();
			return true;
		}		
		
		return false;
	}
}