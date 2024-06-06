// Árvore de busca perfeitamente balanceada usando heap da
// superclasse

import java.util.ArrayList;

public class ArvBal extends ArvBin {
    private int len; // Tamanho total do vetor de strings

    // Construtor da classe
    public ArvBal(int len) {
        super(len);
        this.len = len;
    }

    // Insere uma string na Heap
    @Override
    public void insert(String value) {
        super.insert(value);
        // Balanceia a árvore, caso necessário
        balance();
    }

    // Remove uma string da Heap
    public boolean remove(String v) {
        return super.remove(v);
    }

    // Método para balanceamento da árvore
    public void balance() {
        // Se não estiver balanceada
        if(!isBalance()) {
            // Array que armazena todos os nós em ordem crescente
            ArrayList<String> sortedList = new ArrayList<String>();
            // Vetor auxiliar sobre o qual serão feitas as mudanaças
            String[] newHeap = new String[len];

            // Insere os nós no array ordenado
            fillSorted(sortedList, 0);
            // Inicializa o vetor auxiliar
            for(int i = 0; i < len; i++)
                newHeap[i] = "";

            // Modifica o vetor auxiliar para formar a nova árvore
            fillNewHeap(sortedList, newHeap, 0, sortedList.size() - 1, 0);

            // Copia as mudanças para a árvore original
            for(int i = 0; i < len; i++)
                arvBin[i] = newHeap[i];
        }
    }

    // Método que percorre a árvore em ordem e, para cada nó visitado,
    // o insere no array
    private void fillSorted(ArrayList<String> sorted, int position) {
        if(arvBin[position] != "") {
            fillSorted(sorted, nodeLeft(position));
            sorted.add(arvBin[position]);
            fillSorted(sorted, nodeRight(position));
        }
    }

    // Método que percorre o array sorted de forma binária, adicionando os termos centrais
    // de cada segmento de 'a' até 'b' em newHeap na posição dad
    private void fillNewHeap(ArrayList<String> sorted, String[] newHeap, int a, int b, int dad) {
        // Não há mais elementos para adicionar para (a, b)
        if(a > b)
            return;
        
        // Calcula o termo do meio
        int mid = (a+b)/2;
        // Adiciona o termo do meio na posição dad
        newHeap[dad] = sorted.get(mid);

        // Chama o método para as outras duas metades do array
        fillNewHeap(sorted, newHeap, a, mid - 1, nodeLeft(dad));
        fillNewHeap(sorted, newHeap, mid + 1, b, nodeRight(dad));
    }

    // Verifica se elemento está na Heap
    public boolean find(String v) {
        return super.find(v);
    }

    // Retorna o número de elementos da Heap
    public int len() {
        return super.len();
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

    // Retorna um string no formato de um grafo
    public String toString() {
        return super.toString();
    }

    // Verifica se a árvore está balanceada
    @Override
    protected boolean isBalance() {
        return isBalanceAux(0);
    }

    // Método auxiliar para verificação do balanceamento da árvore de raiz em position
    private boolean isBalanceAux(int position) {
        // Se chegar num nó folha, retorna true
        if(arvBin[nodeLeft(position)] == "" && arvBin[nodeRight(position)] == "")
            return true;
        
        // Calcula a diferença absoluta entre as quantidades de nós das subárvores
        // de position
        int countLeft = countheap(nodeLeft(position));
        int countRight = countheap(nodeRight(position));
        int diff = Math.abs(countLeft - countRight);

        // Retorna true se a diferença é 0 ou 1 e se as subárvores de seus filhos estão balanceadas
        if(diff <= 1 && isBalanceAux(nodeLeft(position)) && isBalanceAux(nodeRight(position)))
            return true;

        return false;
    }
}
