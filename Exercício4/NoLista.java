public class NoLista<T extends Par> {
    T no;

    public NoLista(T par) {
        this.no = par;
    }

    public T getNo() {
        return no;
    }
}