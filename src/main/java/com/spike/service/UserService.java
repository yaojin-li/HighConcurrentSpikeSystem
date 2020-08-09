package com.spike.service;

import com.alibaba.fastjson.JSONObject;
import com.spike.base.dao.UserDao;
import com.spike.base.vo.User;
import com.spike.common.CodeMsg;
import com.spike.common.Result;
import com.spike.common.UserTokenPrefix;
import com.spike.controller.LoginController;
import com.spike.utils.MD5Util;
import com.spike.utils.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Description: --------------------------------------
 * @ClassName: UserService.java
 * @Date: 2020/6/7 12:19
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Service
public class UserService {

    public static final Logger log = LoggerFactory.getLogger(UserService.class);

    public static final String TOKEN_NAME_IN_COOKIE = "token";

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisService redisService;

    public List<User> selectAll() {
        return userDao.selectAll();
    }

    //
    public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> result = new HashMap<>();

        String mobile = request.getParameter("mobile");
        String formPass = request.getParameter("password");

        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(formPass)) {
            log.error("手机号或密码为空...");
            result.put("result", Result.error(CodeMsg.MOBILE_EMPTY));
            return result;
        }

        // 校验手机号
        User user = userDao.selectByMobile(mobile);
        if (null == user) {
            log.error(String.format("该手机用户[%s]不存在...", mobile));
            result.put("result", Result.error(CodeMsg.MOBILE_NOT_EXIST));
            return result;
        }

        // 校验密码
        String salt = user.getSalt();
        String calcPass = MD5Util.formPassToDBPass(formPass, salt);
        // 数据库存储的密码
        String dbPass = user.getPassword();
        if (!dbPass.equals(calcPass)) {
            log.error(String.format("该手机用户[%s]密码错误...数据库密码：%s，用户输入密码：%s", mobile, dbPass, calcPass));
            result.put("result", Result.error(CodeMsg.PASSWORD_ERROR));
            return result;
        }

        // 生成token
        String token = UUIDUtil.UUID();
        // 将token与user存储至redis
        setTokenAndUserToRedis(token, user);
        // 将token添加至cookie中
        addCookie(response, token);

        result.put("result", Result.sucess(CodeMsg.SUCCESS));
        return result;
    }

    /**
     * 将当前token与user的映射存储至redis
     */
    public void setTokenAndUserToRedis(String token, User user) {
        redisService.set(UserTokenPrefix.token, token, user);
    }

    /**
     * 将token写入至cookie中
     * 设置token过期时间
     */
    public void addCookie(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie(TOKEN_NAME_IN_COOKIE, token);
        cookie.setMaxAge(UserTokenPrefix.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }


    /**
     * 通过token从缓存中获取user
     */
    public User getUserByTokenFromRedis(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        User user = redisService.get(UserTokenPrefix.token, token, User.class);
        // 延长有效期（加入redis并写入cookie）
        setTokenAndUserToRedis(token, user);
        return user;
    }


}
