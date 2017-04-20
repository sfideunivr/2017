import random

rockCount = 0
paperCount = 0
scissorsCount = 0

count = 0
if count < 1:
    output = random.choice(["R", "P", "S"])
else:
    if input == "":  # initialize variables for the first round
        rockCount = paperCount = scissorsCount = 0
    elif input == "R":
        rockCount += 1
    elif input == "P":
        paperCount += 1
    elif input == "S":
        scissorsCount += 1
    if rockCount > paperCount and rockCount > scissorsCount:
        output = "P"  # paper beats rock
    elif paperCount > scissorsCount:
        output = "S"  # scissors beats paper
    else:
        output = "R"  # rock beats scissors

count = count + 1