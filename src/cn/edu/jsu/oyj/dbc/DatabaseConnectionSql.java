package cn.edu.jsu.oyj.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionSql{
	//����SQLServer���ݿ���������
//	private static final String DBRIVER="com.microsoft.sqlserver.jdbc.SQLServerDriver";
//	//����SQLServer���ݿ����ӵ�ַ��testdb�ɸĳ��Լ������ݿ�����
//	private static final String DBURL="jdbc:sqlserver://localhost:1433;DatabaseName=testdb";
//	private static final String DBUSER="sa"; //SQLServer���ݿ������û���
//	private static final String PASSWORD="a1426075847"; //SQLServer���ݿ���������
	
	
	 //����MySQL���ݿ���������
		private static final String DBRIVER="com.mysql.cj.jdbc.Driver";
		//����MySQL���ݿ����ӵ�ַ��db_library�ɸĳ��Լ������ݿ�����
		private static final String DBURL="jdbc:mysql://127.0.0.1:3306/Pets?serverTimezone=GMT";
		private static final String DBUSER="root"; //MySQL���ݿ������û���
		private static final String PASSWORD="a1426075847"; //MySQL���ݿ���������


	private Connection conn=null; //�������Ӷ���
	public DatabaseConnectionSql(){//���췽���������ݿ�
		try {
			Class.forName(DBRIVER);
			this.conn=DriverManager.getConnection(DBURL,DBUSER,PASSWORD);
		} catch (Exception e) {e.printStackTrace();}
	}
	public Connection getConnection() {//�������ݿ����Ӷ���
		return this.conn;
	}
	public void close() {//�ر���������
		if(this.conn!=null) {
			try {
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
//	public static void main(String args[]) {
//		DatabaseConnectionSql dcs=new DatabaseConnectionSql();
//		if(dcs.getConnection()!=null) {
//			System.out.println("���ӳɹ�");
//		}
//	}
}