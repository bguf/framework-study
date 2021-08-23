package com.bguf.bean;

import lombok.*;
import org.springframework.stereotype.Component;

/**
 * @author gufb
 * @date 2021/8/23 11:59 AM
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Component
public class Boss {
    private Car car;
}
