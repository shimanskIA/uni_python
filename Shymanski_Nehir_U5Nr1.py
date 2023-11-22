import random
size = 20


def is_sorted(xs):
    n = len(xs)
    for i in range(1, n):
        if xs[i - 1] > xs[i]:
            return False
    return True


def get_random_list(n):
    xs = n * [0]
    for i in range(n):
        xs[i] = random.randint(0, n)
    return xs


def aufsteigend(n):
    xs = n * [0]
    for i in range(n):
        xs[i] = i
    return xs


def absteigend(n):
    xs = n * [0]
    for i in range(n):
        xs[i] = n - i
    return xs


# merge(list[float], list[float], list[float], bool) : None
# Voraussetzung: die zwei Listen bestehen aus Zahlen und sind
# aufsteigend sortiert, die Länge der dritten Endliste ist genau
# gleich der Länge der ersten zwei Listen zusammen
# Ergebnis: Nichts ist geliefert
# Effekt: Die dritte Liste enthält alle Elemente der ersten zwei Listen
# und ist aufsteigend sortiert.
# Auf dem Bildschirm wird jedes Mal das Endergebnis angezeigt und beim
# letzten Mergeprozess werden auch einzelne Schritte auf dem Bildschirm
# angezeigt
'''
Tests:
test_list = [0] * 13
merge([1, 4, 6, 8, 9], [0, 0, 0, 6, 9, 12, 12, 13], test_list)
print(test_list) == [0, 0, 0, 1, 4, 6, 6, 8, 9, 9, 12, 12, 13]
Das ist doch eine Nebenfunkton, ein Test reicht
'''


def merge(left, right, xs, is_last_time=False):
    l = 0
    r = 0
    if is_last_time:
        print(left)
        print(right)
    while l + r < len(xs):
        if l == len(left):
            xs[l + r] = right[r]
            r += 1
        elif r == len(right):
            xs[l + r] = left[l]
            l += 1
        elif left[l] <= right[r]:
            xs[l + r] = left[l]
            l += 1
        else:
            xs[l + r] = right[r]
            r += 1
        if is_last_time: print(xs)
    print(xs)


# mergesort(list[float]) : None
# Voraussetzung: die Liste besteht aus Zahlen
# Ergebnis: Nichts ist geliefert
# Effekt: Die Liste ist aufsteigend sortiert
'''
Tests:
test_list = aufsteigend(20)
mergesort(test_list)
is_sorted(test_list) == True
test_list = absteigend(20)
mergesort(test_list)
is_sorted(test_list) == True
test_list = get_random_list(20)
mergesort(test_list)
is_sorted(test_list) == True
'''


