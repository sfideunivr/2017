package it.univr.SfideDiProgrammazione.Tron;

import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

public class Giocatore2 extends Giocatore {

	public Giocatore2(String nomeGiocatore, int i, int j) {
		super(nomeGiocatore, i, j);
		// TODO Auto-generated constructor stub
	}
	
	/*
	Fa una mossa casuale.
	
	@Override
	protected char scegliMossaDaFare(int posXavv, int posYavv, char[][] scacchiera) {
		int scelta = new Random().nextInt(3);
		
		switch(scelta) {
			case 0: return 'L';
			case 1: return 'R';
			case 2: return 'U';
			case 3: return 'D';
			default: return 'L';
		}

	}
	*/
	
	/*
	Fa una mossa casuale, ma controlla se la casella è libera.
	
	@Override
	protected char scegliMossaDaFare(int posXavv, int posYavv, char[][] scacchiera) {
		boolean cicla = true;
		char mossa = 'L';
		int i = 0;
		
		while(cicla && i < 10) {
			int scelta = new Random().nextInt(3);
			
			switch(scelta) {
				case 0: if(liberaASinistra(scacchiera)) mossa = 'L'; cicla = false; break;
				case 1: if(liberaADestra(scacchiera)) mossa = 'R'; cicla = false; break;
				case 2: if(liberaInAlto(scacchiera)) mossa = 'U'; cicla = false; break;
				case 3: if(liberaInBasso(scacchiera)) mossa = 'D'; cicla = false; break;
			}
			i++;
		}
		
		return mossa;
	}
	*/
	
	/*
	Cerca di riempire tutta la scacchiera.
	*/
	@Override
	protected char scegliMossaDaFare(int posXavv, int posYavv, char[][] scacchiera) {
		
		if(liberaASinistra(scacchiera))
			return 'L';
		else if(liberaInAlto(scacchiera))
			return 'U';
		else if(liberaADestra(scacchiera))
			return 'R';
		else
			return 'D';

	}
	
	private boolean liberaASinistra(char[][] scacchiera) {
		return posY != 0 && scacchiera[posX][posY - 1] == '0';
	}
	
	private boolean liberaADestra(char[][] scacchiera) {
		return posY != DIM - 1 && scacchiera[posX][posY + 1] == '0';
	}
	
	private boolean liberaInAlto(char[][] scacchiera) {
		return posX != 0 && scacchiera[posX - 1][posY] == '0';
	}
	
	private boolean liberaInBasso(char[][] scacchiera) {
		return posX != DIM - 1 && scacchiera[posX + 1][posY] == '0';
	}
	
	private boolean eInAltoASinistra(int posX, int posY) {
		return (posX >= 0 && posX < DIM / 2) && (posY >= 0 && posY < DIM / 2);
	}
	
	private boolean eInAltoADestra(int posX, int posY) {
		return (posX >= 0 && posX < DIM / 2) && (posY >= DIM / 2 && posY < DIM);
	}
	
	private boolean eInBassoASinistra(int posX, int posY) {
		return (posX >= DIM / 2 && posX < DIM) && (posY >= 0 && posY < DIM / 2);
	}
	
	private boolean eInBassoADestra(int posX, int posY) {
		return (posX >= DIM / 2 && posX < DIM) && (posY >= DIM / 2 && posY < DIM);
	}
	
	private boolean ePiuInAltoDellAvversario(int posXavv) {
		return posX < posXavv;
	}
	
	private boolean eInAltoQuantoLAvversario(int posXavv) {
		return posX == posXavv;
	}
	
	private boolean ePiuASinistraDellAvversario(int posYavv) {
		return posY < posYavv;
	}
	
	private boolean eASinistraQuantoLAvversario(int posYavv) {
		return posY == posYavv;
	}
	
	private int distanzaOrizzontale(int posYavv) {
		return Math.abs(posYavv - posY);
	}
	
	private int distanzaVerticale(int posXavv) {
		return Math.abs(posXavv - posX);
	}

}
