//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

public class Lexem {
    private String name;
    private String regexp;
    private int priority;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegexp() {
        return this.regexp;
    }

    public void setRegexp(String regexp) {
        this.regexp = regexp;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    Lexem() {
    }

    Lexem(String n, String r, int p) {
        this.name = n;
        this.regexp = r;
        this.priority = p;
    }
}
