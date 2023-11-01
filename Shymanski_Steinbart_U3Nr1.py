# bar(list, float/int) : bool
# Voraussetzung: die Liste ist nicht leer
# Ergebnis: Der Wahrheitswert davon, dass eine Zahl nicht kleiner als
# alle Elemente in der Liste ist, ist geliefert
# Effekt: keiner
"""
Tests:
bar([-3, 4, 5, 6, 7, 8], -4) == false
bar([-3, 4, 5, 6, 7, 8], -2) == true
bar([1, 1, 1, 1], 1) == true
"""


def bar(xs, k):
    if len(xs) > 0:
        res = k >= xs[0]
        return res or bar(xs[1:], k)
    return False


# add(list(float/int), list(float/int)) : list(float/int)
# Voraussetzung: die beiden Listen enthalten Zahlen und sind gleich groß
# Ergebnis: eine neue Liste mit den Summen der Komponenten ist
# geliefert
# Effekt: Das Skalarprodukt zweier Listen wird auf dem Bildschirm
# angezeigt
"""
Tests:
add([1, 2, 3], [23, -5, -78.4]) == [24, -3, -75.4]
Console: -222.2
add([5], [-5]) == [0]
Console: -25
add([1, 2, 3], [0, 3, -2]) == [1, 5, 1]
Console: 0
"""


def add(list1, list2):
    res_list = list()
    mul = 0
    for i in range(len(list1)):
        res_list.append(list1[i] + list2[i])
        mul += list1[i] * list2[i]
    print(mul)
    return res_list


'''
def count_digits(n):
    try:
        return len(str(n).split('.')[1])
    except IndexError:
        return 0
'''


# count_digits(int) : int
# Voraussetzung: Es wird eine Zahl eingeben, die keine Buchstaben enthält
# Ergebnis: Die Anzahl von Nachkommastellen ist geliefert
# Effekt: keiner
'''
Tests:
count_digits(123.123) == 3
count_digits(123) == 0
count_digits(-123.123) == 3
count_digits(-123) == 0
count_digits(2437382.4364234238) == 10
'''


def count_digits(n, pointer=0):
    n_str = str(n)
    if pointer == 0:
        if not n_str.__contains__('.'): return 0
    if n_str[len(n_str) - 1 - pointer] != '.':
        pointer += 1
        return count_digits(n, pointer)
    else: return pointer


print(bar([-3, 4, 5, 6, 7, 8], -4))
print(bar([-3, 4, 5, 6, 7, 8], -2))
print(bar([1, 1, 1, 1], 1))

print(add([1, 2, 3], [23, -5, -78.4]))
print(add([5], [-5]))
print(add([1, 2, 3], [0, 3, -2]))

print(count_digits(123.123))
print(count_digits(123))
print(count_digits(-123.123))
print(count_digits(-123))
print(count_digits(2437382.4364234238))
