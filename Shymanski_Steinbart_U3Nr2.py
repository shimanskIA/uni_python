# dna2rna(list) : string
# Voraussetzung: keine
# Ergebnis: Ein String, wo T durch U ersetzt sind, ist geliefert
# Effekt: keiner
"""
Tests:
dna2rna(['T', 'T', 'T']) == ['U', 'U', 'U']
dna2rna(['A', 'C', 'T', 'U', 'U', 'C']) == ['A', 'C', 'U', 'U', 'U', 'C']
dna2rna(['U', 'U', 'U']) == ['U', 'U', 'U']
dna2rna(['T']) == ['U']
dna2rna([]) == []
"""


def dna2rna(dna):
    dna_copy = list(dna)
    while dna_copy.__contains__('T'):
        dna_copy[dna_copy.index('T')] = 'U'
    return dna_copy


# dna2rna_(list) : None
# Voraussetzung: keine
# Ergebnis: kein, nichts ist geliefert
# Effekt: die Liste wird verändert, da werden T's durch U's
# ersetzt
"""
Tests:
test_list = ['T', 'T', 'T']
dna2rna_(test_list)
test_list == ['U', 'U', 'U']
test_list = ['A', 'C', 'T', 'U', 'U', 'C']
dna2rna_(test_list)
test_list == ['A', 'C', 'U', 'U', 'U', 'C']
test_list = ['U', 'U', 'U']
dna2rna_(test_list)
test_list == ['U', 'U', 'U']
test_list = ['T']
dna2rna_(test_list)
test_list == ['U']
test_list = []
dna2rna_(test_list)
test_list == []
"""


def dna2rna_(dna):
    while dna.__contains__('T'):
        dna[dna.index('T')] = 'U'


# get_min(list(float/int)) : float/int
# Voraussetzung: die Liste ist nicht leer und enthält nur Zahlen
# Ergebnis: das kleinste Element ist geliefert
# Effekt: Aus der Liste werden alle Elemente außer dem kleinsten
# entfernt
"""
get_min([6, 18, -5, 9, 34, 4, -11, 8, 133, 12, 4]) == -11
get_min([1, 2, 3, 4]) == 1
get_min([4, 3, 2, 1]) == 1
get_min([1, 1, 1, 1]) == 1
get_min([1]) == 1
"""


def get_min(numbers: list):
    if len(numbers) > 1:
        if numbers[0] <= numbers[1]:
            numbers.pop(1)
        else:
            numbers.pop(0)
        get_min(numbers)
    return numbers[0]


# k_smallest(list(float/int), int) : float/int
# Voraussetzung: die k muss kleiner als die Länge der Liste
# sein und k muss auch kleiner als die Anzahl der
# verschiedenen Elemente sein
# Ergebnis: das k-kleinste Element ist geliefert
# Effekt: Aus der Liste werden (1 ... k-1)-kleinste Elemente
# entfernt
"""
Tests:
k_smallest([6, 18, -5, 9, 34, 4, -11, 8, 133, 12, 4], 3) == 4
k_smallest([1, 5, 8, 1, 5, 7, 8, 5], 3) == 7
k_smallest([1, 1, 1, 2, 2, 2, 3, 3, 3], 2) == 2
k_smallest([1, 2, 3, 4], 2) == 2
k_smallest([4, 3, 2, 1], 2) == 2
"""


def k_smallest(numbers: list, k):
    if k == 1:
        return get_min(numbers.copy())
    else:
        k -= 1
        min_el = get_min(numbers.copy())
        while numbers.__contains__(min_el):
            numbers.remove(min_el)
        return k_smallest(numbers, k)


# digit_sum(list) : int
# Voraussetzung: Nur Zahlen werden akzeptiert und sie müssen im Voraus
# in eine Liste umgewandelt werden (Dezimalzahlen sollen mit "."
# sein und nicht mit ",")
# Ergebnis: Die Quersumme der Zahlen und der Nachkommastellen ist
# geliefert
# Effekt: keiner, die Liste wird zwar verändert aber sie wird erst als
# Parameter erstellt, die Zahl selbst bleibt unveränderlich
"""
Tests:
digit_sum(list("123")) == 6
digit_sum(list("123.123")) == 12
digit_sum(list("0.123")) == 6
digit_sum(list("-0.123")) == 6
digit_sum(list("0")) == 0
digit_sum(list("1")) == 1
digit_sum(list("-1")) == 1
"""


def digit_sum(numbers: list):
    if numbers[0] == '-': numbers.pop(0)
    if len(numbers) > 1:
        if numbers[1] == '.': numbers.pop(1)
        numbers[1] = int(numbers[1]) + int(numbers[0])
        numbers.pop(0)
        digit_sum(numbers)
    return numbers[0]


print(dna2rna(['T', 'T', 'T']))
print(dna2rna(['A', 'C', 'T', 'U', 'U', 'C']))
print(dna2rna(['U', 'U', 'U']))
print(dna2rna(['T']))
print(dna2rna([]))

test_list = ['T', 'T', 'T']
dna2rna_(test_list)
print(test_list)
test_list = ['A', 'C', 'T', 'U', 'U', 'C']
dna2rna_(test_list)
print(test_list)
test_list = ['U', 'U', 'U']
dna2rna_(test_list)
print(test_list)
test_list = ['T']
dna2rna_(test_list)
print(test_list)
test_list = []
dna2rna_(test_list)
print(test_list, '\n')

print(get_min([6, 18, -5, 9, 34, 4, -11, 8, 133, 12, 4]))
print(get_min([1, 2, 3, 4]))
print(get_min([4, 3, 2, 1]))
print(get_min([1, 1, 1, 1]))
print(get_min([1]), '\n')

print(k_smallest([6, 18, -5, 9, 34, 4, -11, 8, 133, 12, 4], 3))
print(k_smallest([1, 5, 8, 1, 5, 7, 8, 5], 3))
print(k_smallest([1, 1, 1, 2, 2, 2, 3, 3, 3], 2))
print(k_smallest([1, 2, 3, 4], 2))
print(k_smallest([4, 3, 2, 1], 2), '\n')

print(digit_sum(list("123")))
print(digit_sum(list("123.123")))
print(digit_sum(list("0.123")))
print(digit_sum(list("-0.123")))
print(digit_sum(list("0")))
print(digit_sum(list("1")))
print(digit_sum(list("-1")))
