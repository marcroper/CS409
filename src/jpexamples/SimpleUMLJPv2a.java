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
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

// Simple illustrate of how much UML stuff can be extracted using JP

public class SimpleUMLJPv2a {

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
     * Try and get fields then methods and vairable declared within
     */
    private static class ClassDiagramVisitor extends VoidVisitorAdapter {
    	
//        @Override
//        public void visit(FieldDeclaration n, Object arg) {
//        	System.out.println("Fields...");
//            for (VariableDeclarator var : n.getVariables()) {
//                var.accept(this, arg);
//            }
        }
    
    public void visit(FieldDeclaration n, Object arg) {
    	System.out.println("Fields...");
    	for (VariableDeclarator var : n.getVariables()) {
    		System.out.println(var.getId().getName());
    	}
    	System.out.println("Field type: " + n.getType());
    	// System.out.println("Field modifier: " + n.getModifiers() + " " + decodeModifiers(n.getModifiers()));
    }
        
    	
        public void visit(MethodDeclaration n, Object arg) {
            System.out.println("Method name: " + n.getName());
            System.out.println("Method type: " + n.getType());
        	System.out.println("Method parameters: ");
//        	for (Parameter p : n.getParameters()) {
//                p.accept(this, arg);
//            }
        	n.getBody().accept(this, arg); // process rest of method body
        }
        
        public void visit(VariableDeclarationExpr n, Object arg) {
        	System.out.println("Variables...");
        	for (VariableDeclarator var : n.getVars()) {
              	System.out.println(var.getId().getName());
              }
        }
        
//        @Override
//        public void visit(FieldDeclaration n, Object arg) {
//        	System.out.println("Fields...");
//            for (VariableDeclarator var : n.getVariables()) {
//                var.accept(this, arg);
//            }
//        }
//        
//        @Override
//        public void visit(VariableDeclarator n, Object arg) {
//            n.getId().accept(this, arg);
//        }
//        
//        @Override
//        public void visit(VariableDeclaratorId n, Object arg) {
//            System.out.println("Field: " + n.getName());
//        }
//        
//        @Override
//        public void visit(FieldDeclaration n, Object arg) {
//          System.out.println("Fields...");
//          for (VariableDeclarator var : n.getVariables()) {
//          	System.out.println(var.getId().getName());
//          }
//      	  System.out.println("Field type: " + n.getType());
//      	  // System.out.println("Field modifier: " + n.getModifiers() + " " + decodeModifiers(n.getModifiers()));
//        }
        

        
        // Adapted from DumpVisitor
        /*
        private String decodeModifiers(final int modifiers) {
    		if (ModifierSet.isPrivate(modifiers)) {
    			return("private ");
    		}
    		if (ModifierSet.isProtected(modifiers)) {
    			return("protected ");
    		}
    		if (ModifierSet.isPublic(modifiers)) {
    			return("public ");
    		}
    		if (ModifierSet.isAbstract(modifiers)) {
    			return("abstract ");
    		}
    		if (ModifierSet.isStatic(modifiers)) {
    			return("static ");
    		}
    		if (ModifierSet.isFinal(modifiers)) {
    			return("final ");
    		}
    		if (ModifierSet.isNative(modifiers)) {
    			return("native ");
    		}
    		if (ModifierSet.isStrictfp(modifiers)) {
    			return("strictfp ");
    		}
    		if (ModifierSet.isSynchronized(modifiers)) {
    			return("synchronized ");
    		}
    		if (ModifierSet.isTransient(modifiers)) {
    			return("transient ");
    		}
    		if (ModifierSet.isVolatile(modifiers)) {
    			return("volatile ");
    		}
    		return "";
    	} */
    }
}