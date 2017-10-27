import math
f1 = open("brown-train.txt")

#Read training corpus, pre-processing steps 1-2
train = f1.read().splitlines()
for sen in range(len(train)): #for each sentence in training corpus
    train[sen] = "<s> " + train[sen] + " </s>"
    train[sen] = train[sen].lower()

trainWords = {"": 0} #declare data dictioinary for training corpus word types mapped to their count

trainTokens = 0 #token count for training corpus
#store training corpus word types and their counts, count trainTokens
for sen in range(len(train)): #for each sentence in training corpus
    s = train[sen].split() #list of trainWords in sentence
    for word in range(len(s)): #for each word in sentence
        trainTokens += 1 #count trainTokens
        w = s[word] #current word in sentence
        if(w in trainWords): #check if word is in dictionary
            trainWords[w] += 1
        else:
            trainWords[w] = 1

#read brown-test corpus, solve question 3
f2 = open("brown-test.txt")
test1 = f2.read().splitlines()
test1Words = {"": 0} #declare data dictionary for test corpus 1(brown-test)
typeCount = 0 #count number of word types in test not seen in training
tokenCount = 0 #count number of tokens in test not seen in training
totalTokens = 0 #total number of tokens in test
for sen in range(len(test1)):
    s = test1[sen].split()
    for word in range(len(s)):
        totalTokens += 1
        w = s[word]
        if(w in test1Words):
            test1Words[w] += 1
            if(w not in trainWords): #check if token not in training
                tokenCount += 1
        else:
            test1Words[w] = 1
            if(w not in trainWords): #check if word type not in training
                typeCount += 1
print("Question 3 \nBrown-test")
print("Percentage of tokens not seen in training:")  
print(tokenCount/totalTokens) #percentage of tokens not seen in training
print("Percentage of word types not seen in training:")
print(typeCount/(len(test1Words) - 1)) #percentage of types not seen in training

#read learner-test corpus, solve question 3
f3 = open("learner-test.txt")
test2 = f3.read().splitlines()
test2Words = {"": 0} #declare data dictionary for test corpus 2(learner-test)
typeCount = 0 #count number of word types in test not seen in training
tokenCount = 0 #count number of tokens in test not seen in training
totalTokens = 0 #total number of tokens in test
for sen in range(len(test2)):
    s = test2[sen].split()
    for word in range(len(s)):
        totalTokens += 1
        w = s[word]
        if(w in test2Words):
            test2Words[w] += 1
            if(w not in trainWords): #check if token not in training
                tokenCount += 1
        else:
            test2Words[w] = 1
            if(w not in trainWords): #check if word type not in training
                typeCount += 1
print("Learner-test")
print("Percentage of tokens not seen in training:") 
print(tokenCount/totalTokens) #percentage of tokens not seen in training
print("Percentage of word types not seen in training:")
print(typeCount/(len(test2Words) - 1)) #percentage of types not seen in training
print("")

#Pre-processing step 3
space = " "
numOfUnk = 0
for sen in range(len(train)):
    s = train[sen].split()
    for word in range(len(s)):
        w = s[word]
        if(trainWords[w] == 1):
            numOfUnk += 1
            s[word] = "<unk>"
            train[sen] = space.join(s)

#Question 1 and 2
wordTypes = len(trainWords.keys()) - 1
print("Question 1")
print(wordTypes)
print("\nQuestion2")
print(trainTokens)
print("")

#train unigram model
unigramCount = {"": 0} #unigram word types mapped to their count
unigram = {"": 0} #unigram word types mapped to their probability
for sen in range(len(train)):
    s = train[sen].split()
    for word in range(len(s)):
        w = s[word]
        if(w in unigramCount):
            unigramCount[w] += 1
        else:
            unigramCount[w] = 1
for word in unigramCount:
    unigram[word] = unigramCount[word]/trainTokens

#train bigram model
bigramCount = {"": 0} #bigram word types mapped to their count
bigrams = {"": 0} #bigram word types mapped to their probability
for sen in range(len(train)):
    s = train[sen].split()
    for word in range(1, len(s)):
        i = s[word - 1]
        j = s[word]
        ij = i + " " + j
        if(ij in bigramCount):
            bigramCount[ij] += 1
        else:
            bigramCount[ij] = 1
