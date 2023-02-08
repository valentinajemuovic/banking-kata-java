import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import com.optivem.kata.banking.CustomerApplication;
import com.optivem.kata.banking.CustomerDto;
import com.optivem.kata.banking.CustomerService;
import org.apache.http.HttpHost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.Optional;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = CustomerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Provider("customer-provider")
@PactFolder("../adapter-microservice-real/build/pacts")
public class RealCustomerGatewayProviderTest {
    @LocalServerPort
    private int port;

    @MockBean
    private CustomerService customerService;

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @BeforeEach
    void before(PactVerificationContext context) {
        var testTarget = new HttpTestTarget("localhost", port);
        context.setTarget(testTarget);
    }

    @State("GET User: customer ABC_1001 is blacklisted")
    void toCustomerBlacklistedState() {
        reset(customerService);

        var id = "ABC_1001";
        var response = CustomerDto.builder()
                .id(id)
                .blacklisted(true)
                .build();

        when(customerService.getCustomer(id)).thenReturn(Optional.of(response));
    }

    @State("GET User: customer ABC_1002 is whitelisted")
    void toCustomerWhitelistedState() {
        reset(customerService);

        var id = "ABC_1002";
        var response = CustomerDto.builder()
                .id(id)
                .blacklisted(false)
                .build();

        when(customerService.getCustomer(id)).thenReturn(Optional.of(response));
    }

    @State("GET User: customer DEF_1002 does not exist")
    void toCustomerNotExistState() {
        reset(customerService);
    }
}