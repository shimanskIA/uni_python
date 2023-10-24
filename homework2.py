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


# print(get_histogram(input()))
# print(inverse_dna(input()))
# dna1 = generate_dna(6)
# dna2 = generate_dna(6)
# print(dna1)
# print(dna2)
# print(get_similar_pos_number(dna1, dna2))
# print(get_similar_freq_number(dna1, dna2))
# start_game(6)
