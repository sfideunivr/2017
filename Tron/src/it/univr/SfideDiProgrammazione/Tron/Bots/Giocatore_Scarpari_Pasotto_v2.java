package it.univr.SfideDiProgrammazione.Tron.Bots;

import java.util.ArrayList;

import it.univr.SfideDiProgrammazione.Tron.Giocatore;

public class Giocatore_Scarpari_Pasotto_v2 extends Giocatore {

	/**
	 * Il tipo del simbolo che identifica il giocatore.
	 */
	private PieceType piece;

	/**
	 * <p>
	 * Costruttore che richiama il costruttore della superclasse <i>Giocatore</i>.<br>
	 * Inoltre, inizializza il campo <i>piece</i> per identificare il giocatore.
	 * </p>
	 * 
	 * @param nome Il nome del giocatore.
	 * @param i La riga in cui si trova il giocatore.
	 * @param j La colonna in cui si trova il giocatore.
	 */
	public Giocatore_Scarpari_Pasotto_v2(String nome, int i, int j) {
		super(nome, i, j);
		if(j == 0)
			piece = PieceType.X;
		else
			piece = PieceType.O;
	}

	/**
	 * <p>
	 * Calcola la mossa del giocatore secondo un qualche algoritmo e la ritorna.<br>
	 * Invoca il metodo <i>minimax</i> per calcolare la mossa.
	 * </p>
	 */
	@Override
	protected char calcolaMossa(int posXavv, int posYavv, char[][] scacchiera) {		
		return minimax(new State(scacchiera, posX, posY, posXavv, posYavv));
	}
	
	/**
	 * <p>
	 * Ritorna la mossa migliore dato lo stato del gioco.
	 * </p>
	 * 
	 * @param state Lo stato corrente del gioco.
	 * @return La mossa migliore.
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
	 * <p>
	 * Ritorna il lowest score ricevuto dal min player.
	 * </p>
	 * 
	 * @param state Lo stato corrente del gioco.
	 * @return Il punteggio più basso.
	 */
	private int min(State state) {
		if(state.isLeaf())
			return eval(state);

		int lowestScore = Integer.MAX_VALUE;

		ArrayList<Character> moves = state.getMoves();
		int score = 0;
		for(char move : moves) {
			State clone = state.clone();
			clone.playAdvMove(move);
			score = max(clone);
			if(score < lowestScore)
				lowestScore = score;
		}

		return lowestScore;
	}
	
	/**
	 * <p>
	 * Ritorna highest score ricevuto dal max player.
	 * </p>
	 * 
	 * @param state Lo stato corrente del gioco.
	 * @return Il punteggio più alto.
	 */
	private int max(State state) {
		if(state.isLeaf())
			return eval(state);

		int highestScore = Integer.MIN_VALUE;

		ArrayList<Character> moves = state.getMoves();
		int score = 0;
		for(char move : moves) {
			State clone = state.clone();
			clone.playMove(move);
			score = min(clone);
			if(score > highestScore)
				highestScore = score;
		}

		return highestScore;
	}
	
	/**
	 * <p>
	 * Valuta lo stato del gioco.
	 * </p>
	 * 
	 * @param state Lo stato corrente del gioco.
	 * @return 1 se il giocatore corrente vince; -1 altrimenti.
	 */
	private int eval(State state) {
		PieceType piece = state.winner();
		if(piece == this.piece)
			return 1;
		else
			return -1;
	}
	
	/**
	 * <p>
	 * Il tipo del giocatore.
	 * </p>
	 */
	public static enum PieceType {
		X, O
	}
	
	/**
	 * <p>
	 * Classe interna che rappresenta uno stato del gioco.
	 * </p>
	 */
	public class State {
		
		/**
		 * La matrice che rappresenta il gioco.
		 */
		public char [][] board;
		
		/**
		 * La riga in cui si trova il giocatore.
		 */
		public int x;
		
		/**
		 * La colonna il cui si trova il giocatore.
		 */
		public int y;
		
		/**
		 * La riga in cui si trova il giocatore avversario.
		 */
		public int xa;
		
		/**
		 * La colonna in cui si trova il giocatore avversario.
		 */
		public int ya;
		
		/**
		 * <p>
		 * Costruttore per lo stato del gioco.
		 * </p>
		 * 
		 * @param board La matrice che rappresenta il gioco.
		 * @param x La riga in cui si trova il giocatore.
		 * @param y La colonna il cui si trova il giocatore.
		 * @param xa La riga in cui si trova il giocatore avversario.
		 * @param ya La colonna in cui si trova il giocatore avversario.
		 */
		public State(char board[][], int x, int y, int xa, int ya) {
			this.board = board;
			this.x = x;
			this.y = y;
			this.xa = xa;
			this.ya = ya;
		}
		
		/**
		 * <p>
		 * Ritorna una copia dello stato del gioco.
		 * </p>
		 */
		public State clone() {
			return this;
		}

		/**
		 * <p>
		 * Ritorna le mosse disponibili in un determinato stato.
		 * </p>
		 * 
		 * @return Le mosse disponibili nello stato considerato.
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
		
		public void playMove(char move) {
			switch(move) {
				case 'L': board[x][y - 1] = '1'; break;
				case 'R': board[x][y + 1] = '1'; break;
				case 'U': board[x - 1][y] = '1';break;
				case 'D': board[x + 1][y] = '1'; break;
			}
		}
		
		public void playAdvMove(char move) {
			switch(move) {
				case 'L': board[xa][ya - 1] = '1'; break;
				case 'R': board[xa][ya + 1] = '1'; break;
				case 'U': board[xa - 1][ya] = '1';break;
				case 'D': board[xa + 1][ya] = '1'; break;
			}
		}

		/**
		 * <p>
		 * Controlla se lo stato corrente è finale oppure no.
		 * </p>
		 * 
		 * @return <i>true</i> se lo stato corrente è una foglia, <i>false</i> altrimenti.
		 */
		public boolean isLeaf() {
			return board[x][y] != '0' || board[xa][ya] != '0' ||
				   x < 0 || x > DIM - 1 || y < 0 || y > DIM - 1 ||
				   xa < 0 || xa > DIM - 1 || ya < 0 || ya > DIM - 1;
		}

		/**
		 * <p>
		 * Ritorna il simbolo del vincitore.
		 * </p>
		 * 
		 * @return Il simbolo del vincitore.
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