package fooddelivery.dataStructures;

public class Pair <T, Q>{

    private T first;
    private Q second;

    public Pair(T first, Q second){
        this.setFirst(first);
        this.setSecond(second);
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public Q getSecond() {
        return second;
    }

    public void setSecond(Q second) {
        this.second = second;
    }
}
