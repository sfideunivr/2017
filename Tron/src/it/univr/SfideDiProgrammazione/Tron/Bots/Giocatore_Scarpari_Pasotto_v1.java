package it.univr.SfideDiProgrammazione.Tron.Bots;

import it.univr.SfideDiProgrammazione.Tron.Giocatore;

public class Giocatore_Scarpari_Pasotto_v1 extends Giocatore {

	/**
	 * <p>
	 * Costruttore che richiama il costruttore della superclasse <i>Giocatore</i>.
	 * </p>
	 * 
	 * @param nome Il nome del giocatore.
	 * @param i La riga in cui si trova il giocatore.
	 * @param j La colonna in cui si trova il giocatore.
	 */
	public Giocatore_Scarpari_Pasotto_v1(String nome, int i, int j) {
		super(nome, i, j);
	}

	/**
	 * <p>
	 * Calcola la mossa del giocatore secondo un qualche algoritmo e la ritorna.<br>
	 * L'algoritmo considera la posizione corrente del giocatore sulla scacchiera,<br>
	 * individuata dalle coordinate <i>posX</i> e <i>posY</i>.<br>
	 * A questo punto, conta il numero di celle libere intorno alla posizione del giocatore.<br>
	 * Spostandosi lungo la riga verso sinistra a partire dalla posizione del giocatore<br>
	 * conta il numero di celle libere a sinistra;<br>
	 * Spostandosi lungo la riga verso destra a partire dalla posizione del giocatore<br>
	 * conta il numero di celle libere a destra;<br>
	 * Spostandosi lungo la colonna verso l'alto a partire dalla posizione del giocatore<br>
	 * conta il numero di celle libere in alto;<br>
	 * Spostandosi lungo la colonna verso il basso a partire dalla posizione del giocatore<br>
	 * conta il numero di celle libere in basso.<br><br>
	 * 
	 * Quindi, memorizza in un array i valori per le direzioni appena calcolati,<br>
	 * e in un altro array i caratteri relativi a ciascuna direzione,<br>
	 * mantenendo l'ordine rispetto ai numeri inseriti nel primo array.<br>
	 * Poi, chiama il metodo <i>ordina</i> sugli array <i>valori</i> e <i>direzioni</i><br>
	 * e ordina in senso crescente i valori numerici,<br>
	 * sempre mantendendo l'ordine per i caratteri delle direzioni.<br><br>
	 * 
	 * Infine, estrae dall'array <i>valori</i> la posizione dell'intero massimo<br>
	 * e ritorna il carattere di <i>direzioni</i> corrispondente alla posizione del massimo.
	 * </p>
	 */
	@Override
	protected char calcolaMossa(int posXavv, int posYavv, char[][] scacchiera) {
		int numLibereSinistra = 0;
		int numLibereDestra = 0;
		int numLibereAlto = 0;
		int numLibereBasso = 0;
		
		// Conta il numero di caselle libere nelle 4 direzioni intorno a una posizione.
		numLibereSinistra = contaLibereSinistra(scacchiera);
		numLibereDestra = contaLibereDestra(scacchiera);
		numLibereAlto = contaLibereAlto(scacchiera);
		numLibereBasso = contaLibereBasso(scacchiera);
		
		int[] valori = {numLibereSinistra, numLibereDestra, numLibereAlto, numLibereBasso};
		char[] direzioni = {'L', 'R', 'U', 'D'};
		
		// Ordina in modo crescente gli array del numero di caselle libere nelle 4 direzioni
		// e quello delle lettere corrispondenti alle 4 direzioni.
		ordina(valori, direzioni);
		
		int posizioneMassimo = getPosizioneMassimo(valori, 4);

		/*
		for(int i = 0; i < 4; i++)
			System.out.print(direzioni[i] + ": " + valori[i] + "; ");
		System.out.println();
		*/
		
		// Ritorna la lettera della direzione corrispondente a quella in cui è presente
		// il massimo di caselle libere rispetto alla posizione del giocatore.
		return direzioni[posizioneMassimo];
	}
	
	/**
	 * <p>
	 * Conta il numero di celle libere sulla riga a sinistra<br>
	 * rispetto alla colonna in cui si trova il giocatore.
	 * </p>
	 * 
	 * @param scacchiera La matrice che rappresenta il campo di gioco.
	 * @return Il numero di celle libere a sinistra rispetto alla posizione del giocatore.
	 */
	int contaLibereSinistra(char[][] scacchiera) {
		int libere = 0;
		int i = posX, j = posY - 1;
		
		while(libera(scacchiera, i, j--))
			libere++;
		
		return libere;
	}
	
