package jpexamples;


import java.io.FileInputStream;

import com.github.javaparser.ASTHelper;
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
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class SimpleUMLJPmodv1 {

    public static void main(String[] args) throws Exception {
        FileInputStream in = new FileInputStream("Cipher.java");

        CompilationUnit cu;
        try {
            cu = JavaParser.parse(in);
        } finally {
            in.close();
        }
        new ClassDiagramVisitor().visit(cu, null);
        // write the modified cu back...
        System.out.println(cu.toString());
        // Need to find a better way to do this
        // No, that's fine - can just write it to a file
    }

    /**
     * Example of how to modify code and insert instrumentation
     */
    private static class ClassDiagramVisitor extends VoidVisitorAdapter {
    	
//    	public void visit(ClassOrInterfaceDeclaration n, Object arg){
//    		System.out.println("Class Name: " + n.getName());
//    		super.visit(n, arg);  
//    	}


//        @Override
//        public void visit(MethodDeclaration n, Object arg) {
//        	// As a start add some code to print out every method name as it executes
//        	// Stress difference to just printing out name as we visit it
//        	// Adapted from the Badawi blog post...
//            NameExpr systemOut = ASTHelper.createNameExpr("System.out");
//            MethodCallExpr call = new MethodCallExpr(systemOut, "println");
//            // This is wrong - want to have the method name here so need a different type of
//            // expression, but see what this does first...
//            //ASTHelper.addArgument(call, new StringLiteralExpr("hello, javaparser"));
//            ASTHelper.addArgument(call, new MethodCallExpr(new NameExpr("n"), "getName"));
//            // Excellent! This the best way of doing this????
//
//            // Add this statement to the main method
////            TypeDeclaration helloClass = unit.getTypes().get(0);
////            MethodDeclaration mainMethod = (MethodDeclaration) hello.getMembers().get(0);
//            ASTHelper.addStmt(n.getBody(), call);
////            System.out.println("Method name: " + n.getName());
//            // Works fine but adds to the end of the method...
//        }
        
        // Another go at getting this at the start...
    	// Works!!!!
        @Override
        public void visit(MethodDeclaration n, Object arg) {
        	// As a start add some code to print out every method name as it executes
        	// Stress difference to just printing out name as we visit it
        	// Adapted from the Badawi blog post...
        	BlockStmt block = new BlockStmt();
            NameExpr systemOut = ASTHelper.createNameExpr("System.out");
            MethodCallExpr call = new MethodCallExpr(systemOut, "println");
            // This is wrong - want to have the method name here so need a different type of
            // expression, but see what this does first...
            //ASTHelper.addArgument(call, new StringLiteralExpr("hello, javaparser"));
//            ASTHelper.addArgument(call, new MethodCallExpr(new NameExpr("n"), "getName"));
            ASTHelper.addArgument(call, new StringLiteralExpr(n.getName()));
            // Excellent! This the best way of doing this????

            // Add this statement to the main method
//            TypeDeclaration helloClass = unit.getTypes().get(0);
//            MethodDeclaration mainMethod = (MethodDeclaration) hello.getMembers().get(0);
//            ASTHelper.addStmt(n.getBody(), call);
            ASTHelper.addStmt(block, call);
            BlockStmt oldBody = n.getBody();
            ASTHelper.addStmt(block, oldBody);
            n.setBody(block);
//            System.out.println("Method name: " + n.getName());
            // Works fine but adds to the end of the method...
        }

// Should now look at doing this through the ModifierVisitorAdapter
        
    }
}