package approximator.impl;

import exception.InvalidArgumentException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class SinApproximatorTest {
    @Test
    public void shouldBeDefinedOnRangeFromMinusOneToOneTest() {
        SinApproximator sut = new SinApproximator();

        Assertions.assertThatCode(() ->
                sut.findFunctionAtPointApprox(-2, 0.001)
        ).isExactlyInstanceOf(InvalidArgumentException.class);

        Assertions.assertThatCode(() ->
                sut.findFunctionAtPointApprox(2, 0.001)
        ).isExactlyInstanceOf(InvalidArgumentException.class);

        Assertions.assertThatCode(() -> {
            sut.findFunctionAtPointApprox(-1, 0.001);
            sut.findFunctionAtPointApprox(0, 0.001);
            sut.findFunctionAtPointApprox(1, 0.001);
        }).doesNotThrowAnyException();
    }

    @Test
    public void calculatedValueShouldNotDifferFromExactMoreThanAccuracyTest() {
        SinApproximator sut = new SinApproximator();
        Random rand = new Random();

        double value = rand.nextDouble() * (rand.nextInt() % 2 == 0 ? -1 : 1);
        double proximity = rand.nextDouble();

        double actual = sut.findFunctionAtPointApprox(value, proximity) - sut.findFunctionAtPointExact(value);

        Assertions.assertThat(actual).isLessThan(proximity);
    }
}
