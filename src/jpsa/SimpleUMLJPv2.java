package jpsa;


import java.io.FileInputStream;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
//import com.github.javaparser.ast.body.ModifierSet;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.body.VariableDeclaratorId;
import com.github.javaparser.ast.expr.ClassExpr;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

// Simple illustrate of how much UML stuff can be extracted using JP

public class SimpleUMLJPv2 {

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
     * What about the fields... 
     */
    private static class ClassDiagramVisitor extends VoidVisitorAdapter {
 
        @Override
        public void visit(FieldDeclaration n, Object arg) {
        	System.out.println("Fields:");
            for (VariableDeclarator var : n.getVariables()) {
                System.out.println(var.getId().getName());
            }
            System.out.println(n.getType());
        }
        
        public void visit(VariableDeclarationExpr n, Object arg){
        	System.out.println("Local Var:");
        	for(VariableDeclarator v : n.getVars()){
        		System.out.println(v.getId().getName());
        	}
        	System.out.println(n.getType());
        }
        
        @Override
        public void visit(VariableDeclarator n, Object arg) {
            System.out.println(n.getId().getName());
        }
    }
}
