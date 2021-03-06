package jsCompil;

import jsCompil.utils.Configurator;
import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import jsCompil.MathLangLexer;
import jsCompil.MathLangParser;

import java.util.ArrayList;

public class ASTPrinter {
    public static void main(String[] args) throws Exception {
        CharStream stream = new ANTLRFileStream(Configurator.getPrinterFile());
        MathLangLexer lexer = new MathLangLexer(stream);
        MathLangParser parser = new MathLangParser(new CommonTokenStream(lexer));
        CommonTree tree = (CommonTree) parser.execute().getTree();

        printTree(tree, 0);
    }


    static void printTree(CommonTree tree, int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("|  ");
        }
        System.out.println((char) 27 + "[7m" + "|- " + tree + (char)27 + "[0m");

        if (tree.getChildCount() > 0) {
            ArrayList list = (ArrayList) tree.getChildren();
            for (Object o : list) {
                printTree((CommonTree) o, level + 1);
            }
        }
    }
}
