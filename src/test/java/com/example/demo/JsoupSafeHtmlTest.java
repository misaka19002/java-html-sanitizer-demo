package com.example.demo;

import com.google.common.base.Joiner;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.junit.Test;

@SuppressWarnings("javadoc")
public class JsoupSafeHtmlTest {
    
    @Test
    public void testCannedFormattingTagFilter() {
        Whitelist whitelist = Whitelist.relaxed();
        String src = Joiner.on('\n').join("<b>Fancy</b> with <i><b>soupy</b></i><b> tags</b>.", "<b>Fancy</b> with <i><b>soupy</b></i><b> tags</b>.");
        String result = Jsoup.clean(src, whitelist);
        System.out.println(src);
        System.out.println(result);
        
    }
    
    
    @Test
    public void testImg() {
        Whitelist whitelist = Whitelist.relaxed();
        String src = Joiner.on("\n").join("sdf", "<b>aaaaa</b><a href=\"http:/sdf.jpg\">dd</a><a herf=\"\" ff=\"\"></a><img src=javascript:alert(1337)/>");
        String result = Jsoup.clean(src, whitelist);
        System.out.println(src);
        System.out.println(result);
    }
    
    @Test
    public void testLocation() {
        Whitelist whitelist = Whitelist.relaxed();
        String src = Joiner.on("\n").join("window.location=http://baidu.com", "<b>aaaaa</b><a></a><img/>","=");
        String result = Jsoup.clean(src, whitelist);
        System.out.println(src);
        System.out.println(result);
    }
    
    @Test
    public void basicClean() {
        Whitelist whitelist = Whitelist.basic();
        String src = Joiner.on("\n").join("window.location=http://baidu.com", "<b>aaaaa</b><a></a><img/>","=");
        String result = Jsoup.clean(src, whitelist);
        System.out.println(src);
        System.out.println(result);
    }
}
