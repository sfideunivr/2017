from random import randint


def str2int(s):
    if s == "R":
        return 1
    elif s == "P":
        return 2
    else:
        return 3


def int2str(i):
    if i == 1:
        return "R"
    elif i == 2:
        return "P"
    else:
        return "S"


def allocate(size):
    return allocateM(size, 1)


def allocateM(rows, cols):
    return [[[] for i in range(rows)] for j in range(cols)]


playMax = 500
curSeq = []
choice = randint(1, 3)

userChoice = str2int(input)
curSeq.append(userChoice)
output = int2str(choice)

seqN = len(curSeq)
choiceArr = [0, 0, 0]
sequences = allocate(seqN)

for i in range(2, seqN + 1):
    cellN = seqN + 1 - i
    curCell = allocate(cellN)

    for j in range(0, cellN):
        curCell[j] = curSeq[j:j + i - 1]

    sequences[i] = curCell

leadN = seqN - 1
leadSequences = allocateM(leadN, 2)

for i in range(leadN):
    leadSequences[i][0] = [curSeq[seqN - i + 1:seqN]]
    leadSequences[i][1] = [0]

for i in range(leadN):
    curLeadN = len(leadSequences[i][0])
    curCell = sequences[curLeadN]
    curCellN = len(curCell)

    for j in range(curCellN):
        curCellArr = curCell[j]
        curCellArrN = len(curCellArr)
        curChoice = curCellArr[curCellArrN]

        if leadSequences[i][0] == curCellArr[0:curCellArrN - 1]:
            choiceArr[curChoice] = choiceArr[curChoice] + 3**i

    choiceIndeces = [k for k, x in enumerate(choiceArr) if x == max(choiceArr)]
    choice = choiceIndeces[randint(0, len(choiceIndeces) - 1)]

    choice = ((choice + 3) % 3) + 1 