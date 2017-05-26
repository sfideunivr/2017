package it.univr.SfideDiProgrammazione.Tron.Bots;

import it.univr.SfideDiProgrammazione.Tron.Giocatore;

public class GiocatoreRiempitivo_v1 extends Giocatore {
	
	private boolean giocatore1 = true;

	public GiocatoreRiempitivo_v1(String nome, int i, int j) {
		super(nome, i, j);
	}
	
	/**
	 * <p>
	 * Cerca di riempire tutta la scacchiera, prediligendo le mosse nell'ordine
	 * <ul>
	 * <li>D, L, U, R se Giocatore 1;</li>
	 * <li>U, R, D, L se Giocatore 2.</li>
	 * </p>
	 * */
	@Override
	protected char calcolaMossa(int posXavv, int posYavv, char[][] scacchiera) {
		
		if(mosse.size() == 0)
			if(posY == DIM - 1)
				giocatore1 = false;
		
		if(giocatore1) {
			if(liberaBasso(scacchiera))
				return 'D';
			else if(liberaSinistra(scacchiera))
				return 'L';
			else if(liberaAlto(scacchiera))
				return 'U';
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

}
