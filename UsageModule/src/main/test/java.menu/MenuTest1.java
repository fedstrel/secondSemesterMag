import exception.HardShutDownException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MenuTest1 {

    @Test
    public void shouldThrowHarShutDownExceptionTest() {
        Assertions.assertThatCode(
                () -> Main.getApproxByInput(3)
        ).isExactlyInstanceOf(HardShutDownException.class);
    }
}
