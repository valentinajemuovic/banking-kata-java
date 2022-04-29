package com.optivem.kata.banking.util;

import com.optivem.kata.banking.core.domain.common.Guard;
import com.optivem.kata.banking.core.domain.exceptions.RepositoryMessages;
import com.optivem.kata.banking.core.domain.exceptions.ValidationMessages;
import com.optivem.kata.banking.core.domain.extensions.Extension;
import com.optivem.kata.banking.infra.fake.exceptions.FakeMessages;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UtilityClassTest {
    @Test
    void testConstructor() throws Exception {
        assertPrivateConstructor(FakeMessages.class);
        assertPrivateConstructor(Guard.class);
        assertPrivateConstructor(RepositoryMessages.class);
        assertPrivateConstructor(ValidationMessages.class);
        assertPrivateConstructor(Extension.class);
    }

    private <T> void assertPrivateConstructor(Class<T> clazz) throws NoSuchMethodException {
        final Constructor constructor = clazz.getDeclaredConstructor();
        assertThat(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);

        assertThrows(InvocationTargetException.class, () -> constructor.newInstance());
    }
}
