package VisitorEgMod;

public class Wheel implements ICarElement {
	private String name;
	private double pressure;

    public Wheel(String name, double pressure) {
        this.name = name;
        this.pressure = pressure;
    }

    public String getName() {
        return this.name;
    }
    
    public double getPressure(){
    	return this.pressure;
    }

    public void accept(ICarElementVisitor visitor) {
        visitor.visit(this);
    }
}
