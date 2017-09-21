package jpexamples;


import java.io.FileInputStream;
import java.util.List;

import com.github.javaparser.ASTHelper;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.ModifierSet;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.body.VariableDeclaratorId;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.ClassExpr;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.expr.UnaryExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class SimpleUMLJPmodv2 {

    public static void main(String[] args) throws Exception {
        FileInputStream in = new FileInputStream("ArrayStack.java");

        CompilationUnit cu;
        try {
            cu = JavaParser.parse(in);
        } finally {
            in.close();
        }
        addField(cu);
        // write the modified cu back...
        System.out.println(cu.toString());
        // then modify this with a visitor in the constructor...
        new ClassDiagramVisitor().visit(cu, null);
        // Need to find a better way to do this
        // No, that's fine - can just write it to a file
        System.out.println(cu.toString());
    }
    /**
     * Example of how to modify code and insert instrumentation
     * Look at how we can get object info...
     * i.e. x = new y
     * Start with add in an object id
     * 
     * Need to do programmatically?
     *
     */
    
    	private static void addField(CompilationUnit cu) {
    	    List<TypeDeclaration> types = cu.getTypes();
    	    for (TypeDeclaration type : types) {
//    	    	System.out.println("Types");
//	            System.out.println(type.toString());
//    	        List<BodyDeclaration> members = type.getMembers();  dont need this  -just exploring
    	        // worrying about this too much - trying to give it an initial value...
    	        // Leave for now
//    	        VariableDeclaratorId vid = new VariableDeclaratorId("objectId");
//    	        VariableDeclarator vd = new VariableDeclarator(new VariableDeclaratorId("objectId"));
//    	        AssignExpr ae = new AssignExpr(vd, )
//    	        FieldDeclaration fd = ASTHelper.createFieldDeclaration(ModifierSet.STATIC, ASTHelper.INT_TYPE, "objectId");
    	        FieldDeclaration fd = ASTHelper.createFieldDeclaration(ModifierSet.STATIC, ASTHelper.createReferenceType("String", 0), "objectId");
    	        ASTHelper.addMember(type, fd);
    	        }
    	    }
    	
private static class ClassDiagramVisitor extends VoidVisitorAdapter {
    @Override
    public void visit(ConstructorDeclaration n, Object arg) {
    	BlockStmt block = new BlockStmt();
        NameExpr systemOut = ASTHelper.createNameExpr("System.out");
        MethodCallExpr call = new MethodCallExpr(systemOut, "print");
        ASTHelper.addArgument(call, new StringLiteralExpr(n.getName()));
        NameExpr systemOut2 = ASTHelper.createNameExpr("System.out");
        MethodCallExpr call2 = new MethodCallExpr(systemOut, "println");
        ASTHelper.addArgument(call2, new FieldAccessExpr(new NameExpr("this"), "objectId"));
        ASTHelper.addStmt(block, call);
        ASTHelper.addStmt(block, call2);
        UnaryExpr ue = new UnaryExpr(new NameExpr("objectId"), UnaryExpr.Operator.posIncrement);
        ASTHelper.addStmt(block,ue); 
        BlockStmt oldBody = n.getBlock();
        ASTHelper.addStmt(block, oldBody);
        n.setBlock(block);
    }
}

}



