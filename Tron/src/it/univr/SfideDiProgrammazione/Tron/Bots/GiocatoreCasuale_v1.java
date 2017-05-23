package it.univr.SfideDiProgrammazione.Tron.Bots;

import java.util.Random;

import it.univr.SfideDiProgrammazione.Tron.Giocatore;

public class GiocatoreCasuale_v1 extends Giocatore {

	public GiocatoreCasuale_v1(String nome, int i, int j) {
		super(nome, i, j);
	}
	
	/**
	 * <p>
	 * Effettua una mossa totalmente casuale.
	 * </p>
	 */
	@Override
	protected char calcolaMossa(int posXavv, int posYavv, char[][] scacchiera) {
		int scelta = new Random().nextInt(3);
		
		switch(scelta) {
			case 0: return 'U';
			case 1: return 'D';
			case 2: return 'L';
			case 3: return 'R';
			default: return 'U';
		}
	}
	
}
