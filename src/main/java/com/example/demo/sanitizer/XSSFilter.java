package com.example.demo.sanitizer;

import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by admin on 2019/7/5 17:19:28.
 */
@WebFilter(displayName = "XssFilter", filterName = "xssFilter", urlPatterns = "/*")
public class XSSFilter implements Filter {
    protected PolicyFactory DEFAULT_POLICY = new HtmlPolicyBuilder()
            .allowElements("a","b")
            .toFactory();
    /**
     * 参考，自定策略
     * https://github.com/OWASP/java-html-sanitizer/blob/master/src/test/java/org/owasp/html/HtmlPolicyBuilderTest.java
     */
    private PolicyFactory policyFactory = DEFAULT_POLICY;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        chain.doFilter(new HttpServletXssPolicyRequestWrapper(DEFAULT_POLICY, httpServletRequest), response);
    }
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("sdf");
    }
    
    @Override
    public void destroy() {
    
    }
}
