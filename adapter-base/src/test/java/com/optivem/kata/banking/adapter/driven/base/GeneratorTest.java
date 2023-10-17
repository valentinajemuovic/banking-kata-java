package com.optivem.kata.banking.adapter.driven.base;

import com.optivem.kata.banking.core.ports.driven.common.Generator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class GeneratorTest<G extends Generator<?>> {
    protected G generator;

    @BeforeEach
    public void init() {
        this.generator = create();
    }

    protected abstract G create();

    @Test
    void should_generate_next() {
        var element = generator.next();
        assertThat(element).isNotNull();
    }

    @Test
    void should_generate_multiple_unique_next() {
        var element1 = generator.next();
        assertThat(element1).isNotNull();

        var element2 = generator.next();
        assertThat(element2)
                .isNotNull()
                .isNotEqualTo(element1);

        var element3 = generator.next();
        assertThat(element3)
                .isNotNull()
                .isNotEqualTo(element2);
    }
}
