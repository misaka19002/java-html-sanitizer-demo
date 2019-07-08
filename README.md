# java-html-sanitizer-demo
使用java-html-sanitizer防止xss攻击。过滤富文本。 过滤html标签demo
示例请查看：https://github.com/lazyii/java-html-sanitizer-demo/blob/master/src/test/java/com/example/demo/MyHtmlPolicyBuilderTest.java

# Joup 防御xss
使用示例: https://github.com/lazyii/java-html-sanitizer-demo/blob/master/src/test/java/com/example/demo/JsoupSafeHtmlTest.java


对比：

* 基本的html标签过滤两个jar基本上都可以实现。
* 使用起来两者上手都很简单，但是规则设置方面html-sanitizer相对复杂一点，但是也意味着能更灵活的控制标签。
* jar包依赖，jsoup仅依赖自己。而html-sanitizer则依赖了几个其他的jar包，对于有强迫症的小伙伴还是选择jsoup吧。
