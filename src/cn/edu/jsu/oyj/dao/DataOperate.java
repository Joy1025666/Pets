package cn.edu.jsu.oyj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

import cn.edu.jsu.oyj.dbc.DatabaseConnectionSql;

class Pets {
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}

public class DataOperate {
	private static String cat[] = { "��żè", "����è", "�껨è", "��˹è", "����è", "�ӷ�è", "����è", "����è", "�ٷ���è" };
	private static String dog[] = { "��ë", "��������", "����", "����", "����", "ϲ�ֵ�", "����", "���", "��ţȮ", "����", "��ʿ��", "����˹��",
			"��ĦҮ", "����Ȯ", "�»�Ȯ", "ѩ����", "��ʨȮ", "����Ȯ", "Լ����", "��ʩȮ" };

	private static String wugui[] = { "�л��ݹ�", "�л�����", "ƽ�ع�", "����", "����", "������", "�߾���", "��ǹ�" };

	private static String bird[] = { "ܽ����", "�߲�����", "��˼��", "��ü��", "������", "����", "��Ƥ����", "�˸�", "���", "̫ƽ��" };

	public static int getNum(int start, int end) {// ������ط���ָ����Χ�������
		// Math.random()�������0.0��1.0֮�����
		return (int) (Math.random() * (end - start + 1) + start);
	}

	// �������ѧ��
	public static StringBuilder getStuno() {// ��ʹ��String","��Ϊ��Ҫ����ƴ���ַ���
		StringBuilder num = new StringBuilder();
		StringBuilder num1 = new StringBuilder(String.valueOf(getNum(1, 10000)));
		if (num1.length() == 1) {
			num1 = num1.insert(0, "0000");
		} else if (num1.length() == 2) {
			num1 = num1.insert(0, "000");
			num = num.append(num1);
		} else if (num1.length() == 3) {
			num1 = num1.insert(0, "00");
			num = num.append(num1);
		} else if(num1.length()==4){
			num1 = num1.insert(0, "0");
			num = num.append(num1);
		}else num=num.append(num1);
		return num;
	}

	// �������Ʒ��
	public static String getCategory(Pets p) {
		String cate = null;
		int x = getNum(1, 4);
		if (x == 1) {
			cate = dog[getNum(0, dog.length - 1)];
			p.setType("Ȯ��");
		} else if (x == 2) {
			cate = cat[getNum(0, cat.length - 1)];
			p.setType("è��");
		} else if (x == 3) {
			cate = wugui[getNum(0, wugui.length - 1)];
			p.setType("����");
		} else if (x == 4) {
			cate = bird[getNum(0, bird.length - 1)];
			p.setType("����");
		}
		return cate;
	}

	public static String getTp(Pets p) {
		return p.getType();
	}

