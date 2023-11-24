# matrix_sum(list[list[int]) : int
# Voraussetzung: die Liste besteht aus Zahlenlisten. Alle Listen da sind
# gleich groß und die Außenliste ist auch genauso groß
# Ergebnis: Die Summe aller Elemente der Listen (Matrixsumme) ist geliefert
# Effekt: keiner
"""
Tests:
matrix_sum([[1, 2, 3], [4, 5, 6], [7, 8, 9]]) == 45
matrix_sum([[2, 2, 2], [2, 2, 2], [1, 1, 2]]) == 16
matrix_sum([[2, 2, 2, 2, 2], [2, 2, 2, 2, 2], [1, 1, 1, 2, 2], [1, 1, 1, 1, 2], [1, 1, 1, 1, 1]]) == 38
matrix_sum([]) == 0
"""


def matrix_sum(mx):
    res = 0
    for i in range(len(mx)):
        for j in range(len(mx[i])):
            res += mx[i][j]
    return res


# super_matrix_sum(list[list[int]) : int
# Voraussetzung: die Liste besteht aus Zahlenlisten. Alle Listen da sind
# gleich groß und die Außenliste ist auch genauso groß. Höchstens
# zwei unterschiedliche Elemente tauchen in der Matrix auf, jede Zeile ist aufsteigend
# und jede Spalte ist absteigend sortiert
# Ergebnis: Die Summe aller Elemente der Listen (Matrixsumme) ist geliefert
# Effekt: keiner
'''
Tests:
matrix_sum([[2, 2, 2], [2, 2, 2], [1, 1, 2]]) == 16
matrix_sum([[2, 2, 2, 2, 2], [2, 2, 2, 2, 2], [1, 1, 1, 2, 2], [1, 1, 1, 1, 2], [1, 1, 1, 1, 1]]) == 38
matrix_sum([[2, 2, 2], [2, 2, 2], [2, 2, 2]]) == 18
matrix_sum([[1, 1, 1], [1, 1, 1], [1, 1, 1]]) == 9
'''


def super_matrix_sum(mx):
    res = 0
    n = len(mx)
    max_el = mx[0][n - 1]
    min_el = mx[n - 1][0]
    v = 0
    h = 0
    while v < n and h < n:
        if mx[h][v] == max_el:
            res += max_el * (n - v)
            h += 1
        else:
            res += min_el * (n - h)
            v += 1
    return res


matrix = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
special_matrix1 = [[2, 2, 2], [2, 2, 2], [1, 1, 2]]
special_matrix2 = [[2, 2, 2, 2, 2], [2, 2, 2, 2, 2], [1, 1, 1, 2, 2], [1, 1, 1, 1, 2], [1, 1, 1, 1, 1]]
special_matrix3 = [[2, 2, 2], [2, 2, 2], [2, 2, 2]]
special_matrix4 = [[1, 1, 1], [1, 1, 1], [1, 1, 1]]

'''
Aufgabe 1
Man muss einen Algorithmus entwickeln, der die Summe aller im Matrix enthaltenen
Elemente berechnen kann. Im zweiten Fall haben Elemente des Matrix eine bestimmte
Eigenschaft. Der Nutzer soll ein Matrix als Parameter in eine Funktion eingeben und
die Funktion soll die Summe aller Elemente liefern.

Was soll ich schreiben??? 
'''

print(matrix_sum(matrix))
print(matrix_sum(special_matrix1))
print(matrix_sum(special_matrix2))
print(matrix_sum([]))

'''
Aufgabe 2b
Es gibt insgesamt n Zeilen. Pro Zeile werden n Additionsoperationen gemacht.
Also dann kann man die Laufzeit als Produkt n * n = n^2 berechnen.
O(n) = n^2
Der Algorithmus löst außerdem das spezifische Matrixproblem, es wird keinen
Unterschied gemacht, aus welchen Zahlen ein Matrix besteht. Sie werden einfach
mit einander addiert und, falls für ein Element Additionsoperation bestimmt ist,
ist es möglich die Summe zu berechnen. 
'''

print(super_matrix_sum(special_matrix1))
print(super_matrix_sum(special_matrix2))
print(super_matrix_sum(special_matrix3))
print(super_matrix_sum(special_matrix4))

'''
Aufgabe 2c
Ein Matrix besteht aus n Zeilen und n Spalten. Es werden sozusagen alle ununterbrochene
'Sets' von gleichen Zahlen addiert, indem man eine Zahl mal der Menge gleicher Zahlen im
'Set' rechnet. So ein Ansatz im schlimmsten Fall kann man mit dem Weg vom linken oberen Element zu rechtem
niedrigem Element gleichsetzten. Dafür braucht man 2 * n - 1 Schritte. Also die Laufzeit
muss dann gleich 2 * n - 1 und O(n) = n. 
Der Algorithmus löst das allgemeine Matrixproblem nicht, weil man da nicht mehr darf davon ausgehen
dass es eine bestimmte Menge von gleichen Zahlen gibt, die sog. 'Sets' bilden.
'''
