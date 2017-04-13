package it.univr.SfideDiProgrammazione.Tron;

import java.util.ArrayList;

import jbook.util.Input;

public class Tron {
	/** La scacchiera i dimensione 9 x 9 */
	private static char [][] scacchiera = {
			{ '0', '0', '0', '0', '0', '0', '0', '0', '0'},
			{ '0', '0', '0', '0', '0', '0', '0', '0', '0'},
			{ '0', '0', '0', '0', '0', '0', '0', '0', '0'},
			{ '0', '0', '0', '0', '0', '0', '0', '0', '0'},
			{ '0', '0', '0', '0', '0', '0', '0', '0', '0'},
			{ '0', '0', '0', '0', '0', '0', '0', '0', '0'},
			{ '0', '0', '0', '0', '0', '0', '0', '0', '0'},
			{ '0', '0', '0', '0', '0', '0', '0', '0', '0'},
			{ '0', '0', '0', '0', '0', '0', '0', '0', '0'}
	};
	
	/** Dimensione della scacchiera per controllare se la mossa scelta dal giocatore lo faccia uscire*/
	private static final int DIM = 9;

	public static void main(String[] args) {
		
		/** Inzializzo giocatori 1 e 2*/
		Giocatore g1 = new Giocatore1(Input.readString("Come ti vuoi chiamare? "), 4, DIM - 1);
		Giocatore g2 = new Giocatore2(Input.readString("Come ti vuoi chiamare? "), 4, 0);
	
		/**
		 * Inizializza la posizione di partenza del giocatore 1*/
		scacchiera[g1.getX()][g1.getY()] = '1';
		
		/**
		 * inizializza la posizione di partenza del giocatore 2*/
		scacchiera[g2.getX()][g2.getY()] = '2';
				
		
		int vincitore = -1, stato = 0;
		stampaStatoCorrente(stato);
		
		while(vincitore < 0) {
			
			/** I giocatori, secondo un qualche algoritmo, selezionano la mossa che vorranno fare.*/
			char mossaGiocatore1 = g1.scegliMossaDaFare(g2.getX(), g2.getY(), scacchiera);	
			char mossaGiocatore2 = g2.scegliMossaDaFare(g1.getX(), g1.getY(), scacchiera);
			
			/** Ora la mossa scelta verrà attuata.*/
			vincitore = rendiAttualeMossaDecisa(mossaGiocatore1, mossaGiocatore2, g1.getX(), g1.getY(), g2.getX(), g2.getY());
			
			
			/** Aggiorno la posizione corrente raggiunta dal giocatore.*/
			//if(vincitore != 10 && vincitore != 20 && vincitore != 30 ) {
			g1.aggiornaPosizioneRaggiunta(mossaGiocatore1);
			g2.aggiornaPosizioneRaggiunta(mossaGiocatore2);
		
			
			/** Aggiorno la lista delle mosse fatte dai 2 giocatori.*/			
			g1.setStoricoMosseAvversario(mossaGiocatore2);
			g2.setStoricoMosseAvversario(mossaGiocatore1);
			
			stato++;
			stampaStatoCorrente(stato);
	
		}	
		
		/** Stampa risultato*/
		stampaRisultato(vincitore, g1.getNomeGiocatore(), g2.getNomeGiocatore(), g2.getStoricoMosseAvversario(), g1.getStoricoMosseAvversario());		
	}

