#include "server.hpp"

char field[DIM][DIM];
bool player1_crashed, player2_crashed;
int queue_id;
move_t buffer_bot1, buffer_bot2;

void check_match_condition(char *, char *);
void init_game(pos *, pos *);
void wait_two_moves();
void switch_and_send();

int main(int argc, char *argv[])
{
    // Assumiamo che l'arbitro riceva come parametri da riga di comando gli eseguibili dei 2 bots.
    pid_t bot1;
    pid_t bot2;
    pos player1;
    pos player2;

    srand(time(NULL));

    if((queue_id=msgget(QUEUE_KEY, 0777 | IPC_CREAT | IPC_EXCL))==-1)
    {
        printf("Msgget fallita.\n");
        exit(1);
    }

    if((bot1=fork())==-1)
    {
        printf("Fork di %s fallita.\n", argv[1]);
        exit(1);
    }
    else if(bot1==0)
    {
        execlp(argv[1], argv[1], abs(rand()), ID1, NULL);
        printf("Execlp di %s fallita.\n", argv[1]);
        exit(1);
    }
    else	// Processo padre (arbitro).
    {
        if((bot2=fork())==-1)
        {
            printf("Fork di %s fallita.\n", argv[2]);
            exit(1);
        }
        else if(bot2==0)
        {
            execlp(argv[2], argv[2], abs(rand()), ID2, NULL);
            printf("Execlp di %s fallita.\n", argv[2]);
            exit(1);
        }
        else    // Processo padre (arbitro).
        {            
            init_game(&player1, &player2);

            while(1)
            {
                wait_two_moves();

                if(buffer_bot1.position.x_pos==player1.x_pos && buffer_bot1.position.y_pos==player1.y_pos)
                {
                    switch(buffer_bot1.next)
                    {
                        case up:

                        /* Condizioni verificate: - uscita dal bordo della scacchiera;
                                                 - impatto con una scia (non si compiono distinzioni);
                                                 - impatto con l'altro giocatore. */

                            if((player1.x_pos-1)<0 || field[player1.x_pos-1][player1.y_pos]!=0)
                            {
                                player1_crashed=true;
                                player2_crashed=field[player1.x_pos-1][player1.y_pos]==2 ? true : false;
                            }    
                            else
                            {
                                field[player1.x_pos][player1.y_pos]=3;
                                field[--player1.x_pos][player1.y_pos]=1;
                            }

                        break;

                        case down:

                            if((player1.x_pos+1)<0 || field[player1.x_pos+1][player1.y_pos]!=0)
                            {
                                player1_crashed=true;
                                player2_crashed=field[player1.x_pos+1][player1.y_pos]==2 ? true : false;
                            }    
                            else
                            {
                                field[player1.x_pos][player1.y_pos]=3;
                                field[++player1.x_pos][player1.y_pos]=1;
                            }

                        break;

                        case left:

                            if((player1.y_pos-1)<0 || field[player1.x_pos][player1.y_pos-1]!=0)
                            {
                                player1_crashed=true;
                                player2_crashed=field[player1.x_pos][player1.y_pos-1]==2 ? true : false;
                            }    
                            else
                            {
                                field[player1.x_pos][player1.y_pos]=3;
                                field[player1.x_pos][--player1.y_pos]=1;
                            }

                        break;

                        case right:

                            if((player1.y_pos+1)<0 || field[player1.x_pos][player1.y_pos+1]!=0)
                            {
                                player1_crashed=true;
                                player2_crashed=field[player1.x_pos][player1.y_pos+1]==2 ? true : false;
                            }    
                            else
                            {
                                field[player1.x_pos][player1.y_pos]=3;
                                field[player1.x_pos][++player1.y_pos]=1;
                            }
                        break;

                        default:
                            printf("Errore indefinito per bot1.\n");
                            exit(1);
                        break;
                    }
                }
                else
                {
                    printf("Errore di posizionamento di bot1.\n");
                    exit(1);
                }

                if(buffer_bot2.position.x_pos==player2.x_pos && buffer_bot2.position.y_pos==player2.y_pos)
                {
                    switch(buffer_bot2.next)
                    {
                        case up:

                            if((player2.x_pos-1)<0 || field[player2.x_pos-1][player2.y_pos]!=0)
                            {
                                player2_crashed=true;
                                player1_crashed=field[player2.x_pos-1][player2.y_pos]==2 ? true : false;
                            }    
                            else
                            {
                                field[player2.x_pos][player2.y_pos]=3;
                                field[--player2.x_pos][player2.y_pos]=1;
                            }

                        break;

                        case down:

                            if((player2.x_pos+1)<0 || field[player2.x_pos+1][player2.y_pos]!=0)
                            {
                                player2_crashed=true;
                                player1_crashed=field[player2.x_pos+1][player2.y_pos]==2 ? true : false;
                            }    
                            else
                            {
                                field[player2.x_pos][player2.y_pos]=3;
                                field[++player2.x_pos][player2.y_pos]=1;
                            }

                        break;

                        case left:

                            if((player2.y_pos-1)<0 || field[player2.x_pos][player2.y_pos-1]!=0)
                            {
                                player2_crashed=true;
                                player1_crashed=field[player2.x_pos][player2.y_pos-1]==2 ? true : false;
                            }    
                            else
                            {
                                field[player2.x_pos][player2.y_pos]=3;
                                field[player2.x_pos][--player2.y_pos]=1;
                            }

                        break;

                        case right:

                            if((player2.y_pos+1)<0 || field[player2.x_pos][player2.y_pos+1]!=0)
                            {
                                player2_crashed=true;
                                player1_crashed=field[player2.x_pos][player2.y_pos+1]==2 ? true : false;
                            }    
                            else
                            {
                                field[player2.x_pos][player2.y_pos]=3;
                                field[player2.x_pos][++player2.y_pos]=1;
                            }

                        break;

                        default:
                            printf("Errore indefinito per bot2.\n");
                            exit(1);
                        break;
                    }
                }
                else
                {
                    printf("Errore di posizionamento di bot2.\n");
                    exit(1);
                }

                check_match_condition(argv[1], argv[2]);
                switch_and_send();
            }
        }
    }

    return 0;
}

