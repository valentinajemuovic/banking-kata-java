package com.optivem.kata.banking.core.util;

import com.optivem.kata.banking.core.internal.cleanarch.domain.common.Guard;
import com.optivem.kata.banking.core.internal.cleanarch.domain.common.exceptions.RepositoryMessages;
import com.optivem.kata.banking.core.ports.driver.exceptions.ValidationMessages;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UtilityClassTest {
    @Test
    void testConstructor() throws Exception {
        assertPrivateConstructor(Guard.class);
        assertPrivateConstructor(RepositoryMessages.class);
        assertPrivateConstructor(ValidationMessages.class);
    }

    private <T> void assertPrivateConstructor(Class<T> clazz) throws NoSuchMethodException {
        final Constructor constructor = clazz.getDeclaredConstructor();
        assertThat(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);

        assertThrows(InvocationTargetException.class, () -> constructor.newInstance());
    }
}