	/** Aggiorno la scacchiera.*/
	private static int rendiAttualeMossaDecisa(char mossaGiocatore1, char mossaGiocatore2, int i1, int j1, int i2, int j2) {
		int tempXg1 = i1, tempYg1 = j1 , tempXg2 = i2, tempYg2 = j2;
		
		switch(mossaGiocatore1) {	
		case 'U': tempXg1--; break;
		case 'D': tempXg1++; break;
		case 'L': tempYg1--; break;
		case 'R': tempYg1++; break;
		}
		
		switch(mossaGiocatore2) {
		case 'U': tempXg2--; break;
		case 'D': tempXg2++; break;		
		case 'L': tempYg2--; break;
		case 'R': tempYg2++; break;
		}		
			
		if(((tempXg1 >= 0 && tempXg1 <= DIM - 1) && (tempYg1 >= 0 && tempYg1 <= DIM - 1)) && ((tempXg2 < 0 || tempXg2 > DIM - 1) || (tempYg2 < 0 || tempYg2 > DIM - 1))) {
			scacchiera[tempXg1][tempYg1] = 'W';
			return 1;
		}
		else
			if(((tempXg2 >= 0 && tempXg2 <= DIM - 1) && (tempYg2 >= 0 && tempYg2 <= DIM - 1)) && ((tempXg1 < 0 || tempXg1 > DIM - 1) || (tempYg1 < 0 || tempYg1 > DIM - 1))) {
				scacchiera[tempXg2][tempYg2] = 'W';
				return 2;
				}
			else
				if(((tempXg2 < 0 || tempXg2 > DIM - 1) || (tempYg2 < 0 || tempYg2 > DIM - 1)) && ((tempXg1 < 0 || tempXg1 > DIM - 1) || (tempYg1 < 0 || tempYg1 > DIM - 1)))
					return 0;
				else
					if(miScontroConUnaScia(scacchiera[tempXg1][tempYg1]) && miScontroConUnaScia(scacchiera[tempXg2][tempYg2])) {
						scacchiera[tempXg1][tempYg1] = 'L';	
						scacchiera[tempXg2][tempYg2] = 'L';
						return 0;
					}
					else
						if(miScontroConUnaScia(scacchiera[tempXg1][tempYg1]) && nonMiScontroConUnaScia(scacchiera[tempXg2][tempYg2])) {
							scacchiera[tempXg1][tempYg1] = 'L';
							scacchiera[tempXg2][tempYg2] = 'W';
							return 2;	
						}
						else
							if(miScontroConUnaScia(scacchiera[tempXg2][tempYg2]) && nonMiScontroConUnaScia(scacchiera[tempXg1][tempYg1])) {
								scacchiera[tempXg2][tempYg2] = 'L';
								scacchiera[tempXg1][tempYg1] = 'W';
								return 1;
							}
							else {
								scacchiera[tempXg2][tempYg2] = '2';
								scacchiera[tempXg1][tempYg1] = '1';
								}
		return -1;
	}

	private static boolean miScontroConUnaScia(int cella) {
		if(cella == '1' || cella == '2')
			return true;
		return false;
				
	}
	
	private static boolean nonMiScontroConUnaScia(int cella) {
		if(cella == '0')
			return true;
		return false;
				
	}


	private static void stampaRisultato(int vincitore, String nomeGiocatore1, String nomeGiocatore2, ArrayList<Character> storicoMosseGiocatore1, ArrayList<Character> storicoMosseGiocatore2) {
		
		switch(vincitore){
		
		case 0: System.out.println("Non abbiamo nessun vincitore. Il bot (1) *" + nomeGiocatore1 +
				         "* e il bot (2) *" + nomeGiocatore2 + "* fanno schifo!");
				         break;
				
		case 1: System.out.println("Nell'eterna lotta tra i bot, il vincitore è il bot (1) *" + nomeGiocatore1 + "*.");
				         break;
		
		case 2: System.out.println("Nell'eterna lotta tra i bot, il vincitore è il bot (2) *" + nomeGiocatore2 + "*.");
						 break;
		}
		
		System.out.println("MOSSE GIOCATORE (1) *" + nomeGiocatore1 + "*: "  + storicoMosseGiocatore1);
		System.out.println("MOSSE GIOCATORE (2) *" + nomeGiocatore2 + "*: "  + storicoMosseGiocatore2);
	}

	private static void stampaStatoCorrente(int stato) {
		System.out.println(stato + "\n");
		for(int i = 0; i < DIM; i++) {
			for(int j = 0; j < DIM; j++) {
				System.out.print(scacchiera[i][j] + " ");
			}
			System.out.println();
		}		
		System.out.println();
	}

}
