package it.univr.SfideDiProgrammazione.Tron;

import java.util.ArrayList;

public abstract class Giocatore {

	protected String nome;
	protected int posX;
	protected int posY;
	public static final int DIM = 9;
	protected ArrayList<Character> storicoMosse;
	protected ArrayList<Character> storicoMosseAvversario;
	protected int punteggio;
	
	public Giocatore(String nome) {
		this.nome = nome;
		this.storicoMosse = new ArrayList<Character>();
		this.storicoMosseAvversario = new ArrayList<Character>();
	}

	public Giocatore(String nome, int i, int j) {
		this.nome = nome;
		this.posX = i; 
		this.posY = j;
		this.storicoMosse = new ArrayList<Character>();
		this.storicoMosseAvversario = new ArrayList<Character>();
		this.punteggio = 0;
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
	
	protected String getNome() {
		return this.nome;
	}

	protected void aggiornaPosizioneRaggiunta(char mossaGiocatore) {
		storicoMosse.add(mossaGiocatore);
		switch(mossaGiocatore) {
			case 'U': this.posX--; break;
			case 'D': this.posX++; break;
			case 'L': this.posY--; break;
			case 'R': this.posY++; break;
		}
	}
	
	protected ArrayList<Character> getStoricoMosse() {
		return this.storicoMosse;
	}

	protected ArrayList<Character> getStoricoMosseAvversario() {
		return this.storicoMosseAvversario;
	}
	
	protected void setStoricoMosseAvversario(char mossaFattaDaAvversario) {
		this.storicoMosseAvversario.add(mossaFattaDaAvversario);
	}
	
	protected int getPunteggio() {
		return this.punteggio;
	}
	
	protected void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}
	
	protected void assegnaPunteggio(int punteggio) {
		this.punteggio += punteggio;
	}
	
	protected boolean liberaSinistra(char[][] scacchiera) {
		return posY != 0 && scacchiera[posX][posY - 1] == '0';
	}
	
	protected boolean liberaDestra(char[][] scacchiera) {
		return posY != DIM - 1 && scacchiera[posX][posY + 1] == '0';
	}
	
	protected boolean liberaAlto(char[][] scacchiera) {
		return posX != 0 && scacchiera[posX - 1][posY] == '0';
	}
	
	protected boolean liberaBasso(char[][] scacchiera) {
		return posX != DIM - 1 && scacchiera[posX + 1][posY] == '0';
	}

}
