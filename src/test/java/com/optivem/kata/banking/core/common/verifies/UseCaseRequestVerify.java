package com.optivem.kata.banking.core.common.verifies;

import an.awesome.pipelinr.Command;
import com.optivem.kata.banking.core.usecases.VoidResponse;

import static com.optivem.kata.banking.core.common.Verifications.verifyThat;
import static org.assertj.core.api.Assertions.assertThat;

public class UseCaseRequestVerify<R extends Command<P>, P> {

    private final Command.Handler<R, P> useCase;
    private final R request;

    public UseCaseRequestVerify(Command.Handler<R, P> useCase, R request) {
        this.useCase = useCase;
        this.request = request;
    }

    public void shouldReturnResponse(P expectedResponse) {
        var response = useCase.handle(request);
        assertThat(response).isEqualTo(expectedResponse);
    }

    public void shouldThrowValidationException(String message) {
        verifyThat(() -> useCase.handle(request)).shouldThrowValidationException(message);
    }

    public void shouldExecuteSuccessfully() {
        var response = useCase.handle(request);
        assertThat(response).isInstanceOf(VoidResponse.class);
        assertThat(response).isEqualTo(VoidResponse.EMPTY);
    }
}
