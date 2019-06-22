//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;

public class TestLang {
    public TestLang() {
    }

    public static void main(String[] args) {
        Lexer lexer = new Lexer("src/Test.txt");
        List<Token> tokens = lexer.go_lex();
        System.out.println("lexem:tag:priority");
        Iterator var3 = tokens.iterator();

        while(var3.hasNext()) {
            Token token = (Token)var3.next();
            String var10001 = token.getValue();
            System.out.println(var10001 + " " + token.getTag() + " " + token.getPriority());
        }

        MainParser parser = new MainParser(tokens);
        parser.lang();
        System.out.println("Invert Polish Notation:");
        Iterator var7 = parser.getPoliz().iterator();

        while(var7.hasNext()) {
            String p = (String)var7.next();
            System.out.print(p + " ");
        }

        System.out.print("\n");
        ExecuteMachine executeMachine = new ExecuteMachine(parser.getPoliz());
        executeMachine.process();
    }
}
