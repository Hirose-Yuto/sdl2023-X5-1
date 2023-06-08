package main.java;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.compiler.IScanner;
import org.eclipse.jdt.core.compiler.ITerminalSymbols;
import org.eclipse.jdt.core.compiler.InvalidInputException;

import java.util.ArrayList;
import java.util.List;

public class Splitter {
    static final IScanner scanner = ToolFactory.createScanner(true, true, false, JavaCore.VERSION_19);

    static public List<String> split(String source) {
        ArrayList<String> list = new ArrayList<>();
        scanner.setSource(source.toCharArray());
        try {
            for (; ; ) {
                final int type = scanner.getNextToken();
                if (type == ITerminalSymbols.TokenNameEOF) break;
                final String token = new String(scanner.getCurrentTokenSource());
                list.add(token);
            }
        } catch (final InvalidInputException e) {
            e.printStackTrace();
        }
        return list;
    }
}
