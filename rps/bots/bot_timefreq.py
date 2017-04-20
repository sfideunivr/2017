import random

possible_inputs = ["R","P","S"];

dic_opposite_choice = {"R":"P", "P":"S", "S":"R"};

# Frequenze relative agli istanti di resto 0, 1, 2 per le mosse R, P, S
freq = [[0,0,0],[0,0,0],[0,0,0]];

if input == '':
    count = 0;

if input != '':
    # Incremento le frequenze per quella mossa in quell'istante di tempo
    freq[count%3][possible_inputs.index(input)] += 1

next = (count+1)%3

if freq[next][0] == freq[next][1] and freq[next][1] == freq[next][2]:
    output = dic_opposite_choice[random.choice(possible_inputs)];

if freq[next][0] > freq[next][1] and freq[next][0] > freq[next][2]:
    output = dic_opposite_choice[possible_inputs[0]];

if freq[next][1] > freq[next][0] and freq[next][1] > freq[next][2]:
    output = dic_opposite_choice[possible_inputs[1]];

if freq[next][2] > freq[next][0] and freq[next][2] > freq[next][0]:
    output = dic_opposite_choice[possible_inputs[2]];

if freq[next][0] == freq[next][1] and freq[next][0] > freq[next][2]:
    output = dic_opposite_choice[random.choice(possible_inputs[:2])];

if freq[next][0] == freq[next][2] and freq[next][0] > freq[next][1]:
    output = dic_opposite_choice[random.choice(["R","S"])];

if freq[next][1] == freq[next][2] and freq[next][1] > freq[next][0]:
    output = dic_opposite_choice[random.choice(possible_inputs[1:])];

count += 1