// TODO: da modificare.
void init_game(pos *player1, pos *player2)
{
    for(int i=0; i<DIM; i++)
        for(int j=0; j<DIM; j++)
            field[i][j]=0;

    player1->x_pos=DIM-1;
    player1->y_pos=0;
    player2->x_pos=DIM-1;
    player2->y_pos=DIM-1;
    field[player1->x_pos][player1->y_pos]=1;      //Posizione iniziale giocatore 1.
    field[player2->x_pos][player2->y_pos]=2;      //Posizione iniziale giocatore 2.

    player1_crashed=false;
    player2_crashed=false;
}

void check_match_condition(char *p1, char *p2)
{
    if(player1_crashed==true && player2_crashed==true)
    {
        printf("Il match tra %s e %s si è concluso con un PAREGGIO.\n", p1, p2);
        exit(0);
    }

    if(player1_crashed==true && player2_crashed==false)
    {
        printf("Il match tra %s e %s si è concluso con la VITTORIA di %s.\n", p1, p2, p2);
        exit(0);
    }

    if(player1_crashed==false && player2_crashed==true)
    {
        printf("Il match tra %s e %s si è concluso con la VITTORIA di %s.\n", p1, p2, p1);
        exit(0);
    }

    /* Ovviamente, se nessuno dei due giocatori ha impattato, allora la funzione check_match_condition non compierà alcuna azione. */
}

void wait_two_moves()
{
    if((msgrcv(queue_id, &buffer_bot1, sizeof(move_t), FROM_B1_TO_REF, 0))<sizeof(move_t)) ||
        (msgrcv(queue_id, &buffer_bot2, sizeof(move_t), FROM_B2_TO_REF, 0))<sizeof(move_t))
    {
        printf("Msgrcv fallita: n° bytes letti incongruente.\n");
        exit(1);    
    }
}

void switch_and_send()
{
    message

    buffer_bot2.mtype=FROM_REF_TO_B2;
    buffer_bot1.mtype=FROM_REF_TO_B1;

    if(msgsnd(queue_id, &buffer_bot2, sizeof(move_t), 0)==-1 ||
        msgsnd(queue_id, &buffer_bot1, sizeof(move_t), 0)==-1)
    {
        printf("Msgsnd fallita.\n");
        exit(1);
    }
}
