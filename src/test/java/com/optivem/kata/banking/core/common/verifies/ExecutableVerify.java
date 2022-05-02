package com.optivem.kata.banking.core.common.verifies;

import com.optivem.kata.banking.core.domain.exceptions.RepositoryException;
import com.optivem.kata.banking.core.domain.exceptions.ValidationException;
import org.junit.jupiter.api.function.Executable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExecutableVerify {

    private final Executable executable;

    public ExecutableVerify(Executable executable) {
        this.executable = executable;
    }

    public void throwsRepositoryException(String message) {
        var exception = assertThrows(RepositoryException.class, executable);
        assertThat(exception.getMessage()).isEqualTo(message);
    }

    public void throwsValidationException(String message) {
        var exception = assertThrows(ValidationException.class, executable);
        assertThat(exception.getMessage()).isEqualTo(message);
    }
}
