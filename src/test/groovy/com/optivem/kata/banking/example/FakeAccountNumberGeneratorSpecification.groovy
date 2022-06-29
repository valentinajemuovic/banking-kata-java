package com.optivem.kata.banking.example

import com.optivem.kata.banking.core.common.givens.FakeAccountNumberGeneratorGiven
import com.optivem.kata.banking.adapters.driven.fake.FakeAccountNumberGenerator
import com.optivem.kata.banking.adapters.driven.fake.exceptions.FakeException
import com.optivem.kata.banking.adapters.driven.fake.exceptions.FakeMessages
import spock.lang.Specification
import spock.lang.Unroll

class FakeAccountNumberGeneratorSpecification extends Specification {
    FakeAccountNumberGenerator generator

    def setup() {
        generator = new FakeAccountNumberGenerator()
    }

    def "throw exception when no elements"() {
        when: "no elements are specified"
            generator.next()

        then: "an exception should be thrown"
            def exception = thrown(FakeException)

        and: "the exception message should be"
            exception.message == FakeMessages.GENERATOR_DOES_NOT_HAVE_NEXT
    }

    def "return next element when there is one element"() {
        given: "the following expected value"
            def expectedValue = "GB54BARC20032611545669"

        when: "the next account number is generated"
            new FakeAccountNumberGeneratorGiven(generator).willGenerate(expectedValue)

        then: "the generated account number should be equal to the expected value"
            generator.next() == expectedValue
    }

    @Unroll
    def "generate account number for the following value #expectedValue"() {
        given: "the following value #expectedValue"

        when: "the account number is generated"
            new FakeAccountNumberGeneratorGiven(generator).willGenerate(expectedValue)

        then: "the generated account number should be equal to #expectedValue"
            generator.next() == expectedValue

        where: "these are the expected values"
            _ | expectedValue
            _ | "GB54BARC20032611545669"
            _ | "GB36BARC20038032622823"
            _ | "GB10BARC20040184197751"
    }
}
