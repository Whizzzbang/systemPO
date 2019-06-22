//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

public class LinkedList {
    int size = 0;
    private Wrapper last = new Wrapper();
    private Wrapper first = new Wrapper();

    public LinkedList() {
    }

    public void add(Object value) {
        if (this.size == 0) {
            this.first = new Wrapper(value);
            this.last = this.first;
        } else {
            Wrapper oldEndNode = this.getLast();
            this.last = new Wrapper(this.getFirst(), oldEndNode, value);
            oldEndNode.next = this.getLast();
            this.getFirst().prev = this.getLast();
        }

        ++this.size;
    }

    public Wrapper getLast() {
        return this.last;
    }

    public Wrapper getFirst() {
        return this.first;
    }

    public int getSize() {
        return this.size;
    }
}
