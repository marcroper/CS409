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

// Simple illustrate of how much UML stuff can be extracted using JP

public class SimpleUMLJP {

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
        new ClassDiagramVisitor().visit(cu, null);
//        DumpVisitor dv = new DumpVisitor();
//        dv.visit(cu, null);
//        System.out.println(dv.getSource());
    }

    /**
     * Simple visitor implementation for visiting MethodDeclaration nodes. 
     */
    private static class ClassDiagramVisitor extends VoidVisitorAdapter {
    	
    	public void visit(ClassOrInterfaceDeclaration n, Object arg){
    		System.out.println("Class Name: " + n.getName());
    		System.out.println("Class Extends: ");
//    		for (ClassOrInterfaceType coi : n.getExtends()) { // crashes - need to check for empty list
//            	System.out.println(coi.getName());
//    		}
    		System.out.println("Class Implements: ");
    		for (ClassOrInterfaceType coi : n.getImplements()) {
            	System.out.println(coi.getName());
    		}
    		super.visit(n, arg);  // need this to process rest of content
    	}
    	
        @Override
        public void visit(FieldDeclaration n, Object arg) {
        	System.out.println("Fields...");
        	System.out.println("Field type: " + n.getType());
        	System.out.println("Field modifier: " + n.getModifiers() + " " + decodeModifiers(n.getModifiers()));
            for (VariableDeclarator var : n.getVariables()) {
            	System.out.println(var.getId().getName()); // avoid problem of VariableDeclarator picking up constructor parameter
//                var.accept(this, arg);
            }
        }
        
//        Maybe causing problems... yes - stopping the ObjectCreationExpr being called.
//        @Override
//        public void visit(VariableDeclarator n, Object arg) {
//        	System.out.println("VarDec...");
//            n.getId().accept(this, arg);
//        }
        
//        @Override
//        public void visit(VariableDeclaratorId n, Object arg) {
//        	System.out.println("VarDecId...");
//            System.out.println("Field: " + n.getName());
//        }
        
        @Override
        public void visit(MethodDeclaration n, Object arg) {
            System.out.println("Method name: " + n.getName());
            System.out.println("Method type: " + n.getType());
        	System.out.println("Method modifier: " + n.getModifiers() + " " + decodeModifiers(n.getModifiers()));
        	System.out.println("Method parameters: ");
        	for (Parameter p : n.getParameters()) {
            	System.out.println("delegating to parameter"); // avoid problem of VariableDeclarator picking up constructor parameter
                p.accept(this, arg);
            }
        	n.getBody().accept(this, arg); // process the body to pick up the variable declaration
        }
        
        public void visit(Parameter p, Object arg) {
        	System.out.println("Parameter");
        	System.out.println("Parameter type: " + p.getType());
        	System.out.println("Parameter name: " + p.getId().getName());
        }
        
        // Now trying to spot object allocations
        public void visit(ObjectCreationExpr p, Object arg) {
        	System.out.println("New class");
        	System.out.println("Type: " + p.getType());
        }
        
        private void processField(VariableDeclarator v){
        	System.out.println("Field name: " + v.getId().getName());
        }
        
        // Adapted from DumpVisitor
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
    	}
    }
}