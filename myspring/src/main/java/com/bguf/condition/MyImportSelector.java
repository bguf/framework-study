package com.bguf.condition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Set;

/**
 * 自定义逻辑返回需要导入的组件
 * @author gufb
 * @date 2021/8/18 4:13 PM
 */
public class MyImportSelector implements ImportSelector{
    /**
     * 返回值：导入到容器中的组件全类名
     * @param annotationMetadata：当前标注@Import注解的类的所有注解信息
     * @return
     */
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{"com.bguf.bean.Blue", "com.bguf.bean.Yellow"};
    }
}
