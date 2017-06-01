package it.univr.SfideDiProgrammazione.Tron;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import jbook.util.Input;

public class Tron {

	/**
	 * la lista dei giocatori.
	 */
	private static ArrayList<Giocatore> giocatori = new ArrayList<Giocatore>();
	
	/**
	 * La matrice che rappresenta la scacchiera di gioco.
	 */
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
	
	/**
	 * La dimensione della scacchiera di gioco.
	 */
	private static final int DIM = 9;
	
	/**
	 * Le posizioni iniziali dei giocatori.
	 */
	private static int x1, y1, x2, y2;
	
	/**
	 * L'opzione per la stampa della scacchiera, mossa per mossa.
	 */
	private static boolean verbose = false;

	/**
	 * <p>
	 * Trova i bots nel package Bots e aggiunge le classi alla lista dei giocatori.<br>
	 * Sceglie il tipo di arena, in base alle posizioni iniziali che si desiderano per i giocatori.<br>
	 * Determina le posizioni iniziali dei giocatori, che saranno uguali in ogni scontro tra giocatori diversi.<br>
	 * Chiede se si desidera stampare la scacchiera di gioco dopo ogni mossa dei giocatori.<br>
	 * Fa scontrare tutti i giocatori tra loro.
	 * Ogni giocatore, in scontri diversi, è<br>
	 * sia Giocatore 1 (il quale parte dalla prima colonna a sinistra della scacchiera, ad eccezione della partenza totalmente casuale)<br>
	 * sia Giocatore 2 (il quale parte dall'ultima colonna a destra della scacchiera, ad eccezione della partenza totalmente casuale).<br>
	 * Nessun giocatore si scontra con se stesso.<br>
	 * Stampa la classifica alla fine di tutti gli scontri,<br>
	 * indicando il punteggio totale ottenuto da ciascun giocatore nelle diverse partite.<br>
	 * Stampa alcune statistiche di vittoria, pareggio e sconfitta per ogni giocatore.
	 * </p>
	 * 
	 * @param args
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		
		// Trova i bots nel package Bots e aggiunge le classi alla lista dei giocatori.
		trovaGiocatori();
		
		// Sceglie il tipo di arena, in base alle posizioni iniziali che si desiderano per i giocatori.
		int arena = scegliArena();
		
		// Determina le posizioni iniziali dei giocatori, che saranno uguali in ogni scontro tra giocatori diversi.
		scegliPosizioniIniziali(arena);
		
		// Chiede se si desidera stampare la scacchiera di gioco dopo ogni mossa dei giocatori.
		scegliStampa();
		
		Giocatore g1 = null;
		Giocatore g2 = null;
		int numeroGiocatori = giocatori.size();
		int numeroScontro = 1;
		
		// Fa scontrare tutti i giocatori tra loro.
		// Ogni giocatore, in scontri diversi, è
		// sia Giocatore 1 (il quale parte dalla prima colonna a sinistra della scacchiera, ad eccezione della partenza totalmente casuale)
		// sia Giocatore 2 (il quale parte dall'ultima colonna a destra della scacchiera, ad eccezione della partenza totalmente casuale).
		// Nessun giocatore si scontra con se stesso.
		for(int i = 0; i < numeroGiocatori; i++) {
			
			for(int j = 0; j < numeroGiocatori; j++) {
				
				g1 = giocatori.get(i);
				g2 = giocatori.get(j);
				
				if(!g1.getNome().equals(g2.getNome())) {
					
					System.out.println("\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n");
					
					System.out.println("Scontro #" + numeroScontro + "\n");
					
					g1.setX(x1);
					g1.setY(y1);
					g2.setX(x2);
					g2.setY(y2);

					scontra(g1, g2);
					
					azzeraGioco(g1, g2);
					
					numeroScontro++;

					if(verbose) {
						Input.readString("\nPremi INVIO per continuare.");
					}
					
				}
				
				
			}
		}
		
		// Stampa la classifica alla fine di tutti gli scontri,
		// indicando il punteggio totale ottenuto da ciascun giocatore nelle diverse partite.
		stampaClassifica();
		
		// Stampa alcune statistiche di vittoria, pareggio e sconfitta per ogni giocatore.
		stampaStatistiche();
		
	}
	
	/**
	 * <p>
	 * Trova, nel package <i>Bots</i>, le classi java che implementano le diverse strategie dei giocatori.
	 * </p>
	 * 
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private static void trovaGiocatori() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String directoryBots = new File("src/it/univr/SfideDiProgrammazione/Tron/Bots").getAbsolutePath();
		File name = new File(directoryBots);
		String[] files = name.list();
		
		System.out.println("Bots trovati:");
		
		for(String s : files) {
			
			String nome = s.substring(0, s.indexOf("."));
			System.out.println(nome);
			
			Class classe = Class.forName("it.univr.SfideDiProgrammazione.Tron.Bots." + nome);
			Constructor costruttore = classe.getConstructor(String.class, int.class, int.class);
			
			Giocatore giocatore = (Giocatore) costruttore.newInstance(nome, 0, 0);
			giocatori.add(giocatore);

		}

		System.out.println();
	}
	
	/**
	 * <p>
	 * Permette di scegliere il tipo di arena.
	 * <ul>
	 * <ol>Partenza centrale, stessa riga, lati opposti</ol>
	 * <ol>Partenza riga casuale, lati opposti</ol>
	 * <ol>Partenza puramente casuale</ol>
	 * </ul>
	 * </p>
	 * 
	 * @return Un intero che indica l'arena scelta.
	 */
	private static int scegliArena() {
		int scelta;

		System.out.println("Scegliere il tipo di arena: ");
		do {
			System.out.println(
				"(1) Partenza sulla riga centrale sulle colonne opposte della scacchiera\n" +
				"(2) Partenza su una riga casuale sulle colonne opposte della scacchiera\n" +
				"(3) Partenza in celle puramente casuali della scacchiera\n"
			);
			scelta = Input.readInt();
		} while(scelta < 1 || scelta > 3);

		return scelta;
	}
	
