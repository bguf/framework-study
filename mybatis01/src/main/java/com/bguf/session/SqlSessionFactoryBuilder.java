package com.bguf.session;

import com.bguf.config.Configuration;

/**
 * @author gufb
 * @date 2021/9/1 2:37 PM
 */
public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(Configuration configuration) {
        configuration.loadConfigurations();
        return new SqlSessionFactory();
    }
}
