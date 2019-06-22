//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.ArrayList;
import java.util.List;

public class MainParser {
    private List<Token> tokens;
    private int pos;
    private List<String> poliz;
    private List<Token> buffer;
    private List<Integer> addrsForFilling;
    private List<Integer> addrsJumps;
    private List<String> calls;

    public MainParser(List<Token> tokens) {
        this.tokens = tokens;
        this.poliz = new ArrayList();
        this.buffer = new ArrayList();
        this.addrsForFilling = new ArrayList();
        this.addrsJumps = new ArrayList();
        this.calls = new ArrayList();
        this.pos = 0;
    }

    public List<String> getPoliz() {
        return this.poliz;
    }

    public boolean lang() {
        while(true) {
            if (this.pos < this.tokens.size()) {
                if (this.expr()) {
                    continue;
                }

                this.printExeption(((Token)this.tokens.get(this.pos)).getValue(), "Expression");
                return false;
            }

            return true;
        }
    }

    private boolean expr() {
        return this.init() || this.if_stmt() || this.loopwhile() || this.IO();
    }

    private boolean IO() {
        return this.cout() || this.cin();
    }

    private boolean cout() {
        if (!this.COUT_KW()) {
            return false;
        } else if (!this.brcktexpr()) {
            return false;
        } else {
            return this.semicolon();
        }
    }

    private boolean cin() {
        if (!this.CIN_KW()) {
            return false;
        } else if (!this.bracket_open()) {
            return false;
        } else if (!this.var()) {
            this.printExeption(((Token)this.tokens.get(this.pos)).getValue(), "variable");
            return false;
        } else if (!this.bracket_close()) {
            this.printExeption(((Token)this.tokens.get(this.pos)).getValue(), ")");
            return false;
        } else {
            return this.semicolon();
        }
    }

    private boolean COUT_KW() {
        if (((Token)this.tokens.get(this.pos)).getTag().equals("COUT")) {
            this.push((Token)this.tokens.get(this.pos++));
            return true;
        } else {
            return false;
        }
    }

    private boolean CIN_KW() {
        if (((Token)this.tokens.get(this.pos)).getTag().equals("CIN")) {
            this.push((Token)this.tokens.get(this.pos++));
            return true;
        } else {
            return false;
        }
    }

    private boolean addobj() {
        if (!this.dot()) {
            this.printExeption(((Token)this.tokens.get(this.pos)).getValue(), ".");
            return false;
        } else if (!this.add()) {
            this.printExeption(((Token)this.tokens.get(this.pos)).getValue(), "add");
            return false;
        } else if (!this.bracket_open()) {
            this.printExeption(((Token)this.tokens.get(this.pos)).getValue(), "(");
            return false;
        } else if (!this.stmt()) {
            this.printExeption(((Token)this.tokens.get(this.pos)).getValue(), "Statement");
            return false;
        } else if (!this.bracket_close()) {
            this.printExeption(((Token)this.tokens.get(this.pos)).getValue(), ")");
            return false;
        } else {
            return true;
        }
    }

    private boolean add() {
        if (((Token)this.tokens.get(this.pos)).getTag().equals("Add")) {
            this.push((Token)this.tokens.get(this.pos++));
            return true;
        } else {
            return false;
        }
    }

    private boolean init() {
        if (!this.var()) {
            return false;
        } else {
            if (this.assign_op()) {
                if (!this.LL_KW() && !this.objref() && !this.stmt()) {
                    this.printExeption(((Token)this.tokens.get(this.pos)).getValue(), "object or object reference or statement");
                    return false;
                }
            } else if (!this.addobj()) {
                return false;
            }

            return this.semicolon();
        }
    }

    private boolean LL_KW() {
        if (((Token)this.tokens.get(this.pos)).getTag().equals("LinkedList")) {
            this.push((Token)this.tokens.get(this.pos++));
            return true;
        } else {
            return false;
        }
    }

    private boolean semicolon() {
        if (((Token)this.tokens.get(this.pos)).getTag().equals("SEMICOLON")) {
            this.push((Token)this.tokens.get(this.pos++));
            return true;
        } else {
            return false;
        }
    }

