package com.ssm.manager.enums;

/**
 * @Author: chen
 * @Date: Created in 21:18 2018/4/22
 */
public enum RoleEnum {

    USER(1,"普通用户"),

    ADMIN(2,"管理员");

    private int value;

    private String desc;

    private RoleEnum(int value, String desc){
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public RoleEnum getRoleEnum(int value){
        for (RoleEnum role : RoleEnum.values()){
            if (value == role.getValue()){
                return role;
            }
        }
        return null;
    }
}
