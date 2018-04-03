"""
Converts:
    BL = BLVD
    AV = AVE
    WY = WAY
"""
import csv

def script(filename: ".csv with no extension"):
    with open(filename+".csv", newline = '') as f, open(filename+"New.txt", "w") as f2:
        contents = csv.reader(f)
        for row in contents:
            formatted = (row[0].split("\t"))

            st1 = extendSt(formatted[3])
            st2 = extendSt(formatted[4])
            
            toWrite = formatted[0]+","+formatted[1]+","+formatted[2]+","+st1+","+ st2 +"\n"
            print(toWrite)
            f2.write(toWrite)
            
def extendSt(street):
    tmp = []
    splt = street.split()
    for i in range(len(splt)):
        if splt[i] == "BL":
            tmp.append("BLVD ")
        elif splt[i] == "AV":
            tmp.append("AVE ")
        elif splt[i] == "WY":
            tmp.append("WAY ")
        else:
            tmp.append(splt[i] +" ")
    return "".join(tmp[:len(tmp)])[:len("".join(tmp[:len(tmp)]))-1]

script("LA_DATA")
