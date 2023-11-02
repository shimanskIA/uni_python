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
    guessed_dna = str(input("Gib eine DNA-Sequenz ein:\n"))
    help_timer = 0
    while dna != guessed_dna:
        help_timer += 1
        print("Anzahl der gleichen Stellen: ", get_similar_pos_number(dna, guessed_dna))
        print("Anzahl der gleichen Frequenzen: ", get_similar_freq_number(dna, guessed_dna))
        if help_timer == 5:
            help_timer = 0
            if input("Soll ich dir helfen? (ja/nein)\n") == "ja":
                print(dna[int(input("Welches Element willst du sehen?\n")) - 1])
        guessed_dna = str(input("Gib eine DNA-Sequenz ein:\n"))
    print("Richtig!\n", dna)


gen_dna1 = generate_dna(6)  # aufgabe 2a
gen_dna2 = generate_dna(6)  # aufgabe 2a
print(gen_dna1)
print(gen_dna2)
print(get_similar_pos_number(gen_dna1, gen_dna2))  # aufgabe 2b
print(get_similar_freq_number(gen_dna1, gen_dna2))  # aufgabe 2c
start_game(6)  # aufgabe 2d
