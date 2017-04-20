package it.univr.SfideDiProgrammazione.Tron;

import java.util.ArrayList;
import java.util.Random;

public class Giocatore1 extends Giocatore {

	public Giocatore1(String nomeGiocatore, int i, int j) {
		super(nomeGiocatore, i, j);
		// TODO Auto-generated constructor stub
	}
	
	/*
		Cerca di riempire tutta la scacchiera.
	*/
	@Override
	protected char scegliMossaDaFare(int posXavv, int posYavv, char[][] scacchiera) {
		boolean giocatore1 = true;
	
		if(storicoMosse.size() == 0)
			if(posY == DIM - 1)
				giocatore1 = false;
		
		if(giocatore1) {
			if(liberaDestra(scacchiera))
				return 'R';
			else if(liberaBasso(scacchiera))
				return 'D';
			else if(liberaSinistra(scacchiera))
				return 'L';
			else
				return 'U';
		}
			
		else {
			if(liberaSinistra(scacchiera))
				return 'L';
			else if(liberaAlto(scacchiera))
				return 'U';
			else if(liberaDestra(scacchiera))
				return 'R';
			else
				return 'D';
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
