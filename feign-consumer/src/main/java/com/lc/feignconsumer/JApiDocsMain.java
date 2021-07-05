package com.lc.feignconsumer;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2021/5/6 0006
 * @since 1.0.0
 */
public class JApiDocsMain {
    public static void main(String[] args) {
        DocsConfig config = new DocsConfig();
        config.setProjectPath("E:\\IdeaProjects\\Myprojects\\LcSpringCloud\\feign-consumer"); // 项目根目录
        config.setProjectName("feign-consumer"); // 项目名称
        config.setApiVersion("V2.0");       // 声明该API的版本
        config.setDocsPath("F:\\feign-consumer"); // 生成API 文档所在目录
        config.setAutoGenerate(Boolean.FALSE);  // 配置自动生成
        Docs.buildHtmlDocs(config); // 执行生成文档
    }
}