	/**
	 * <p>
	 * Conta il numero di celle libere sulla riga a destra<br>
	 * rispetto alla colonna in cui si trova il giocatore.
	 * </p>
	 * 
	 * @param scacchiera La matrice che rappresenta il campo di gioco.
	 * @return Il numero di celle libere a destra rispetto alla posizione del giocatore.
	 */
	int contaLibereDestra(char[][] scacchiera) {
		int libere = 0;
		int i = posX, j = posY + 1;
		
		while(libera(scacchiera, i, j++))
			libere++;

		return libere;
	}
	
	/**
	 * <p>
	 * Conta il numero di celle libere sulla colonna in alto<br>
	 * rispetto alla riga in cui si trova il giocatore.
	 * </p>
	 * 
	 * @param scacchiera La matrice che rappresenta il campo di gioco.
	 * @return Il numero di celle libere in alto rispetto alla posizione del giocatore.
	 */
	int contaLibereAlto(char[][] scacchiera) {
		int libere = 0;
		int i = posX - 1, j = posY;
		
		while(libera(scacchiera, i--, j))
			libere++;
		
		return libere;
	}
	
	/**
	 * <p>
	 * Conta il numero di celle libere sulla colonna in basso<br>
	 * rispetto alla riga in cui si trova il giocatore.
	 * </p>
	 * 
	 * @param scacchiera La matrice che rappresenta il campo di gioco.
	 * @return Il numero di celle libere in basso rispetto alla posizione del giocatore.
	 */
	int contaLibereBasso(char[][] scacchiera) {
		int libere = 0;
		int i = posX + 1, j = posY;
		
		while(libera(scacchiera, i++, j))
			libere++;
		
		return libere;
	}
	
	/**
	 * <p>
	 * Controlla se una cella sia interna alla scacchiera e sia libera.
	 * </p>
	 * 
	 * @param scacchiera La matrice che rappresenta il campo di gioco.
	 * @param posX La posizione nella riga che si vuole controllare.
	 * @param posY La posizione nella colonna che si vuole controllare.
	 * @return
	 */
	private boolean libera(char[][] scacchiera, int posX, int posY) {
		return posX >= 0 && posX < DIM && posY >= 0 && posY < DIM && scacchiera[posX][posY] == '0';
	}
	
	/**
	 * <p>
	 * Invoca il metodo <i>bubbleSort()</i> per ordinare due array.
	 * </p>
	 * 
	 * @param valori L'array che contiene per ogni direzione<br>
	 * il numero di celle libere dalla posizione del giocatore.
	 * @param direzioni L'array delle direzioni intorno a una posizione.
	 */
	void ordina(int[] valori, char[] direzioni) {
		bubbleSort(valori, direzioni, 4);
	}
	
	/**
	 * <p>
	 * Ordina di pari passo, secondo l'algoritmo <i>Bubble Sort</i>, i due array ricevuti come parametro.
	 * </p>
	 * 
	 * @param valori L'array che contiene per ogni direzione<br>
	 * il numero di celle libere dalla posizione del giocatore.
	 * @param direzioni L'array delle direzioni intorno a una posizione.
	 * @param N Il numero di elementi degli array.
	 */
	void bubbleSort(int[] valori, char[] direzioni, int N) {
		int left = 0, right = N - 1;

		while(left != right) {
			for(int i = left; i < right; i++) {
				if(valori[i] > valori[i + 1]) {
					int tempValori = valori[i];
					valori[i] = valori[i + 1];
					valori[i + 1] = tempValori;
					char tempDirezioni = direzioni[i];
					direzioni[i] = direzioni[i + 1];
					direzioni[i + 1] = tempDirezioni;
				}
			}
			right--;
		}
	}
	
	/**
	 * <p>
	 * Calcola l'intero corrispondente al valore massimo in un array di interi.
	 * </p>
	 * 
	 * @param valori L'array di interi.
	 * @param N Il numero di valori nell'array di interi.
	 * @return L'intero avente valore massimo.
	 */
	int getPosizioneMassimo(int[] valori, int N) {
		int posizioneMassimo = 0;
		
		for(int i = 0; i < N; i++)
			if(valori[i] > valori[posizioneMassimo])
				posizioneMassimo = i;
		
		return posizioneMassimo;
	}

}
