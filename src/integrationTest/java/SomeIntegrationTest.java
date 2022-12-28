import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SomeIntegrationTest {
    @Test
    void run() {
        assertThat(3 == 3);
        throw new RuntimeException();
    }
}
