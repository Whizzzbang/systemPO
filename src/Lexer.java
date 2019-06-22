//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Lexer {
    private StringBuilder input = new StringBuilder();
    private List<Lexem> lexems = (new LexDef()).getLexems();

    public Lexer(String path) {
        try {
            Stream st = Files.lines(Paths.get(path));

            try {
                StringBuilder var10001 = this.input;
                Objects.requireNonNull(var10001);
                st.forEach(var10001::append);
            } catch (Throwable var6) {
                if (st != null) {
                    try {
                        st.close();
                    } catch (Throwable var5) {
                        var6.addSuppressed(var5);
                    }
                }

                throw var6;
            }

            if (st != null) {
                st.close();
            }

        } catch (IOException var7) {
            var7.fillInStackTrace();
        }
    }

    public List<Token> go_lex() {
        int pos = 0;
        ArrayList tokens = new ArrayList();

        Boolean match;
        do {
            if (pos >= this.input.length()) {
                return tokens;
            }

            match = false;
            Iterator var6 = this.lexems.iterator();

            while(var6.hasNext()) {
                Lexem lexem_def = (Lexem)var6.next();
                Pattern pattern = Pattern.compile(lexem_def.getRegexp());
                Matcher matcher = pattern.matcher(this.input.substring(pos));
                match = matcher.find();
                if (match) {
                    if (!lexem_def.getName().equals("NULL")) {
                        tokens.add(new Token(matcher.group(), lexem_def.getName(), lexem_def.getPriority()));
                        pos += matcher.end();
                    } else {
                        pos += matcher.end();
                    }
                    break;
                }
            }
        } while(match);

        System.out.println("Wrong character : " + this.input.toString().charAt(pos));
        return tokens;
    }
}
