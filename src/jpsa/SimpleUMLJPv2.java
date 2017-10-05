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
 
    	public void visit(FieldDeclaration n, Object a){
    		System.out.println("Field Type is: " + n.getElementType());
    		for(VariableDeclarator v : n.getVariables()){
    			System.out.println("Name: " + v.getName());
    		}
    	}
    	
    	public void visit(VariableDeclarationExpr n, Object a){
    		System.out.println("Var Type is: " + n.getElementType());
    		for(VariableDeclarator v : n.getVariables()){
    			System.out.println("Name: " + v.getName());
    		}
    	}
    	
//    	public void visit(VariableDeclarator v, Object a){
//    		System.out.println("Name: " + v.getName());
//    	}
    }
}
