package VisitorEgMod;

public class CarElementMechanicVisitor implements ICarElementVisitor {
    public void visit(Wheel wheel) {
        System.out.println("Pressure of " + wheel.getName() + " wheel is " + wheel.getPressure());
    }

    public void visit(Engine engine) {
        System.out.println("Oil level of engine is " + engine.getOilLevel());
    }

    public void visit(Body body) {
        System.out.println("Condition of body is " +body.getCondition());
    }

    public void visit(Car car) {
//        System.out.println("Checked car");
    }
}
