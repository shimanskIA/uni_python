import random


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


# partition(list[float], int, int, bool) : int
# Voraussetzung: die Liste besteht aus Zahlen, Start und End sind positiv
# Ergebnis: die neue Position des Pivot-Elements ist geliefert
# Effekt: die Liste wird so verändert, dass alle Elemente, die kleiner als
# Pivot-Element sind, auf der linken Seite stehen, alle größere Elemente
# stehen auf der rechten Seite vom Pivot. Auf dem Bildschirm wird jedes Mal
# das Endergebnis angezeigt. Beim ersten Aufruf werden auch alle einzelne
# Schritte angezeigt
'''
Tests:
Das ist eine Hilfsfunktion, getestet wurde die Hauptfunktion zusammen
mit der Hilfsfunktion
'''


def partition(xs, start, end, is_first_time=False):
    if is_first_time: print(xs)
    pivot = xs[start]
    l = start
    for i in range(start+1,end):
        if xs[i] < pivot:
            l = l + 1
            xs[i], xs[l] = xs[l], xs[i]
            if is_first_time: print(xs)
    xs[start], xs[l] = xs[l], xs[start]
    print(xs)
    return l


# partition_(list[tuple(float, int)], int, int) : int
# Voraussetzung: die Liste besteht aus Tupeln. Tupeln bestehen aus Zahlenpaaren
# Start und End sind positiv
# Ergebnis: die neue Position des Pivot-Elements ist geliefert
# Effekt: die Liste wird so verändert, dass alle Elemente, die kleiner als
# Pivot-Element sind, auf der linken Seite stehen, alle größere Elemente
# stehen auf der rechten Seite vom Pivot. Die ursprüngliche Reihenfolge der
# Elemente wird behalten
'''
Tests:
Das ist eine Hilfsfunktion, getestet wurde die Hauptfunktion zusammen
mit der Hilfsfunktion
'''


def partition_(xs, start, end):
    pivot = xs[start]
    l = start
    for i in range(start + 1, end):
        if xs[i][0] < pivot[0] or (xs[i][0] == pivot[0] and xs[i][1] < pivot[1]):
            l += 1
            xs[i], xs[l] = xs[l], xs[i]
    xs[start], xs[l] = xs[l], xs[start]
    return l


# locate_pivot(list(float)) : list(float)
# Voraussetzung: die Liste besteht aus Zahlen
# Ergebnis: eine Liste mit dem Pivot-Element an der ersten (0-ten)
# Position ist geliefert
# Effekt: in der Liste wird das Pivot-Element mit dem ersten
# Element getauscht
'''
Tests:
Das ist eine Hilfsfunktion, getestet wurde die Hauptfunktion zusammen
mit der Hilfsfunktion
'''


def locate_pivot(xs):
    l1, l2, l3 = random.sample(range(0, len(xs)), 3)
    l = [xs[l1], xs[l2], xs[l3]]
    l.sort()
    if xs[l1] == l[1]:
        pos = l1
    elif xs[l2] == l[1]:
        pos = l2
    else:
        pos = l3
    xs[0], xs[pos] = xs[pos], xs[0]
    return xs


# mega_partition(list[float], int, int) : int
# Voraussetzung: die Liste besteht aus Zahlen, Start und End sind positiv
# Ergebnis: die neue Position des Pivot-Elements ist geliefert
# Effekt: die Liste wird so verändert, dass alle Elemente, die kleiner als
# Pivot-Element sind, auf der linken Seite stehen, alle größere Elemente
# stehen auf der rechten Seite vom Pivot
'''
Tests:
Das ist eine Hilfsfunktion, getestet wurde die Hauptfunktion zusammen
mit der Hilfsfunktion
'''


def mega_partition(xs, start, end):
    xs[start:end] = locate_pivot(xs[start:end])
    pivot = xs[start]
    l = start
    for i in range(start + 1, end):
        if xs[i] < pivot:
            l = l + 1
            xs[i], xs[l] = xs[l], xs[i]
    xs[start], xs[l] = xs[l], xs[start]
    return l


# selection_sort(list[float]) : None
# Voraussetzung: die Liste besteht aus Zahlen
# Ergebnis: Nichts ist geliefert
# Effekt: die Liste ist aufsteigend mit Selection-sort sortiert
'''
Tests:
test_list = aufsteigend(1000)
selection_sort(test_list)
is_sorted(test_list) == True
test_list = absteigend(1000)
selection_sort(test_list)
is_sorted(test_list) == True
test_list = get_random_list(1000)
selection_sort(test_list)
is_sorted(test_list) == True
'''


def selection_sort(xs):
    n = len(xs)

    def min(i):
        m = i
        for j in range(i + 1, n):
            if xs[j] < xs[m]:
                m = j
        return m

    for i in range(n):
        mini = min(i)
        xs[mini], xs[i] = xs[i], xs[mini]


