#include <cstdio>
#include <cstdlib>
#include <unistd.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>

#define DIM 8
#define QUEUE_KEY 667
#define FROM_B1_TO_REF 1
#define FROM_B2_TO_REF 2
#define FROM_REF_TO_B1 3
#define FROM_REF_TO_B2 4
#define ID1 1
#define ID2 2

enum dir_t {up, down, left, right};

typedef struct
{
    int x_pos;
    int y_pos;
}
pos;

typedef struct
{
    pos position;
    dir_t next;
}
move_t;

typedef struct
{
    long mtype;
    move_t move;
}
message_t;
