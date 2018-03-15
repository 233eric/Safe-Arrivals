"""
> change lat long first instance to average lat long
"""

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
                        #risk = collisionCount[element]//50
                        newFormat = rmWS(element)
                        #newFormat2 = '0000'
            
                        """
                        try:
                            temp = element.strip()
                            print(temp)
                        except:
                            name = element
                            print(name+"EXCEPTION")
                        """
                        print(newFormat+"NEWFORMAT")
                        print(type(newFormat))
                        ## converts str: "(lat, long)" into an iterable list type
                        latlong = addressMap[element][1:len(addressMap[element])-1].split(", ")
                        willWrite = str(latlong[0] + " " + latlong[1] + " " + str(collisionCount[element]) +" "+ newFormat +"\n")
                        newTxt.write(willWrite)
                        print(willWrite)
                        
                        #print(addressMap[element] + " | " + str(collisionCount[element]))
                        
                        
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
"""            
    if len(tmp) >= 3:
        newtmp = "".join(tmp).split()
        sep = separateSt(newtmp[1])
        #print("SEP = " + sep[0] + " " + sep[1])
        tmp2 = []
        for i in range(len(newtmp)):
            if i != 1:
                tmp2.append(newtmp[i]+" ")
            if i == 1:
                tmp2.append(sep[0]+" ")
                tmp2.append(sep[1]+" ")
        #print(tmp2)
        return "".join(tmp2)
        #print(tmp)"""
    
    #return "".join(tmp)
""" try:
        tmp2 = "".join(tmp).split(" ")
        tmp3 = [tmp[0]," ",tmp2[1][0:2]," ",tmp2[1][2:len(tmp2[1][2])-1]," ",tmp[2]]
    except:
        print(tmp,'EXCEPTION')"""
    


def separateSt(s):
    
    return[s[0:2], s[2:len(s)]]
    
main()

"""
Format:
[15] = "Address"
[16] = "Cross Street"
[17] = "(float, float)"

"""