for sen in range(len(train)):
    s = train[sen].split()
    for word in range(1, len(s)):
        i = s[word - 1]
        j = s[word]
        ij = i + " " + j
        bigrams[ij] = bigramCount[ij]/unigramCount[i]

#train bigram model with Add-one smoothing
smoothedBigramCount = {"": 0}
smoothedBigrams = {"": 0}
for sen in range(len(train)):
    s = train[sen].split()
    for word in range(1, len(s)):
        i = s[word - 1]
        j = s[word]
        ij = i + " " + j
        if(ij in smoothedBigramCount):
            smoothedBigramCount[ij] += 1
        else:
            smoothedBigramCount[ij] = 1
for sen in range(len(train)):
    s = train[sen].split()
    for word in range(1, len(s)):
        i = s[word - 1]
        j = s[word]
        ij = i + " " + j
        smoothedBigrams[ij] = (smoothedBigramCount[ij] + 1)/unigramCount[i]

#Question 4
test1Bigrams = {"": 0} #declare data dictionary for test corpus 1 bigrams(brown-test)
typeCount = 0 #count number of bigram types in test not seen in training
tokenCount = 0 #count number of bigram tokens in test not seen in training
totalBigrams = 0 #total number of bigrams in test
for sen in range(len(test1)):
    s = test1[sen].split()
    for word in range(1, len(s)):
        totalBigrams += 1
        i = s[word - 1]
        j = s[word]
        ij = i + " " + j
        if(ij in test1Bigrams):
            test1Bigrams[ij] += 1
            if(ij not in bigrams): #check if bigram token not in training
                tokenCount += 1
        else:
            test1Bigrams[ij] = 1
            if(ij not in bigrams): #check if word type not in training
                typeCount += 1
print("Question 4 \nBrown-test")
print("Percentage of bigram tokens not seen in training:")
print(tokenCount/totalBigrams) #percentage of bigram tokens not seen in training
print("Percentage of bigram types not seen in training:")
print(typeCount/(len(test1Bigrams) - 1)) #percentage of bigram types not seen in training

test2Bigrams = {"": 0} #declare data dictionary for test corpus 2 bigrams(learner-test)
typeCount = 0 #count number of bigram types in test not seen in training
tokenCount = 0 #count number of bigram tokens in test not seen in training
totalBigrams = 0 #total number of bigrams in test
for sen in range(len(test2)):
    s = test2[sen].split()
    for word in range(1, len(s)):
        totalBigrams += 1
        i = s[word - 1]
        j = s[word]
        ij = i + " " + j
        if(ij in test2Bigrams):
            test2Bigrams[ij] += 1
            if(ij not in bigrams): #check if bigram token not in training
                tokenCount += 1
        else:
            test2Bigrams[ij] = 1
            if(ij not in bigrams): #check if word type not in training
                typeCount += 1
print("Learner-test")
print("Percentage of bigram tokens not seen in training:")
print(tokenCount/totalBigrams) #percentage of bigram tokens not seen in training
print("Percentage of bigram types not seen in training:")
print(typeCount/(len(test2Bigrams) - 1)) #percentage of bigram types not seen in training
print("")

#Question 5 and 6
string1 = "<s> he was laughed off the screen . </s>"
s1 = string1.split()
string2 = "<s> there was no compulsion behind them . </s>"
s2 = string2.split()
string3 = "<s> i look forward to hearing your reply . </s>"
s3 = string3.split()
m = 28 #number of tokens

print("Question 5")
ps1 = 1 #Probability of sentence 1
for word in range(1, len(s1)):
    w = s1[word]
    if(w in unigram):
        ps1 *= unigram[w]
    else:
        ps1 = 0
        break
print("logP(" + ' '.join(s1) + ") under unigram model:")
if(ps1 != 0):
    lps1 = math.log(ps1,2) #log probability
else:
    lps1 = 0
print(lps1)
pr1 = math.pow(2, -lps1/m) #perplexity of sentence 1
print("Perplexixty: " + str(pr1))

ps1 = 1
for word in range(1, len(s1)):
    i = s1[word - 1]
    j = s1[word]
    ij = i + " " + j
    if(ij in bigrams):
        ps1 *= (bigrams[ij])
    else:
        ps1 = 0
        break
print("logP(" + ' '.join(s1) + ") under bigram model:")
if(ps1 != 0):
    lps1 = math.log(ps1,2)
