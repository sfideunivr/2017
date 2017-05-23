package it.univr.SfideDiProgrammazione.Tron;

import java.util.ArrayList;

public abstract class Giocatore {

	/**
	 * Il nome del bot che rappresenta il giocatore.
	 */
	protected String nome;

	/**
	 * La riga in cui si trova il giocatore.
	 */
	protected int posX;
	
	/**
	 * La colonna in cui si trova il giocatore.
	 */
	protected int posY;
	
	/**
	 * La lista delle mosse effettuale dal giocatore.
	 */
	protected ArrayList<Character> mosse;
	
	/**
	 * La lista delle mosse effettuale dal giocatore avversario.
	 */
	protected ArrayList<Character> mosseAvversario;
	
	/**
	 * Il punteggio totalizzato dal giocatore durante gli scontri.
	 */
	protected int punteggio;
	
	/**
	 * Il numero di vittorie ottenute dal giocatore.
	 */
	protected int vittorie;
	
	/**
	 * Il numero di pareggi ottenuti dal giocatore.
	 */
	protected int pareggi;
	
	/**
	 * Il numero di sconfitte ottenute dal giocatore.
	 */
	protected int sconfitte;
	
	/**
	 * La dimensione, in numero di righe e colonne, della scacchiera (quadrata) di gioco.
	 */
	public static final int DIM = 9;

	/**
	 * <p>
	 * Inizializza le propriet&agrave; della classe <i>Giocatore</i>.
	 * </p>
	 * 
	 * @param nome Il nome del bot del giocatore.
	 * @param i La riga in cui &grave; inizialmente posizionato il giocatore.
	 * @param j La colonna in cui &grave; inizialmente posizionato il giocatore.
	 */
	public Giocatore(String nome, int i, int j) {
		this.nome = nome;
		this.posX = i; 
		this.posY = j;
		this.mosse = new ArrayList<Character>();
		this.mosseAvversario = new ArrayList<Character>();
		this.punteggio = 0;
		this.vittorie = 0;
		this.pareggi = 0;
		this.sconfitte = 0;
	}

	/**
	 * <p>
	 * Cacola la mossa del giocatore, secondo un qualche algoritmo implementato nel singolo bot.
	 * </p>
	 * 
	 * @param iAvversario La riga in cui si trova il giocatore avversario.
	 * @param jAvversario La colonna in cui si trova il giocatore avversario.
	 * @param scacchiera La configurazione della scacchiera con le posizioni dei giocatori e le caselle da essi occupate.
	 * @return Un carattere che indica la mossa che il giocatore vuole effettuare.<br>
	 * <ul>
	 * <li>U per UP (alto)</li>
	 * <li>D per DOWN (basso)</li>
	 * <li>L per LEFT (sinistra)</li>
	 * <li>R per RIGHT (destra)</li>
	 * </ul>
	 */
	protected abstract char calcolaMossa(int iAvversario, int jAvversario, char[][] scacchiera);

	/**
	 * <p>
	 * Ritorna il nome del bot del giocatore.
	 * </p>
	 * 
	 * @return Il nome del bot del giocatore.
	 */
	protected String getNome() {
		return this.nome;
	}
	
	/**
	 * <p>
	 * Imposta il nome del bot del giocatore.
	 * </p>
	 * 
	 * @param nome Il nome del bot del giocatore.
	 */
	protected void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * <p>
	 * Ritorna la riga in cui si trova il giocatore.
	 * </p>
	 * 
	 * @return La riga in cui si trova il giocatore.
	 */
	protected int getX() {
		return this.posX;
	}
	
	/**
	 * <p>
	 * Imposta la riga in cui si trova il giocatore.
	 * </p>
	 * 
	 * @param i La riga in cui si trova il giocatore.
	 */
	protected void setX(int i) {
		this.posX = i;
	}
	
	/**
	 * <p>
	 * Ritorna la colonna in cui si trova il giocatore.
	 * </p>
	 * 
	 * @return La colonna in cui si trova il giocatore.
	 */
	protected int getY() {
		return this.posY;
	}
	
