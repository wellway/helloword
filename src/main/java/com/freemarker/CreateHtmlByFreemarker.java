package com.freemarker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Locale;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public class CreateHtmlByFreemarker {
	public static void main(String[] args) {
		createHtmlFromModel();
	}
	
	/**
     * 使用模板生成HTML代码
     */
    public static void createHtmlFromModel() {
        FileWriter out = null;
        try {
            // 通过FreeMarker的Confuguration读取相应的模板文件
            Configuration configuration = new Configuration(Configuration.VERSION_2_3_22);
            // 设置模板路径
            configuration.setClassForTemplateLoading(CreateHtmlByFreemarker.class, "static");
            // 设置默认字体
            configuration.setDefaultEncoding("utf-8");
            
            // 获取模板
            Template template = configuration.getTemplate("user.ftl");
            //设置模型
            User user = new User("tom", "hahahah", 28, "上海市");
            
            //设置输出文件
            File file = new File("src/main/java/com/freemarker/static/result.html");
            if(!file.exists()) {
                file.createNewFile();
            }
            //设置输出流
            out = new FileWriter(file);
            //模板输出静态文件
            template.process(user, out);
            System.out.println("模板生成成功！");
        } catch (TemplateNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedTemplateNameException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } finally {
            if(null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static String gethtmlString(){
    	FileWriter out = null;
    	 String html = null ;
        try {
            // 通过FreeMarker的Confuguration读取相应的模板文件
            Configuration configuration = new Configuration(Configuration.VERSION_2_3_22);
            // 设置模板路径
            configuration.setClassForTemplateLoading(CreateHtmlByFreemarker.class, "static");
            // 设置默认字体
            configuration.setEncoding(Locale.CHINA, "utf-8");
          //  configuration.setDefaultEncoding("utf-8");
            // 获取模板
            Template template = configuration.getTemplate("user.ftl");
            template.setEncoding("utf-8");
            //设置模型
            User user = new User("tom", "hahahah", 28, "上海市");
            
            StringWriter writer = new StringWriter();
            template.process(user, writer);
            writer.flush();
          html = writer.toString();
          System.out.println(html);
           System.out.println("模板生成成功！");
          
          
           
           
        } catch (TemplateNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedTemplateNameException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } finally {
            if(null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            
        }
        return html;
    }
}