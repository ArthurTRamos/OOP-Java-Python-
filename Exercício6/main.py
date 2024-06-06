from sys import stdin

class ArvBin:
    def __init__(self):
        self.arvore = {}

    def inserirNo(self, numero):
        if not self.arvore:
            self.arvore[numero] = (None, None)
        else:
            self.insertAux(numero, next(iter(self.arvore)))

    def insertAux(self, numero, atual):
        if numero < atual:
            left = self.arvore[atual][0]
            if left == None:
                self.arvore[atual] = (numero, self.arvore[atual][1])
                self.arvore[numero] = (None, None)
            else:
                self.insertAux(numero, left)
        elif numero > atual:
            right = self.arvore[atual][1]
            if right == None:
                self.arvore[atual] = (self.arvore[atual][0], numero)
                self.arvore[numero] = (None, None)
            else:
                self.insertAux(numero, right)

    def inOrder(self):
        sequencia = []
        self.inOrderAux(next(iter(self.arvore)), sequencia)
        print("".join(sequencia))

    def inOrderAux(self, word, sequencia):
        if(word != None):
            self.inOrderAux(self.arvore[word][0], sequencia)
            sequencia.append(" " + word + " ")
            self.inOrderAux(self.arvore[word][1], sequencia)


    def getDict(self):
        return self.arvore
    
    def str(self):
        outString = ["digraph {\n"]
        valores = ()
        
        if self.arvore:
            for alunos, valores in (self.arvore).items():
                if valores[0] != None:
                    outString.append(alunos + " -> " + valores[0] + "\n")
                if valores[1] != None:
                    outString.append(alunos + " -> " + valores[1] + "\n")

        outString.append("}\n")
        return outString

if __name__ ==  "__main__":
    arvBin = ArvBin()

    for linha in stdin:
        operacao, word = linha.split()

        arvBin.inserirNo(word)

arvBin.inOrder()
print("".join(arvBin.str()))