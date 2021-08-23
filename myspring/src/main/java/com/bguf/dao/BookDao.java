package com.bguf.dao;

import lombok.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author gufb
 * @date 2021/8/17 5:51 PM
 */
@Repository
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookDao {
    private String label = "1";
}
