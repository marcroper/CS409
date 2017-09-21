package VisitorEgMod;

public class Body implements ICarElement {
	private String colour;
	private String condition;

    public Body(String colour, String condition) {
        this.colour = colour;
        this.condition = condition;
    }

    public String getColour() {
        return this.colour;
    }
    
    public String getCondition(){
    	return this.condition;
    }
	
    public void accept(ICarElementVisitor visitor) {
        visitor.visit(this);
    }
}
