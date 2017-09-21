package VisitorEgMod;


public class Car implements ICarElement {
    ICarElement[] elements;

    public Car() {
        this.elements = new ICarElement[] { new Wheel("front left", 2.1),
            new Wheel("front right", 2.3), new Wheel("back left", 1.8) ,
            new Wheel("back right", 1.9), new Body("brown", "average"), new Engine(1500, "low") };
    }

    public void accept(ICarElementVisitor visitor) {    
        for(ICarElement elem : elements) {
            elem.accept(visitor);
        }
        visitor.visit(this);    
    }
}
