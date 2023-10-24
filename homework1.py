import random


def get_triangle_area(a, ha):
    return a * ha/2


def fill():
    arr: list[int] = list()
    for i in range(3):
        arr.append(int(input()))
    return arr


def fill_until_zero():
    arr: list[int] = list()
    while True:
        k = int(input())
        if k != 0:
            arr.append(k)
        else:
            break
    return arr


def average(arr):
    return sum(arr) / len(arr)


def check(arr):
    for i in arr:
        if arr.count(i) != 1: return False
    return True


def get_variability(arr):
    return len(set(arr))


def get_price(age: int):
    if age < 12:
        return 10
    elif age < 18:
        return 12
    elif age < 65:
        return 14
    else:
        return 12


def print_spaces(n):
    for i in range(n): print(' ', sep='', end='')


def print_pyramid(n):
    for i in range(1, n + 1):
        k = i
        print_spaces(n - i)
        while k >= 1:
            print(k, sep='', end='')
            k -= 1
        k = k + 2
        while k <= i:
            print(k, sep='', end='')
            k += 1
        print()


def simulate_coin_experiment(n, n_exp):
    total = 0
    for i in range(n_exp):
        z = 0
        counter = 0
        while z < n:
            if random.random() > 0.5 or z == 0: z += 1
            else: z -= 1
            counter += 1
        total += counter
    print(total/n_exp)


# print(check(fill()))
# print(average(fill_until_zero()))
# print(get_price(int(input())))
# print(get_variability(fill()))
# print_pyramid(int(input()))
# simulate_coin_experiment(50, 10000)

