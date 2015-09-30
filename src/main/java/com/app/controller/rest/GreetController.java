package com.app.controller.rest;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 *
 */
@RestController
public class GreetController {
    private static final String template = "Hello, %s! The user count is %s";
    private final AtomicLong counter = new AtomicLong();
    
    @RequestMapping(value = "/greeter", method = RequestMethod.GET)
    public String meetAndGreet(@RequestParam(value="name", defaultValue="World") String name) {
        return String.format(template, name, counter.getAndIncrement());
    }
}
