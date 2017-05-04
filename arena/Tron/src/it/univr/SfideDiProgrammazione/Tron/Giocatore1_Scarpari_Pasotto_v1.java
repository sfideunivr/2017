package it.univr.SfideDiProgrammazione.Tron;

public class Giocatore1_Scarpari_Pasotto_v1 extends Giocatore {

	public Giocatore1_Scarpari_Pasotto_v1(String nomeGiocatore, int i, int j) {
		super(nomeGiocatore, i, j);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected char scegliMossaDaFare(int posXavv, int posYavv, char[][] scacchiera) {
		int numLibereSinistra = 0;
		int numLibereDestra = 0;
		int numLibereAlto = 0;
		int numLibereBasso = 0;
		
		numLibereSinistra = contaLibereSinistra(scacchiera);
		numLibereDestra = contaLibereDestra(scacchiera);
		numLibereAlto = contaLibereAlto(scacchiera);
		numLibereBasso = contaLibereBasso(scacchiera);
		
		int[] valori = {numLibereSinistra, numLibereDestra, numLibereAlto, numLibereBasso};
		char[] direzioni = {'L', 'R', 'U', 'D'};
		
		ordina(valori, direzioni);
		
		int posizioneMassimo = getPosizioneMassimo(valori, 4);

		for(int i = 0; i < 4; i++)
			System.out.print(direzioni[i] + ": " + valori[i] + "; ");
		System.out.println();
		
		return direzioni[posizioneMassimo];
	}
	
	int getPosizioneMassimo(int[] valori, int N) {
		int posizioneMassimo = 0;
		
		for(int i = 0; i < N; i++)
			if(valori[i] > valori[posizioneMassimo])
				posizioneMassimo = i;
		
		return posizioneMassimo;
	}
	
	void ordina(int[] valori, char[] direzioni) {
		bubbleSort(valori, direzioni, 4);
	}
	
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
	
	int contaLibereSinistra(char[][] scacchiera) {
		int caselleLibere = 0;
		int i = posX, j = posY - 1;
		
		while(libera(scacchiera, i, j--))
			caselleLibere++;
		
		return caselleLibere;
	}
	
	int contaLibereDestra(char[][] scacchiera) {
		int caselleLibere = 0;
		int i = posX, j = posY + 1;
		
		while(libera(scacchiera, i, j++))
			caselleLibere++;

		return caselleLibere;
	}
	
	int contaLibereAlto(char[][] scacchiera) {
		int caselleLibere = 0;
		int i = posX - 1, j = posY;
		
		while(libera(scacchiera, i--, j))
			caselleLibere++;
		
		return caselleLibere;
	}
	
	int contaLibereBasso(char[][] scacchiera) {
		int caselleLibere = 0;
		int i = posX + 1, j = posY;
		
		while(libera(scacchiera, i++, j))
			caselleLibere++;
		
		return caselleLibere;
	}
	
	private boolean libera(char[][] scacchiera, int posX, int posY) {
		return posX >= 0 && posX < DIM && posY >= 0 && posY < DIM && scacchiera[posX][posY] == '0';
	}

}