else:
    lps1 = 0
print(lps1)
pr1 = math.pow(2, -lps1/m) #perplexity of sentence 1
print("Perplexixty: " + str(pr1))

ps1 = 1
for word in range(1, len(s1)):
    i = s1[word - 1]
    j = s1[word]
    ij = i + " " + j
    if(ij in smoothedBigrams):
        ps1 *= (bigrams[ij])
    else:
        ps1 = 0
        break
print("logP(" + ' '.join(s1) + ") under smoothed bigram model:")
if(ps1 != 0):
    lps1 = math.log(ps1,2)
else:
    lps1 = 0
print(lps1)
pr1 = math.pow(2, -lps1/m) #perplexity of sentence 1
print("Perplexixty: " + str(pr1))

ps2 = 1 #Probability of sentence 2
for word in range(1, len(s2)):
    w = s2[word]
    if(w in unigram):
        ps2 *= unigram[w]
    else:
        ps2 = 0
        break
print("logP(" + ' '.join(s2) + ") under unigram model:")
if(ps2 != 0):
    lps2 = math.log(ps2,2) #log probability
else:
    lps2 = 0
print(lps2)
pr2 = math.pow(2, -lps2/m) #perplexity of sentence 2
print("Perplexixty: " + str(pr2))

ps2 = 1
for word in range(1, len(s2)):
    i = s2[word - 1]
    j = s2[word]
    ij = i + " " + j
    if(ij in bigrams):
        ps2 *= (bigrams[ij])
    else:
        ps2 = 0
        break
print("logP(" + ' '.join(s2) + ") under bigram model:")
if(ps2 != 0):
    lps2 = math.log(ps2,2)
else:
    lps2 = 0
print(lps2)
pr2 = math.pow(2, -lps2/m) #perplexity of sentence 2
print("Perplexixty: " + str(pr2))

ps2 = 1
for word in range(1, len(s2)):
    i = s2[word - 1]
    j = s2[word]
    ij = i + " " + j
    if(ij in smoothedBigrams):
        ps2 *= (bigrams[ij])
    else:
        ps2 = 0
        break
print("logP(" + ' '.join(s2) + ") under smoothed bigram model:")
if(ps2 != 0):
    lps2 = math.log(ps2,2)
else:
    lps2 = 0
print(lps2)
pr2 = math.pow(2, -lps2/m) #perplexity of sentence 2
print("Perplexixty: " + str(pr2))

ps3 = 1 #Probability of sentence 3
for word in range(1, len(s3)):
    w = s3[word]
    if(w in unigram):
        ps3 *= unigram[w]
    else:
        ps3 = 0
        break
print("logP(" + ' '.join(s3) + ") under unigram model:")
if(ps3 != 0):
    lps3 = math.log(ps3,2) #log probability
else:
    lps3 = 0
print(lps3)
pr3 = math.pow(2, -lps3/m) #perplexity of sentence 3
print("Perplexixty: " + str(pr3))

ps3 = 1
for word in range(1, len(s3)):
    i = s3[word - 1]
    j = s3[word]
    ij = i + " " + j
    if(ij in bigrams):
        ps3 *= (bigrams[ij])
    else:
        ps3 = 0
        break
print("logP(" + ' '.join(s3) + ") under bigram model:")
if(ps3 != 0):
    lps3 = math.log(ps3,2)
else:
    lps3 = 0
print(lps3)
pr3 = math.pow(2, -lps3/m) #perplexity of sentence 3
print("Perplexixty: " + str(pr3))

ps3 = 1
for word in range(1, len(s3)):
    i = s3[word - 1]
    j = s3[word]
    ij = i + " " + j
    if(ij in smoothedBigrams):
        ps3 *= (bigrams[ij])
    else:
        ps3 = 0
        break
print("logP(" + ' '.join(s3) + ") under smoothed bigram model:")
if(ps3 != 0):
    lps3 = math.log(ps3,2)
else:
    lps3 = 0
print(lps3)
pr3 = math.pow(2, -lps3/m) #perplexity of sentence 3
print("Perplexixty: " + str(pr3))

#Question 7
pt1 = 1
for sen in range(len(test1)):
    s = test1[sen].split()
    for word in range(len(s)):
        w = s[word]
        if(w in unigram):
            pt1 *= unigram[w]



