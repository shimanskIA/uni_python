import random


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


def generate_dna(length):
    dna = list()
    for i in range(length):
        rnd = random.random()
        if rnd < 0.25: dna.append('A')
        elif rnd < 0.5: dna.append('T')
        elif rnd < 0.75: dna.append('G')
        else: dna.append('C')
    return "".join(dna)


def get_similar_pos_number(dna1: str, dna2: str):
    counter = 0
    for i in range(len(dna1)):
        if dna1[i] == dna2[i]: counter += 1
    return counter


def get_similar_freq_number(dna1: str, dna2: str):
    hist1 = get_histogram(dna1)
    hist2 = get_histogram(dna2)
    counter = 0
    for key in {'A', 'T', 'G', 'C'}:
        if hist1.get(key) == hist2.get(key): counter += 1
    return counter


def start_game(length):
    dna = generate_dna(length)
    guessed_dna = str(input())
    while dna != guessed_dna:
        print(get_similar_pos_number(dna, guessed_dna))
        print(get_similar_freq_number(dna, guessed_dna))
        guessed_dna = str(input())
    print(dna)


# aufgabe 1a
'''
???
'''

# aufgabe 1b
'''
Bei ganzen Zahlen kann es zu einem Überschlag kommen, wenn man veruscht eine größere Zahl darzustellen als das vom
Datentyp erlaubt ist. In Python gibt es dieses Problem nicht, da können ganze Zahlen beliebig groß sein. Dafür sind aber
alle Rechenoperationen in Python aufwendiger als in manchen hardwarenäheren Sprachen. Das passiert dadurch, dass in diesen
Sprachen die ganzen Zahlen in Binärsystem dargestellt werden, also als eine Folge von 1 und 0. 
Das Problem bei Gleitkommazahlen ist, dass es unendlich viele Gleitkommazahlen gibt, allerdings kann man nur 2^32 oder 2^64 
Zahlen in einem Recher darstellen. Das führt dazu, das alle Gleitkommazahlen können nur angenähert dargestellt werden.
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

print(get_histogram(input()))  # aufgabe 1c
print(inverse_dna(input()))  # aufgabe 1d
dna1 = generate_dna(6)  # aufgabe 2a
dna2 = generate_dna(6)  # aufgabe 2a
print(dna1)
print(dna2)
print(get_similar_pos_number(dna1, dna2))  # aufgabe 2b
print(get_similar_freq_number(dna1, dna2))  # aufgabe 2c
start_game(6)  # aufgabe 2d
