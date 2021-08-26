package com.bguf.tx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author gufb
 * @date 2021/8/25 10:04 AM
 */
@Service
@Slf4j
public class CoursesService {
    @Autowired
    private CoursesDao coursesDao;

    @Transactional
    public void insertCourse() {
        coursesDao.insert();
        log.info("{}", "成功插入数据");
        // 模拟插入事务进行时异常
        int i = 10 / 0;

    }
}
