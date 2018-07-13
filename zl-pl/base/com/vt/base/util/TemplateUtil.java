package com.vt.base.util;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Created by zhangtao on 15/5/12.
 */
public class TemplateUtil {
    /**
     * 配置路径
     */
    public static String CONFIG_FOLDER = "templates";
    /**
     * 模板缓存
     */
    private static final Map<String, Template> TEMPLATE_CACHE = new HashMap<String, Template>();

    /**
     * 合并模板, 生成数据
     *
     * @param templateName
     * @param data
     * @return
     */
    public static String merge(String templateName, Object data) {
        try {
            Template template;

            if (TEMPLATE_CACHE.containsKey(templateName)) {
                template = TEMPLATE_CACHE.get(templateName);
            } else {
                File templateFolder = new ClassPathResource(CONFIG_FOLDER).getFile();
                Configuration configuration = new Configuration();
                configuration.setDirectoryForTemplateLoading(templateFolder);
                configuration.setObjectWrapper(new DefaultObjectWrapper());

                template = configuration.getTemplate(templateName + ".ftl", "UTF-8");

                TEMPLATE_CACHE.put(templateName, template);
            }

            StringWriter out = new StringWriter();

            template.process(data, out);

            return out.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (TemplateException e) {
            e.printStackTrace();
            return null;
        }
    }
}