	/**
	 * <p>
	 * Calcola le posizioni iniziali dei giocatori, in base alla tipologia di arena scelta precedentemente.<br>
	 * <ul>
	 * <li>1: 
	 * Il giocatore 1 si trova nella cella individuata dalla riga centrale e dalla prima colonna a sinistra.
	 * Il giocatore 2 si trova nella cella individuata dalla riga centrale e dall'ultima colonna a destra.
	 * </li>
	 * <li>2: 
	 * Il giocatore 1 in una riga casuale della prima colonna a sinistra.
	 * Il giocatore 2 in una riga casuale dell'ultima colonna a destra.
	 * </li>
	 * <li>3: 
	 * Il giocatore 1 e il giocatore 2 si trovano in celle casuali.
	 * </li>
	 * </ul>
	 * Le posizioni iniziali di giocatore 1 e giocatore 2 saranno le stesse in tutti gli scontri.
	 * </p>
	 * 
	 * @param scelta La tipologia di arena scelta.
	 */
	private static void scegliPosizioniIniziali(int scelta) {
		switch(scelta) {
			case 1:
				x1 = (DIM / 2); y1= 0;
				x2 = (DIM / 2); y2 = DIM - 1;
				break;
			case 2:
				x1 = new Random().nextInt(DIM); y1= 0;
				x2 = new Random().nextInt(DIM); y2 = DIM - 1;
				break;
			case 3: 
				do {
					x1 = new Random().nextInt(DIM);
					y1 = new Random().nextInt(DIM);
					x2 = new Random().nextInt(DIM);
					y2 = new Random().nextInt(DIM);
				}
				while(x1 == x2 && y1 == y2);
				break;
			default:
				x1 = 0; y1 = 0;
				x2 = 0; y2 = 0;
				break;
		}
	}
	
