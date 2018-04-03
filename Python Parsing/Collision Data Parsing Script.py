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
                
                newTxt = open(toWrite+".txt","w")
                
                print(max(collisionCount.items(), key=operator.itemgetter(1))[0])
                print(max(collisionCount.items(), key=operator.itemgetter(1))[1])
                try:
                    print(addressMap[max(collisionCount.items(), key=operator.itemgetter(1))[0]])
                except:
                    pass
                for element in collisionCount:
                    if collisionCount[element] > 50:
                        
                        ## converts str: "(lat, long)" into an iterable list type
                        latlong = addressMap[element][1:len(addressMap[element])-1].split(", ")
                        
                        print((latlong[0] + "," + latlong[1] + "," + str(collisionCount[element]) +"\n"))

                        separatedStreets = element.split(",")
                        st1 = changeStType(removeExcessSpace(separatedStreets[0]))
                        st2 = changeStType(removeExcessSpace(separatedStreets[1]))
                        
                        newTxt.write(str(latlong[0]) + "," + str(latlong[1]) + "," + str(collisionCount[element]) + "," + st1 + ","+ st2 +"\n")
                newTxt.close()
                return

            try:
                
                index = row[15] +","+ row[16]
                if i == 100000: print(index)
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

def removeExcessSpace(street):
    tmp = street.split(" ")
    newStr = []
    for i in range(len(tmp)):
        if tmp[i] != "":
            newStr.append(tmp[i])
            if i < len(tmp)-1:
                newStr.append(" ")
    return "".join(newStr)

def changeStType(street):
    tmp = street.split()
    newStr = []
    for i in range(len(tmp)):
        if tmp[i] == "BL":
            tmp.append("BLVD ")
        elif tmp[i] == "AV":
            tmp.append("AVE ")
        elif tmp[i] == "WY":
            tmp.append("WAY ")
        else:
            tmp.append(tmp[i] +" ")
    return "".join(tmp[:len(tmp)-1])

main()

"""
Format:
[15] = "Address"
[16] = "Cross Street"
[17] = "(float, float)"
"""