	/**
	 * <p>
	 * Imposta la colonna in cui si trova il giocatore.
	 * </p>
	 * 
	 * @param j La colonna in cui si trova il giocatore.
	 */
	protected void setY(int j) {
		this.posY = j;
	}

	/**
	 * <p>
	 * Aggiorna la posizione del giocatore in base alla mossa scelta.<br>
	 * <ul>
	 * <li>U: Decrementa di uno il valore della riga (sposta il giocatore verso l'alto di una posizione).</li>
	 * <li>D: Incrementa di uno il valore della riga (sposta il giocatore verso il basso di una posizione).</li>
	 * <li>L: Decrementa di uno il valore della colonna (sposta il giocatore a sinistra di una posizione).</li>
	 * <li>L: Incrementa di uno il valore della colonna (sposta il giocatore a destra di una posizione).</li>
	 * </ul>
	 * Aggiunge la mossa alla lista delle mosse del giocatore.
	 * </p>
	 * 
	 * @param mossa La mossa calcolata precedentemente dal giocatore.
	 */
	protected void aggiornaPosizione(char mossa) {
		switch(mossa) {
			case 'U': this.posX--; break;
			case 'D': this.posX++; break;
			case 'L': this.posY--; break;
			case 'R': this.posY++; break;
		}
		mosse.add(mossa);
	}
	
	/**
	 * <p>
	 * Ritorna la lista di mosse effettuate dal giocatore.
	 * </p>
	 * 
	 * @return La lista di mosse effettuate dal giocatore.
	 */
	protected ArrayList<Character> getMosse() {
		return this.mosse;
	}
	
	/**
	 * <p>
	 * Imposta la lista di mosse del giocatore.
	 * </p>
	 * 
	 * @param mosse La lista di mosse del giocatore.
	 */
	protected void setMosse(ArrayList<Character> mosse) {
		this.mosse = mosse;
	}

	/**
	 * <p>
	 * Ritorna la lista di mosse effettuate dall'avversario.
	 * </p>
	 * 
	 * @return La lista di mosse effettuate dall'avversario.
	 */
	protected ArrayList<Character> getMosseAvversario() {
		return this.mosseAvversario;
	}
	
	/**
	 * <p>
	 * Aggiunge alla lista di mosse dell'avversario l'ultima mossa effettuata dall'avversario.
	 * </p>
	 * 
	 * @param mossaAvversario L'ultima mossa effettuata dall'avversario
	 */
	protected void setMosseAvversario(char mossaAvversario) {
		this.mosseAvversario.add(mossaAvversario);
	}
	
	/**
	 * <p>
	 * Ritorna il punteggio totalizzato dal giocatore.
	 * </p>
	 * 
	 * @return Il punteggio totalizzato dal giocatore.
	 */
	protected int getPunteggio() {
		return this.punteggio;
	}
	
