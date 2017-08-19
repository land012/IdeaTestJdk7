package cf.umbrella.poidemo;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

/**
 * POI处理Excel
 * @author xudazhou
 *
 */
public class POIExcel {

    /**
     * 读取 excel
     */
    @Test
	public void test1() {
		try {
			FileInputStream fis = new FileInputStream("D:\\_c_myEc9\\MyTest\\files\\excel1.xlsx");
//			Workbook wb = new HSSFWorkbook(new POIFSFileSystem(fis));
//			Workbook wb = WorkbookFactory.create(fis);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			Sheet shet = wb.getSheet("Sheet1");
			System.out.println(shet.getFirstRowNum());
			System.out.println(shet.getLastRowNum());
			int rows = shet.getLastRowNum(); // 最后一行有数据的行的行号
//			int rows = shet.getPhysicalNumberOfRows(); // 实际有数据的行数，即不计算空行
			
			DataFormatter df = new DataFormatter();
			df.addFormat("yyyy\\-MM\\-dd", new SimpleDateFormat());
			df.setDefaultNumberFormat(new DecimalFormat("0.0"));
			df.addFormat("0.0", new DecimalFormat());
//			df.addFormat("yyyy-MM-dd", new SimpleDateFormat());
			XSSFFormulaEvaluator xssfFE = new XSSFFormulaEvaluator(wb);
			
			String id = "";
			String name = "";
			for(int i=0; i<=rows; i++) {
				Row row = shet.getRow(i);
				if(row!=null) {
					Cell cell0 = row.getCell(0);
					Cell cell1 = row.getCell(1);
					id = POIExcelUtil.getCellValueAsString(cell0);
					name = POIExcelUtil.getCellValueAsString(cell1);
					
//					id = df.formatCellValue(cell0, xssfFE);
//					name = df.formatCellValue(cell1, xssfFE);
					
//					int cell0Type = -1;
//					int cell1Type = -1;
//					if(cell0 != null) {
//						cell0Type = cell0.getCellType();
//					}
//					if(cell1 != null) {
//						cell1Type = cell1.getCellType();
//					}
//					System.out.println(cell0Type + " -- " + cell1Type);
				} else {
					id = "";
					name = "";
				}
				System.out.println(id + " ------- " + name);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    /**
     * 追加写 xls
     */
    @Test
    public void test2() throws Exception {
        String filepath = "E:\\TDDOWNLOAD\\test.xls";
        File f = new File(filepath);
        HSSFWorkbook wb = null;

        if (!f.exists()) {
            f.createNewFile();
            wb = new HSSFWorkbook();
        } else {
            wb = new HSSFWorkbook(new FileInputStream(f));
        }

        HSSFSheet sheet = null;
        if (wb.getSheet("zp") != null) {
            sheet = wb.getSheet("zp");
        } else {
            sheet = wb.createSheet("zp");
        }
        // 有记录的最后一行
        int lastrow = sheet.getLastRowNum(); // 1
        System.out.println(lastrow);

        // 行列序号从 0 开始
        HSSFRow row = sheet.createRow(lastrow + 1);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("嘿嘿");

        FileOutputStream fos = new FileOutputStream(f);
        wb.write(fos);
        fos.close();
    }

    /**
     * 复制 Excel
     */
    @Test
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
