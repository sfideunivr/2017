import random


# Sistema di punteggi per le euristiche
def scoring(out):
    reward = 0.0
    if ((input == "R") and (out == "P")):
        reward += 1.0
    elif ((input == "P") and (out == "S")):
        reward += 1.0
    elif ((input == "S") and (out == "R")):
        reward += 1.0
    else:
        reward += 0.0
    return reward


# History String Matching
if (input == ""):
    # Inizializzo tutte le variabili necessarie per le euristiche
    test = ""
    rewards = [0,0,0,0,0,0,0,0,0]
    scoreProb = 0
    scoreRot0 = 0
    scoreRot1 = 0
    scoreRot2 = 0
    scoreRndm = 0
    scoreAlwaysR = 0
    scoreAlwaysP = 0
    scoreAlwaysS = 0
    # Primo outuput random
    outHSM = random.choice(['R', 'P', 'S'])
    rewards[0] = scoreHSM = 0
    e_ta_R = 0.0
    e_ta_P = 0.0
    e_ta_S = 0.0
    v_ra_R = 0.0
    v_ra_P = 0.0
    v_ra_S = 0.0
    # Inizializzo vettore con le probabilita' di risposta alla mia giocata Rock
    Pr_R = [0.0,0.0,0.0]
    # Probabilità che avversario risponda Rock al mio Rock
    Pr_R[0] = 1.0/3.0
    # Probabilità che avversario risponda Paper al mio Rock
    Pr_R[1] = 1.0/3.0
    # Probabilità che avversario risponda Scissor al mio Rock
    Pr_R[2] = 1.0/3.0
    # Inizializzo vettore con le probabilita' di risposta alla mia giocata Paper
    Pr_P = [0.0,0.0,0.0]
    # Probabilità che avversario risponda Rock al mio Paper
    Pr_P[0] = 1.0/3.0
    # Probabilità che avversario risponda Paper al mio Paper
    Pr_P[1] = 1.0/3.0
    # Probabilità che avversario risponda Scissor al mio Paper
    Pr_P[2] = 1.0/3.0
    # Inizializzo vettore con le probabilita' di risposta alla mia giocata Scissor
    Pr_S = [0.0,0.0,0.0]
    # Probabilità che avversario risponda Rock al mio Scissor
    Pr_S[0] = 1.0/3.0
    # Probabilità che avversario risponda Paper al mio Scissor
    Pr_S[1] = 1.0/3.0
    # Probabilità che avversario risponda Scissor al mio Scissor
    Pr_S[2] = 1.0/3.0
    output_1 = ""
    output_2 = ""
else:
    # Calcolando lo scoring qui posso confrontare l'input del passo precedente (perche'
    # se sono in else e' valorizzato) con il corrispondente output
    test += input
    scoreHSM += scoring(outHSM)
    rewards[0] = scoreHSM
    for length in range(min(5, len(test)-1), 0, -1):
        trova = test[-length:]
        pos = test.rfind(trova, 0, (len(test) - length))
        if (pos == -1):
            outHSM = random.choice(['R', 'P', 'S'])
        else:
            if (test[pos+length] == "R"):
                outHSM = "P"
            elif (test[pos+length] == "S"):
                outHSM = "R"
            else:
                outHSM = "S"


# AI - Probabilita' condizionata
if (output_2 == ""):
    outProb = random.choice(['R', 'P', 'S'])
    rewards[8] = scoreProb
else:
    scoreProb += scoring(outProb)
    if (input == "R") and (output_2 == "R"):
        e_ta_R += 1.0
        v_ra_R += 1.0
        Pr_R[0] = (1.0 + e_ta_R)/(3.0 + v_ra_R)
    elif (input == "P") and (output_2 == "R"):
        e_ta_P += 1.0
        v_ra_R += 1.0
        Pr_R[1] = (1.0 + e_ta_P)/(3.0 + v_ra_R)
    elif (input == "S") and (output_2 == "R"):
        e_ta_S += 1.0
        v_ra_R += 1.0
        Pr_R[2] = (1.0 + e_ta_S)/(3.0 + v_ra_R)
    if (input == "R") and (output_2 == "P"):
        e_ta_R += 1.0
        v_ra_P += 1.0
        Pr_P[0] = (1.0 + e_ta_R)/(3.0 + v_ra_P)
    elif (input == "P") and (output_2 == "P"):
        e_ta_P += 1.0
        v_ra_P += 1.0
        Pr_P[1] = (1.0 + e_ta_P)/(3.0 + v_ra_P)
    elif (input == "S") and (output_2 == "P"):
        e_ta_S += 1.0
        v_ra_P += 1.0
        Pr_P[2] = (1.0 + e_ta_S)/(3.0 + v_ra_P)
    if (input == "R") and (output_2 == "S"):
        e_ta_R += 1.0
        v_ra_S += 1.0
        Pr_R[0] = (1.0 + e_ta_R)/(3.0 + v_ra_S)
    elif (input == "P") and (output_2 == "S"):
        e_ta_P += 1.0
        v_ra_S += 1.0
        Pr_R[1] = (1.0 + e_ta_P)/(3.0 + v_ra_S)
    elif (input == "S") and (output_2 == "S"):
        e_ta_S += 1.0
        v_ra_S += 1.0
        Pr_R[2] = (1.0 + e_ta_S)/(3.0 + v_ra_S)
