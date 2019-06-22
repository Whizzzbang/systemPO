//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

public class Wrapper {
    Object value;
    Wrapper prev;
    Wrapper next;

    public Wrapper getNext() {
        return this.next;
    }

    public Wrapper getPrev() {
        return this.prev;
    }

    public Object getValue() {
        return this.value;
    }

    public Wrapper() {
        this.next = null;
        this.prev = null;
        this.value = null;
    }

    public Wrapper(Object value) {
        this.next = null;
        this.prev = null;
        this.value = value;
    }

    public Wrapper(Wrapper next, Wrapper prev, Object value) {
        this.next = next;
        this.prev = prev;
        this.value = value;
    }
}
