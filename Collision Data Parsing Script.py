import csv
import operator

def parseData(toRead: ".csv file to read", toWrite: ".txt file to write to"):

    collisionCount = dict() #{street+cross : count}
    addressMap = dict() #{street+cross : (lat, long)

    with open(toRead+".csv", newline='') as read:
        reader = csv.reader(read)
        i = 0
        for row in reader:
            i += 1
            if i == 400000:
                z = csv.reader(reader, delimiter='\t')

                newTxt = open(toWrite+".txt","w")
                """
                for item in collisionCount:
                    if collisionCount[item] > 1:
                        print(item +": "+ str(collisionCount[item]))
                """
                print(max(collisionCount.items(), key=operator.itemgetter(1))[0])
                print(max(collisionCount.items(), key=operator.itemgetter(1))[1])
                try:
                    print(addressMap[max(collisionCount.items(), key=operator.itemgetter(1))[0]])
                except:
                    pass
                for element in collisionCount:
                    if collisionCount[element] > 50:
                        risk = collisionCount[element]//50
                        #breakTuple = 
                        print(addressMap[element] + " | " + str(collisionCount[element])+" "+ str(risk))
                        newTxt.write(addressMap[element] + " " + str(collisionCount[element]) +" "+ str(risk) + "\n")
                newTxt.close()
                return

            try:
                #print(row[15].split()[0]+"\n"+ row[16].split()[0] +"\n"+ row[17]+"\n""\n")
                index = row[15] + row[16]
                #collisionCount[row[15].split()[0]+" "+row[16].split()[0]] = collisionCount.get(row[15]+row[16], 0) + 1
                collisionCount[index] = collisionCount.get(index, 0) + 1
                if index not in addressMap:
                    addressMap[index] = row[17]
            except:
                pass
def main():
    print("Note: Please exempt all file extensions when entering file names.")
    print("Name of .csv file to read from:")
    toRead = input()
    print("Name of .txt file to write to:")
    toWrite = input()
    parseData(toRead, toWrite)
    
main()

"""
Format:

[15] = "Address"
[16] = "Cross Street"
[17] = "(float, float)"

"""
