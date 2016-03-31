package com.umbrella.demo.pdf.itext;

import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;

/**
 * Created by xudazhou on 2016/3/29.
 */
public class ITextDemo {

    private static final Logger log = LoggerFactory.getLogger(ITextDemo.class);

    @Test
    public void test1() {
        try {
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("E:\\TDDOWNLOAD\\1.pdf"));
            document.open();
            document.add(new Paragraph("I am a paragraph!", FontFactory.getFont(FontFactory.HELVETICA, 14)));
            document.close();
        } catch (Exception e) {
            log.error("异常了", e);
        }
    }

    /**
     * html to pdf
     */
    @Test
    public void test2() {
        try {
        } catch (Exception e) {
            log.error("异常了", e);
        }
    }
}