    private boolean var() {
        if (((Token)this.tokens.get(this.pos)).getTag().equals("VAR")) {
            this.push((Token)this.tokens.get(this.pos++));
            return true;
        } else {
            return false;
        }
    }

    private boolean assign_op() {
        if (((Token)this.tokens.get(this.pos)).getTag().equals("ASSIGN_OP")) {
            this.push((Token)this.tokens.get(this.pos++));
            return true;
        } else {
            return false;
        }
    }

    private boolean stmt() {
        if (this.value()) {
            do {
                if (!this.op()) {
                    return true;
                }
            } while(this.value());

            this.printExeption(((Token)this.tokens.get(this.pos)).getValue(), "Value");
            return false;
        } else {
            return false;
        }
    }

    private boolean value() {
        return this.var() || this.digit() || this.brcktexpr();
    }

    private boolean brcktexpr() {
        if (!this.bracket_open()) {
            return false;
        } else if (!this.stmt()) {
            this.printExeption(((Token)this.tokens.get(this.pos)).getValue(), "Statement");
            return false;
        } else if (!this.bracket_close()) {
            this.printExeption(((Token)this.tokens.get(this.pos)).getValue(), ")");
            return false;
        } else {
            return true;
        }
    }

    private boolean digit() {
        if (!((Token)this.tokens.get(this.pos)).getTag().equals("INT") && !((Token)this.tokens.get(this.pos)).getTag().equals("FLOAT")) {
            return false;
        } else {
            this.push((Token)this.tokens.get(this.pos++));
            return true;
        }
    }

    private boolean if_stmt() {
        if (!this.if_kw()) {
            return false;
        } else if (!this.bracket_open()) {
            this.printExeption(((Token)this.tokens.get(this.pos)).getValue(), "(");
            return false;
        } else if (!this.comp()) {
            this.printExeption(((Token)this.tokens.get(this.pos)).getValue(), "Comparison");
            return false;
        } else if (!this.bracket_close()) {
            this.printExeption(((Token)this.tokens.get(this.pos)).getValue(), ")");
            return false;
        } else if (!this.fbracket_open()) {
            this.printExeption(((Token)this.tokens.get(this.pos)).getValue(), "{");
            return false;
        } else {
            while(this.expr()) {
            }

            if (!this.fbracket_close()) {
                this.printExeption(((Token)this.tokens.get(this.pos)).getValue(), "}");
                return false;
            } else {
                return true;
            }
        }
    }

    private boolean if_kw() {
        if (((Token)this.tokens.get(this.pos)).getTag().equals("IF_KW")) {
            this.push((Token)this.tokens.get(this.pos++));
            return true;
        } else {
            return false;
        }
    }

    private boolean bracket_open() {
        if (((Token)this.tokens.get(this.pos)).getTag().equals("BRACKET_OPEN")) {
            this.push((Token)this.tokens.get(this.pos++));
            return true;
        } else {
            return false;
        }
    }

    private boolean bracket_close() {
        if (((Token)this.tokens.get(this.pos)).getTag().equals("BRACKET_CLOSE")) {
            this.push((Token)this.tokens.get(this.pos++));
            return true;
        } else {
            return false;
        }
    }

    private boolean fbracket_open() {
        if (((Token)this.tokens.get(this.pos)).getTag().equals("FBRACKET_OPEN")) {
            this.push((Token)this.tokens.get(this.pos++));
            return true;
        } else {
            return false;
        }
    }

    private boolean fbracket_close() {
        if (((Token)this.tokens.get(this.pos)).getTag().equals("FBRACKET_CLOSE")) {
            this.push((Token)this.tokens.get(this.pos++));
            return true;
        } else {
            return false;
        }
    }

    private boolean op() {
        if (!((Token)this.tokens.get(this.pos)).getTag().equals("OP_PLUS") && !((Token)this.tokens.get(this.pos)).getTag().equals("OP_MINUS") && !((Token)this.tokens.get(this.pos)).getTag().equals("OP_MUL") && !((Token)this.tokens.get(this.pos)).getTag().equals("OP_DIV")) {
            return false;
        } else {
            this.push((Token)this.tokens.get(this.pos++));
            return true;
        }
    }

