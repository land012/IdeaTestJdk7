package com.umbrella.util;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by 大洲 on 14-11-16.
 */
public class PoiDemo {
    public static void main(String[] args) {

    }

    /**
     * 复制 Excel
     */
    public void testCopyExcel() {
        try {
            Workbook wb = WorkbookFactory.create(new FileInputStream("D:\\_temp\\1111.xlsx"));
            wb.write(new FileOutputStream("D:\\_temp\\2222.xlsx"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }
}