	/**
	 * <p>
	 * Permette di scegliere se stampare la scacchiera ad ogni stato del gioco,<br>
	 * impostando un valore per il campo booleano <i>verbose</i>.<br>
	 * Digitando <i>Y</i> oppure <i>y</i> imposta <i>verbose</i> a <i>true</i><br>
	 * e acconsente alla stampa della scacchiera.<br>
	 * Digitando <i>N</i> oppure <i>n</i> imposta <i>verbose</i> a <i>false</i><br>
	 * e non acconsente alla stampa della scacchiera.
	 * </p>
	 */
	private static void scegliStampa() {
		char scelta;
		
		do {
			scelta = Input.readChar("\nAttivare la stampa della scacchiera (Y/N)? ");
		} while(scelta != 'Y' && scelta != 'y' && scelta != 'N' && scelta != 'n');
		
		if(scelta == 'Y' || scelta == 'y')
			verbose = true;

	}
	
	/**
	 * <p>
	 * Posiziona i due giocatori sulla scacchiera.<br>
	 * Stampa lo stato corrente del gioco.<br>
	 * Finché non c'è un vincitore, ogni giocatore calcola la mossa da effettuare.<br>
	 * Attua la mossa scelta dai giocatori.<br>
	 * Aggiorna la posizione raggiunta dai giocatori.<br>
	 * Aggiorna per ciascun giocatore la lista delle mosse degli avversari.<br>
	 * Incrementa il numero dello stato.
	 * Se si è scelto l'opzione verbose, stampa la scacchiera relativa allo stato corrente.<br>
	 * Stampa il risultato della partita, indicando attribuendo vittoria, pareggio e sconfitta.
	 * </p>
	 * 
	 * @param g1 Il giocatore 1.
	 * @param g2 Il giocatore 2.
	 */
	private static void scontra(Giocatore g1, Giocatore g2) {
		
		System.out.println("Giocatore 1 = " + g1.getNome() + " in posizione (" + g1.getX() + ", " + g1.getY() + ")");
		System.out.println("Giocatore 2 = " + g2.getNome() + " in posizione (" + g2.getX() + ", " + g2.getY() + ")\n");
		
		// Posiziona i due giocatori sulla scacchiera.
		scacchiera[g1.getX()][g1.getY()] = '1';
		scacchiera[g2.getX()][g2.getY()] = '2';

		int vincitore = -1, stato = 0;

		stampaStatoCorrente(stato);

		// Finché non c'è un vincitore,
		while(vincitore < 0) {
			
			// I giocatori selezionano la mossa da fare, secondo un qualche algoritmo.
			char mossaGiocatore1 = g1.calcolaMossa(g2.getX(), g2.getY(), scacchiera);	
			char mossaGiocatore2 = g2.calcolaMossa(g1.getX(), g1.getY(), scacchiera);
			
			// Attua la mossa scelta dai giocatori.
			vincitore = effettuaMossa(mossaGiocatore1, mossaGiocatore2, g1.getX(), g1.getY(), g2.getX(), g2.getY());
			
			// Aggiorna la posizione raggiunta dai giocatori.
			g1.aggiornaPosizione(mossaGiocatore1);
			g2.aggiornaPosizione(mossaGiocatore2);

			// Aggiorna per ciascun giocatore la lista delle mosse degli avversari.
			g1.setMosseAvversario(mossaGiocatore2);
			g2.setMosseAvversario(mossaGiocatore1);
			
			// Incrementa il numero dello stato.
			stato++;
			
			// Se si è scelto l'opzione verbose, stampa la scacchiera relativa allo stato corrente.
			if(verbose)
				stampaStatoCorrente(stato);
	
		}
		
		// Stampa il risultato della partita, indicando attribuendo vittoria, pareggio e sconfitta.
		stampaRisultato(vincitore, g1, g2);
		
	}

