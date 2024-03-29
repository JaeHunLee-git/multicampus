package com.sds.dataroom.excel;

import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//자바언어로 엑셀을 연동하려면, apache 지원하는  POI 를 이용하면 된다
public class ExcelRead {
	public static void main(String[] args) {
		//HSSFWorkbook  엑셀 97~200x 구버전  xls 확장자 
		//XSSFWorkbook 최신엑셀  확장자  xlsx 확장자
		
		//하드 디스크에 있는 파일을 대상으로 Workbook 객체를 생성하자
		try {
			
			//엑셀파일을 접근한다 
			Workbook workbook=new XSSFWorkbook("D:/javaee_workspace/Dataroom/src/main/webapp/data/emp2.xlsx");
			
			//Sheet 쉬트에 접근
			Sheet sheet = workbook.getSheetAt(0); //첫번째 시트에 접근
			
			//시트는 row(행)들을  Iterator 로 반환해준다 
			Iterator<Row> rowIt=sheet.iterator();
			
			while(rowIt.hasNext()) { //row 즉 행만큼 반복문 돌리자 

				Row row = rowIt.next(); // row 1개 반환 
				Iterator<Cell> cellIt = row.iterator();//이 row를 구성하는 컬럼
				
				while(cellIt.hasNext()) { //하나의 Row 를 구성하는 컬럼 수 만큼...
					Cell cell = cellIt.next();
					if(cell.getCellType()==CellType.STRING) {//문자형이라면..
						System.out.print(cell.getStringCellValue());
					}else if(cell.getCellType()==CellType.NUMERIC) {//숫자형이라면..
						System.out.println(cell.getNumericCellValue());
					}
				}
				System.out.println("-----------------------------------");
			}
			
			workbook.close();//파일닫기
			
		} catch (IOException e) {
			e.printStackTrace();
		}  
		
		
	}
}
