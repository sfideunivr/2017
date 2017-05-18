package it.univr.SfideDiProgrammazione.Tron;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.stream.Stream;

import jbook.util.Input;

public class Tron {
	
	private static ArrayList<String> nomiGiocatori = new ArrayList<String>();

	private static ArrayList<Giocatore> giocatori = new ArrayList<Giocatore>();
	
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
	
	private static final int DIM = 9;
	
	private static boolean verbose = false;

	public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		
		String directoryBots = new File("src/it/univr/SfideDiProgrammazione/Tron/Bots").getAbsolutePath();
		File name = new File(directoryBots);
		String[] files = name.list();
		
		System.out.println("Bots trovati:");
		for (String s : files) {
			String nome = s.substring(0, s.indexOf("."));
			System.out.println(nome);
			nomiGiocatori.add(nome);
			Class classe = Class.forName("it.univr.SfideDiProgrammazione.Tron.Bots." + nome);
			Constructor costruttore = classe.getConstructor(String.class, int.class, int.class);
			Giocatore giocatore = (Giocatore) costruttore.newInstance(nome, 0, 0);
			giocatori.add(giocatore);
		}
		System.out.println();
		
		int arena = scegliArena();
		char sceltaStampa;
		int x1, y1, x2, y2;

		switch(arena) {
		case 1: x1 = 4; y1= 0; x2 = 4; y2 = 8; break;
		case 2: x1 = new Random().nextInt(DIM); y1= 0; x2 = new Random().nextInt(DIM); y2 = 8; break;
		case 3: 
			do {
				x1 = new Random().nextInt(DIM);
				y1 = new Random().nextInt(DIM);
				x2 = new Random().nextInt(DIM);
				y2 = new Random().nextInt(DIM);
			}
			while(x1 == x2 && y1 == y2); break;
		default: x1 = 0; y1 = 0; x2 = 0; y2 = 0; break;
		}
		
		do {
			sceltaStampa = Input.readChar("\nAttivare la stampa della scacchiera? (Y/N) ");
		} while(sceltaStampa != 'Y' && sceltaStampa != 'y' && sceltaStampa != 'N' && sceltaStampa != 'n');
		
		if(sceltaStampa == 'Y' || sceltaStampa == 'y')
			verbose = true;

		int numeroGiocatori = giocatori.size();
		
		Giocatore g1 = null;
		Giocatore g2 = null;
		int numeroScontro = 1;
		for(int i = 0; i < numeroGiocatori; i++) {
			for(int j = 0; j < numeroGiocatori; j++) {
				g1 = giocatori.get(i);
				g2 = giocatori.get(j);
				if(!g1.getNome().equals(g2.getNome())) {
					g1.setX(x1);
					g1.setY(y1);
					g2.setX(x2);
					g2.setY(y2);
					System.out.println("Scontro #" + numeroScontro + "\n");
					numeroScontro++;
					scontra(g1, g2);
				}
			}
		}
		