	/**
	 * <p>
	 * Applica la mossa scelta da ciascun giocatore<br>
	 * e controlla se con essa si raggiunge uno stato finale del gioco.
	 * </p>
	 * 
	 * @param mossaGiocatore1 La mossa scelta dal giocatore 1.
	 * @param mossaGiocatore2 La mossa scelta dal giocatore 2.
	 * @param i1 La riga in cui si trova il giocatore 1.
	 * @param j1 La colonna in cui si trova il giocatore 1.
	 * @param i2 La riga in cui si trova il giocatore 2.
	 * @param j2 La colonna in cui si trova il giocatore 2.
	 * @return
	 * <ul>
	 * <li><i>-1</i> se non c'è ancora alcun vincitore.</li>
	 * <li><i>0</i> se c'è un pareggio tra i giocatori.</li>
	 * <li><i>1</i> se ha vinto il giocatore 1.</li>
	 * <li><i>2</i> se ha vinto il giocatore 2.</li>
	 * </ul>
	 */
	private static int effettuaMossa(char mossaGiocatore1, char mossaGiocatore2, int i1, int j1, int i2, int j2) {
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

	/**
	 * <p>
	 * Indica se la cella &egrave; occupata dal giocatore 1 o dal giocatore 2.
	 * </p>
	 * 
	 * @param cella Un carattere della scacchiera.
	 * @return <i>true</i> se la cella vale <i>1</i> oppure <i>2</i>, <i>false</i> altrimenti.
	 */
	private static boolean miScontroConUnaScia(char cella) {
		if(cella == '1' || cella == '2')
			return true;
		return false;
				
	}
	
	/**
	 * <p>
	 * Indica se la cella &egrave; libera.
	 * </p>
	 * 
	 * @param cella Un carattere della scacchiera.
	 * @return <i>true</i> se la cella vale <i>0</i>, false altrimenti.
	 */
	private static boolean nonMiScontroConUnaScia(char cella) {
		if(cella == '0')
			return true;
		return false;
				
	}
	
	/**
	 * <p>
	 * Stampa la scacchiera relativa allo stato corrente,<br>
	 * visualizzando le posizioni di ciascun giocatore e tutte le caselle da essi occupate.<br>
	 * <ul>
	 * <li>1: Casella occupata dal giocatore 1</li>
	 * <li>2: Casella occupata dal giocatore 2</li>
	 * </ul>
	 * </p>
	 * 
	 * @param stato Un intero che indica il numero dello stato.
	 */
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

	/**
	 * <p>
	 * Stampa il risultato di un singolo scontro tra due giocatori,
	 * indicando il vincitore e lo sconfitto e il numero di punti ad essi assegnati,<br>
	 * <i>3 punti</i> per il vincitore e <i>0 punti</i> per lo sconfitto.<br>
	 * In caso di pareggio a ciascun giocatore viene assegnato <i>1 punto</i>.
	 * </p>
	 * 
	 * @param vincitore Un intero che rappresenta il vincitore dello scontro.<br>
	 * <ul>
	 * <li>0: Pareggio</li>
	 * <li>1: Vittoria per il giocatore 1</li>
	 * <li>2: Vittoria per il giocatore 2</li>
	 * </ul>
	 * @param g1 Il giocatore 1.
	 * @param g2 Il giocatore 2.
	 */
	private static void stampaRisultato(int vincitore, Giocatore g1, Giocatore g2) {
		
		System.out.println("Mosse Giocatore (1): " + g1.getNome() + " = " + g1.getMosse());
		System.out.println("Mosse Giocatore (2): " + g2.getNome() + " = "  + g2.getMosse());
		System.out.println();
		
		System.out.println("Esito dell'eterna lotta tra bots...\n");
		
		switch(vincitore) {
		
		case 0:
			g1.assegnaPunteggio(1);
			g1.incrementaPareggi();
			g2.assegnaPunteggio(1);
			g2.incrementaPareggi();
			System.out.println("Pareggio");
			System.out.println("Giocatore (1) * " + g1.getNome() + " * : 1 punto");
			System.out.println("Giocatore (2) * " + g2.getNome() + " * : 1 punto");
			break;
				
		case 1:
			g1.assegnaPunteggio(3);
			g1.incrementaVittorie();
			g2.incrementaSconfitte();
			System.out.println("Vincitore Giocatore (1) * " + g1.getNome() + " * : 3 punti");
			System.out.println("Sconfitto Giocatore (2) * " + g2.getNome() + " * : 0 punti");
			break;
		
		case 2:
			g2.assegnaPunteggio(3);
			g2.incrementaVittorie();
			g1.incrementaSconfitte();
			System.out.println("Vincitore Giocatore (2) * " + g2.getNome() + " * : 3 punti");
			System.out.println("Sconfitto Giocatore (1) * " + g1.getNome() + " * : 0 punti");
			break;
		}

	}
	
	/**
	 * <p>
	 * Resetta le celle della scacchiera inserendo in ognuna il carattere <i>0</i>.<br>
	 * Azzera le liste di mosse dei giocatori.
	 * </p>
	 * 
	 * @param g1 Il giocatore 1.
	 * @param g2 Il giocatore 2.
	 */
	private static void azzeraGioco(Giocatore g1, Giocatore g2) {
		
		for(int i = 0; i < DIM; i++)
			for(int j = 0; j < DIM; j++)
				scacchiera[i][j] = '0';
		
		g1.getMosse().clear();
		g1.getMosseAvversario().clear();
		
		g2.getMosse().clear();
		g2.getMosseAvversario().clear();
		
	}
	
	/**
	 * <p>
	 * Stampa la classifica finale al termine degli scontri tra tutti i giocatori,<br>
	 * indicando in prima posizione il giocatore che ha totalizzato il maggior numero di punti<br>
	 * e in ultima posizione quello che ha ottenuto il punteggio più basso.
	 * </p>
	 */
	private static void stampaClassifica() {
		
		System.out.println("\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n");
		
		System.out.println("Classifica finale\n");
	
		Collections.sort(giocatori, new Comparator<Giocatore>() {
		    @Override
		    public int compare(Giocatore o1, Giocatore o2) {
		        return ((Integer) o1.getPunteggio()).compareTo((Integer) o2.getPunteggio());
		    }
		});
		
		int numeroGiocatori = giocatori.size();
		
		for(int i = numeroGiocatori - 1; i >= 0; i--) {
			
			Giocatore g = giocatori.get(i);
			
			System.out.print("#" + (numeroGiocatori - i) + " " + g.getNome());
			System.out.println(spaziToString(37 - g.getNome().length()) + "Punteggio:   " + g.getPunteggio());

		}
	
		System.out.println();
	}
	
	/**
	 * <p>
	 * Stampa per ciascun giocatore il numero di vittorie, pareggi e sconfitte,<br>
	 * e la percentuale di vittorie, pareggi e sconfitte.
	 * </p>
	 */
	private static void stampaStatistiche() {
		System.out.println("Statistiche\n");
		
		int numeroGiocatori = giocatori.size();
		int partitePerGiocatore = (2 * numeroGiocatori) - 2;
		
		for(int i = numeroGiocatori - 1; i >= 0; i--) {
			
			Giocatore g = giocatori.get(i);

			System.out.println("#" + (numeroGiocatori - i) + " " + g.getNome() + "\n" +
			"\tNumero vittorie:\t" + g.getVittorie() +
			"\tNumero pareggi:\t\t" + g.getPareggi() +
			"\tNumero sconfitte:\t" + g.getSconfitte() + "\n" +
			"\tPercentuale vittorie:\t" + (((float) (g.getVittorie())) / ((float) (partitePerGiocatore))) * 100 + "%" +
			"\tPercentuale pareggi:\t" + (((float) (g.getPareggi())) / ((float) (partitePerGiocatore))) * 100 + "%" +
			"\tPercentuale sconfitte:\t" + (((float) (g.getSconfitte())) / ((float) (partitePerGiocatore))) * 100 + "%\n");
			
		}
	
	}
	
	/**
	 * <p>
	 * Restituisce una stringa di spazi.
	 * </p>
	 * 
	 * @param numeroSpazi Il numero di spazi da inserire nella stringa.
	 * @return La stringa di spazi.
	 */
	private static String spaziToString(int numeroSpazi) {
		String spazi = "";
		
		for(int i = 0; i < numeroSpazi; i++)
			spazi += " ";
		
		return spazi;
	}

}
