package com.example.demo;

import com.google.common.base.Joiner;
import org.junit.Test;
import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;

@SuppressWarnings("javadoc")
public class MyHtmlPolicyBuilderTest {
    
    @Test
    public void testCannedFormattingTagFilter() {
        PolicyFactory pf = new HtmlPolicyBuilder().allowElements("b", "i","img").toFactory();
        String src = Joiner.on('\n').join("<b>Fancy</b> with <i><b>soupy</b></i><b> tags</b>.", "<b>Fancy</b> with <i><b>soupy</b></i><b> tags</b>.");
        String result = pf.sanitize(src);
        System.out.println(src);
        System.out.println(result);
        
    }
    
    
    @Test
    public void testImg() {
        /**
         * <a></a>标签
         * <img></img>标签
         * 这些有属性的标签都需要特殊配置。不能仅配置allowElements
         * 例如a标签，写了标签对儿，但是没有配置herf，也是没有办法展示的。
         * 特殊的标签必须得配上属性拦截。
         */
        PolicyFactory pf = new HtmlPolicyBuilder()
                .allowElements("b", "i", "a", "img")
                .allowAttributes("src").onElements("img")
                .allowAttributes("herf","ff").onElements("a")
                .toFactory();
        String src = Joiner.on("\n").join("sdf", "<b>aaaaa</b><a herf=\"\">ss</a><img/><a/><a herf=\"\" ff=\"\"></a>");
        String result = pf.sanitize(src);
        System.out.println(src);
        System.out.println(result);
    }
    
}
