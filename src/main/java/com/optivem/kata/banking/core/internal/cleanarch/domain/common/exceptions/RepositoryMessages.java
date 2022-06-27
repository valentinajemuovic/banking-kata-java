package com.optivem.kata.banking.core.internal.cleanarch.domain.common.exceptions;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RepositoryMessages {

    public static final String REPOSITORY_CONSTRAINT_VIOLATION = "Repository constraint violation";
    public static final String REPOSITORY_CANNOT_UPDATE_NON_EXISTENT = "Repository cannot update non existent";
}
