package cn.edu.jsu.oyj.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.edu.jsu.oyj.vo.Pets;

public class PetsExcel {
	public void exportExcel(List<Pets> listpets) throws IOException {
		File file = new File("outputData\\a.xlsx");// 定位要操作的excel文件
		Workbook workbook = new XSSFWorkbook();// 创建工作簿对象
		Sheet sheet = workbook.createSheet("宠物信息表");// 创建工作表对象

		Row title = sheet.createRow(0);
		Cell c1 = title.createCell(0);
		c1.setCellValue("宠物编号");
		Cell c2 = title.createCell(1);
		c2.setCellValue("宠物品种");
		Cell c3 = title.createCell(2);
		c3.setCellValue("宠物性别");
		Cell c4 = title.createCell(3);
		c4.setCellValue("宠物重量");
		Cell c5 = title.createCell(4);
		c5.setCellValue("宠物状态");
		Cell c6 = title.createCell(5);
		c6.setCellValue("宠物热度");
		Cell c7=title.createCell(6);
		c7.setCellValue("宠物价格");
		Cell c8=title.createCell(7);
		c8.setCellValue("宠物类别");
		int i = 1;
		for (Pets p : listpets) {
			Row row = sheet.createRow(i);// 创建行对象，下标从0开始
			Cell cell1 = row.createCell(0);// 创建单元格，从0开始
			cell1.setCellValue(p.getNum());
			Cell cell2 = row.createCell(1);
			cell2.setCellValue(p.getCategory());
			Cell cell3 = row.createCell(2);
			cell3.setCellValue(p.getSex());
			Cell cell4 = row.createCell(3);
			cell4.setCellValue(p.getWeight());
			Cell cell5 = row.createCell(4);
			cell5.setCellValue(p.isState());
			Cell cell6 = row.createCell(5);
			cell6.setCellValue(p.getHeat());
			Cell cell7=row.createCell(6);
			cell7.setCellValue(p.getPrice());
			Cell cell8=row.createCell(7);
			cell8.setCellValue(p.getType());
			i++;
		}
		FileOutputStream fos = new FileOutputStream(file);
		workbook.write(fos);
	}

	public static void main(String args[]) {
		try {
			new PetsExcel().exportExcel(new PetsDAO().getAllPetData());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
