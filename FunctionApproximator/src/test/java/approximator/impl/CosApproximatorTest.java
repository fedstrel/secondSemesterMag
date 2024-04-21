package approximator.impl;

import exception.InvalidArgumentException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class CosApproximatorTest {
    @Test
    public void shouldBeDefinedOnRangeFromMinusOneToOneTest() {
        CosApproximator sut = new CosApproximator();

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
        CosApproximator sut = new CosApproximator();
        Random rand = new Random();

        double value = rand.nextDouble() * (rand.nextInt() % 2 == 0 ? -1 : 1);
        double proximity = rand.nextDouble();

        double actual = sut.findFunctionAtPointApprox(value, proximity) - sut.findFunctionAtPointExact(value);

        Assertions.assertThat(actual).isLessThan(proximity);
    }
}
