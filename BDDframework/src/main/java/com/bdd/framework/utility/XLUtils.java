package com.bdd.framework.utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
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
	
	public static HashMap<String,String> storeValues = new HashMap<String, String>();
	
	  public static List<HashMap<String,String>> data(String filepath,String sheetName)
	   {
	      List<HashMap<String,String>> mydata = new ArrayList<>();
	      try
	      {
	         FileInputStream fs = new FileInputStream(filepath);
	         XSSFWorkbook workbook = new XSSFWorkbook(fs);
	         XSSFSheet sheet = workbook.getSheet(sheetName);
	         Row HeaderRow = sheet.getRow(0);
	         for(int i=1;i<sheet.getPhysicalNumberOfRows();i++)
	         {
	            Row currentRow = sheet.getRow(i);
	            HashMap<String,String> currentHash = new HashMap<String,String>();
	            for(int j=0;j<currentRow.getPhysicalNumberOfCells();j++)
	            {
	               Cell currentCell = currentRow.getCell(j);
	               switch (currentCell.getCellType())
	               {
	               case Cell.CELL_TYPE_STRING:
	                  System.out.print(currentCell.getStringCellValue() + "\t");
	                  currentHash.put(HeaderRow.getCell(j).getStringCellValue(), currentCell.getStringCellValue());
	                  break;
	               }
	            }
	            mydata.add(currentHash);
	         }
	         fs.close();
	      }
	      catch (Exception e)
	      {
	         e.printStackTrace();
	      }
	      return mydata;
	   }
	
}