# quicksort(list[float]) : None
# Voraussetzung: die Liste besteht aus Zahlen
# Ergebnis: Nichts ist geliefert
# Effekt: die Liste ist aufsteigend aber nicht stabil mit Quicksort sortiert
'''
Tests:
test_list = aufsteigend(20)
quicksort(test_list)
is_sorted(test_list) == True
test_list = absteigend(20)
quicksort(test_list)
is_sorted(test_list) == True
test_list = get_random_list(20)
quicksort(test_list)
is_sorted(test_list) == True
'''


def quicksort(xs):

    def quicksort_help(start, end):
        if end - start <= 1:
            return
        if start == 0 and end == len(xs):
            split = partition(xs, start, end, True)
        else:
            split = partition(xs, start, end)
        quicksort_help(start, split)
        quicksort_help(split+1, end)

    quicksort_help(0, len(xs))


# quicksort_(list[float]) : None
# Voraussetzung: die Liste besteht aus Zahlen
# Ergebnis: Nichts ist geliefert
# Effekt: die Liste ist aufsteigend und stabil mit Quicksort sortiert
'''
Tests:
test_list = aufsteigend(500)
quicksort_(test_list)
is_sorted(test_list) == True
test_list = absteigend(500)
quicksort_(test_list)
is_sorted(test_list) == True
test_list = get_random_list(500)
quicksort_(test_list)
is_sorted(test_list) == True
'''


def quicksort_(xs):
    paired_xs = list()
    for i in range(len(xs)):
        paired_xs.append(tuple((xs[i], i)))

    def quicksort_help_(start, end):
        if end - start <= 1:
            return
        split = partition_(paired_xs, start, end)
        quicksort_help_(start, split)
        quicksort_help_(split + 1, end)

    quicksort_help_(0, len(xs))
    for i in range(len(xs)):
        xs[i] = paired_xs[i][0]


# mega_sort(list[float]) : None
# Voraussetzung: die Liste besteht aus Zahlen
# Ergebnis: Nichts ist geliefert
# Effekt: die Liste ist aufsteigend mit Mega-sort sortiert
'''
Tests:
test_list = aufsteigend(1000)
mega_sort(test_list)
is_sorted(test_list) == True
test_list = absteigend(1000)
mega_sort(test_list)
is_sorted(test_list) == True
test_list = get_random_list(1000)
mega_sort(test_list)
is_sorted(test_list) == True
'''


def mega_sort(xs):
    def mega_sort_help(start, end):
        if end - start <= 1:
            return
        split = mega_partition(xs, start, end)
        if split - start < 100 <= end - split:
            left = xs[start:split]
            selection_sort(left)
            xs[start:split] = left
            mega_sort_help(split + 1, end)
        elif end - split < 100 <= split - start:
            right = xs[split:end]
            selection_sort(right)
            xs[split:end] = right
            mega_sort_help(start, split)
        elif end - split < 100 and split - start < 100:
            left = xs[start:split]
            selection_sort(left)
            xs[start:split] = left
            right = xs[split + 1:end]
            selection_sort(right)
            xs[split + 1:end] = right
        else:
            mega_sort_help(start, split)
            mega_sort_help(split + 1, end)

    mega_sort_help(0, len(xs))


test_list = aufsteigend(20)
quicksort(test_list)
print(is_sorted(test_list))
test_list = absteigend(20)
quicksort(test_list)
print(is_sorted(test_list))
test_list = get_random_list(20)
quicksort(test_list)
print(is_sorted(test_list))

test_list = aufsteigend(1000)
selection_sort(test_list)
print(is_sorted(test_list))
test_list = absteigend(1000)
selection_sort(test_list)
print(is_sorted(test_list))
test_list = get_random_list(1000)
selection_sort(test_list)
print(is_sorted(test_list))

test_list = aufsteigend(500)
quicksort_(test_list)
print(is_sorted(test_list))
test_list = absteigend(500)
quicksort_(test_list)
print(is_sorted(test_list))
test_list = get_random_list(500)
quicksort_(test_list)
print(is_sorted(test_list))

'''
aufgabe 2c
Der Algorithmus ist stabil, weil die ursprüngliche Reihenfolge der gleichen Zahlen
berücksichtigt wird. Es wird nicht nur kontrolliert, ob eine Zahl kleiner, als Pivot-Element ist,
sondern auch, falls die Zahlen gleich sind, ob die ursprüngliche Nummer kleiner ist.
'''

test_list = aufsteigend(1000)
mega_sort(test_list)
print(is_sorted(test_list))
test_list = absteigend(1000)
mega_sort(test_list)
print(is_sorted(test_list))
test_list = get_random_list(1000)
mega_sort(test_list)
print(is_sorted(test_list))
