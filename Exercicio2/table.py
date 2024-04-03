import math
 
class Table:
    def __init__(self, sequenciaInicial):
        self.dimensao = int(math.sqrt(len(sequenciaInicial)))
        self.matriz = [[0 for _ in range(self.dimensao)] for _ in range(self.dimensao)]
        k = 0

        for i in range(self.dimensao):
            for j in range(self.dimensao):
                self.matriz[i][j] = sequenciaInicial[k]
                if sequenciaInicial[k] == 0:
                    self.linha = i
                    self.coluna = j
                k += 1

    def movimento(self, movimento):
        linha = self.linha
        coluna = self.coluna

        if movimento == 'u' and linha != self.dimensao - 1:
            self.matriz[linha][coluna], self.matriz[linha + 1][coluna] = self.matriz[linha + 1][coluna], self.matriz[linha][coluna]
            self.linha = self.linha + 1

        elif movimento == 'd' and linha != 0:
            self.matriz[linha][coluna], self.matriz[linha - 1][coluna] = self.matriz[linha - 1][coluna], self.matriz[linha][coluna]
            self.linha = self.linha - 1

        elif movimento == 'l' and coluna != self.dimensao - 1:
            self.matriz[linha][coluna], self.matriz[linha][coluna + 1] = self.matriz[linha][coluna + 1], self.matriz[linha][coluna]
            self.coluna = self.coluna + 1

        elif movimento == 'r' and self.coluna != 0:
            self.matriz[linha][coluna], self.matriz[linha][coluna - 1] = self.matriz[linha][coluna - 1], self.matriz[linha][coluna]
            self.coluna = self.coluna - 1

    def printTable(self):
        index1 = 0
        index2 = 0

        for i in range(self.dimensao):
            for j in range(self.dimensao):
                print("+", end="")
                for k in range(6):
                    print("-", end="")
            print("+\n", end="")

            for index in range(self.dimensao):
                if self.matriz[index1][index2] >= 10:
                    print("|  ", end="")
                else:
                    print("|   ", end="")
                if self.matriz[index1][index2] == 0:
                    print(" ", end="")
                else:
                    print(self.matriz[index1][index2], end="")
                print("  ", end="")
                index2 += 1
            print("|\n", end="")
            index1 += 1
            index2 = 0

        for counter in range(self.dimensao):
            print("+", end="")
            for counter2 in range(6):
                print("-", end="")
        print("+\n\n", end="")

    def complete(self):
        verifica = True
        index = 0

        for i in range(self.dimensao):
            for j in range(self.dimensao):
                if self.matriz[i][j] != index:
                    verifica = False
                    break
                index = index + 1

        print("Posicao final: " + str(verifica))