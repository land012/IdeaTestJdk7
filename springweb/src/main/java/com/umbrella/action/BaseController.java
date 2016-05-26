package com.umbrella.action;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xudazhou on 2016/5/26.
 */
public class BaseController {
    private static final Logger log = LoggerFactory.getLogger(BaseController.class);

    @ExceptionHandler
    public ModelAndView exceptionHandler(HttpServletRequest req, HttpServletResponse resp, Exception e) {
        try {
            log.error("异常了", e);
        } catch (Exception e1) {
            log.error("e1", e);
        } finally {
            try {
                PrintWriter pw = resp.getWriter();
                pw.write("exception");
                pw.flush();
                pw.close();
            } catch (Exception e2) {
                log.error("e2", e2);
            }
        }
        return null;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new HectorDateEditor());
    }

    static class HectorDateEditor extends PropertiesEditor {
        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            text = StringUtils.trimToEmpty(text);
            if(StringUtils.isEmpty(text)) {
                this.setValue(null);
                return;
            }

            Date d = null;
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                d = sdf1.parse(text);
                this.setValue(d);
            } catch (ParseException e) {
                sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    d = sdf1.parse(text);
                    this.setValue(d);
                } catch (ParseException e1) {
                    log.error("date parse exception", e1);
                    throw new IllegalArgumentException(e1.getMessage());
                }
            }
        }

        @Override
        public String getAsText() {
            return super.getAsText();
        }
    }
}
