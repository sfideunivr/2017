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
	
	@Override
	protected char scegliMossaDaFare(int posXavv, int posYavv, char[][] scacchiera) {
		
		if(liberaADestra(scacchiera))
			return 'R';
		else if(liberaInBasso(scacchiera))
			return 'D';
		else if(liberaASinistra(scacchiera))
			return 'L';
		else
			return 'U';

	}
	*/
	
	@Override
	protected char scegliMossaDaFare(int posXavv, int posYavv, char[][] scacchiera) {
		boolean giocatore1 = true;
		
		if(storicoMosse.size() == 0)
			if(posY == DIM - 1)
				giocatore1 = false;
			
		if(giocatore1) {
			
			if(distanzaOrizzontale(posYavv) > 5) {
			
				if(liberaADestra(scacchiera))
					return 'R';
				else if(liberaInBasso(scacchiera))
					return 'D';
				else if(liberaASinistra(scacchiera))
					return 'L';
				else
					return 'U';
			}
			else {
				
				if(storicoMosse.size() == 1) {
					if(ultimaSequenzaDiMosse(storicoMosse, 1).toString().equals("R")) {
						if(liberaInAlto(scacchiera))
							return 'U';
						else if(liberaInBasso(scacchiera))
							return 'D';
						else if(liberaADestra(scacchiera))
							return 'R';
						else
							return 'L';
					}
				}
				
				else if(storicoMosse.size() == 2) {
					if(ultimaSequenzaDiMosse(storicoMosse, 2).toString().equals("RU")) {
						if(liberaADestra(scacchiera))
							return 'R';
						else if(liberaInAlto(scacchiera))
							return 'U';
						else if(liberaASinistra(scacchiera))
							return 'L';
						else
							return 'D';
					}
					else if(ultimaSequenzaDiMosse(storicoMosse, 2).toString().equals("RD")) {
						if(liberaADestra(scacchiera))
							return 'R';
						else if(liberaInBasso(scacchiera))
							return 'D';
						else if(liberaASinistra(scacchiera))
							return 'L';
						else
							return 'U';
					}
				}
				
				else if(storicoMosse.size() == 3) {
					if(ultimaSequenzaDiMosse(storicoMosse, 3).toString().equals("RUR")) {
						if(liberaInBasso(scacchiera))
							return 'D';
						else if(liberaInAlto(scacchiera))
							return 'U';
						if(liberaADestra(scacchiera))
							return 'R';
						else
							return 'L';
					}
					else if(ultimaSequenzaDiMosse(storicoMosse, 3).toString().equals("RDR")) {
						if(liberaInAlto(scacchiera))
							return 'U';
						else if(liberaInBasso(scacchiera))
							return 'D';
						else if(liberaADestra(scacchiera))
							return 'R';
						else return 'L';
					}
				}
				
				if(liberaADestra(scacchiera))
					return 'R';
				else if(liberaInBasso(scacchiera))
					return 'D';
				else if(liberaASinistra(scacchiera))
					return 'L';
				else
					return 'U';
				
			}
				
		}
		else {
			
			if(distanzaOrizzontale(posYavv) > 5) {
				
				if(liberaASinistra(scacchiera))
					return 'L';
				else if(liberaInBasso(scacchiera))
					return 'D';
				else if(liberaADestra(scacchiera))
					return 'R';
				else
					return 'U';
			}
			else {
				
				if(storicoMosse.size() == 1) {
					if(ultimaSequenzaDiMosse(storicoMosse, 1).toString().equals("L")) {
						if(liberaInAlto(scacchiera))
							return 'U';
						else if(liberaInBasso(scacchiera))
							return 'D';
						else if(liberaASinistra(scacchiera))
							return 'L';
						else
							return 'R';
					}
				}
				
				else if(storicoMosse.size() == 2) {
					if(ultimaSequenzaDiMosse(storicoMosse, 2).toString().equals("LU")) {
						if(liberaASinistra(scacchiera))
							return 'L';
						else if(liberaInAlto(scacchiera))
							return 'U';
						else if(liberaADestra(scacchiera))
							return 'R';
						else
							return 'D';
					}
					else if(ultimaSequenzaDiMosse(storicoMosse, 2).toString().equals("LD")) {
						if(liberaASinistra(scacchiera))
							return 'L';
						else if(liberaInBasso(scacchiera))
							return 'D';
						else if(liberaADestra(scacchiera))
							return 'R';
						else
							return 'U';
					}
				}
				
				else if(storicoMosse.size() == 3) {
					if(ultimaSequenzaDiMosse(storicoMosse, 3).toString().equals("LUL")) {
						if(liberaInBasso(scacchiera))
							return 'D';
						else if(liberaInAlto(scacchiera))
							return 'U';
						else if(liberaASinistra(scacchiera))
							return 'L';
						else
							return 'R';
					}
					else if(ultimaSequenzaDiMosse(storicoMosse, 3).toString().equals("LDL")) {
						if(liberaInAlto(scacchiera))
							return 'U';
						else if(liberaInBasso(scacchiera))
							return 'D';
						else if(liberaASinistra(scacchiera))
							return 'L';
						else return 'R';
					}
				}
				
			}
			
			if(liberaASinistra(scacchiera))
				return 'L';
			else if(liberaInAlto(scacchiera))
				return 'U';
			else if(liberaADestra(scacchiera))
				return 'R';
			else
				return 'D';

		}

	}
	
	private String ultimaSequenzaDiMosse(ArrayList<Character> mosse, int dim) {
		String sequenza = "";

		for(int i = dim; i > 0; i--)
			sequenza += mosse.get(mosse.size() - i);
		
		return sequenza;
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
	
	private int distanzaOrizzontale(int posYavv) {
		return Math.abs(posYavv - posY - 1);
	}
	
	private int distanzaVerticale(int posXavv) {
		return Math.abs(posXavv- posX - 1);
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

}
