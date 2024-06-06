public class ListaLigada<T extends NoLista> {
    T lista[];
    int fim;
    
    public ListaLigada() {
        this.lista[] = (T[]) new Object[100];
        this.fim = 0;
    }

    public void insertInit() {

    }

    public void insertEnd(T novoNo) {
        fim++;
        lista[fim] = novoNo;
    }

    public void removeInit() {

    }

    public void removeEnd() {
        
    }
}
