package it.univr.SfideDiProgrammazione.Tron.Bots;

import it.univr.SfideDiProgrammazione.Tron.Giocatore;

public class Giocatore_Nico_v1 extends Giocatore {

	public Giocatore_Nico_v1(String nome, int i, int j) {
		super(nome, i, j);
	}

	/**
	 * Funzione per implementare una strategia. Si ha a disposizio: - char[][]
	 * scacchiera --> la scacchiera - iAvversario, jAvversario --> coordinate
	 * dell'avversaio - this.posX, this.posY --> le proprie coordinate -
	 * storicoMosseAvversario --> arrayList delle mosse dell'avversario;
	 */

	private int testaG2posX, testaG2posY;
	private int caselleLibere_sopra;
	private int caselleLibere_sotto;
	private boolean flag = true;

	@Override
	protected char calcolaMossa(int iAvversario, int jAvversario, char[][] scacchiera) {

		// System.out.println("G1: Sono in (" + posX + ", " + posY + "). ");

		if (flag) {
			inizializzazione_testa_avversario(scacchiera);
			flag = false;
		}

		if (!mosseAvversario.isEmpty())
			aggiornaTestaAvversario();

		// aggiorna il numero delle caselle libere che stanno dalla posizione
		// del giocatore in alto a dx
		this.caselleLibere_sopra(scacchiera);
		this.caselleLibere_sotto(scacchiera);

		/*
		 * System.out.println("Libere sopra = " + caselleLibere_sopra);
		 * System.out.println("Libere sotto = " + caselleLibere_sotto);
		 */

		// se lo scontro testa-testa ORIZZONTALE è imminente , scansati su/giu
		if (scontroTesteOrizzontaleImminente())
			return this.gestisciScontroOrizzontale(scacchiera);

		// se lo scontro testa-testa VERTICALE è imminente , scansati
		// destra/sinistra
		if (scontroTesteVerticaleImminente())
			return this.gestisciScontroVerticale(scacchiera);

		// se non ci sono scontri imminenti (testa distante almeno 2), copro
		// spazio inizio andando verso l'avversario, a dx o sx che sia
		if (mossaOK('R', scacchiera))
			return 'R';
		else if (mossaOK('L', scacchiera))
			return 'L';

		// in tutti gli altri casi ????
		else if (this.caselleLibere_sopra > this.caselleLibere_sotto) {
			if (mossaOK('U', scacchiera))
				return 'U';
			else
				return 'D';
		}
		else
			return 'D';
	}

	private void aggiornaTestaAvversario() {
		char ultima_mossa_avversario = mosseAvversario.get(mosseAvversario.size() - 1);
		if (ultima_mossa_avversario == 'U')
			testaG2posX--;
		else if (ultima_mossa_avversario == 'D')
			testaG2posX++;
		else if (ultima_mossa_avversario == 'R')
			testaG2posY++;
		else if (ultima_mossa_avversario == 'L')
			testaG2posY--;

		// System.out.println("Ultima mossa avvesario: " +
		// ultima_mossa_avversario + " -> testa in (" + testaG2posX + ", " +
		// testaG2posY + ")");

	}

	/*
	 * controlla se le nuove coordinate sono dentro la scacchiera e vanno su
	 * casella libera
	 */
	private boolean mossaOK(int nuovaX, int nuovaY, char[][] scacchiera) {
		// controlla se resto dentro alla scacchiera
		if ((nuovaX >= 0 && nuovaX < this.DIM) && (nuovaY >= 0 && nuovaY < this.DIM)) {
			// controlla se la la nuova casella è libera
			if (scacchiera[nuovaX][nuovaY] == '0')
				return true;
		}

		// se esco dalla scacchiera o se la casella è già visitata, ritorno
		// false
		return false;
	}

	// verifica se la mossa è lecita = dentro la scacchiera e verso una casella
	// libera
	private boolean mossaOK(char a, char[][] scacchiera) {
		// controlla se resto dentro alla scacchiera
		int nuovaX = posX, nuovaY = posY;
		switch (a) {
		case 'U':
			nuovaX = posX - 1;
			break;
		case 'D':
			nuovaX = posX + 1;
			break;
		case 'L':
			nuovaY = posY - 1;
			break;
		case 'R':
			nuovaY = posY + 1;
			break;
		}
		// controlla se è dentro la scacchiera
		if ((nuovaX >= 0 && nuovaX < this.DIM) && (nuovaY >= 0 && nuovaY < this.DIM)) {
			// controlla se la la nuova casella è libera
			if (scacchiera[nuovaX][nuovaY] == '0')
				return true;
		}

		// se esco dalla scacchiera o se la casella è già visitata, ritorno
		// false
		return false;
	}

