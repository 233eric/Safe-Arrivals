"""
Converts:
    BL = BLVD
    AV = AVE
    WY = WAY
"""

def script(filename: ".txt with no extension"):
    with open(filename+".txt", "r") as f, open(filename+"New.txt", "w") as f2:
        contents = f.readlines()
        for row in contents:
            splt = row.split()
            tmp = []
            for i in range(len(splt)):
                if splt[i] == "BL":
                    tmp.append("BLVD ")
                elif splt[i] == "AV":
                    tmp.append("AVE ")
                elif splt[i] == "WY":
                    tmp.append("WAY ")
                else:
                    tmp.append(splt[i] +" ")
            f2.write("".join(tmp)+"\n")
            print("".join(tmp)+"\n")

script("parsedCollisions")