def mergesort(xs):
    n = len(xs)
    if n <= 1:
        return
    left = xs[:n // 2]
    right = xs[n // 2:]
    mergesort(left)
    mergesort(right)
    if n == size:
        merge(left, right, xs, True)
    else:
        merge(left, right, xs)


# binary_search(list[float], float, int, int) : int
# Voraussetzung: die Liste besteht aus Zahlen
# Ergebnis: Die Position des nächsten größeren Elements (größer als k)
# ist geliefert, falls es keinen größeren Elementen gibt, wird die Länge
# der Liste geliefert
# Effekt: keiner
'''
Tests:
binary_search([1, 2, 4, 8, 16, 32, 42, 64, 128, 130, 243, 244, 289], 32, 0, 13) == 6
binary_search([1, 2, 4, 8, 16, 32, 32, 32, 128, 130, 243, 244, 289], 32, 0, 13) == 8
binary_search([1], 32, 0, 1) == 1
binary_search([], 32, 0, 0) == 0
'''


def binary_search(xs, k, left, right):
    if left >= right: return right
    pos = (left + right) // 2
    if xs[pos] > k:
        return binary_search(xs, k, left, pos)
    elif xs[pos] < k:
        return binary_search(xs, k, pos + 1, right)
    else:
        pos += 1
        while pos < len(xs):
            if xs[pos] > k: return pos
            pos += 1
        return pos


# insertion_sort(list[float]) : None
# Voraussetzung: die Liste besteht aus Zahlen
# Ergebnis: Nichts ist geliefert
# Effekt: Elemente der ursprünglichen Liste sind aufsteigend
# sortiert
'''
Tests:
test_list = aufsteigend(1000)
insertion_sort(test_list)
is_sorted(test_list) == True
test_list = absteigend(1000)
insertion_sort(test_list)
is_sorted(test_list) == True
test_list = get_random_list(1000)
insertion_sort(test_list)
is_sorted(test_list) == True
'''


def insertion_sort(xs):
    n = len(xs)

    def insert(i):
        key = xs[i]
        pos = binary_search(xs[0:i], key, 0, i)
        old_key = xs[pos]
        new_key = key
        for j in range(pos, i):
            xs[j] = new_key
            new_key = old_key
            old_key = xs[j + 1]
        xs[i] = new_key

    for i in range(1, n):
        insert(i)


# merge_lists(list[float], list[float], list[float]) : list(float)
# Voraussetzung: die drei Listen bestehen aus Zahlen und sind
# aufsteigend sortiert
# Ergebnis: Eine aufsteigend sortierte zusammengesetzte Liste ist geliefert
# Effekt: keiner
'''
Tests:
merge_lists([1, 4, 6, 8, 9], [0, 2, 3, 5, 7], [0, 0, 0, 6, 9, 12, 12, 13]) ==
[0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 6, 7, 8, 9, 9, 12, 12, 13]
Das ist doch eine Nebenfunkton, ein Test reicht
'''


def merge_lists(l1, l2, l3):
    merged_list = list()
    l = 0
    m = 0
    r = 0
    length = len(l1) + len(l2) + len(l3)
    while l + m + r < length:
        if l == len(l1) and m == len(l2):
            merged_list.append(l3[r])
            r += 1
        elif l == len(l1) and r == len(l3):
            merged_list.append(l2[m])
            m += 1
        elif m == len(l2) and r == len(l3):
            merged_list.append(l1[l])
            l += 1
        elif l == len(l1):
            if l2[m] <= l3[r]:
                merged_list.append(l2[m])
                m += 1
            else:
                merged_list.append(l3[r])
                r += 1
        elif m == len(l2):
            if l1[l] <= l3[r]:
                merged_list.append(l1[l])
                l += 1
            else:
                merged_list.append(l3[r])
                r += 1
        elif r == len(l3):
            if l1[l] <= l2[m]:
                merged_list.append(l1[l])
                l += 1
            else:
                merged_list.append(l2[m])
                m += 1
        elif min(l1[l], l2[m], l3[r]) == l1[l]:
            merged_list.append(l1[l])
            l += 1
        elif min(l1[l], l2[m], l3[r]) == l2[m]:
            merged_list.append(l2[m])
            m += 1
        else:
            merged_list.append(l3[r])
            r += 1
    return merged_list


# power_sort(list[float]) : list[float]
# Voraussetzung: die Liste besteht aus Zahlen
# Ergebnis: Eine aufsteigend mit Powersort sortierte Liste ist geliefert
# Effekt: keiner
'''
Tests:
is_sorted(power_sort(aufsteigend(1000))) == True
is_sorted(power_sort(absteigend(1000))) == True
is_sorted(power_sort(get_random_list(1000))) == True
'''


def power_sort(xs):
    l1 = xs[0::3]
    l2 = xs[1::3]
    l3 = xs[2::3]
    if len(l1) <= 1 and len(l2) <= 1 and len(l3) <= 1:
        return merge_lists(l1, l2, l3)
    else:
        insertion_sort(l2)
        insertion_sort(l3)
        return merge_lists(power_sort(l1), l2, l3)


test_list = aufsteigend(20)
mergesort(test_list)
print(is_sorted(test_list))
test_list = absteigend(20)
mergesort(test_list)
print(is_sorted(test_list))
test_list = get_random_list(20)
mergesort(test_list)
print(is_sorted(test_list))

test_list = [0] * 13
merge([1, 4, 6, 8, 9], [0, 0, 0, 6, 9, 12, 12, 13], test_list)
print(test_list)

print(binary_search([1, 2, 4, 8, 16, 32, 42, 64, 128, 130, 243, 244, 289], 32, 0, 13))
print(binary_search([1, 2, 4, 8, 16, 32, 32, 32, 128, 130, 243, 244, 289], 32, 0, 13))
print(binary_search([1], 32, 0, 1))
print(binary_search([], 32, 0, 0))

test_list = aufsteigend(1000)
insertion_sort(test_list)
print(is_sorted(test_list))
test_list = absteigend(1000)
insertion_sort(test_list)
print(is_sorted(test_list))
test_list = get_random_list(1000)
insertion_sort(test_list)
print(is_sorted(test_list))

'''
aufgabe 1c
Der Algorithmus ist stabil, weil die Zahl jedes Mal nach der schon vorhandenen gleichen Zahl
eingefügt wird. Also, wenn es zum Beispiel eine Zahl X gibt, dann ist in der Funktion
binary_search die Nummer des Nachfolgers geliefert und nicht der Zahl selbst. Wenn es 2 Mal
X gibt, ist es die Nummer des Nachfolgers des 2ten X geliefert.
'''

print(merge_lists([1, 4, 6, 8, 9], [0, 2, 3, 5, 7], [0, 0, 0, 6, 9, 12, 12, 13]))

print(is_sorted(power_sort(aufsteigend(1000))))
print(is_sorted(power_sort(absteigend(1000))))
print(is_sorted(power_sort(get_random_list(1000))))
