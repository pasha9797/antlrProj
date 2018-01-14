package ru.vsu.js;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.tree.CommonTree;
import ru.vsu.js.model.abstracts.ExecNode;
import ru.vsu.js.model.abstracts.Namespace;
import ru.vsu.js.utils.Configurator;
import ru.vsu.js.utils.DefaultFuncsAdder;
import ru.vsu.MathLangLexer;
import ru.vsu.MathLangParser;
import ru.vsu.js.utils.ToExecNodeConverter;

public class ASTInterpreter {
    public static void main(String[] args) throws Exception {
        CharStream stream = new ANTLRFileStream(Configurator.getInterpreterFile());
        MathLangLexer lexer = new MathLangLexer(stream);
        MathLangParser parser = new MathLangParser(new CommonTokenStream(lexer));
        CommonTree tree = (CommonTree) parser.execute().getTree();
        if (parser.getNumberOfSyntaxErrors() > 0) {
            System.out.println((char) 27 + "[35m" + "Syntax analysis errors occurred (text above)" + (char)27 + "[0m");
            return;
        }
        else {
            System.out.println((char) 27 + "[35m" + "Syntax analysis OK!" + (char)27 + "[0m");
            try {
                ExecNode node = ToExecNodeConverter.convertTree(tree, null);
                DefaultFuncsAdder.addDefaultFuncs((Namespace) node);
                node.execute();
            } catch (Exception e) {
                System.out.println((char) 27 + "[35m" + "Semantic analysis error: " + e.getMessage() + (char) 27 + "[0m");
                return;
            }
            System.out.println((char) 27 + "[35m" + "Semantic analysis OK! Interpretation completed" + (char)27 + "[0m");
        }
    }
}
