package com.bguf.session;

import java.util.List;

/**
 * @author gufb
 * @date 2021/9/1 10:59 AM
 */
public interface Executor {
    <T> T query();
}
