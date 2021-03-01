package com.miaosha.config;

import com.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: --------------------------------------
 * @ClassName: UserArgumentResolver.java
 * @Date: 2020/8/13 9:36
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Service
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    UserService userService;

    /**
     * 判断参数类型是否是指定的user类型
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        // 获取参数类型
        Class<?> clazz = methodParameter.getParameterType();
        return clazz == User.class;
    }


    /**
     * 参数处理
     * supportsParameter() 方法返回 true 时调用。
     */
    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);

        if (null == request){
            return null;
        }

        // 从请求中获取token
        String paramToken = request.getParameter(UserService.TOKEN_NAME_IN_COOKIE);
        // 从cookie中获取token
        String cookieToken = getCookieValue(request, UserService.TOKEN_NAME_IN_COOKIE);
        if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
            return null;
        }

        //
        String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
        if (null != response) {
            userService.addCookie(response, token);
        }
        return userService.getUserByTokenFromRedis(token);
    }


    /**
     * 遍历 request 中的 cookie，获取指定 cookie 的值
     */
    private String getCookieValue(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (null == cookies || cookies.length == 0) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookieName.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }

}
