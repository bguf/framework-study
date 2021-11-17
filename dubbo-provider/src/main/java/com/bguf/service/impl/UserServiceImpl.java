package com.bguf.service.impl;

import com.bguf.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author gufb
 * @date 2021-10-13 16:41
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public String sayHello() {
        return "hello dubbo";
    }
}