	/**
	 * <p>
	 * Imposta il punteggio totalizzato dal giocatore.
	 * </p>
	 * 
	 * @param punteggio Il punteggio totalizzato dal giocatore.
	 */
	protected void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}
	
	/**
	 * <p>
	 * Assegna al giocatore il punteggio totalizzato alla fine di uno scontro con un altro giocatore<br>
	 * e lo somma al punteggio totalizzato dal giocatore fino a quel momento.
	 * </p>
	 * 
	 * @param punteggio Il punteggio ottenuto dal giocatore in base all'esito di un singolo scontro.
	 */
	protected void assegnaPunteggio(int punteggio) {
		this.punteggio += punteggio;
	}
	
	/**
	 * <p>
	 * Ritorna il numero di vittorie ottenute dal giocatore.
	 * </p>
	 * 
	 * @return Il numero di vittorie ottenute dal giocatore.
	 */
	protected int getVittorie() {
		return this.vittorie;
	}
	
	/**
	 * <p>
	 * Imposta il numero di vittorie ottenute dal giocatore.
	 * </p>
	 * 
	 * @param vittorie Il numero di vittorie ottenute dal giocatore.
	 */
	protected void setVittorie(int vittorie) {
		this.vittorie = vittorie;
	}
	
	/**
	 * <p>
	 * Incrementa di uno il numero di vittorie del giocatore.
	 * </p>
	 */
	protected void incrementaVittorie() {
		this.vittorie++;
	}
	
	/**
	 * <p>
	 * Ritorna il numero di pareggi ottenuti
	 * 
	 * @return Il numero di pareggi ottenuti dal giocatore.
	 */
	protected int getPareggi() {
		return this.pareggi;
	}
	
	/**
	 * <p>
	 * Imposta il numero di pareggi ottenuti dal giocatore.
	 * </p>
	 * 
	 * @param pareggi Il numero di pareggi ottenuti dal giocatore.
	 */
	protected void setPareggi(int pareggi) {
		this.pareggi = pareggi;
	}
	
	/**
	 * <p>
	 * Incrementa di uno il numero di pareggi del giocatore.
	 * </p>
	 */
	protected void incrementaPareggi() {
		this.pareggi++;
	}
	
	/**
	 * <p>
	 * Ritorna il numero di sconfitte ottenute dal giocatore.
	 * </p>
	 * 
	 * @return Il numero di sconfitte ottenute dal giocatore.
	 */
	protected int getSconfitte() {
		return this.sconfitte;
	}
	
	/**
	 * <p>
	 * Imposta il numero di sconfitte ottenute dal giocatore.
	 * </p>
	 * 
	 * @param vittorie Il numero di sconfitte ottenute dal giocatore.
	 */
	protected void setSconfitte(int sconfitte) {
		this.sconfitte = sconfitte;
	}
	
	/**
	 * <p>
	 * Incrementa di uno il numero di sconfitte del giocatore.
	 * </p>
	 */
	protected void incrementaSconfitte() {
		this.sconfitte++;
	}
	
	/**
	 * <p>
	 * Controlla se la posizione in alto rispetto a quella del giocatore sia interna alla scacchiera e libera.
	 * </p>
	 * 
	 * @param scacchiera La scacchiera di gioco.
	 * @return <i>true</i> se la posizione in alto &egrave; interna alla scacchiera e libera, <i>false</i> altrimenti.
	 */
	protected boolean liberaAlto(char[][] scacchiera) {
		return posX != 0 && scacchiera[posX - 1][posY] == '0';
	}
	
	/**
	 * <p>
	 * Controlla se la posizione in basso rispetto a quella del giocatore sia interna alla scacchiera e libera.
	 * </p>
	 * 
	 * @param scacchiera La scacchiera di gioco.
	 * @return <i>true</i> se la posizione in basso &egrave; interna alla scacchiera e libera, <i>false</i> altrimenti.
	 */
	protected boolean liberaBasso(char[][] scacchiera) {
		return posX != DIM - 1 && scacchiera[posX + 1][posY] == '0';
	}

	/**
	 * <p>
	 * Controlla se la posizione a sinistra rispetto a quella del giocatore sia interna alla scacchiera e libera.
	 * </p>
	 * 
	 * @param scacchiera La scacchiera di gioco.
	 * @return <i>true</i> se la posizione a sinistra &egrave; interna alla scacchiera e libera, <i>false</i> altrimenti.
	 */
	protected boolean liberaSinistra(char[][] scacchiera) {
		return posY != 0 && scacchiera[posX][posY - 1] == '0';
	}
	
	/**
	 * <p>
	 * Controlla se la posizione a destra rispetto a quella del giocatore sia interna alla scacchiera e libera.
	 * </p>
	 * 
	 * @param scacchiera La scacchiera di gioco.
	 * @return <i>true</i> se la posizione a destra &egrave; interna alla scacchiera e libera, <i>false</i> altrimenti.
	 */
	protected boolean liberaDestra(char[][] scacchiera) {
		return posY != DIM - 1 && scacchiera[posX][posY + 1] == '0';
	}

}
