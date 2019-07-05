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
        PolicyFactory pf = new HtmlPolicyBuilder()
                .allowElements("b", "i", "a", "img")
                .allowAttributes("src").onElements("img")
                .toFactory();
        String src = Joiner.on("\n").join("sdf", "<b>aaaaa</b><a></a><img/>");
        String result = pf.sanitize(src);
        System.out.println(src);
        System.out.println(result);
    }
    
}
