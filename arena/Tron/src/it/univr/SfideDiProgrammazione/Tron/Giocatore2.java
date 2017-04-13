package it.univr.SfideDiProgrammazione.Tron;

import java.util.Random;

public class Giocatore2 extends Giocatore {

	public Giocatore2(String nomeGiocatore, int i, int j) {
		super(nomeGiocatore, i, j);
		// TODO Auto-generated constructor stub
	}

//	@Override
//	protected char scegliMossaDaFare(int iAvversario, int jAvversario, char[][] scacchiera) {
//		System.out.println("Giocatore 2 mossa effettuata.");
//		return 'U';
//	
//
//	}
	
	@Override
	protected char scegliMossaDaFare(int iAvversario, int jAvversario, char[][] scacchiera) {
		// Se la posizione a sinistra esiste ed è libera va a sinistra.
		if ((this.getY() != 0) && (scacchiera[this.getX()][this.getY()-1] == '0')){
			return 'L';
		}
		// Se la posizione a sinistra non è libera o non esiste vai su se esiste ed è libera.
		else if (this.getX() != 0 && scacchiera[this.getX()-1][this.getY()] == '0'){
			return 'U';
		}
		// Se la posizione in alto non è libera o non esiste vai a destra se esiste ed è libera.
		else if (this.getY() != 7 && scacchiera[this.getX()][this.getY()+1] == '0'){
			return 'R';
		}
		// Altrimenti se non posso andare né a sinistra, né in alto, né a destra posso andare solo in basso.
		else
			return 'D';
	}

}