	// �����Ա�
	public static String getSex() {
		int x = getNum(0, 1);
		if (x == 0) {
			return "��";
		} else
			return "��";
	}
/**
 * ���ӳ�����Ϣ�����ݿ�
 * */
	public static void addPets(String num, String category, String xb, Double weight, Boolean status, int heat,
			int price, String type) {
		DatabaseConnectionSql dbcs = new DatabaseConnectionSql();// ʹ��1�ж�����������ݿ����
		String sql = "insert into pets(num,category,sex,weight,status,heat,price,type) values(?,?,?,?,?,?,?,?)";// ʹ��ռλ������������
		try (Connection conn = dbcs.getConnection(); // ��ȡ���ݿ��
				PreparedStatement pstmt = conn.prepareStatement(sql);) {// ʵ����
			pstmt.setString(1, num);// �����1��ռλ��������
			pstmt.setString(2, category);// �����2��ռλ��������
			pstmt.setString(3, xb);// �����3��ռλ��������
			pstmt.setDouble(4, weight);// �����4��ռλ��������
			pstmt.setBoolean(5, status);
			pstmt.setInt(6, heat);
			pstmt.setInt(7, price);
			pstmt.setString(8, type);
			pstmt.executeUpdate();// ִ�в������
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
/**
 * ��ѯ����ѡ�еĳ�����Ϣ
 * */
	public static Vector<Vector> getSelectAll(String sql) {
		Vector<Vector> rows = new Vector<Vector>();// ����Ҫ���ص����м�¼����
		DatabaseConnectionSql dbcs = new DatabaseConnectionSql();// ʹ��1�ж�����������ݿ����
		try (Connection conn = dbcs.getConnection(); // ��ȡ���ݿ��
				PreparedStatement pstmt = conn.prepareStatement(sql);) {// ʵ����PreparedStatement
			ResultSet rs = pstmt.executeQuery();// ִ�в�ѯ���","����ŵ����ݼ���
			while (rs.next()) {// �������ݼ�
				Vector row = new Vector();// ����������
				row.add(rs.getString(1));// ��ȡ��һ���ֶα��
				row.add(rs.getString(2));// ��ȡ�ڶ����ֶ�Ʒ��
				row.add(rs.getString(3));// ��ȡ�������ֶ��Ա�
				row.add(rs.getDouble(4));// ��ȡ���ĸ��ֶ�����
				row.add(rs.getBoolean(5));// ��ȡ������ֶ�״̬
				row.add(rs.getInt(6));// ��ȡ�������ֶ��ȶ�
				row.add(rs.getInt(7));// ��ȡ���߸��ֶμ۸�
				row.add(rs.getString(8));// ��ȡ�ڰ˸��ֶ����
				rows.add(row);// ����������ӵ���¼������
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;// ��������������
	}
/**
 * ��ѯ���ж�����Ϣ
 * */
	public static Vector<Vector> getSelectAllOrders(String sql) {
		Vector<Vector> rows = new Vector<Vector>();// ����Ҫ���ص����м�¼����
		DatabaseConnectionSql dbcs = new DatabaseConnectionSql();// ʹ��1�ж�����������ݿ����
		try (Connection conn = dbcs.getConnection(); // ��ȡ���ݿ��
				PreparedStatement pstmt = conn.prepareStatement(sql);) {// ʵ����PreparedStatement
			ResultSet rs = pstmt.executeQuery();// ִ�в�ѯ���","����ŵ����ݼ���
			while (rs.next()) {// �������ݼ�
				Vector row = new Vector();// ����������
				row.add(rs.getString(1));// ��ȡ��һ���ֶα��
				row.add(rs.getString(2));// ��ȡ�ڶ����ֶ�Ʒ��
				row.add(rs.getString(3));// ��ȡ�������ֶ��Ա�
				row.add(rs.getDouble(4));// ��ȡ���ĸ��ֶ�����
				row.add(rs.getInt(5));// ��ȡ���߸��ֶμ۸�
				row.add(rs.getString(6));// ��ȡ�ڰ˸��ֶ����
				row.add(rs.getString(7));//��ȡ�ھŸ��ֶ��µ�ʱ��
				rows.add(row);// ����������ӵ���¼������
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;// ��������������
	}
	public static void main(String[] args) {
		Pets p = new Pets();
		ArrayList<String> alist = new ArrayList<String>();// ���弯��
		for (int i = 1; i <= 100;) {
			String num = getStuno().toString();// �����ȡ���
			if (!alist.contains(num)) {
				alist.add(num);
				String cate = getCategory(p);
				String sex = getSex();
				Double w = getNum(10, 1000) * 0.1;
				Double weight = (double) Math.round(w * 100) / 100; // ������λС��
				Boolean status = true;
				int heat = getNum(0,10000);
				int price = getNum(100, 800);
				String type = getTp(p);
				addPets(num, cate, sex, weight, status, heat, price, type);// �����ݱ�����������
				i++;// ���Ψһ","ѭ����������ִ��
			}
		}
		JOptionPane.showMessageDialog(null, "sucess");
	}
}