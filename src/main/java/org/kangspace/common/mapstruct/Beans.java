package org.kangspace.common.mapstruct;

import lombok.*;

public class Beans {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class UserBean{
        private Long userId;
        private String userName;
        private Integer userSex;
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class UserBeanDto{
        private Long userId;
        private String userName;
        private String sex;
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class UserBeanDto2{
        private Long userId;
    }
}
