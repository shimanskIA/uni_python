def get_histogram(word: str):
    hist = dict()
    length = len(word)
    for i in range(length):
        counter = 1
        if word[i] not in hist.keys():
            for j in range(i + 1, length):
                if word[i] == word[j]: counter += 1
            hist[word[i]] = counter
    return hist


def inverse_dna(dna: str):
    length = len(dna)
    inversed_dna = list()
    for i in range(length):
        inversed_dna.append(dna[length - i - 1])
    for i in range(length):
        if inversed_dna[i] == 'A': inversed_dna[i] = 'T'
        elif inversed_dna[i] == 'T': inversed_dna[i] = 'A'
        elif inversed_dna[i] == 'C': inversed_dna[i] = 'G'
        else: inversed_dna[i] = 'C'
    return "".join(inversed_dna)


# aufgabe 1a
'''
1a - ...
1b - hauptsächlich KdP5 (aber bisschen KdP2 auch)
1c - KdP2
1d - KdP2
2a - KdP2
2b - KdP2
2c - KdP2
2d - KdP2
'''

# aufgabe 1b
'''
Bei ganzen Zahlen kann es zu einem Überschlag kommen, wenn man versucht eine größere Zahl darzustellen als das vom
Datentyp erlaubt ist. In Python gibt es dieses Problem nicht, da können ganze Zahlen beliebig groß sein. Dafür sind aber
alle Rechenoperationen in Python aufwendiger als in manchen hardwarenäheren Sprachen. Das passiert dadurch, dass in diesen
Sprachen die ganzen Zahlen in Binärsystem dargestellt werden, also als eine Folge von 1 und 0. 
Das Problem bei Gleitkommazahlen ist, dass es unendlich viele Gleitkommazahlen gibt, allerdings kann man nur 2^32 oder 2^64 
Zahlen in einem Rechner darstellen. Das führt dazu, dass alle Gleitkommazahlen können nur angenähert dargestellt werden.
Auch in Python gibts dieses Problem. 
In Python könnte das mit folgendem Beispiel veranschaulicht werden:

fact = 1
for i in range(1, 100):
    fact *= 0.1
print(str(fact).split('e')[0])

Als Ausgabe bekommt man eine Kommazahl, die tatsächlich außer 1 und 0 noch andere Zahlen enthält, was eigentlich laut den
mathematischen Regeln nicht der Fall sein soll. 
Also statt einer Zahl 1.0e-99 bekommt man so etwas wie 1.0000000000000065e-99. So kann man sehen, dass der Unterschied zwischen
zwei Zahlen wegen mehreren Annäherungen merkwürdig wurde. Oder wenn man zum Beispiel im Program oben 1000 statt 100 eingibt, dann
kriegt man überhaupt eine 0, da die Zahl so klein wird, dass der Rechner sie nicht mehr von 0 unterscheiden kann. 

Für den Überlauf kann man sich eine Programmiersprache vorstellen, bei der 5 Bits für eine ganze Zahl eingeräumt werden.
Das bedeutet, dass die größte positive ganze Zahl, die in dieser Sprache möglich ist, ist 2^4-1, also 15:
Im Binärsystem wäre das 01111. Wenn man noch eine 1 dazu addiert, dann bekommt man:
01111
00001
10000 = -16
So ist man nicht bei 16 sondern bei -16 wegen des Überlaufs
Und wenn man versucht in dieser Sprache so eine Anweisung zu schreiben, wie:
a = 16
dann kriegt man einen Fehler
'''

print(get_histogram(input("Gib ein Wort oder ein Satz ein:\n")))  # aufgabe 1c
print(inverse_dna(input("Gib eine DNA-Sequenz ein:\n")))  # aufgabe 1d