		stampaClassifica();
		
	}
	
	private static void scontra(Giocatore g1, Giocatore g2) {
		
		System.out.println("Giocatore 1 = " + g1.getNome() + " in posizione (" + g1.getX() + ", " + g1.getY() + ")");
		System.out.println("Giocatore 2 = " + g2.getNome() + " in posizione (" + g2.getX() + ", " + g2.getY() + ")\n");
		
		/**
		 * inizializza la posizione di partenza del giocatore 1*/
		scacchiera[g1.getX()][g1.getY()] = '1';
		
		/**
		 * inizializza la posizione di partenza del giocatore 2*/
		scacchiera[g2.getX()][g2.getY()] = '2';
				
		int vincitore = -1, stato = 0;
		stampaStatoCorrente(stato);
		while(vincitore < 0) {
			
			/**I giocatori, secondo un qualche algoritmo, selezionano la mossa che vorranno fare.*/
			char mossaGiocatore1 = g1.scegliMossaDaFare(g2.getX(), g2.getY(), scacchiera);	
			char mossaGiocatore2 = g2.scegliMossaDaFare(g1.getX(), g1.getY(), scacchiera);
			
			/**Ora la mossa scelta verra attuata.*/
			vincitore = rendiAttualeMossaDecisa(mossaGiocatore1, mossaGiocatore2, g1.getX(), g1.getY(), g2.getX(), g2.getY());
			
			
			/**Aggiorno la posizione corrente raggiunta dal giocatore*/
			g1.aggiornaPosizioneRaggiunta(mossaGiocatore1);
			g2.aggiornaPosizioneRaggiunta(mossaGiocatore2);
						
			g1.setStoricoMosseAvversario(mossaGiocatore2);
			g2.setStoricoMosseAvversario(mossaGiocatore1);
			
			stato++;
			
			if(verbose)
				stampaStatoCorrente(stato);
	
		}	
		
		/** Stampa risultato*/
		stampaRisultato(vincitore, g1, g2);		
		
		resetScacchiera();
		
		System.out.println("\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n");
		
	}
	
	private static int scegliArena() {
		int scelta;

		System.out.println("Scegli il tipo di arena");
		do {
			System.out.println(
				"(1) Partenza centrale, stessa riga, lati opposti\n" +
				"(2) Partenza riga casuale, lati opposti\n" +
				"(3) Partenza puramente casuale\n"
			);
			scelta = Input.readInt();
		} while(scelta < 1 || scelta > 3);

		return scelta;
	}

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
					if((miScontroConUnaScia(scacchiera[tempXg1][tempYg1]) && miScontroConUnaScia(scacchiera[tempXg2][tempYg2])) || (tempXg1 == tempXg2 && tempYg1 == tempYg2)) {
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


	private static void stampaRisultato(int vincitore, Giocatore g1, Giocatore g2) {
		
		switch(vincitore) {
		
		case 0:
			g1.assegnaPunteggio(1);
			g2.assegnaPunteggio(1);
			System.out.println("Non abbiamo nessun vincitore. Il bot (1) *" + g1.getNome() +
				         "* e il bot (2) *" + g2.getNome() + "* fanno schifo!");
				         break;
				
		case 1:
			g1.assegnaPunteggio(3);
			System.out.println("Nell'eterna lotta tra i bot, il vincitore è il bot (1) *" + g1.getNome() + "*.");
				         break;
		
		case 2:
			g2.assegnaPunteggio(3);
			System.out.println("Nell'eterna lotta tra i bot, il vincitore è il bot (2) *" + g2.getNome() + "*.");
						 break;
		}
		
		System.out.println("MOSSE GIOCATORE (1) *" + g1.getNome() + "*: "  + g1.getStoricoMosse());
		System.out.println("MOSSE GIOCATORE (2) *" + g2.getNome() + "*: "  + g2.getStoricoMosse());
	}

	private static void stampaStatoCorrente(int stato) {
		if(stato == 0)
			System.out.println("Situazione iniziale\n");
		else
			System.out.println("Mossa #" + stato + "\n");
		
		for(int i = 0; i < DIM; i++) {
			for(int j = 0; j < DIM; j++) {
				System.out.print(scacchiera[i][j] + " ");
			}
			System.out.println();
		}		
		System.out.println();
	}
	
	private static void resetScacchiera() {
		for(int i = 0; i < DIM; i++)
			for(int j = 0; j < DIM; j++)
				scacchiera[i][j] = '0';
	}
	
	private static void stampaClassifica() {
		
		System.out.println("Classifica finale\n");
	
		Collections.sort(giocatori, new Comparator<Giocatore>() {
		    @Override
		    public int compare(Giocatore o1, Giocatore o2) {
		        return ((Integer) o1.getPunteggio()).compareTo((Integer) o2.getPunteggio());
		    }
		});
		
		for(int i = giocatori.size() - 1; i >= 0; i--)
			System.out.println("#" + (giocatori.size() - i) + " " + giocatori.get(i).getNome() + ": " + giocatori.get(i).getPunteggio());
	
	}

}
