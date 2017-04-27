Questa è una versione preliminare di un'arena per i bot di TRON.
L'idea generale di esecuzione dell'Arena è la seguente:
- si lancia l'Arbitro, passando come argomenti da riga di comando gli eseguibili dei 2 bot che si sfideranno;
- l'Arbitro si occupa delle operazioni di fork ed esecuzione di dei bot specificati;
- a questo punto, l'Arbitro istanzia una coda di messaggi che servirà per stabilire un canale di comunicazione (bidirezionale) inter-processo tra bot1-Arbitro e bot2-Arbitro;
- si ha ora l'inizializzazione della plancia di gioco locale ad Arbitro;
- l'Arbitro entrerà dunque in un ciclo che si articola in 4 fasi fondamentali:
  1. attesa di ricezione della mossa scelta da bot1 e bot2;
  2. aggiornamento della plancia di gioco sulla base delle mosse ricevute;
  3. verifica delle condizioni dei due giocatori (controlla, in sostanza, se si sia giunti alla vittoria di un bot o al pareggio);
  4. se non si sono manifestate le condizioni del punto 3, si procede all'invio dell'ultima mossa all'avversario (a bot1 sarà segnalata l'ultima mossa di bot2, a bot2 sarà segnalata l'ultima mossa di bot1).
  
Il programma termine se una delle condizioni specificate al punto 3 viene soddisfatta.
Secondo questo schema, è evidente che i bot partecipanti sarebbero costretti all'uso di primitive legate alle code di messaggi per la gestione della comunicazione.
Per questo motivo, si è intenzionati a sviluppare una piattaforma di funzioni (in sostanza, un header) da rendere disponibile ai programmatori che vogliano usare questa arena.
In essa, verranno implementate le procedure fondamentali per l'invio e la ricezione di messaggi: tali operazione verrebbero così rese trasparenti ai programmatori, permettendo loro di concentrarsi sulla strategia di gioco, svincolandoli da tediosi dettagli implementativi.
