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


public class SimpleUMLJPv4 {

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
     * Relationships between classes
     */
    private static class ClassDiagramVisitor extends VoidVisitorAdapter {
    	
    	public void visit(ClassOrInterfaceDeclaration n, Object arg){
    		System.out.println("Class Name: " + n.getName());
    		System.out.println("Class Implements: ");
    		for (ClassOrInterfaceType coi : n.getImplements()) {
            	System.out.println(coi.getName());
    		}
    		System.out.println("Class Extends: ");
    		for (ClassOrInterfaceType coi :n.getExtends()){
    			System.out.println(coi.getName());
    		}
    		super.visit(n, arg);
    	}
    }
}