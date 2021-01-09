package cn.edu.jsu.oyj.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.edu.jsu.oyj.dbc.DatabaseConnectionSql;

public class Inport {
	/**
	 * ����Excel
	 * */
	public static void addExcel() throws Exception{
		DatabaseConnectionSql dbcs = new DatabaseConnectionSql();// ʹ��1�ж�����������ݿ����
		String sql = "insert into pets(num,category,sex,weight,status,heat,price,type) values(?,?,?,?,?,?,?,?)";// ʹ��ռλ������������
		try (FileInputStream fis = new FileInputStream("inportData\\pets.xlsx");
				Connection cn = dbcs.getConnection();
				PreparedStatement pstmt = cn.prepareStatement(sql);// ʵ����
		) {

			Workbook wb = new XSSFWorkbook(fis);
			Sheet sheet = wb.getSheetAt(0);// �õ���һ�����������
			int i = 1;
			while (i <= sheet.getLastRowNum()) {
				Row row = sheet.getRow(i);// �õ�Excel������ĵ�2��
				String num = row.getCell(0).toString();
				String category = row.getCell(1).toString();
				String sex = row.getCell(2).toString();
				double weight = row.getCell(3).getNumericCellValue();
				boolean status = row.getCell(4).getBooleanCellValue();
				int heat = (int) row.getCell(5).getNumericCellValue();
				int price = (int) row.getCell(6).getNumericCellValue();
				String type = row.getCell(7).toString();

				pstmt.setString(1, num);// �����1��ռλ��������
				pstmt.setString(2, category);// �����2��ռλ��������
				pstmt.setString(3, sex);// �����3��ռλ��������
				pstmt.setDouble(4, weight);// �����4��ռλ��������
				pstmt.setBoolean(5, status);
				pstmt.setInt(6, heat);
				pstmt.setInt(7, price);
				pstmt.setString(8, type);
				pstmt.executeUpdate();// ִ�в������
				i++;
			}
		} 
	}
	/**
	 * ����word
	 * */
	public void addWord() throws Exception {
		String s=null;
		try (FileReader fr = new FileReader(new File("inportData\\pets.txt"));
				BufferedReader br = new BufferedReader(fr);) {
				String title =br.readLine();
//				System.out.println(title);
				int i=0;
			while ((s = br.readLine()) != null) {
				System.out.println(s);
				String[] s1 =s.split("\t");
				String num = s1[0];
				String category = s1[1];
				String xb = s1[2];
				Double weight = Double.parseDouble(s1[3]);
				Boolean status = Boolean.parseBoolean(s1[4]);
				Integer heat = Integer.parseInt(s1[5]);
				Integer price = Integer.parseInt(s1[6]);
				String type=s1[7];
				System.out.println(category);
				DatabaseConnectionSql dbcs = new DatabaseConnectionSql();// ʹ��1�ж�����������ݿ����
				String sql = "insert into pets(num,category,sex,weight,status,heat,price,type) values(?,?,?,?,?,?,?,?)";// ʹ��ռλ������������
				try (Connection conn = dbcs.getConnection(); // ��ȡ���ݿ�����
						PreparedStatement pstmt = conn.prepareStatement(sql);) {// ʵ����
					pstmt.setString(1, num);// �����1��ռλ��������
					pstmt.setString(2, category);// �����2��ռλ��������
					pstmt.setString(3, xb);// �����3��ռλ��������
					pstmt.setDouble(4, weight);// �����4��ռλ��������
					pstmt.setBoolean(5, status);
					pstmt.setInt(6, heat);
					pstmt.setInt(7, price);
					pstmt.setString(8,type);
					pstmt.executeUpdate();// ִ�в������
				} catch (SQLException e) {
//					e.printStackTrace();
//					JOptionPane.showMessageDialog(null, "�����Ѿ�����");
				}
			}
		} 
	}
	public static void main(String args[]) {
		try {
			new Inport().addExcel();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}