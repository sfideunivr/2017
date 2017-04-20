# mix code composto da rFind e Boltzmann Counter, tiene un rating dei due algoritmi e lancia quello con score maggiore

import random
import math

if input == "":
  dna = ""
  combine = { 'PP': '1', 
              'PR': '2', 
              'PS': '3',
              'RP': '4',
              'RS': '5',
              'RR': '6',
              'SS': '7',
              'SP': '8',
              'SR': '9',}
  split = {   '1':'PP', 
              '2':'PR', 
              '3':'PS',
              '4':'RP',
              '5':'RS',
              '6':'RR',
              '7':'SS',
              '8':'SP',
              '9':'SR',}
  anti = {'P': 'S', 'R': 'P', 'S': 'R'}
  rockRating = scissorsRating = paperRating = 0
  rating_one = 0
  rating_two = 0
  output_one = random.choice(['R', 'P', 'S'])
else:
  if output_one != "":
    dna += combine[output_one + input]

  for length in range(min(20, len(dna)-1), 0, -1):
    search = dna[-length:]
    idx = dna.rfind(search, 0, -1)
    if idx != -1:
      answered = dna[idx + length]
      expected = split[answered][1]
      output_one = anti[expected]
    else:
      output_one = random.choice(['R', 'P', 'S'])


rockRating *= 0.95
scissorsRating *= 0.95
paperRating *= 0.95
if input == "R":
    paperRating += 0.1
    scissorsRating -= 0.1
elif input == "P":
    scissorsRating += 0.1
    rockRating -= 0.1
elif input == "S":
    rockRating += 0.1
    paperRating -= 0.1

randNum = random.random()*(math.exp(rockRating)+math.exp(scissorsRating)+math.exp(paperRating))

if randNum < math.exp(rockRating):
    output_two = "R"
elif randNum < math.exp(rockRating) + math.exp(paperRating):
    output_two = "P"
else:
    output_two = "S"

if (output_one == "R" and input == "S")or(output_one == "P" and input == "R")or(output_one == "S" and input == "P"):
  rating_one += 1 
else:
  rating_one -= 1

if (output_two == "R" and input == "S")or(output_two == "P" and input == "R")or(output_two == "S" and input == "P"):
  rating_two += 1 
else:
  rating_two -= 1
  
if(rating_one>rating_two):
  output = output_one
else:
  output = output_two
