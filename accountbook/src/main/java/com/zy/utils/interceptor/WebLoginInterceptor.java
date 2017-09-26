package com.zy.utils.interceptor;

import com.zy.projectUtils.ProjectConstant;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * Web端登录拦截器
 * 处理请求时Session失效的问题，包含Ajax请求和普通请求
 * Created by ZJZL_HP on 2017/9/11.
 */

public class WebLoginInterceptor extends HandlerInterceptorAdapter {

    /**
     * 默认注销URL
     * 即Session超时后，发起请求到此地址，只对普通请求有效
     */
    private static final String DEFAULT_LOGOUT_URL = "/login";
    /**
     * 注销URL
     */
    private String logoutUrl;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (null != session.getAttribute(ProjectConstant.Session_User_Key)) {
            return true;
        }

        response.setContentType("text/html;charset=UTF-8");
        // Ajax请求, 前端根据此header进行处理
        if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
            response.setHeader("sessionTimeout", "Session time out, you need relogin !");
            // 返回未认证的状态码(401)
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        } else {
            // 普通请求（非Ajax请求）
            // 因为Ajax请求是XMLHTTPRequest对象发起的而不是浏览器，在验证失败后的页面跳转无法反应到浏览器中，因为服务器返回（或输出）的信息被JavaScript（XMLHTTPRequest对象）接到了。
            // 既然服务器返回的消息被XMLHTTPRequest对象接收，而XMLHTTPRequest对象又是在JavaScript的掌控之中，我们是可以利用JavaScript来完成页面跳转

            // httpResponse.sendRedirect(httpRequest.getContextPath() + "/index.html");
            // 由于Web端使用iframe嵌套, 因此直接重定向到登录页面并不能总是完成地很完美, 比如HTTP请求来自
            // iframe对象的时候, 只能让iframe加载到index.html, 体验不够好; 所以在这里将直接重定向改为向
            // 页面输出一段JS代码来实现使顶部window跳转到默认的登录页面.

            String jsCode = "<script type='text/javascript'>" +
                    " var yes = confirm('session已过期, 请重新登录.');\n" +
                    " if (yes) { var p=window;while(p!=p.parent){p=p.parent; } p.location.href='" +
                    request.getContextPath() + getLogoutUrl() +
                    "';}</script>";
            PrintWriter writer = response.getWriter();
            writer.print(jsCode);
            writer.flush();
            writer.close();
        }
        return false;
    }

    public String getLogoutUrl() {
        // 使用默认值
        if (StringUtils.isEmpty(logoutUrl)) {
            return DEFAULT_LOGOUT_URL;
        }
        return logoutUrl;
    }

    public void setLogoutUrl(String logoutUrl) {
        this.logoutUrl = logoutUrl;
    }
}