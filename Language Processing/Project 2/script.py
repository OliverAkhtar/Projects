import math

file = open("training.txt")

reviews = file.read().splitlines()

numOfReviews = len(reviews) #number of documents
classCount = {} #"class" mapped to count(documents labeled with class)
classFeatureCount = {} #"class" mapped to count(features from all documents labeled with class)
featureClass = {} #"feature,class" mapped to count(feature,class)
vocab = {} #vocabulary, each word mapped to its count

for i in range(len(reviews)):
        review = reviews[i].split()
        genre = review[-1]
        if(genre in classCount):
                classCount[genre] += 1
        else:
                classCount[genre] = 1
        if(genre not in classFeatureCount):
                classFeatureCount[genre] = 0
        for word in range(len(review) - 1):
                classFeatureCount[genre] += 1
                feature = review[word]
                if(feature in vocab):
                        vocab[feature] += 1
                else:
                        vocab[feature] = 1
                key = feature + "," + genre
                if(key in featureClass):
                        featureClass[key] += 1
                else:
                        featureClass[key] = 1

vocabSize = len(vocab)

outputFile = open("movie-review.NB", "w")

outputFile.write("Prior Probabilities:\n")

priorProbClass = {} #class mapped to it's prior probability
for genre in classCount:
        priorProbClass[genre] =  classCount[genre]/numOfReviews
        outputFile.write(genre + ": " + str(priorProbClass[genre]) + " ")

outputFile.write("\n\nFeature context probabilities before add-one smoothing:\n")
for pair in featureClass:
        outputFile.write(pair + ": " + str(featureClass[pair]) + "\n")

#Add-one smoothing
outputFile.write("\n\nFeature context probabilities after add-one smoothing:\n")
for pair in featureClass:
        featureClass[pair] += 1
        outputFile.write(pair + ": " + str(featureClass[pair]) + "\n")

outputFile.close()

newReview = "fast couple shoot fly".split()
newReviewProb = {} #class mapped to probability of new review being labeled by it
maxProb = 0
maxClass = ""
for genre in classCount:
        product = priorProbClass[genre]
        for word in range(len(newReview)):
                feature = newReview[word]
                key = feature + "," + genre
                if(key in featureClass):
                        product *= (featureClass[key]/(classFeatureCount[genre] + vocabSize))
                else:
                        product *= (1/(classFeatureCount[genre] + vocabSize))
        newReviewProb[genre] = product
        if(product > maxProb):
                maxProb = product
                maxClass = genre

print(newReviewProb)
print("Most likely class: " + maxClass + " " + "Probability: " + str(newReviewProb[maxClass]))
        




