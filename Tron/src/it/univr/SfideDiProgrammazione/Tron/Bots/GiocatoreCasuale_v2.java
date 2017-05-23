package it.univr.SfideDiProgrammazione.Tron.Bots;

import java.util.Random;

import it.univr.SfideDiProgrammazione.Tron.Giocatore;

public class GiocatoreCasuale_v2 extends Giocatore {

	public GiocatoreCasuale_v2(String nome, int i, int j) {
		super(nome, i, j);
	}
	
	/**
	 * <p>
	 * Effettua una mossa casuale, ma controlla se la casella &egrave; interna alla scacchiera ed &egrave; libera.
	 * </p>
	 */
	@Override
	protected char calcolaMossa(int posXavv, int posYavv, char[][] scacchiera) {
		boolean cicla = true;
		char mossa = 'L';
		int i = 0;
		
		while(cicla && i < 10) {
			int scelta = new Random().nextInt(3);
			
			switch(scelta) {
				case 0: if(liberaSinistra(scacchiera)) mossa = 'L'; cicla = false; break;
				case 1: if(liberaDestra(scacchiera)) mossa = 'R'; cicla = false; break;
				case 2: if(liberaAlto(scacchiera)) mossa = 'U'; cicla = false; break;
				case 3: if(liberaBasso(scacchiera)) mossa = 'D'; cicla = false; break;
			}
			i++;
		}
		
		return mossa;
	}

}
