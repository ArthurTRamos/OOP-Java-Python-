// Árvore de busca não balanceada com heap (Superclasse)
// Raiz: índice 0
// Filhos do nó de índice i: índices (2*i + 1) e (2*i + 2)

// Árvore binária de busca de tipo genérico

public class ArvBin<T extends  Comparable<T>> {
    protected T[] arvBin; // Vetor de strings da árvore

    // Construtor da Classe da Heap de tamanho len
    public ArvBin(int len) {
        arvBin = (T[]) new Comparable[len];

        // Inicializa os nós com strings vazias
        for(int i = 0; i < len; i++) {
            arvBin[i] = null;
        }
    }

    // Insere uma string value na Heap
    public void insert(T value) {   
        // Se não encontrar a string, insere na heap
        if(!find(value))  {
            insertAux(value, 0);
        }
    }

    // Método auxiliar para inserção
    private void insertAux(T value, int position) {
        // Se achar uma string vazia, insere value
        if(arvBin[position] == null)
            arvBin[position] = value;

        // Percorre a árvore de busca
        else {
            if(value.compareTo(arvBin[position]) > 0)
                insertAux(value, nodeRight(position));
            else
                insertAux(value, nodeLeft(position));
        }
    }

    // Remove uma string v da Heap
    public boolean remove(T v) {
        int positionToRemove = findPosition(v, 0); // Procura pelo índice de v

        // Se não encontrar v, retorna false
        if(positionToRemove == -1)
            return false;

        // Obtém os filhos de v
        int left = nodeLeft(positionToRemove);
        int right = nodeRight(positionToRemove);

        // Se o nó tiver 0 ou 1 filho
        if(arvBin[left] == null || arvBin[right] == null) {
            // Se tiver apenas o filho esquerdo
            if(arvBin[left] != null) {
                // Troca o filho com v
                arvBin[positionToRemove] = arvBin[left];
                arvBin[left] = null;
                // Sobe/Corrige para a direita todos os nós das subárvores do antigo
                // filho de v
                fixTree(nodeRight(left), 'r');
                fixTree(nodeLeft(left), 'r');
                return true;
            }

            // Se tiver apenas o filho direito
            if(arvBin[right] != null) {
                // Troca o filho com v
                arvBin[positionToRemove] = arvBin[right];
                arvBin[right] = null;
                // Sobe/Corrige para a esquerda todos os nós das subárvores do antigo
                // filho de v
                fixTree(nodeLeft(right), 'l');
                fixTree(nodeRight(right), 'l');
                return true;
            }

            // Se for um nó folha, só remove
            arvBin[positionToRemove] = null;
            return true;
        }
        // Se tiver 2 filhos, pega o maior da subárvore esquerda
        else {
            // biggestLeft armazena a posição do maior à esquerda
            int biggestLeft = left;

            while(arvBin[nodeRight(biggestLeft)] != null) {
                biggestLeft = nodeRight(biggestLeft);
            }

            // Troca o maior com v
            arvBin[positionToRemove] = arvBin[biggestLeft];
            arvBin[biggestLeft] = null;
            // Sobe/Corrige para a direita os nós da subárvore esquerda de
            // biggestLeft
            fixTree(nodeLeft(biggestLeft), 'r');
            return true;
        }
    }

    // Método que, para um nó son, sobe ele e seus descendentes em uma
    // posição, seja em direção à direita (r) ou para a esquerda (l)
    protected void fixTree(int son, char direction) {
        if(direction == 'l')
            fixTreeToLeft(son);
        else {
            if(direction == 'r')
                fixTreeToRight(son);
        }
    }

    // Método auxiliar de subida/correção de nós para a esquerda
    private void fixTreeToLeft(int son) {
        int dad = nodeDad(son);

        if(arvBin[son] == null)
            return;

        // Verifica se é um filho esquerdo
        if(son == (2*dad + 1)) {
            arvBin[dad - 1] = arvBin[son];
            arvBin[son] = null;
        }
        // Verifica se é um filho direito
        else {
            arvBin[dad] = arvBin[son];
            arvBin[son] = null;
        }

        fixTreeToLeft(nodeLeft(son));
        fixTreeToLeft(nodeRight(son));
    }

