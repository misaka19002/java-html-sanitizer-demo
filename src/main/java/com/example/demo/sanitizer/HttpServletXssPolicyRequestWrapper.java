package com.example.demo.sanitizer;

import org.owasp.html.PolicyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by admin on 2019/7/5 16:48:53.
 */
public class HttpServletXssPolicyRequestWrapper extends HttpServletRequestWrapper {
    
    
    protected Logger loggger = LoggerFactory.getLogger(getClass());
    
    /**Xss检查策略工厂*/
    protected PolicyFactory policyFactory = null;
    
    public HttpServletXssPolicyRequestWrapper(PolicyFactory policyFactory, HttpServletRequest request) {
        super(request);
        this.policyFactory = policyFactory;
    }
    
    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> request_map = super.getParameterMap();
        Iterator<Map.Entry<String, String[]>> iterator = request_map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String[]> me = iterator.next();
            String[] values = (String[]) me.getValue();
            for (int i = 0; i < values.length; i++) {
                // /System.out.println(values[i]);
                values[i] = xssClean(values[i]);
            }
        }
        return request_map;
    }
    
    @Override
    public String[] getParameterValues(String name) {
        String[] rawValues = super.getParameterValues(name);
        if (rawValues == null){
            return null;
        }
        String[] cleanedValues = new String[rawValues.length];
        for (int i = 0; i < rawValues.length; i++) {
            cleanedValues[i] = xssClean(rawValues[i]);
        }
        return cleanedValues;
    }
    
    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        if (value == null){
            return null;
        }
        return xssClean(value);
    }
    
    @Override
    public Cookie[] getCookies() {
        Cookie[] existingCookies = super.getCookies();
        if (existingCookies != null) {
            for (int i = 0; i < existingCookies.length; ++i) {
                Cookie cookie = existingCookies[i];
                cookie.setValue(xssClean(cookie.getValue()));
            }
        }
        return existingCookies;
    }
    
    @Override
    public String getQueryString() {
        return xssClean(super.getQueryString());
    }
    
    public String xssClean(String taintedHTML) {
        loggger.debug("Tainted :" + taintedHTML);
        System.out.println("Tainted :" + taintedHTML);
        String cleanHTML = policyFactory.sanitize(taintedHTML);
        System.out.println("XSS Clean :" + cleanHTML);
        loggger.debug("XSS Clean :" + cleanHTML);
        return cleanHTML;
    }
    
    protected HttpServletRequest _getHttpServletRequest() {
        return (HttpServletRequest) super.getRequest();
    }
}
