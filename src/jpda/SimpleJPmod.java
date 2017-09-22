package jpda;


import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class SimpleJPmod {

    public static void main(String[] args) throws Exception {
        // FileInputStream in = new FileInputStream("C:\\Users\\aes02104\\workspace\\CS409TestCode\\src\\AKSTest.java");
        FileInputStream in = new FileInputStream("/Users/aes02104/Documents/workspace/CS409TestCode/src/AKSTest.java");

        CompilationUnit cu;
        try {
            cu = JavaParser.parse(in);
        } finally {
            in.close();
        }
        new MethodModVisitor().visit(cu, null);
        // write the modified cu back...
        System.out.println(cu.toString());
        
        // Write modified AST to a file
        byte[] modfile = cu.toString().getBytes();
        // Path file = Paths.get("C:\\Users\\aes02104\\workspace\\CS409TestCode\\src\\AKSTestMod.java");
        Path file = Paths.get("/Users/aes02104/Documents/workspace/CS409TestCode/src/AKSTestMod.java");
        Files.write(file, modfile);
    }

    /**
     * Example of how to modify code and insert instrumentation
     * Just print out name of class after execution
     */
    private static class MethodModVisitor extends VoidVisitorAdapter {
        @Override
        public void visit(MethodDeclaration n, Object arg) {
        	// Step 1 - create a new node
        	// (Various options)
            // NameExpr systemOut = NameExpr.name("System.out");
            // MethodCallExpr call = new MethodCallExpr(systemOut, "println");
            // MethodCallExpr call = new MethodCallExpr(NameExpr.name("System.out"), "println");
            MethodCallExpr call = new MethodCallExpr(new NameExpr("System.out"), "println");
            // Add in the argument - name of method visited
            call.addArgument(new StringLiteralExpr(n.getName()));

            // Step 2 - Add this statement to the method
//            n.getBody().addStatement(call);

            // Step 3 - Modify block
        	BlockStmt block = new BlockStmt();
        	block.addStatement(call);
        	block.addStatement(n.getBody());
        	n.setBody(block);
        }        
    }
}