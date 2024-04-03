from table import Table

if __name__ ==  "__main__":
    sequenciaInicial = input()
    sequenciaMovimentos = input()

    posicaoInicial = [int(x) for x in sequenciaInicial.split()]

    game = Table(posicaoInicial)
    game.printTable()

    for mov in sequenciaMovimentos:
        game.movimento(mov)
        game.printTable()
    game.complete()
    
        