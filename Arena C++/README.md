Questa è una versione C/C++ di un'arena per i bot di TRON che sfrutta le IPC.
L'idea generale di esecuzione dell'Arena è la seguente:
- si lancia l'Arbitro, passando come argomenti da riga di comando gli eseguibili dei 2 bot che si sfideranno;
- l'Arbitro si occupa delle operazioni di fork ed esecuzione dei 2 bot specificati;
- a questo punto, l'Arbitro istanzia una coda di messaggi che servirà per stabilire un canale di comunicazione (bidirezionale) inter-processo tra bot1-Arbitro e bot2-Arbitro;
- si ha ora l'inizializzazione della plancia di gioco locale ad Arbitro;
- l'Arbitro entrerà dunque in un ciclo che si articola in 4 fasi fondamentali:
  1. attesa di ricezione della mossa scelta da bot1 e bot2;
  2. aggiornamento della plancia di gioco sulla base delle mosse ricevute;
  3. verifica delle condizioni dei due giocatori (controlla, in sostanza, se si sia giunti alla vittoria di un bot o al pareggio);
  4. se non si sono manifestate le condizioni del punto 3, si procede all'invio dell'ultima mossa all'avversario (a bot1 sarà segnalata l'ultima mossa di bot2, a bot2 sarà segnalata l'ultima mossa di bot1).
  
Il programma termina se una delle condizioni specificate al punto 3 viene soddisfatta.
Secondo questo schema, è evidente che i bot partecipanti sono costretti all'uso di primitive legate alle code di messaggi per la gestione della comunicazione.
Per questo motivo, si è sviluppata una piattaforma di funzioni wrapper (racchiusa in un header) da rendere disponibile ai programmatori che vogliano usare questa arena. Tale piattaforma è implementata in client.hpp.
In essa, sono implementate le procedure fondamentali per l'invio e la ricezione di messaggi: tali operazione verrebbero così rese trasparenti ai programmatori di bots, permettendo loro di concentrarsi sulla strategia di gioco, svincolandoli da tediosi dettagli implementativi. Agli sviluppatori si richiede soltanto l'uso accorto delle API date.

In client.hpp, si trovano tre funzioni:
- bool connect(): è una funzione accessoria che si occupa di verificare che la coda di messaggi relativa all'arena Arbitro sia già presente tra le IPC dell'ambiente di lancio. È buona norma, anche se non indispensabile, che ogni bot compia un controllo preliminare sull'esistenza di detta coda prima di procedere al calcolo di una mossa. 
- void send(long key, move_t n): funzione che funge da API per l'invio di un messaggio nella coda di messaggi associata ad Arbitro. L'argomento key definisce l'identificativo del giocatore-bot che desidera comunicare la mossa n all'Arbitro.
- void wait_response(long key, move_t *buf): un programma-bot che chiami wait-response si pone in attesa della ricezione di un messaggio, da parte dell'Arbitro, riguardante la mossa compiuta dall'avversario.
