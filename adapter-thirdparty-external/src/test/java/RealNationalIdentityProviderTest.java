import com.optivem.kata.banking.adapters.driven.NationalIdentityProviderTest;
import com.optivem.kata.banking.core.ports.driven.NationalIdentityProvider;
import org.junit.jupiter.api.Disabled;

@Disabled
public class RealNationalIdentityProviderTest extends NationalIdentityProviderTest {
    @Override
    protected NationalIdentityProvider create() {
        return null;
    }
}
