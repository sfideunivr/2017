package it.univr.SfideDiProgrammazione.Tron.Bots;

import it.univr.SfideDiProgrammazione.Tron.Giocatore;

public class GiocatoreRiempitivo_v2 extends Giocatore {
	
	private boolean giocatore1 = true;

	public GiocatoreRiempitivo_v2(String nome, int i, int j) {
		super(nome, i, j);
	}
	
	/**
	 * <p>
	 * Cerca di riempire tutta la scacchiera, prediligendo le mosse nell'ordine
	 * <ul>
	 * <li>R, D, L, U se Giocatore 1;</li>
	 * <li>L, U, R, D se Giocatore 2.</li>
	 * </p>
	 * */
	@Override
	protected char calcolaMossa(int posXavv, int posYavv, char[][] scacchiera) {

		if(mosse.size() == 0)
			if(scacchiera[posX][posY] == '2')
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

}
