package jpexamples;


import java.io.FileInputStream;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.ModifierSet;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.body.VariableDeclaratorId;
import com.github.javaparser.ast.expr.ClassExpr;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;


public class SimpleUMLJPv5 {

    public static void main(String[] args) throws Exception {
        FileInputStream in = new FileInputStream("ArrayStack.java");

        CompilationUnit cu;
        try {
            cu = JavaParser.parse(in);
        } finally {
            in.close();
        }
        new ClassDiagramVisitor().visit(cu, null);
    }

    /**
     * class instantiation
     */
    private static class ClassDiagramVisitor extends VoidVisitorAdapter {
 
        @Override
        public void visit(MethodDeclaration n, Object arg) {
            System.out.println("Method name: " + n.getName());
        	n.getBody().accept(this, arg); // process the body to pick up object creation
        }
        
        public void visit(ObjectCreationExpr p, Object arg) {
        	System.out.println("New class instance");
        	System.out.println("Type: " + p.getType());
        }
    }
}