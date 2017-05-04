package it.univr.SfideDiProgrammazione.Tron;

import java.util.ArrayList;
import java.util.Random;

public class bot_enrico_giovanni extends Giocatore {

	public bot_enrico_giovanni(String nomeGiocatore, int i, int j) {
		super(nomeGiocatore, i, j);
		// TODO Auto-generated constructor stub
	}
	
	/*
		Cerca di riempire tutta la scacchiera.
	*/
	@Override
	protected char scegliMossaDaFare(int posXavv, int posYavv, char[][] scacchiera) {
		boolean giocatore1 = true;
		boolean primaMossa = false;
	
		if(storicoMosse.size() <= 6){
			primaMossa = true;
			
		}
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
				return riempiScacchiera(scacchiera);
			}
			if(primaMossa == false && ((posX >4 && posXavv >4)||(posX <4 && posXavv <4))){
				if(posX >4 && posXavv >4){
					if(liberaBasso(scacchiera)){
						return 'D';
					}else {
						return riempiScacchiera(scacchiera);
					}
					
				}

				if(posX <4 && posXavv <4){
					if(liberaAlto(scacchiera)){
						return 'U';
					}else {
						return riempiScacchiera(scacchiera);
						
					}
					
				}
			}

		}
		//giocatore2	
		else {
			if(liberaSinistra(scacchiera))
				return 'L';
			else if(liberaAlto(scacchiera))
				return 'U';
			else if(liberaDestra(scacchiera))
				return 'R';
			else
				return 'D';
		}
		return 'c';
	}
	
	private boolean liberaSinistra(char[][] scacchiera) {
		return posY != 0 && scacchiera[posX][posY - 1] == '0';
	}

	private boolean liberaDestra(char[][] scacchiera) {
		return posY != DIM - 1 && scacchiera[posX][posY + 1] == '0';
	}
	
	private boolean liberaAlto(char[][] scacchiera) {
		return posX != 0 && scacchiera[posX - 1][posY] == '0';
	}
	
	private boolean liberaBasso(char[][] scacchiera) {
		return posX != DIM - 1 && scacchiera[posX + 1][posY] == '0';
	}


	private char riempiScacchiera(char[][] scacchiera){
		if(liberaDestra(scacchiera))
				return 'R';
			else if(liberaBasso(scacchiera))
				return 'D';
			else if(liberaSinistra(scacchiera))
				return 'L';
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
