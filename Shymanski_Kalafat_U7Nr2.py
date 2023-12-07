# summe(list[list[int]) : int
# Voraussetzung: die Liste besteht aus Zahlen
# Ergebnis: Die Summe aller geraden Elemente der Listen ist geliefert
# Effekt: keiner


"""
Tests:
summe([1, 2, 3, 4]) == 6
summe([2, 4, 6, 8]) == 20
summe([1, 3, 5, 7]) == 0
"""


def summe(xs):
    if len(xs) == 0:
        return 0
    else:
        if xs[0] % 2 == 0:
            return xs[0] + summe(xs[1:len(xs)])
        return summe(xs[1:len(xs)])


print(summe([1, 2, 3, 4]))
print(summe([2, 4, 6, 8]))
print(summe([1, 3, 5, 7]))

'''
I.
Für eine leere Liste ist die Summe aller geraden Elemente gleich 0.
Genau das ist geliefert, wenn die Liste leer ist.
II.
Hier geht man davon aus, dass die Summe für eine Teilliste korrekt berechnet wird,
das gilt für Liste xs[1:len(xs)]. Dann muss man zeigen, dass das Ergebnis für die
ganze Liste auch korrekt berechnet wird.
Fall 1: xs[0] ist gerade, dann wird das Element zur korrekt berechneten Summe aller
anderen geraden Elemente addiert, somit ist die gesamte Summe aller geraden Elemente
geliefert => Algorithmus ist korrekt
Fall 2: xs[0] ist ungerade. Dann wird einfach die korrekt berechnete Summe von
xs[1:len(xs)] geliefert => Algorithmus ist korrekt
III.
Jedes Mal wird die Länge der Liste um 1 kleiner, deswegen Rekursionsanker len(xs) == 0
wird auf jeden Fall erreicht
'''
