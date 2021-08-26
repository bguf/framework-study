package com.bguf.tx;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * 声明式事务：
 *  环境搭建：
 *      1、导入相关依赖：数据源、数据库驱动、spring-jdbc模块
 *      2、配置数据源、JdbcTemplate操作数据库
 *      3、给方法上标注@Transactional，标志当前方法是一个事务方法
 *      4. 给配置类添加@EnableTransactionManagement，开启基于注解的事务管理功能
 *      5、配置事务管理器来控制事务
 * @author gufb
 * @date 2021/8/25 9:52 AM
 */
@Configuration
@ComponentScan(value = {"com.bguf.tx"})
@EnableTransactionManagement
public class TxConfig {
    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("qw12");
        dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://172.16.60.100:3307/test");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() throws PropertyVetoException {
        // spring对@Configuration类会特殊处理：给容器中加组件的方法，多次调用都只是从容器中找组件
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
        return jdbcTemplate;
    }

    // 注册事务管理器在容器中
    @Bean
    public PlatformTransactionManager transactionManager() throws PropertyVetoException {
        return new DataSourceTransactionManager(dataSource());
    }
}
