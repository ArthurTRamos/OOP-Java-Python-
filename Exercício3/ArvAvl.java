// Árvore AVL implementada em um heap alocado na superclasse

public class ArvAvl extends ArvBin {
    private int len; // Tamanho do vetor heap
    private int removed; // Posição do último nó removido

    // Construtor da classe
    public ArvAvl(int len) {
        super(len);
        this.len = len;
        removed = -1;
    }

    // Insere a string value na heap e balanceia
    @Override
    public void insert(String value) {    
        super.insert(value);
        // Obtém a posição de inserção e salva
        int position = findPosition(value, 0);
        int save = position;
        // Fator de balanceamento de um nó
        int fb = 0;
        
        // Loop para procura de um nó desbalanceado (fb = 2 ou fb = -2) a partir
        // da posição de value
        while(position >= 0) {
            fb = height(nodeLeft(position)) - height(nodeRight(position));
            if(fb == 2 || fb == -2)
                break;

            if(position == 0)
                position = -1;
            else
                position = nodeDad(position);
        }

        // Se achar um nó deslanceado, entra no if
        if(position >= 0) {
            // Heap auxiliar para modificações
            String[] newHeap = new String[len];
            for(int i = 0; i < len; i++) {
                newHeap[i] = arvBin[i];
            }

            // Se fb = 2, então temos uma rotação direita simples ou rotação esquerda/direita
            if(fb == 2) {
                // Se a string value foi inserida na subárvore esquerda do nó desbalanceado,
                // temos uma rotação simples direita centrada em position
                if(arvBin[save].compareTo(arvBin[nodeLeft(position)]) < 0)
                    rightRotate(newHeap, position);
                // Senão, temos uma rotação dupla
                else {
                    // Centrada no filho esquerdo de position
                    leftRotate(newHeap, nodeLeft(position));
                    // Centrada em position
                    rightRotate(newHeap, position);
                }
            }
            // Rotação esquerda simples ou rotação direita/esquerda
            else {
                // Se a string value foi inserida na subárvore direita do nó desbalanceado,
                // temos uma rotação simples esquerda centrada em position
                if(arvBin[save].compareTo(arvBin[nodeRight(position)]) > 0)
                    leftRotate(newHeap, position);
                // Senão, temos uma rotação dupla
                else {
                    // Centrada no filho direito de position
                    rightRotate(newHeap, nodeRight(position));
                    // Centrada em position
                    leftRotate(newHeap, position);
                }
            }
        }
    }

    // Rotaciona os elementos de heap centrando-se em a
    private void rightRotate(String[] heap, int a) {
        int b = nodeLeft(a);
        int direita = nodeRight(a);
        // String do filho direito de a
        String old = getNode(direita);
        // Troca String do filho pela string de a ("desce o a para a direita")
        heap[direita] = arvBin[a];
        heap[a] = "";

        // Move em uma posição para baixo todos os nós abaixo da nova posição de a,
        // salvando a string do nó sobrescrito em old
        moveDownNodes(nodeRight(direita), old, heap, 1);
        CopySubTreeB(heap, nodeLeft(direita), nodeRight(b));

        heap[a] = heap[b];
        heap[b] = "";

        fixTreeRight(heap, nodeLeft(b));

        for(int i = 0; i < len; i++) {
            arvBin[i] = heap[i];
        }
    }

    private void leftRotate(String[] heap, int a) {
        int b = nodeRight(a);

        int esquerda = nodeLeft(a);
        String old = getNode(esquerda);

        heap[esquerda] = arvBin[a];
        heap[a] = "";

        moveDownNodes(nodeLeft(esquerda), old, heap, -1);
        CopySubTreeB(heap, nodeRight(esquerda), nodeLeft(b));

        heap[a] = heap[b];
        heap[b] = "";

        fixTreeLeft(heap, nodeRight(b));

        for(int i = 0; i < len; i++) {
            arvBin[i] = heap[i];
        }
    }

    // Método auxiliar que move todos os nós abaixo de newNode em uma posição no
    // sentido indicado por direction (1/-1 - esquerda/direita)
    private void moveDownNodes(int newNode, String old, String[] heap, int direction) {
        // Se a string sobrescrita é nula, então temos um nó folha e fim do processo
        if(old.equals(""))
            return;

        // String do nó que será sobrescrito
        String newOld = getNode(newNode);
        // Sobrescreve com o conteúdo do nó da chamada passada
        heap[newNode] = old;

        // Se as descidas dos nós são para a direita, entra no if
        if(direction == 1) {
            // Copia todos os nós "perdidos" na esquerda do pai de newNode para
            // a esquerda de newNode
            corrigeEsquerda(heap, nodeLeft(nodeDad(newNode)), nodeLeft(newNode));
            moveDownNodes(nodeRight(newNode), newOld, heap, 1);
        }
        // Senão, entre no else
        else {
            // Copia todos os nós "perdidos" na direita do pai de newNode para
            // a direita de newNode
            corrigeDireita(heap, nodeRight(nodeDad(newNode)), nodeRight(newNode));
            moveDownNodes(nodeLeft(newNode), newOld, heap, -1);
        }
    }

    // Método que copia toda a subárvore de esqPai para esqFilho
    private void corrigeEsquerda(String[] heap, int esqPai, int esqFilho) {
        if(arvBin[esqPai] == "")
            return;

        heap[esqFilho] = arvBin[esqPai];
        heap[esqPai] = "";

        corrigeEsquerda(heap, nodeLeft(esqPai), nodeLeft(esqFilho));
        corrigeEsquerda(heap, nodeRight(esqPai), nodeRight(esqFilho));
    }
    