	// controlla se nella prossima mossa potrei scontrarmi con la testa
	// avversaria in orizzontale:
	// 1 1 0 2 2
	// 1 1 X 2 2
	private boolean scontroTesteOrizzontaleImminente() {
		if (((posY + 2 == testaG2posY) && (posX == testaG2posX))
				|| ((posY - 2 == testaG2posY) && (posX == testaG2posX)))
			return true;
		return false;
	}

	// 1 1 0
	// 0 1 0
	// 0 0 0
	// 0 2 2
	private boolean scontroTesteVerticaleImminente() {
		if (((posX + 2 == testaG2posX) && (posY == testaG2posY))
				|| ((posX - 2 == testaG2posX) && (posY == testaG2posY)))
			return true;
		return false;
	}

	private char gestisciScontroOrizzontale(char[][] scacchiera) {
		// System.out.println("Scontro orrizzontale imminente!!!");
		if (mossaOK('U', scacchiera) && this.caselleLibere_sopra > this.caselleLibere_sotto) {
			// System.out.println("MossaOK -> U");
			return 'U';
		} else if (mossaOK('D', scacchiera)) {
			// System.out.println("MossaOK -> D");
			return 'D';
		}
		// se non posso fare ne U ne D, meglio accettare una patta con L o R che
		// perdere
		else {
			// System.out.println("MossaOK -> no U no D! scegli L o R");
			if (mossaOK('L', scacchiera)) {
				// System.out.print("MossaOK -> scelto L");
				return 'L';
			} else {
				// System.out.print("MossaOK -> scelto R");
				return 'R';
			}
		}
	}

	private char gestisciScontroVerticale(char[][] scacchiera) {
		// System.out.println("Scontro verticale imminente!");
		if (mossaOK('L', scacchiera)) {
			// System.out.println("MossaOK -> L");
			return 'L';
		} else if (mossaOK('R', scacchiera)) {
			// System.out.println("MossaOK -> R");
			return 'R';
		} else {
			// System.out.println("MossaOK -> no R no L! scegli U o D");
			if (mossaOK('U', scacchiera)) {
				// System.out.print("MossaOK -> scelto U");
				return 'U';
			} else {
				// System.out.print("MossaOK -> scelto D");
				return 'D';
			}

		}
	}

	// caselle libere se vado giu dalla posizione attuale (non conto le libere
	// della riga corrente)
	void caselleLibere_sotto(char[][] scacchiera) {
		int temp = 0;
		for (int x = posX + 1; x < DIM; x++) {
			for (int y = 0; y < DIM; y++) {
				// System.out.print("Controllo libere (" + x + ", " + y + ") ->
				// ");
				if (scacchiera[x][y] == '0') {
					// System.out.println("SI");
					temp++;
				}
				// else System.out.println("NO");
			}
		}
		this.caselleLibere_sotto = temp;
	}

	void caselleLibere_sopra(char[][] scacchiera) {
		int temp = 0;
		for (int x = posX - 1; x >= 0; x--) {
			for (int y = 0; y < DIM; y++) {
				// System.out.print("Controllo libere (" + x + ", " + y + ") ->
				// ");
				if (scacchiera[x][y] == '0') {
					// System.out.println("SI");
					temp++;
				}
				// else System.out.println("NO");
			}
		}
		this.caselleLibere_sopra = temp;
	}

	void inizializzazione_testa_avversario(char[][] scacchiera) {
		// capisco se io sono '1' o '2' e di conseguenza chi � l'avversario (G1
		// o G2)
		char chi_sono_io = scacchiera[this.posX][this.posY];
		char chi_e_avversario;
		if (chi_sono_io == '1')
			chi_e_avversario = '2';
		else
			chi_e_avversario = '1';

		for (int x = 0; x < DIM; x++) {
			for (int y = 0; y < DIM; y++) {
				if (scacchiera[x][y] == chi_e_avversario) {

					this.testaG2posX = x;
					this.testaG2posY = y;
				}

			}
		}
	}

}