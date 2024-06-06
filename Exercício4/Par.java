public class Par<T1, T2> {
    T1 first;
    T2 second;

    public Par(T1 t1, T2 t2) {
        this.first = t1;
        this.second = t2;
    }

    public T1 getFirst() {
        return first;
    }

    public T2 getSecond() {
        return second;
    }

    @Override
    public String toString(T1 first, T2 second) {
        
    }
}