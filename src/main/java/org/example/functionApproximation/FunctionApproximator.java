package org.example.functionApproximation;

public abstract class FunctionApproximator {
    protected double[] definedOn = new double[2];
    abstract public double findFunctionAtPointApprox(double x, double accuracy);
    abstract public double findFunctionAtPointExact(double x);
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
