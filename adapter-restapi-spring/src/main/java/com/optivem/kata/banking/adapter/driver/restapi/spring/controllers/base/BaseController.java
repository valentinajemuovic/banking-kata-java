package com.optivem.kata.banking.adapter.driver.restapi.spring.controllers.base;

import an.awesome.pipelinr.Pipeline;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {
    @Autowired
    protected Pipeline pipeline;
}