    private boolean comp() {
        if (!this.stmt()) {
            return false;
        } else if (!this.compare_op()) {
            this.printExeption(((Token)this.tokens.get(this.pos)).getValue(), "Compare operation");
            return false;
        } else if (!this.stmt()) {
            this.printExeption(((Token)this.tokens.get(this.pos)).getValue(), "Statement");
            return false;
        } else {
            return true;
        }
    }

    private boolean compare_op() {
        if (!((Token)this.tokens.get(this.pos)).getTag().equals("COP_NEQ") && !((Token)this.tokens.get(this.pos)).getTag().equals("COP_EQ") && !((Token)this.tokens.get(this.pos)).getTag().equals("COP_MOR") && !((Token)this.tokens.get(this.pos)).getTag().equals("COP_LES") && !((Token)this.tokens.get(this.pos)).getTag().equals("COP_EQMOR") && !((Token)this.tokens.get(this.pos)).getTag().equals("COP_EQLES")) {
            return false;
        } else {
            this.push((Token)this.tokens.get(this.pos++));
            return true;
        }
    }

    private boolean loopwhile() {
        if (!this.while_kw()) {
            return false;
        } else if (!this.bracket_open()) {
            this.printExeption(((Token)this.tokens.get(this.pos)).getValue(), "(");
            return false;
        } else if (!this.comp()) {
            this.printExeption(((Token)this.tokens.get(this.pos)).getValue(), "Comparison");
            return false;
        } else if (!this.bracket_close()) {
            this.printExeption(((Token)this.tokens.get(this.pos)).getValue(), ")");
            return false;
        } else if (!this.fbracket_open()) {
            this.printExeption(((Token)this.tokens.get(this.pos)).getValue(), "{");
            return false;
        } else {
            while(this.expr()) {
            }

            if (!this.fbracket_close()) {
                this.printExeption(((Token)this.tokens.get(this.pos)).getValue(), "}");
                return false;
            } else {
                return true;
            }
        }
    }

    private boolean while_kw() {
        if (((Token)this.tokens.get(this.pos)).getTag().equals("WHILE_KW")) {
            this.push((Token)this.tokens.get(this.pos++));
            return true;
        } else {
            return false;
        }
    }

    private Token endEl(List<Token> list) {
        return list.size() != 0 ? (Token)list.get(list.size() - 1) : null;
    }

    private boolean dot() {
        if (((Token)this.tokens.get(this.pos)).getTag().equals("DOT")) {
            ++this.pos;
            return true;
        } else {
            return false;
        }
    }

    private boolean objref() {
        if (!this.var()) {
            return false;
        } else if (!this.dot()) {
            --this.pos;
            this.poliz.remove(this.poliz.size() - 1);
            return false;
        } else if (!this.method()) {
            this.printExeption(((Token)this.tokens.get(this.pos)).getValue(), "Method");
            return false;
        } else {
            while(this.dot()) {
                if (!this.method()) {
                    return false;
                }
            }

            return true;
        }
    }

    private boolean method() {
        if (!((Token)this.tokens.get(this.pos)).getTag().equals("GetLast") && !((Token)this.tokens.get(this.pos)).getTag().equals("GetFirst") && !((Token)this.tokens.get(this.pos)).getTag().equals("GetSize") && !((Token)this.tokens.get(this.pos)).getTag().equals("GetNext") && !((Token)this.tokens.get(this.pos)).getTag().equals("GetPrev") && !((Token)this.tokens.get(this.pos)).getTag().equals("GetValue")) {
            return this.isSet();
        } else {
            this.push((Token)this.tokens.get(this.pos++));
            return true;
        }
    }

    private boolean isSet() {
        if (!this.isSett()) {
            this.printExeption(((Token)this.tokens.get(this.pos)).getValue(), "IsSet");
            return false;
        } else if (!this.bracket_open()) {
            this.printExeption(((Token)this.tokens.get(this.pos)).getValue(), "(");
            return false;
        } else if (!this.stmt()) {
            this.printExeption(((Token)this.tokens.get(this.pos)).getValue(), "Statement");
            return false;
        } else if (!this.bracket_close()) {
            this.printExeption(((Token)this.tokens.get(this.pos)).getValue(), ")");
            return false;
        } else {
            return true;
        }
    }

