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
		File file = new File("outputData\\a.xlsx");// ��λҪ������excel�ļ�
		Workbook workbook = new XSSFWorkbook();// ��������������
		Sheet sheet = workbook.createSheet("������Ϣ��");// �������������

		Row title = sheet.createRow(0);
		Cell c1 = title.createCell(0);
		c1.setCellValue("������");
		Cell c2 = title.createCell(1);
		c2.setCellValue("����Ʒ��");
		Cell c3 = title.createCell(2);
		c3.setCellValue("�����Ա�");
		Cell c4 = title.createCell(3);
		c4.setCellValue("��������");
		Cell c5 = title.createCell(4);
		c5.setCellValue("����״̬");
		Cell c6 = title.createCell(5);
		c6.setCellValue("�����ȶ�");
		Cell c7=title.createCell(6);
		c7.setCellValue("����۸�");
		Cell c8=title.createCell(7);
		c8.setCellValue("�������");
		int i = 1;
		for (Pets p : listpets) {
			Row row = sheet.createRow(i);// �����ж����±��0��ʼ
			Cell cell1 = row.createCell(0);// ������Ԫ�񣬴�0��ʼ
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
