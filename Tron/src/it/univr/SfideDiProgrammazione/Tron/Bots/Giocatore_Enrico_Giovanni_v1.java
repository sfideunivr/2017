package it.univr.SfideDiProgrammazione.Tron.Bots;

import it.univr.SfideDiProgrammazione.Tron.Giocatore;

public class Giocatore_Enrico_Giovanni_v1 extends Giocatore {
	
	boolean primaMossa = true;
	boolean giocatore1 = true;
	
	public Giocatore_Enrico_Giovanni_v1(String nome, int i, int j) {
		super(nome, i, j);
	}
	
	/*
		Cerca di riempire tutta la scacchiera.
	*/
	@Override
	protected char calcolaMossa(int posXavv, int posYavv, char[][] scacchiera) {

		if(mosse.size() == 0)
			if(posY == DIM - 1)
				giocatore1 = false;
	
		//giocatore1
		if(giocatore1) {
			if(liberaDestra(scacchiera))
				return 'R';

			else if(!liberaDestra(scacchiera) && primaMossa){
				if(caselleLibereSopra(scacchiera)>caselleLibereSotto(scacchiera)){
					primaMossa = false;
					return 'U';

				}
				if(caselleLibereSopra(scacchiera)<=caselleLibereSotto(scacchiera)){
					primaMossa = false;
					return 'D';
				}
			}
			//teste dei giocatori una sopra e una sotto(riempimento normale)
			if(primaMossa == false && ((posX< 4 && posXavv>4)||(posX>4 && posXavv<4))){			
				return riempiScacchieraGiocatoreUno(scacchiera);
			}
			if(primaMossa == false && ((posX >4 && posXavv >4)||(posX <4 && posXavv <4))){
				if(posX >4 && posXavv >4){
					if(liberaBasso(scacchiera)){
						return 'D';
					}else {
						return riempiScacchieraGiocatoreUno(scacchiera);
					}
					
				}

				if(posX <4 && posXavv <4){
					if(liberaAlto(scacchiera)){
						return 'U';
					}else {
						return riempiScacchieraGiocatoreUno(scacchiera);
						
					}
					
				}
			}

		}
		//giocatore2	
		else {
			if(liberaSinistra(scacchiera))
				return 'L';

			else if(!liberaSinistra(scacchiera) && primaMossa){
				if(caselleLibereSopra(scacchiera)>caselleLibereSotto(scacchiera)){
					primaMossa = false;
					return 'U';

				}
				if(caselleLibereSopra(scacchiera)<=caselleLibereSotto(scacchiera)){
					primaMossa = false;
					return 'D';
				}
			}
			//teste dei giocatori una sopra e una sotto(riempimento normale)
			if(primaMossa == false && ((posX< 4 && posXavv>4)||(posX>4 && posXavv<4))){			
				return riempiScacchieraGiocatoreDue(scacchiera);
			}
			if(primaMossa == false && ((posX >4 && posXavv >4)||(posX <4 && posXavv <4))){
				if(posX >4 && posXavv >4){
					if(liberaBasso(scacchiera)){
						return 'D';
					}else {
						return riempiScacchieraGiocatoreDue(scacchiera);
					}
					
				}

				if(posX <4 && posXavv <4){
					if(liberaAlto(scacchiera)){
						return 'U';
					}else {
						return riempiScacchieraGiocatoreDue(scacchiera);
						
					}
					
				}
			}
		}
		return 'c';
	}

	private char riempiScacchieraGiocatoreUno(char[][] scacchiera){
		if(liberaDestra(scacchiera))
				return 'R';
			else if(liberaBasso(scacchiera))
				return 'D';
			else if(liberaSinistra(scacchiera))
				return 'L';
			else
				return 'U';

	}
	private char riempiScacchieraGiocatoreDue(char[][] scacchiera){
		if(liberaSinistra(scacchiera))
				return 'L';
			else if(liberaBasso(scacchiera))
				return 'D';
			else if(liberaDestra(scacchiera))
				return 'R';
			else
				return 'U';

	}

	private int caselleLibereSopra(char[][] scacchiera){
		int caselleLibere = 0;
		for(int i=0; i<=3; i++){
			for(int j=0; j<=8; j++){
				if(scacchiera[i][j] == '0'){
					caselleLibere++;
				}
			}
		}
		return caselleLibere;
	}
	private int caselleLibereSotto(char[][] scacchiera){
		int caselleLibere = 0;
		for(int i=5; i<=8; i++){
			for(int j=0; j<=8; j++){
				if(scacchiera[i][j] == '0'){
					caselleLibere++;
				}
			}
		}
		return caselleLibere;
	}
}