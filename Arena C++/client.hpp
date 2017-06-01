#include "server.hpp"
#include <cstdio>
#include <cstdlib>

bool connect()
{
    if((msgget(QUEUE_KEY, 0777))==-1)
    {
        printf("Msgget fallita.\n");
        return false;
    }

    return true;
}

void send(long key, move_t n)
{
    message_t mes;
    mes.mtype=key;
    mes.move=n;

    if(msgsnd(msgget(QUEUE_KEY, 0777), &mes, sizeof(move_t), 0)==-1)
    {
        printf("Msgsnd fallita.\n");
		msgctl(msgget(QUEUE_KEY, 0777), IPC_RMID, NULL);
        exit(1);
    }
}

void wait_response(long key, move_t *buf)
{
    if(msgrcv(msgget(QUEUE_KEY, 0777), buf, sizeof(move_t), key+2, 0)==-1)
    {
        printf("Msgrcv fallita.\n");
		msgctl(msgget(QUEUE_KEY, 0777), IPC_RMID, NULL);
        exit(1);
    }
}
