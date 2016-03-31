package com.umbrella.demo.pdf.xhtmlrenderer;

import com.sun.org.apache.xerces.internal.impl.xs.opti.DefaultDocument;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;

/**
 * Created by xudazhou on 2016/3/29.
 */
public class SaucerDemo {

    private static final Logger log = LoggerFactory.getLogger(SaucerDemo.class);

    /**
     * 测试生成pdf
     */
    @Test
    public void test1() {
        try {
            String html = "<div><div style=\"color:red\">Hello Flying Saucer!</div><div>Ha Ha!</div></div>";
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(html);
            renderer.layout();
            FileOutputStream fos = new FileOutputStream("E:\\TDDOWNLOAD\\1.pdf");
            renderer.createPDF(fos);
            if(fos!=null) fos.close();
        } catch (Exception e) {
            log.error("异常了", e);
        }
    }

    /**
     * 结合 Velocity 生成 html
     */
    @Test
    public void test2() throws Exception {
        VelocityEngine velocityEngine = new VelocityEngine();
//        velocityEngine.addProperty(Velocity.FILE_RESOURCE_LOADER_PATH, this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
        // 正面这两句作用等于上面的一句 指定模板文件的加载路径
        velocityEngine.addProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.addProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.init();

        VelocityContext vContext = new VelocityContext();
        vContext.put("telephone", "1234567");
        vContext.put("jdAddress", "No.18Kechuang 11 Street, BDA, China");

        Template ciT = velocityEngine.getTemplate("ci.vm", "utf-8");
        FileOutputStream fos = new FileOutputStream("E:\\TDDOWNLOAD\\ci.htm");
        OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
        ciT.merge(vContext, osw);
        osw.flush();
    }

    /**
     * 结合 Velocity 生成 pdf
     */
    @Test
    public void test3() throws Exception {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.addProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.addProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.init();

        VelocityContext vContext = new VelocityContext();
        vContext.put("telephone", "1234567");
        vContext.put("jdAddress", "No.18Kechuang 11 Street, BDA, China");

        Template ciT = velocityEngine.getTemplate("ci.vm", "utf-8");
        StringWriter sw = new StringWriter();
        ciT.merge(vContext, sw);

        ITextRenderer iTextRenderer = new ITextRenderer();
        iTextRenderer.setDocumentFromString(sw.toString());
        iTextRenderer.layout();
        iTextRenderer.createPDF(new FileOutputStream("E:\\TDDOWNLOAD\\1.pdf"));
    }
}
