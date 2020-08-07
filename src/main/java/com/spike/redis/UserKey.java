package com.spike.redis;

import com.spike.common.BasePrefix;

/**
 * @Description: --------------------------------------
 * @ClassName: UserKey.java
 * @Date: 2020/8/7 21:45
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public class UserKey extends BasePrefix {

    public UserKey(String prefix) {
        super(prefix);
    }

    public static UserKey getById = new UserKey("id");

    public static UserKey getByName = new UserKey("name");
}
