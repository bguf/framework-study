package com.bguf.controller;

import com.bguf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gufb
 * @date 2021-10-13 17:07
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/sayHello", method = RequestMethod.GET)
    public String sayHello() {
        return userService.sayHello();
    }
}
