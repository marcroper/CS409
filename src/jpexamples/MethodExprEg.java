package jpexamples;

import java.io.FileInputStream;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;


// Another example from javaparser website

public class MethodExprEg {

    public static void main(String[] args) throws Exception {
        // creates an input stream for the file to be parsed
        FileInputStream in = new FileInputStream("ArrayStack.java");

        CompilationUnit cu;
        try {
            // parse the file
            cu = JavaParser.parse(in);
        } finally {
            in.close();
        }

        // visit and print the methods names
        new MethodCallExpressionVisitor().visit(cu, null);
    }

    /**
     * Simple visitor implementation for visiting MethodDeclaration nodes. 
     */
    private static class MethodCallExpressionVisitor extends VoidVisitorAdapter {

        @Override
        public void visit(MethodCallExpr n, Object arg) {
            // Details of methods
            System.out.println("Name "+n.getName());
            System.out.println("Args "+n.getArguments());
            System.out.println("Name Expr "+n.asNameExpr());
            System.out.println("Scope "+n.getScope());
        }
        
        
    }
}