    // Método que copia toda a subárvore de esqPai para esqFilho
    private void corrigeDireita(String[] heap, int dirPai, int dirFilho) {
        if(arvBin[dirPai] == "")
            return;

        heap[dirFilho] = arvBin[dirPai];
        heap[dirPai] = "";

        corrigeEsquerda(heap, nodeLeft(dirPai), nodeLeft(dirFilho));
        corrigeEsquerda(heap, nodeRight(dirPai), nodeRight(dirFilho));
    }

    // Move son para cima no sentido esquerda
    private void fixTreeLeft(String[] heap, int son) {
        int dad = nodeDad(son);

        if(heap[son] == "")
            return;

        if(son == (2*dad + 1)) {
            heap[dad - 1] = heap[son];
            heap[son] = "";
        }
        else {
            heap[dad] = heap[son];
            heap[son] = "";
        }

        fixTreeLeft(heap, nodeLeft(son));
        fixTreeLeft(heap, nodeRight(son));
    }

    // Move son para cima no sentido direita
    private void fixTreeRight(String[] heap, int son) {
        int dad = nodeDad(son);

        if(heap[son] == "")
            return;

        if(son == (2*dad + 1)) {
            heap[dad] = heap[son];
            heap[son] = "";
        }
        else {
            heap[dad + 1] = heap[son];
            heap[son] = "";
        }

        fixTreeRight(heap, nodeLeft(son));
        fixTreeRight(heap, nodeRight(son));
    }

    // Copia os nós da subárvore origem para a subárvore destino
    private void CopySubTreeB(String[] heap, int destino, int origem) {
        if(arvBin[origem] == "")
            return;

        heap[destino] = arvBin[origem];
        heap[origem] = "";

        CopySubTreeB(heap, nodeLeft(destino), nodeLeft(destino));
        CopySubTreeB(heap, nodeRight(destino), nodeRight(destino));
    }

    // Remove uma string da Heap
    public boolean remove(String v) {
        // Procura pela posição do nó a ser removido para salvá-la no
        // atributo removed
        int position = findPosition(v, 0);

        if(position == -1)
            return false;
        
        if(getNode(nodeLeft(position)) == "" || getNode(nodeRight(position)) == "")
            removed = position;
        else {
            removed = nodeLeft(position);
            while(arvBin[nodeRight(removed)] != "")
                removed = nodeRight(removed);
        }

        return super.remove(v);
    }

    // Balanceia uma árvore após uma remoção
    public void balance() {
        int fb = height(nodeLeft(removed)) - height(nodeRight(removed));

        while(removed >= 0) {
            fb = height(nodeLeft(removed)) - height(nodeRight(removed));

            if(fb == -2 || fb == 2)
                break;

            if(removed == 0) {
                removed = -1;
                continue;
            }

            removed = nodeDad(removed);
        }

        // Se o nó realmente foi removido, entra no if
        if(removed >= 0) {
            String[] newHeap = new String[len];
            for(int i = 0; i < len; i++) {
                newHeap[i] = arvBin[i];
            }

            int son;
            // Rotação à direita predomina
            if(fb == 2) {
                son = nodeLeft(removed);
                son = height(nodeLeft(son)) - height(nodeRight(son));
                if(son >= 0)
                    rightRotate(newHeap, removed);
                else {
                    leftRotate(newHeap, nodeLeft(removed));
                    rightRotate(newHeap, removed);
                }
            }
            else {
                son = nodeRight(removed);
                son = height(nodeLeft(son)) - height(nodeRight(son));
                if(son <= 0)
                    leftRotate(newHeap, removed);
                else {
                    rightRotate(newHeap, nodeRight(removed));
                    leftRotate(newHeap, removed);
                }
            }
        }
    }

    // Verifica se elemento está na Heap
    public boolean find(String v) {
        return super.find(v);
    }

    public int findPosition(String v, int position) {
        return super.findPosition(v, position);
    }

    // Retorna o número de elementos da Heap
    public int len() {
        return super.len();
    }

    protected int nodeDad(int i) {
        return super.nodeDad(i);
    }

    // Retorna o número do filho à esquerda
    protected int nodeLeft(int i) {
        return super.nodeLeft(i);
    }

    // Retorna o número do filho à direita
    protected int nodeRight(int i) {
        return super.nodeRight(i);
    }

    // Atribui uma string ao elemento de índice i
    // da Heap
    protected void setNode(int i, String v) {
        super.setNode(i, v);
    }

    // Retorna a string do elemento de índice i
    // da Heap
    protected String getNode(int i) {
        return super.getNode(i);
    }

    // Retorna o número de nós de uma árvore
    // de raiz i
    protected int countheap(int i) {
        return super.countheap(i);
    }

    // Calcula a altura do nó em position
    private int height(int position) {
        if(arvBin[position] == "")
            return -1;

        int leftHeight = height(nodeLeft(position));
        int rightHeight = height(nodeRight(position));

        if(leftHeight > rightHeight)
            return 1 + leftHeight;
        else
            return 1 + rightHeight;
    }

    // Retorna um string no formato de um grafo
    public String toString() {
        return super.toString();
    }

    // Verifica se a árvore está balanceada
    @Override
    protected boolean isBalance() {
        return isBalanceAux(0);
    }

    // Método auxiliar para verificar balanceamento
    private boolean isBalanceAux(int position) {
        if(arvBin[nodeLeft(position)] == "" && arvBin[nodeRight(position)] == "")
            return true;

        int leftHeight = height(position);
        int rightHeight = height(position);
        int diff = leftHeight - rightHeight;

        if(Math.abs(diff) <= 1 && isBalanceAux(nodeLeft(position)) && isBalanceAux(nodeRight(position)))
            return true;

        return false;
    }
}
