package jpda;

import java.io.FileInputStream;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.type.PrimitiveType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class MethodChanger {

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

        // visit and change the methods names and parameters
        new MethodChangerVisitor().visit(cu, null);
        // visit and change the methods names and parameters
//        cu.accept(new MethodChangerVisitor(), null);

        // prints the changed compilation unit
        System.out.println(cu.toString());
    }

    /**
     * Simple visitor implementation for visiting MethodDeclaration nodes.
     */
    private static class MethodChangerVisitor extends VoidVisitorAdapter {

        @Override
        public void visit(MethodDeclaration n, Object arg) {
        	System.out.println("Name: " + n.getName());
            // change the name of the method
        	if (n.getName().asString().equals("putintotest")){
        		
//        		System.out.println("Found it");
        		// n.setName(n.getName().toUpperCase());
        		n.setName("betterName");

        		// create the new parameter
        		//was Parameter newArg = ASTHelper.createParameter(ASTHelper.INT_TYPE, "value");
        		// also was Parameter newArg = Parameter.create(PrimitiveType.INT_TYPE, "newparameter");
        		// now
        		n.addParameter(PrimitiveType.intType(), "value");

        		// add the parameter to the method
        		// was ASTHelper.addParameter(n, newArg);
        		// n.addParameter(newArg);
        	}
        }
    }
}

