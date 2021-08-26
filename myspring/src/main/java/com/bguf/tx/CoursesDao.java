package com.bguf.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author gufb
 * @date 2021/8/25 10:05 AM
 */
@Repository
public class CoursesDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert() {
        String sql = "insert into courses(student, class) values(?, ?)";
        String student = "lisi";
        String class1 = "lishi";
        jdbcTemplate.update(sql, student, class1);
    }
}
