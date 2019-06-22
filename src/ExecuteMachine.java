//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ExecuteMachine {
    private int pos;
    private List<String> poliz;
    private List<Object> stack;
    private HashMap<Object, Object> variables;

    public ExecuteMachine(List<String> poliz) {
        this.poliz = poliz;
        this.stack = new ArrayList();
        this.pos = 0;
        this.variables = new HashMap();
    }

    private void cout(Object obj) {
        PrintStream var10000 = System.out;
        String var10001 = obj.toString();
        var10000.println(var10001 + " = " + this.variables.get(obj));
    }

    private void cin(Object obj) {
        System.out.println(">> ");

        try {
            Scanner scanner = new Scanner(System.in);
            Object value = scanner.nextInt();
            this.variables.put(obj, value);
        } catch (Exception var4) {
            System.out.print("Error with read into " + obj.toString());
        }

    }

    public void process() {
        while(this.pos < this.poliz.size()) {
            this.stack.add(this.poliz.get(this.pos++));
            Object stackHead = this.stack.remove(this.stack.size() - 1);
            if (stackHead.equals("!")) {
                this.pos = Integer.valueOf(this.stack.remove(this.stack.size() - 1).toString());
            } else if (stackHead.equals("!F")) {
                int addr = Integer.valueOf(this.stack.remove(this.stack.size() - 1).toString());
                if (!Boolean.valueOf(this.stack.remove(this.stack.size() - 1).toString())) {
                    this.pos = addr;
                }
            } else if (stackHead.equals("cout")) {
                this.cout(this.stack.remove(this.stack.size() - 1));
            } else if (stackHead.equals("cin")) {
                this.cin(this.stack.remove(this.stack.size() - 1));
            } else if (!stackHead.equals("*") && !stackHead.equals("/") && !stackHead.equals("=") && !stackHead.equals("+") && !stackHead.equals("-") && !stackHead.equals(">") && !stackHead.equals("<") && !stackHead.equals("==") && !stackHead.equals("!=") && !stackHead.equals("<=") && !stackHead.equals(">=") && !stackHead.equals("add") && !stackHead.equals("getValue") && !stackHead.equals("getSize") && !stackHead.equals("getNext") && !stackHead.equals("getPrev") && !stackHead.equals("getLast") && !stackHead.equals("getFirst")) {
                this.stack.add(stackHead);
            } else {
                this.calculate(String.valueOf(stackHead));
            }
        }

    }

    private Object check(Object a) {
        if (this.variables.get(a) != null) {
            a = this.variables.get(a);
        }

        return a;
    }

    private void calculate(String op) {
        Object b = new Object();
        new Object();
        Object a;
        if (!op.equals("getValue") && !op.equals("getSize") && !op.equals("getNext") && !op.equals("getPrev") && !op.equals("getLast") && !op.equals("getFirst")) {
            b = this.stack.remove(this.stack.size() - 1);
            a = this.stack.remove(this.stack.size() - 1);
        } else {
            a = this.stack.remove(this.stack.size() - 1);
            Object objRef = this.variables.get(a) != null ? this.variables.get(a) : a;
            byte var6 = -1;
            switch(op.hashCode()) {
                case -75367668:
                    if (op.equals("getLast")) {
                        var6 = 4;
                    }
                    break;
                case -75304087:
                    if (op.equals("getNext")) {
                        var6 = 2;
                    }
                    break;
                case -75232599:
                    if (op.equals("getPrev")) {
                        var6 = 3;
                    }
                    break;
                case -75151241:
                    if (op.equals("getSize")) {
                        var6 = 1;
                    }
                    break;
                case 1953265914:
                    if (op.equals("getFirst")) {
                        var6 = 5;
                    }
                    break;
                case 1967798203:
                    if (op.equals("getValue")) {
                        var6 = 0;
                    }
            }

            switch(var6) {
                case 0:
                    this.getValue(objRef);
                    break;
                case 1:
                    this.getSize(objRef);
                    break;
                case 2:
                    this.getNext(objRef);
                    break;
                case 3:
                    this.getPrev(objRef);
                    break;
                case 4:
                    this.getLast(objRef);
                    break;
                case 5:
                    this.getFirst(objRef);
            }
        }

        b = this.check(b);
        if (op.equals("=")) {
            if (b.equals("LinkedList")) {
                this.initLL(a);
            } else {
                this.assign(a, b);
            }

        } else {
            a = this.check(a);
            byte var5 = -1;
            switch(op.hashCode()) {
                case 42:
                    if (op.equals("*")) {
                        var5 = 1;
                    }
                    break;
                case 43:
                    if (op.equals("+")) {
                        var5 = 3;
                    }
                    break;
                case 45:
                    if (op.equals("-")) {
                        var5 = 2;
                    }
                    break;
                case 47:
                    if (op.equals("/")) {
                        var5 = 4;
                    }
                    break;
                case 60:
                    if (op.equals("<")) {
                        var5 = 7;
                    }
                    break;
                case 62:
                    if (op.equals(">")) {
                        var5 = 5;
                    }
                    break;
                case 1084:
                    if (op.equals("!=")) {
                        var5 = 10;
                    }
                    break;
                case 1921:
                    if (op.equals("<=")) {
                        var5 = 8;
                    }
                    break;
                case 1952:
                    if (op.equals("==")) {
                        var5 = 9;
                    }
                    break;
                case 1983:
                    if (op.equals(">=")) {
                        var5 = 6;
                    }
                    break;
                case 96417:
                    if (op.equals("add")) {
                        var5 = 0;
                    }
            }

            switch(var5) {
                case 0:
                    this.add(a, b);
                    break;
                case 1:
                    this.mul(a, b);
                    break;
                case 2:
                    this.minus(a, b);
                    break;
                case 3:
                    this.plus(a, b);
                    break;
                case 4:
                    this.div(a, b);
                    break;
                case 5:
                    this.greater(a, b);
                    break;
                case 6:
                    this.greater_eq(a, b);
                    break;
                case 7:
                    this.less(a, b);
                    break;
                case 8:
                    this.less_eq(a, b);
                    break;
                case 9:
                    this.equal(a, b);
                    break;
                case 10:
                    this.not_equal(a, b);
            }

        }
    }

    private void getNext(Object objRef) {
        try {
            this.stack.add(((Wrapper)objRef).getNext());
        } catch (Exception var3) {
            var3.fillInStackTrace();
        }

    }

    private void getSize(Object objRef) {
        this.stack.add(((LinkedList)objRef).getSize());
    }

    private void getPrev(Object objRef) {
        try {
            this.stack.add(((Wrapper)objRef).getPrev());
        } catch (Exception var3) {
            var3.fillInStackTrace();
        }

    }

    private void getFirst(Object objRef) {
        try {
            this.stack.add(((LinkedList)objRef).getFirst());
        } catch (Exception var3) {
            var3.fillInStackTrace();
        }

    }

    private void getValue(Object objRef) {
        try {
            this.stack.add(((Wrapper)objRef).getValue());
        } catch (Exception var3) {
            var3.fillInStackTrace();
        }

    }

    private void getLast(Object objRef) {
        try {
            this.stack.add(((LinkedList)objRef).getLast());
        } catch (Exception var3) {
            var3.fillInStackTrace();
        }

    }

    private void assign(Object a, Object b) {
        if (this.variables.get(b) != null) {
            b = this.variables.get(b);
        }

        this.variables.put(a, b);
    }

    private void mul(Object a, Object b) {
        this.stack.add(Integer.valueOf(a.toString()) * Integer.valueOf(b.toString()));
    }

    private void minus(Object a, Object b) {
        this.stack.add(Integer.valueOf(a.toString()) - Integer.valueOf(b.toString()));
    }

    private void greater(Object a, Object b) {
        this.stack.add(Integer.valueOf(a.toString()) > Integer.valueOf(b.toString()));
    }

    private void greater_eq(Object a, Object b) {
        this.stack.add(Integer.valueOf(a.toString()) >= Integer.valueOf(b.toString()));
    }

    private void less(Object a, Object b) {
        this.stack.add(Integer.valueOf(a.toString()) < Integer.valueOf(b.toString()));
    }

    private void less_eq(Object a, Object b) {
        this.stack.add(Integer.valueOf(a.toString()) <= Integer.valueOf(b.toString()));
    }

    private void div(Object a, Object b) {
        this.stack.add(Integer.valueOf(a.toString()) / Integer.valueOf(b.toString()));
    }

    private void plus(Object a, Object b) {
        this.stack.add(Integer.valueOf(a.toString()) + Integer.valueOf(b.toString()));
    }

    private void equal(Object a, Object b) {
        this.stack.add(a.toString().equals(b.toString()));
    }

    private void not_equal(Object a, Object b) {
        this.stack.add(a.toString().equals(b.toString()));
    }

    private void initLL(Object a) {
        try {
            this.variables.put(a.toString(), new LinkedList());
        } catch (Exception var3) {
            var3.fillInStackTrace();
        }

    }

    private void add(Object obj, Object value) {
        try {
            ((LinkedList)obj).add(value);
        } catch (Exception var4) {
            System.out.print("Error to add element in LinkedList");
        }

    }
}