# calcolo max tra le probabilita' del vettore corrispondente alla mia mossa giocata al passo -1
if (output_1 == "R"):
    max_Pr_R = 0
    maxIndex_Pr_R = 0
    for i in range(len(Pr_R)):
        if Pr_R[i] > max:
            max = Pr_R[i]
            maxIndex_Pr_R = i
    if (maxIndex_Pr_R == 0):
        outProb = "R"
    elif (maxIndex_Pr_R == 1):
        outProb = "P"
    elif (maxIndex_Pr_R == 2):
        outProb = "S"
elif (output_1 == "P"):
    max_Pr_P = 0
    maxIndex_Pr_P = 0
    for i in range(len(Pr_P)):
        if (Pr_P[i] > max):
            max = Pr_P[i]
            maxIndex_Pr_P = i
    if (maxIndex_Pr_P == 0):
        outProb = "R"
    elif (maxIndex_Pr_P == 1):
        outProb = "P"
    elif (maxIndex_Pr_P == 2):
        outProb = "S"
elif (output_1 == "S"):
    max_Pr_S = 0
    maxIndex_Pr_S = 0
    for i in range(len(Pr_S)):
        if (Pr_S[i] > max):
            max = Pr_S[i]
            maxIndex_Pr_S = i
    if (maxIndex_Pr_S == 0):
        outProb = "R"
    elif (maxIndex_Pr_S == 1):
        outProb = "P"
    elif (maxIndex_Pr_S == 2):
        outProb = "S"


# Rotation by 0
if (input == ""):
    outRot0 = random.choice(['R', 'P', 'S'])
    rewards[1] = scoreRot0 = 0
else:
    outRot0 = {'R':'R', 'P':'P', 'S':'S'}[input]
    scoreRot0 += scoring(outRot0)
    rewards[1] = scoreRot0


# Rotation by 1
if (input == ""):
    outRot1 = random.choice(['R', 'P', 'S'])
    rewards[2] = scoreRot1 = 0
else:
    outRot1 = {'R':'P', 'P':'S', 'S':'R'}[input]
    scoreRot1 += scoring(outRot1)
    rewards[2] = scoreRot1


# Rotation by 2
if (input == ""):
    outRot2 = random.choice(['R', 'P', 'S'])
    rewards[3] = scoreRot2 = 0
else:
    outRot2 = {'R':'S', 'P':'R', 'S':'P'}[input]
    scoreRot2 += scoring(outRot2)
    rewards[3] = scoreRot2


# Random
outRndm = random.choice(['R', 'P', 'S'])
scoreRndm += scoring(outRndm)
rewards[4] = scoreRndm


# Always Rock
outAlwaysR = 'R'
scoreAlwaysR += scoring(outAlwaysR)
rewards[5] = scoreAlwaysR


# Always Paper
outAlwaysP = 'P'
scoreAlwaysP += scoring(outAlwaysP)
rewards[6] = scoreAlwaysP


# Always Scissor
outAlwaysS = 'S'
scoreAlwaysS += scoring(outAlwaysS)
rewards[7] = scoreAlwaysS


# Gioco la strategia con score massimo
max = 0
maxIndex = 0
for i in range(len(rewards)):
    if rewards[i] > max:
        max = rewards[i]
        maxIndex = i
if (maxIndex == 0):
    output = outHSM
elif (maxIndex == 1):
    output = outRot0
elif (maxIndex == 2):
    output = outRot1
elif (maxIndex == 3):
    output = outRot2
elif (maxIndex == 4):
    output = outRndm
elif (maxIndex == 5):
    output = outAlwaysR
elif (maxIndex == 6):
    output = outAlwaysP
elif (maxIndex == 7):
    output = outAlwaysS
elif (maxIndex == 8):
    output = outProb

output_2 = output_1
output_1 = output
