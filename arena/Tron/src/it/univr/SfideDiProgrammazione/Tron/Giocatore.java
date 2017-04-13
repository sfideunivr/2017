package it.univr.SfideDiProgrammazione.Tron;

import java.util.ArrayList;

public abstract class Giocatore {

	/** Il nome del giocatore.*/
	protected String nomeGiocatore;
	
	/** Posizione corrente nella scacchiera. */
	protected int posX;
	protected int posY;
	
	/** Dimensione scacchiera per controllare. */
	protected static final int DIM = 9;
	
	/**La lista delle mosse fatte dall'avversario. */
	protected ArrayList<Character> storicoMosseAvversario;
	/**La lista delle mosse fatte dall'avversario. */
	protected ArrayList<Character> storicoMosse;
	
	public Giocatore(String nomeGiocatore, int i, int j) {
		this.nomeGiocatore = nomeGiocatore;
		this.posX = i; 
		this.posY = j;
		this.storicoMosseAvversario = new ArrayList<Character>();
		this.storicoMosse = new ArrayList<Character>();
	}

	protected abstract char scegliMossaDaFare(int iAvversario, int jAvversario, char[][] scacchiera);

	protected int getX() {
		return this.posX;
	}
	
	protected int getY() {
		return this.posY;
	}
	
	protected void setX(int i) {
		this.posX = i;
	}
	
	protected void setY(int j) {
		this.posY = j;
	}
	
	protected String getNomeGiocatore() {
		return this.nomeGiocatore;
	}

	protected void aggiornaPosizioneRaggiunta(char mossaGiocatore) {
			
		switch(mossaGiocatore) {
		
		case 'U': this.posX--; break;
		case 'D': this.posX++; break;
		case 'L': this.posY--; break;
		case 'R': this.posY++; break;
		}
	}

	protected ArrayList<Character> getStoricoMosseAvversario() {
		
		return this.storicoMosseAvversario;
	}
	
	protected void setStoricoMosseAvversario(char mossaFattaDaAvversario) {
		
		this.storicoMosseAvversario.add(mossaFattaDaAvversario);
	}

}