    // Método auxiliar de subida/correção de nós para a direita
    private void fixTreeToRight(int son) {
        int dad = nodeDad(son);

        if(arvBin[son] == null)
            return;

        // Verifica se é um filho esquerdo
        if(son == (2*dad + 1)) {
            arvBin[dad] = arvBin[son];
            arvBin[son] = null;
        }
        // Verifica se é um filho direito
        else {
            arvBin[dad + 1] = arvBin[son];
            arvBin[son] = null;
        }

        fixTreeToRight(nodeLeft(son));
        fixTreeToRight(nodeRight(son));
    }

    // Verifica se elemento está na Heap
    public boolean find(T v) {
        return(findAux(v, 0));
    }

    // Método auxiliar para verificar se v está na heap
    private boolean findAux(T v, int position) {
        if(arvBin[position] == null)
            return false;
        
        if(v.equals(arvBin[position]))
            return true;

        else {
            if(v.compareTo(arvBin[position]) > 0)
                return(findAux(v, nodeRight(position)));
            else
                return(findAux(v, nodeLeft(position)));
        }
    }

    // Retorna a posição de v na heap (ou -1, caso não esteja)
    public int findPosition(T v, int position) {
        if(arvBin[position] == null)
            return -1;
        
        if(v.equals(arvBin[position]))
            return position;

        else {
            if(v.compareTo(arvBin[position]) > 0)
                return(findPosition(v, nodeRight(position)));
            else
                return(findPosition(v, nodeLeft(position)));
        }
    }

    // Retorna o número de elementos da Heap
    public int len() {
        return(lenAux(0));
    }

    // Método auxiliar que retorna o número de elementos
    // da subárvore de raiz em position
    protected int lenAux(int position) {
        if(arvBin[position] == null)
            return 0;

        return 1 + lenAux(nodeLeft(position)) + lenAux(nodeRight(position));
    }

    // Retorna o número do pai
    protected int nodeDad(int i) {
        return (i-1)/2;
    }

    // Retorna o número do filho à esquerda
    protected int nodeLeft(int i) {
        return 2*i + 1;
    }

    // Retorna o número do filho à direita
    protected int nodeRight(int i) {
        return 2*i + 2;
    }

    // Atribui uma string ao elemento de índice i
    // da Heap
    protected void setNode(int i, T v) {
        arvBin[i] = v;
    }

    // Retorna a string do elemento de índice i
    // da Heap
    protected T getNode(int i) {
        return(arvBin[i]);
    }

    // Retorna o número de nós de uma árvore
    // de raiz i
    protected int countheap(int i) {
        return(lenAux(i));
    }

    // Retorna um string no formato de um grafo
    public String toString() {
        String arv = new String("digraph {\n");
        int i, counter, totalSize, left, right;

        i = counter = 0;
        totalSize = len();

        if(totalSize == 1) {
            arv += "\"" + "0 " + arvBin[0] + "\"" + " ->";
            counter++;
        }

        while(counter < totalSize) {
            if(arvBin[i] == null) {
                i++;
                continue;
            }

            left = nodeLeft(i);
            right = nodeRight(i);

            if(!(arvBin[left] == null)) {
                arv += "\"" + i + " " + arvBin[i] + "\"" + " ->" + "\"" + left + " " + arvBin[left] + "\"" + "\n";
            }

            if(!(arvBin[right] == null)) {
                arv += "\"" + i + " " + arvBin[i] + "\"" + " ->" + "\"" + right + " " + arvBin[right] + "\"" + "\n";
            }

            counter++;
            i++;
        }

        arv += "}\n";

        return arv;
    }

    // Verifica se a árvore está balanceada
    protected boolean isBalance() {
        return true;
    }
}