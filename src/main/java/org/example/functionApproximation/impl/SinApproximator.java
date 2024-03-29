package org.example.functionApproximation.impl;

import org.example.exception.InvalidArgumentException;
import org.example.functionApproximation.FunctionApproximator;

public class SinApproximator extends FunctionApproximator {
    public SinApproximator() {
        definedOn[0] = -1;
        definedOn[1] = 1;
    }
    @Override
    public double findFunctionAtPointApprox(double x, double accuracy) {
        if (!isDefined(x))
            throw new InvalidArgumentException("Значение x находится вне области определения функции");
        double result = 0;
        double step = 1;
        double delta = x;
        boolean flag = true;

        while (accuracy <= delta) {
            result += delta * (flag ? 1 : -1);
            step += 2;
            delta *= (x * x) / (step * (step - 1));
            flag = !flag;
        }
        return result;
    }

    @Override
    public double findFunctionAtPointExact(double x) {
        return Math.sin(x);
    }
}
