package it.univr.SfideDiProgrammazione.Tron;

import java.util.ArrayList;
import java.util.Random;

public class Giocatore1_Scarpari_Pasotto_v2 extends Giocatore {

	private PieceType piece;

	public Giocatore1_Scarpari_Pasotto_v2(String nomeGiocatore, int i, int j) {
		super(nomeGiocatore, i, j);
		if(j == 0)
			piece = PieceType.X;
		else
			piece = PieceType.O;
	}

	@Override
	protected char scegliMossaDaFare(int posXavv, int posYavv, char[][] scacchiera) {
		State state = new State(scacchiera, posX, posY, posXavv, posYavv);
		
		return minimax(state);
	}
	
	/**
	 * Ritorna la mossa migliore dato lo stato del gioco.
	 * 
	 */
	public char minimax(State state) {
		int highestScore = Integer.MIN_VALUE;
		char bestMove = 'L';

		ArrayList<Character> moves = state.getMoves();
		int score = 0;
		for(char move : moves) {
			State clone = state.clone();
			score = min(clone);
			if(score > highestScore) {
				highestScore = score;
				bestMove = move;
			}
		}

		return bestMove;
	}
	
	/**
	 * Ritorna il lowest score ricevuto dal min player.
	 * 
	 */
	private int min(State state) {
		if(state.isLeaf())
			return eval(state);

		int lowestScore = Integer.MAX_VALUE;

		ArrayList<Character> moves = state.getMoves();
		int score = 0;
		for(char move : moves) {
			State clone = state.clone();
			score = max(clone);
			if (score < lowestScore)
				lowestScore = score;
		}

		return lowestScore;
	}
	
	/**
	 * Ritorna highest score ricevuto dal max player.
	 * 
	 */
	private int max(State state) {
		if(state.isLeaf())
			return eval(state);

		int highestScore = Integer.MIN_VALUE;

		ArrayList<Character> moves = state.getMoves();
		int score = 0;
		for(char move : moves) {
			State clone = state.clone();
			score = min(clone);
			if (score > highestScore)
				highestScore = score;
		}

		return highestScore;
	}
	
	/**
	 * Ritorna 1 se questo giocatore vince; -1 altrimenti.
	 * 
	 */
	private int eval(State state) {
		PieceType piece = state.winner();
		if(piece == this.piece)
			return 1;
		else
			return -1;
	}
	
	/**
	 * Tipo di giocatore.
	 *
	 */
	public static enum PieceType {
		X, O
	}
	
	/**
	 * Rappresenta uno stato del gioco.
	 * 
	 */
	public class State {
		public char [][] board;
		public int x;
		public int y;
		public int xa;
		public int ya;
		
		public State(char board[][], int x, int y, int xa, int ya) {
			this.board = board;
			this.x = x;
			this.y = y;
			this.xa = xa;
			this.ya = ya;
		}
		
		public State clone() {
			return this;
		}

		/**
		 * Ritorna le mosse disponibili in un determinato stato.
		 * 
		 */
		public ArrayList<Character> getMoves() {
			ArrayList<Character> moves = new ArrayList<Character>();
			
			if(y != 0 && board[x][y - 1] == '0')
				moves.add('L');
			
			if(y != DIM - 1 && board[x][y + 1] == '0')
				moves.add('R');
			
			if(x != 0 && board[x - 1][y] == '0')
				moves.add('U');
			
			if(x != DIM - 1 && board[x + 1][y] == '0')
				moves.add('D');

			return moves;
		}

		/**
		 * Ritorna true se lo stato corrente è una foglia; false altrimenti.
		 * 
		 */
		public boolean isLeaf() {
			return board[x][y] != '0' || board[xa][ya] != '0' ||
				   x < 0 || x > DIM - 1 || y < 0 || y > DIM - 1 ||
				   xa < 0 || xa > DIM - 1 || ya < 0 || ya > DIM - 1;
		}

		/**
		 * Ritorna il simbolo del vincitore.
		 * 
		 */
		public PieceType winner() {
			if(board[x][y] == '0' && board[xa][ya] == '0')
				return null;
			else if(board[x][y] == '0')
				return piece;
			else if(board[xa][ya] == '0') {
				if(piece == PieceType.X)
					return PieceType.O;
				else
					return PieceType.X;
			}
			else
				return null;
		}
	}

}
