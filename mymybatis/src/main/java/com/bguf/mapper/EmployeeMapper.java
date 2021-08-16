package com.bguf.mapper;

import com.bguf.po.Employee;
import org.apache.ibatis.annotations.Select;

/**
 * @author gufb
 * @date 2021/8/16 4:42 PM
 */
public interface EmployeeMapper {
    @Select("select * from employee where id = #{id}")
    Employee selectEmployee(int id);
}
