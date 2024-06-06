public class Main {
    public static void main(String[] args) {
        ListaLigada<Par<Integer, Double>> lista;
        lista = new ListaLigada<Par<Integer, Double>>();
        lista.insertInit(new Par<Integer, Double>(1,1.0));
        lista.insertEnd(new Par<Integer, Double>(0, 0.0));
        System.out.println();
    }
}
