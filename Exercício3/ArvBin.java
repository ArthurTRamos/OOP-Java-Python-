// Árvore de busca não balanceada com heap (Superclasse)
// Raiz: índice 0
// Filhos do nó de índice i: índices (2*i + 1) e (2*i + 2)

// Observação: a árvore binária de busca esperada do caso de teste 13 aparenta estar errada

public class ArvBin {
    protected String[] arvBin; // Vetor de strings da árvore

    // Construtor da Classe da Heap de tamanho len
    public ArvBin(int len) {
        arvBin = new String[len];

        // Inicializa os nós com strings vazias
        for(int i = 0; i < len; i++) {
            arvBin[i] = "";
        }
    }

    // Insere uma string value na Heap
    public void insert(String value) {   
        // Se não encontrar a string, insere na heap
        if(!find(value))  {
            insertAux(value, 0);
        }
    }

    // Método auxiliar para inserção
    private void insertAux(String value, int position) {
        // Se achar uma string vazia, insere value
        if(arvBin[position] == "")
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
    public boolean remove(String v) {
        int positionToRemove = findPosition(v, 0); // Procura pelo índice de v

        // Se não encontrar v, retorna false
        if(positionToRemove == -1)
            return false;

        // Obtém os filhos de v
        int left = nodeLeft(positionToRemove);
        int right = nodeRight(positionToRemove);

        // Se o nó tiver 0 ou 1 filho
        if(arvBin[left] == "" || arvBin[right] == "") {
            // Se tiver apenas o filho esquerdo
            if(arvBin[left] != "") {
                // Troca o filho com v
                arvBin[positionToRemove] = arvBin[left];
                arvBin[left] = "";
                // Sobe/Corrige para a direita todos os nós das subárvores do antigo
                // filho de v
                fixTree(nodeRight(left), 'r');
                fixTree(nodeLeft(left), 'r');
                return true;
            }

            // Se tiver apenas o filho direito
            if(arvBin[right] != "") {
                // Troca o filho com v
                arvBin[positionToRemove] = arvBin[right];
                arvBin[right] = "";
                // Sobe/Corrige para a esquerda todos os nós das subárvores do antigo
                // filho de v
                fixTree(nodeLeft(right), 'l');
                fixTree(nodeRight(right), 'l');
                return true;
            }

            // Se for um nó folha, só remove
            arvBin[positionToRemove] = "";
            return true;
        }
        // Se tiver 2 filhos, pega o maior da subárvore esquerda
        else {
            // biggestLeft armazena a posição do maior à esquerda
            int biggestLeft = left;

            while(arvBin[nodeRight(biggestLeft)] != "") {
                biggestLeft = nodeRight(biggestLeft);
            }

            // Troca o maior com v
            arvBin[positionToRemove] = arvBin[biggestLeft];
            arvBin[biggestLeft] = "";
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

        if(arvBin[son] == "")
            return;

        // Verifica se é um filho esquerdo
        if(son == (2*dad + 1)) {
            arvBin[dad - 1] = arvBin[son];
            arvBin[son] = "";
        }
        // Verifica se é um filho direito
        else {
            arvBin[dad] = arvBin[son];
            arvBin[son] = "";
        }

        fixTreeToLeft(nodeLeft(son));
        fixTreeToLeft(nodeRight(son));
    }

    // Método auxiliar de subida/correção de nós para a direita
    private void fixTreeToRight(int son) {
        int dad = nodeDad(son);

        if(arvBin[son] == "")
            return;

        // Verifica se é um filho esquerdo
        if(son == (2*dad + 1)) {
            arvBin[dad] = arvBin[son];
            arvBin[son] = "";
        }
        // Verifica se é um filho direito
        else {
            arvBin[dad + 1] = arvBin[son];
            arvBin[son] = "";
        }

        fixTreeToRight(nodeLeft(son));
        fixTreeToRight(nodeRight(son));
    }

    // Verifica se elemento está na Heap
    public boolean find(String v) {
        return(findAux(v, 0));
    }

    // Método auxiliar para verificar se v está na heap
    private boolean findAux(String v, int position) {
        if(arvBin[position] == "")
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
    public int findPosition(String v, int position) {
        if(arvBin[position] == "")
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
        if(arvBin[position] == "")
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
    protected void setNode(int i, String v) {
        arvBin[i] = v;
    }

    // Retorna a string do elemento de índice i
    // da Heap
    protected String getNode(int i) {
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
        String newLine;

        i = counter = 0;
        totalSize = len();

        if(totalSize == 1) {
            newLine = String.format("\"0 %s\" ", arvBin[0]);
            arv = arv.concat(newLine);
            counter++;
        }

        while(counter < totalSize) {
            if(arvBin[i].equals("")) {
                i++;
                continue;
            }

            left = nodeLeft(i);
            right = nodeRight(i);

            if(!(arvBin[left].equals(""))) {
                newLine = String.format("\"%d %s\" " +
                                        "->" +
                                        "\"%d %s\"\n",
                                        i, arvBin[i], left, arvBin[left]);
                arv = arv.concat(newLine);
            }

            if(!(arvBin[right].equals(""))) {
                newLine = String.format("\"%d %s\" " +
                                              "->" +
                                              "\"%d %s\"\n",
                                              i, arvBin[i], right, arvBin[right]);
                arv = arv.concat(newLine);
            }

            counter++;
            i++;
        }

        arv = arv.concat("}\n");

        return arv;
    }

    // Verifica se a árvore está balanceada
    protected boolean isBalance() {
        return true;
    }
}