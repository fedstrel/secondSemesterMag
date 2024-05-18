import exception.HardShutDownException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MenuTest {
    @Test
    public void shouldThrowHarShutDownExceptionTest() {
        Assertions.assertThatCode(
                () -> Main.getApproxByInput(3)
        ).isExactlyInstanceOf(HardShutDownException.class).hasMessage("Эктренное завершение работы");
    }
}
