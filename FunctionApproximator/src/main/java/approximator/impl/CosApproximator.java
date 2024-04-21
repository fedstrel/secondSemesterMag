package approximator.impl;

import approximator.FunctionApproximator;
import exception.InvalidArgumentException;

public class CosApproximator extends FunctionApproximator {
    public CosApproximator() {
        definedOn[0] = -1;
        definedOn[1] = 1;
    }
    @Override
    public double findFunctionAtPointApprox(double x, double accuracy) {
        if (!isDefined(x))
            throw new InvalidArgumentException("Значение x находится вне области определения функции");
        double result = 1;
        double step = 2;
        double delta = (x * x) / (step * (step - 1));
        boolean flag = false;

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
        return Math.cos(x);
    }
}
