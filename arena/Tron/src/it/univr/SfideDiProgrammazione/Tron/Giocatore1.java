package it.univr.SfideDiProgrammazione.Tron;

import java.util.Random;

public class Giocatore1 extends Giocatore	{

	public Giocatore1(String nomeGiocatore, int i, int j) {
		super(nomeGiocatore, i, j);
	}

	/**Funzione per implementare una strategia. Si ha a disposizio:
	 * - char[][] scacchiera --> la scacchiera
	 * - iAvversario, jAvversario --> coordinate dell'avversaio
	 * - this.posX, this.posY --> le proprie coordinate
	 * - storicoMosseAvversario --> arrayList delle mosse dell'avversario;*/
	@Override
	protected char scegliMossaDaFare(int iAvversario, int jAvversario, char[][] scacchiera) {
		
		if(this.storicoMosseAvversario.isEmpty()) {
			switch(new Random(2).nextInt()) {
				case 0: return 'U';
				case 1: return 'D';
				case 2: if(this.posX == 4 && this.posY == DIM - 1)
							return 'R';
						return 'L';
			}
		}

		if(this.storicoMosseAvversario.get(this.storicoMosseAvversario.size() - 1) == 'U' && rimaneNellaScacchiera(this.posY + 1))
			//if(casellaLibera)
			return 'D';
		else
			if(this.storicoMosseAvversario.get(this.storicoMosseAvversario.size() - 1) == 'D' && rimaneNellaScacchiera(this.posY - 1))
				return 'U';
			else 
				if(this.storicoMosseAvversario.get(this.storicoMosseAvversario.size() - 1) == 'L' && rimaneNellaScacchiera(this.posX + 1))
					return 'R';
				else
					return 'L';
	}

	private boolean casellaLibera(char valoreDellaCasella, int coordinataNuova) {
		
			if(valoreDellaCasella == '0')
				return true;
		return false;
	}
	
	private boolean rimaneNellaScacchiera(int coordinataNuova) {
		if(coordinataNuova >= 0 && coordinataNuova < this.DIM - 1)
			return true;
		return false;
	}


}
