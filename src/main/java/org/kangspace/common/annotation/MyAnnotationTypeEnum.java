package org.kangspace.common.annotation;

/**
 * @author kango2gler@gmail.com
 * @desc 注解相关枚举
 * @date 2017/6/23 16:55
 */
public enum MyAnnotationTypeEnum {
    CLASS(0, "class"), FIELD(1, "field"), METHOD(2, "method"),PARAM(3,"param");

    MyAnnotationTypeEnum(int type, String typeDesc) {
        this.type = type;
        this.typeDesc = typeDesc;
    }
    private int type;
    private String typeDesc;

    @Override
    public String toString() {
        return "MyAnnotationTypeEnum{" +
                "type=" + type +
                ", typeDesc='" + typeDesc + '\'' +
                "} " + super.toString();
    }
}
