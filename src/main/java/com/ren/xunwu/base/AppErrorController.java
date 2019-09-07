package com.ren.xunwu.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 全局异常处理
 * Spring Boot 会将所有的错误默认映射到/error，可以自定义Controller实现ErrorController接口来实现全局异常处理。
 */
@Controller
public class AppErrorController implements ErrorController {
    private static final String ERROR_PATH = "/error";

    @Autowired
    private ErrorAttributes errorAttributes;

    //服务器会将getErrorPath()返回的路径重定向到该路径对应的处理类
    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
    //RequestMapping中produces属性可以设置返回数据的类型以及编码。eg：application/json;charset=UTF-8"
    //与之对应的是consumes属性，可以指定处理请求的提交内容类型

    @GetMapping("/404")
    public String notFoundPage() {
        return "404";
    }

    @GetMapping("/403")
    public String accessError() {
        return "403";
    }

    @GetMapping("/500")
    public String internalError() {
        return "500";
    }

    /**
     * web页面的错误处理
     * @param response
     * @return
     */
    @RequestMapping(value = ERROR_PATH,produces = "text/html")
    public String errPageHandler(HttpServletResponse response){
        int status = response.getStatus();
        switch (status){
            case 403:return "403";
            case 404:return "404";
            case 500:return "500";
        }
        return "index";
    }

    /**
     * 除web页面外的异常处理，如json/xml等
     * @param request
     * @return
     */
    @RequestMapping(ERROR_PATH)
    public ApiResponse errApiHandler(HttpServletRequest request){
        ServletWebRequest webRequest = new ServletWebRequest(request);
        Map<String, Object> errorMap = this.errorAttributes.getErrorAttributes(webRequest, false);

        int status = getStatus(request);
        return ApiResponse.ofMessage(status, String.valueOf(errorMap.getOrDefault("message","error")));

        /*
        springboot1
        Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace);

        springboot2
        Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace);

        返回一个错误属性的map集合，includeStackTrace：是否包含堆栈信息
        springboot1与2该方法参数不同，所以代码写法不同，参考org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController类

        1的写法
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        Map<String, Object> attr = this.errorAttributes.getErrorAttributes(requestAttributes, false);
        */
    }

    private int getStatus(HttpServletRequest request){
        Integer status = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (status != null){
            return status;
        }
        return 500;
    }



}
