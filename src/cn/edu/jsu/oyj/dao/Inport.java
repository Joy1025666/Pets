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
	 * 导入Excel
	 * */
	public static void addExcel() throws Exception{
		DatabaseConnectionSql dbcs = new DatabaseConnectionSql();// 使用1中定义的连接数据库的类
		String sql = "insert into pets(num,category,sex,weight,status,heat,price,type) values(?,?,?,?,?,?,?,?)";// 使用占位符定义插入语句
		try (FileInputStream fis = new FileInputStream("inportData\\pets.xlsx");
				Connection cn = dbcs.getConnection();
				PreparedStatement pstmt = cn.prepareStatement(sql);// 实例化
		) {

			Workbook wb = new XSSFWorkbook(fis);
			Sheet sheet = wb.getSheetAt(0);// 得到第一个工作表对象
			int i = 1;
			while (i <= sheet.getLastRowNum()) {
				Row row = sheet.getRow(i);// 得到Excel工作表的第2行
				String num = row.getCell(0).toString();
				String category = row.getCell(1).toString();
				String sex = row.getCell(2).toString();
				double weight = row.getCell(3).getNumericCellValue();
				boolean status = row.getCell(4).getBooleanCellValue();
				int heat = (int) row.getCell(5).getNumericCellValue();
				int price = (int) row.getCell(6).getNumericCellValue();
				String type = row.getCell(7).toString();

				pstmt.setString(1, num);// 定义第1个占位符的内容
				pstmt.setString(2, category);// 定义第2个占位符的内容
				pstmt.setString(3, sex);// 定义第3个占位符的内容
				pstmt.setDouble(4, weight);// 定义第4个占位符的内容
				pstmt.setBoolean(5, status);
				pstmt.setInt(6, heat);
				pstmt.setInt(7, price);
				pstmt.setString(8, type);
				pstmt.executeUpdate();// 执行插入语句
				i++;
			}
		} 
	}
	/**
	 * 导入word
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
				DatabaseConnectionSql dbcs = new DatabaseConnectionSql();// 使用1中定义的连接数据库的类
				String sql = "insert into pets(num,category,sex,weight,status,heat,price,type) values(?,?,?,?,?,?,?,?)";// 使用占位符定义插入语句
				try (Connection conn = dbcs.getConnection(); // 获取数据库连接
						PreparedStatement pstmt = conn.prepareStatement(sql);) {// 实例化
					pstmt.setString(1, num);// 定义第1个占位符的内容
					pstmt.setString(2, category);// 定义第2个占位符的内容
					pstmt.setString(3, xb);// 定义第3个占位符的内容
					pstmt.setDouble(4, weight);// 定义第4个占位符的内容
					pstmt.setBoolean(5, status);
					pstmt.setInt(6, heat);
					pstmt.setInt(7, price);
					pstmt.setString(8,type);
					pstmt.executeUpdate();// 执行插入语句
				} catch (SQLException e) {
//					e.printStackTrace();
//					JOptionPane.showMessageDialog(null, "数据已经存在");
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