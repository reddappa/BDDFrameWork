package com.bdd.framework.utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtils {
	
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static int getRowCount(String xlfile,String xlsheet) throws IOException 
	{
		fi=new FileInputStream(xlfile);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		int rowcount=ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowcount;		
	}
	
	
	public static int getCellCount(String xlfile,String xlsheet,int rownum) throws IOException
	{
		fi=new FileInputStream(xlfile);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		int cellcount=row.getLastCellNum();
		wb.close();
		fi.close();
		return cellcount;
	}
	
	
	public static String getCellData(String xlfile,String xlsheet,int rownum,int colnum) throws IOException
	{
		fi=new FileInputStream(xlfile);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		cell=row.getCell(colnum);
		String data;
		try 
		{
			DataFormatter formatter = new DataFormatter();
            String cellData = formatter.formatCellValue(cell);
            return cellData;
		}
		catch (Exception e) 
		{
			data="";
		}
		wb.close();
		fi.close();
		return data;
	}
	
	public static void setCellData(String xlfile,String xlsheet,int rownum,int colnum,String data) throws IOException
	{
		fi=new FileInputStream(xlfile);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		fo=new FileOutputStream(xlfile);
		wb.write(fo);		
		wb.close();
		fi.close();
		fo.close();
	}
	

	public static String [][] getData() throws IOException
	{
		String path=System.getProperty("user.dir")+"/testData/TestData.xlsx";
		
		int rownum=XLUtils.getRowCount(path, "Sheet1");
		int colcount=XLUtils.getCellCount(path,"Sheet1",1);
		
		String logindata[][]=new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				logindata[i-1][j]=XLUtils.getCellData(path,"Sheet1", i,j);//1 0
			}
				
		}
	return logindata;
	}
	
	public static String ReadScenarioData(String filePath,String ScenarioName,String SheetName,String FiledName,Map<String,Map<String,Map<String,String>>> GlobalExcelData)
	{
		String inputfield="";
		for(Map.Entry<String, Map<String,Map<String,String>>> SheetnameItr :GlobalExcelData.entrySet())
		{
			String SheetnameAct=SheetnameItr.getKey();
			if(SheetnameAct==SheetName)
				
			{
				Map<String,Map<String,String>> ScenarionameItr=SheetnameItr.getValue();
				
				for(Map.Entry<String,Map<String,String>> ScenaroData : ScenarionameItr.entrySet())
				{
					String Scenarioname = ScenaroData.getKey();
					
					if(Scenarioname==ScenarioName)
					{
						Map<String,String> Scnariovalue=ScenaroData.getValue();
						for(Map.Entry<String,String> scdata :Scnariovalue.entrySet())
						{
							String fieldname=scdata.getKey();
							if(fieldname==FiledName)
							{
								inputfield=scdata.getValue();
								break;
							}
						}
						
					}
					
				}
				
			}
			
		}
		
		
		return 	inputfield;
	}
	
	
	
}
