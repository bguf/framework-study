package com.bguf.service.impl;

import com.bguf.dao.BookDao;
import com.bguf.service.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.inject.Inject;

/**
 * @author gufb
 * @date 2021/8/17 5:52 PM
 */
@Service
@Slf4j
public class BookServiceImpl implements BookService {
//    @Qualifier("bookDao")
//    @Autowired(required = false)
//    @Resource
    @Inject
    private BookDao bookDao;

    public void print() {
        log.info("{}", bookDao);
    }
}
