package builder;

import exception.InvalidArgumentException;
import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.List;
import java.util.stream.Stream;

public class TableBuilderTest {

    @Test
    public void happyCaseTest() {
        TableBuilder builder = new TableBuilder();
        String name = "one";

        String table = builder
                .addColumn(name, 5)
                .addColumn(name, 5)
                .data()
                .addRow(List.of("a", "100"))
                .addRow(List.of("b", "200"))
                .addRow(List.of("c", "30"))
                .getTable();

        Assertions.assertThat(table)
                .isEqualTo(
                        "| "+name+" | "+name+" | \n" +
                        "-------------\n" +
                        "|   a | 100 |\n" +
                        "-------------\n" +
                        "|   b | 200 |\n" +
                        "-------------\n" +
                        "|   c |  30 |\n" +
                        "-------------\n"
                );
    }

    @ParameterizedTest
    @ArgumentsSource(IllegalValuesArgumentProvider.class)
    public void shouldThrowInvalidArgumentExceptionWhenValuesAreNotEqualToColumnsTest(List<String> input) {
        TableBuilder builder = new TableBuilder();

        Assertions.assertThatCode(
                () -> builder.addColumn(RandomStringUtils.randomAlphabetic(5), 3)
                        .addColumn(RandomStringUtils.randomAlphabetic(5), 3)
                        .addColumn(RandomStringUtils.randomAlphabetic(5), 3)
                        .addColumn(RandomStringUtils.randomAlphabetic(5), 3)
                        .data()
                        .addRow(input)
        ).isExactlyInstanceOf(InvalidArgumentException.class);
    }

    static class IllegalValuesArgumentProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(List.of("a", "b", "c")),
                    Arguments.of(List.of("a", "b", "c", "d", "e"))
            );
        }
    }
}
