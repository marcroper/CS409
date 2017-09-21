package VisitorEgMod;

public class Engine implements ICarElement {
	private int capacity;
	private String oilLevel;
	
	public Engine(int capacity, String oilLevel) {
        this.capacity = capacity;
        this.oilLevel = oilLevel;
    }
	
	public int getCapacity(){
		return capacity;
	}

    public String getOilLevel() {
        return this.oilLevel;
    }
    
    public void accept(ICarElementVisitor visitor) {
        visitor.visit(this);
    }
}
