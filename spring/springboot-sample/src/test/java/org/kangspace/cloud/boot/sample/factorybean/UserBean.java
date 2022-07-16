package org.kangspace.cloud.boot.sample.factorybean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kango2gler@gmail.com
 * @since 2022/6/18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBean {
    private String username;
    private Integer sex;
}
