package com.jani.taskmanager.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author Jani
 * @data 2026/11/23 21:14
 **/
public class LoginInterceptor implements HandlerInterceptor {

    // preHandle：在请求到达 Controller 之前执行。返回 true 代表放行，返回 false 代表拦截。
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 1. 如果是预检请求（OPTIONS），直接放行（这是跨域的小知识，暂不深究）
        if ("OPTIONS".equals(request.getMethod())) {
            return true;
        }

        // 2. 从请求头里拿 token
        String token = request.getHeader("token");

        // 3. 校验逻辑（这里我们写死一个简单的模拟校验）
        if (token == null || !"my-secret-token".equals(token)) {
            // 校验失败，设置响应状态码为 401 (未授权)，并返回一段 JSON 给前端
            response.setStatus(401);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"code\": 401, \"message\": \"请先登录！拦截器已拦截\", \"data\": null}");

            // 返回 false，直接拦死，不让进入 Controller
            return false;
        }

        // 4. 校验通过，放行
        return true;
    }
}