    private boolean isSett() {
        if (((Token)this.tokens.get(this.pos)).getTag().equals("IsSet")) {
            this.push((Token)this.tokens.get(this.pos++));
            return true;
        } else {
            return false;
        }
    }

    private void push(Token token) {
        if (!token.getTag().equals("VAR") && !token.getTag().equals("INT") && !token.getTag().equals("FLOAT") && !token.getTag().equals("LinkedList") && !token.getTag().equals("HSet") && !token.getTag().equals("GetLast") && !token.getTag().equals("GetFirst") && !token.getTag().equals("GetSize") && !token.getTag().equals("GetNext") && !token.getTag().equals("GetPrev") && !token.getTag().equals("GetValue")) {
            if (!token.getValue().equals("while") && !token.getValue().equals("if")) {
                if (token.getValue().equals(")")) {
                    while(!((Token)this.buffer.get(this.buffer.size() - 1)).getValue().equals("(")) {
                        this.poliz.add(((Token)this.buffer.remove(this.buffer.size() - 1)).getValue());
                    }

                    this.buffer.remove(this.buffer.size() - 1);
                    if (((Token)this.buffer.get(this.buffer.size() - 1)).getValue().equals("while") || ((Token)this.buffer.get(this.buffer.size() - 1)).getValue().equals("if")) {
                        this.buffer.remove(this.buffer.size() - 1);
                        this.addrsForFilling.add(this.poliz.size());
                        this.poliz.add("");
                        this.poliz.add("!F");
                    }

                    return;
                }

                if (token.getValue().equals("}")) {
                    while(!this.endEl(this.buffer).getValue().equals("{")) {
                        this.poliz.add(((Token)this.buffer.remove(this.buffer.size() - 1)).getValue());
                    }

                    this.buffer.remove(this.buffer.size() - 1);
                    String lastCall = (String)this.calls.remove(this.calls.size() - 1);
                    int buf;
                    if (lastCall.equals("while")) {
                        this.poliz.add(((Integer)this.addrsJumps.remove(this.addrsJumps.size() - 1)).toString());
                        this.poliz.add("!");
                        buf = (Integer)this.addrsForFilling.remove(this.addrsForFilling.size() - 1);
                        this.poliz.set(buf, String.valueOf(this.poliz.size()));
                    } else {
                        buf = (Integer)this.addrsForFilling.remove(this.addrsForFilling.size() - 1);
                        this.poliz.set(buf, String.valueOf(this.poliz.size()));
                    }

                    return;
                }

                if (!token.getValue().equals("(") && !token.getValue().equals("{") && this.buffer.size() != 0 && token.getPriority() < ((Token)this.buffer.get(this.buffer.size() - 1)).getPriority()) {
                    if (token.getValue().equals(";")) {
                        while(!((Token)this.buffer.get(this.buffer.size() - 1)).getValue().equals("=") && !((Token)this.buffer.get(this.buffer.size() - 1)).getValue().equals("cout") && !((Token)this.buffer.get(this.buffer.size() - 1)).getValue().equals("cin") && !((Token)this.buffer.get(this.buffer.size() - 1)).getValue().equals("add")) {
                            this.poliz.add(((Token)this.buffer.remove(this.buffer.size() - 1)).getValue());
                        }
                    }

                    this.poliz.add(((Token)this.buffer.remove(this.buffer.size() - 1)).getValue());
                }
            } else {
                this.calls.add(token.getValue());
                this.buffer.add(token);
                if (token.getValue().equals("while")) {
                    this.addrsJumps.add(this.poliz.size());
                }
            }

            if (!token.getValue().equals(";")) {
                this.buffer.add(token);
            }
        } else {
            this.poliz.add(token.getValue());
        }

    }

    private void printExeption(String detected, String expected) {
        System.out.println("\nParse error: detected '" + detected + "', but '" + expected + "' are expected!");
        System.exit(0);
    }
}
