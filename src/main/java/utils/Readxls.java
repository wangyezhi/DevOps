package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 	读取.xls文件的工具类(文件格式：第�?行是标题行，每行的第�?个单元格是关键字、第二个单元格是SQL语句)
 * @author hogan.wang
 *
 */
public class Readxls {
	
	public static HashMap<String,String> readXls(String xlsFilePath) throws IOException {
		
		HashMap<String,String> map = new HashMap<String,String>();
		
		//读取xls文件
		FileInputStream fileIn = new FileInputStream(xlsFilePath);
		
		//创建HSSFWorkbook对象
		HSSFWorkbook workbook = new HSSFWorkbook(fileIn);
		
		//获取sheet总数，然后遍历sheet
		int numberOfSheets = workbook.getNumberOfSheets();
		for (int i = 0; i < numberOfSheets; i++) {
		    HSSFSheet sheet = workbook.getSheetAt(i);
		    //获取sheet中的总行数，遍历行（跳过标题行）
		    int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
		    for (int j = 1; j < physicalNumberOfRows; j++) {
			    HSSFRow row = sheet.getRow(j);
		        //将单元格的数据作为键值对储存在集�?		    
		        String keyWorld = row.getCell(0).getStringCellValue();
		        String sql = row.getCell(1).getStringCellValue();
		        map.put(keyWorld, sql);
		    }
		}
		return map;
	}
}
