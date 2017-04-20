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
		boolean giocatore2 = true;
		
		if(storicoMosse.size() == 0)
			if(posY == DIM - 1)
				giocatore2 = false;
		
		if(giocatore2) {
			if(liberaAlto(scacchiera))
				return 'U';
			else if(liberaSinistra(scacchiera))
				return 'L';
			else if(liberaBasso(scacchiera))
				return 'D';
			else
				return 'R';
		}
		
		else {
			if(liberaAlto(scacchiera))
				return 'U';
			else if(liberaDestra(scacchiera))
				return 'R';
			else if(liberaBasso(scacchiera))
				return 'D';
			else
				return 'L';
		}

	}
	
	private boolean liberaSinistra(char[][] scacchiera) {
		return posY != 0 && scacchiera[posX][posY - 1] == '0';
	}
	
	private boolean liberaDestra(char[][] scacchiera) {
		return posY != DIM - 1 && scacchiera[posX][posY + 1] == '0';
	}
	
	private boolean liberaAlto(char[][] scacchiera) {
		return posX != 0 && scacchiera[posX - 1][posY] == '0';
	}
	
	private boolean liberaBasso(char[][] scacchiera) {
		return posX != DIM - 1 && scacchiera[posX + 1][posY] == '0';
	}

}
