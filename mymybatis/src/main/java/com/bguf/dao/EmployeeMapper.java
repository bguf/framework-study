package com.bguf.dao;

import com.bguf.po.Employee;

/**
 * @author gufb
 * @date 2021/8/16 2:19 PM
 */
public interface EmployeeMapper {
    Employee selectEmployee(int id);
}
