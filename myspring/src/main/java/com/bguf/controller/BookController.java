package com.bguf.controller;

import com.bguf.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author gufb
 * @date 2021/8/17 5:50 PM
 */
@Controller
public class BookController {
    @Autowired
    private BookService bookService;

}
