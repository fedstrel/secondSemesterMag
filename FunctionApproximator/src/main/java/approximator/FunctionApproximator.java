package approximator;

public abstract class FunctionApproximator {
    protected double[] definedOn = new double[2];
    public abstract double findFunctionAtPointApprox(double x, double accuracy);
    public abstract double findFunctionAtPointExact(double x);
    public double getLowerBound() {
        return definedOn[0];
    }
    public double getUpperBound() {
        return definedOn[1];
    }
    protected boolean isDefined(double x) {
        return x >= definedOn[0] && x <= definedOn[1];
    }
}
