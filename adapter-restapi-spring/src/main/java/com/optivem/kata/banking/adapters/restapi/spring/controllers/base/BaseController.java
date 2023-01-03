package com.optivem.kata.banking.adapters.restapi.spring.controllers.base;

import an.awesome.pipelinr.Pipeline;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {
    @Autowired
    protected Pipeline pipeline;
}
