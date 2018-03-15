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

                        newFormat = rmWS(element)

                        ## converts str: "(lat, long)" into an iterable list type
                        latlong = addressMap[element][1:len(addressMap[element])-1].split(", ")
                        willWrite = str(latlong[0] + " " + latlong[1] + " " + str(collisionCount[element]) +" "+ newFormat +"\n")
                        newTxt.write(willWrite)
                        print(willWrite)
                        
                newTxt.close()
                reparse(toWrite+".txt", 'data.txt')

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
    
def rmWS(s):
    tmp = []
    for i in range(len(s)):
        if s[i] != " ":
            tmp.append(s[i])
        elif s[i] == " " and s[i+1] != " ":
            tmp.append(" ")
        elif i+1 == len(s)-1 and s[len(s)-1] != " ":
            tmp.append(s[i])
    return "".join(tmp)

def separateSt(s):
    
    return[s[0:2], s[2:len(s)]]

def reparse(toRead, toWrite):
    reader = open(toRead, 'r')
    writer = open(toWrite, 'w')
    contents = reader.readlines()
    for row in contents:
        tmp = []
        splt = row.split()
        for i in range(len(splt)):
            if i != len(splt)-2:
                tmp.append(splt[i])
                tmp.append(" ")
            else:
                tmp.append(splt[len(splt)-2][0:2])
                tmp.append(" ")
                tmp.append(splt[len(splt)-2][2:len(splt[len(splt)-2])])
                tmp.append(" ")
        tmp = "".join(tmp)
        writer.write(tmp+"\n")
        print(tmp)
    writer.close()
    reader.close()
   
main()
