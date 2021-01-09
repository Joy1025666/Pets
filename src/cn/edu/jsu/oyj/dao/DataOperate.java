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
	private static String cat[] = { "布偶猫", "暹罗猫", "狸花猫", "波斯猫", "金吉拉猫", "加菲猫", "虎斑猫", "缅因猫", "蒂凡尼猫" };
	private static String dog[] = { "金毛", "拉布拉多", "德牧", "边牧", "苏牧", "喜乐蒂", "银狐", "贵宾", "斗牛犬", "博美", "哈士奇", "阿拉斯加",
			"萨摩耶", "腊肠犬", "柯基犬", "雪纳瑞", "松狮犬", "京巴犬", "约克夏", "西施犬" };

	private static String wugui[] = { "中华草龟", "中华花龟", "平胸龟", "蛋龟", "锦龟", "拟鳄龟", "蛇颈龟", "猪鼻龟" };

	private static String bird[] = { "芙蓉鸟", "七彩文鸟", "相思鸟", "画眉鸟", "绣眼鸟", "百灵", "虎皮鹦鹉", "八哥", "黄鹂", "太平鸟" };

	public static int getNum(int start, int end) {// 随机返回返回指定范围间的整数
		// Math.random()随机返回0.0至1.0之间的数
		return (int) (Math.random() * (end - start + 1) + start);
	}

	// 随机返回学号
	public static StringBuilder getStuno() {// 不使用String","因为需要大量拼接字符串
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

	// 随机返回品种
	public static String getCategory(Pets p) {
		String cate = null;
		int x = getNum(1, 4);
		if (x == 1) {
			cate = dog[getNum(0, dog.length - 1)];
			p.setType("犬类");
		} else if (x == 2) {
			cate = cat[getNum(0, cat.length - 1)];
			p.setType("猫类");
		} else if (x == 3) {
			cate = wugui[getNum(0, wugui.length - 1)];
			p.setType("龟类");
		} else if (x == 4) {
			cate = bird[getNum(0, bird.length - 1)];
			p.setType("鸟类");
		}
		return cate;
	}

	public static String getTp(Pets p) {
		return p.getType();
	}

	// 返回性别
	public static String getSex() {
		int x = getNum(0, 1);
		if (x == 0) {
			return "雌";
		} else
			return "雄";
	}
/**
 * 增加宠物信息到数据库
 * */
	public static void addPets(String num, String category, String xb, Double weight, Boolean status, int heat,
			int price, String type) {
		DatabaseConnectionSql dbcs = new DatabaseConnectionSql();// 使用1中定义的连接数据库的类
		String sql = "insert into pets(num,category,sex,weight,status,heat,price,type) values(?,?,?,?,?,?,?,?)";// 使用占位符定义插入语句
		try (Connection conn = dbcs.getConnection(); // 获取数据库接
				PreparedStatement pstmt = conn.prepareStatement(sql);) {// 实例化
			pstmt.setString(1, num);// 定义第1个占位符的内容
			pstmt.setString(2, category);// 定义第2个占位符的内容
			pstmt.setString(3, xb);// 定义第3个占位符的内容
			pstmt.setDouble(4, weight);// 定义第4个占位符的内容
			pstmt.setBoolean(5, status);
			pstmt.setInt(6, heat);
			pstmt.setInt(7, price);
			pstmt.setString(8, type);
			pstmt.executeUpdate();// 执行插入语句
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
/**
 * 查询所有选中的宠物信息
 * */
	public static Vector<Vector> getSelectAll(String sql) {
		Vector<Vector> rows = new Vector<Vector>();// 定义要返回的所有记录集合
		DatabaseConnectionSql dbcs = new DatabaseConnectionSql();// 使用1中定义的连接数据库的类
		try (Connection conn = dbcs.getConnection(); // 获取数据库接
				PreparedStatement pstmt = conn.prepareStatement(sql);) {// 实例化PreparedStatement
			ResultSet rs = pstmt.executeQuery();// 执行查询语句","结果放到数据集中
			while (rs.next()) {// 遍历数据集
				Vector row = new Vector();// 定义行数据
				row.add(rs.getString(1));// 获取第一个字段编号
				row.add(rs.getString(2));// 获取第二个字段品种
				row.add(rs.getString(3));// 获取第三个字段性别
				row.add(rs.getDouble(4));// 获取第四个字段重量
				row.add(rs.getBoolean(5));// 获取第五个字段状态
				row.add(rs.getInt(6));// 获取第六个字段热度
				row.add(rs.getInt(7));// 获取第七个字段价格
				row.add(rs.getString(8));// 获取第八个字段类别
				rows.add(row);// 将行数据添加到记录集合中
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;// 返回所有行数据
	}
/**
 * 查询所有订单信息
 * */
	public static Vector<Vector> getSelectAllOrders(String sql) {
		Vector<Vector> rows = new Vector<Vector>();// 定义要返回的所有记录集合
		DatabaseConnectionSql dbcs = new DatabaseConnectionSql();// 使用1中定义的连接数据库的类
		try (Connection conn = dbcs.getConnection(); // 获取数据库接
				PreparedStatement pstmt = conn.prepareStatement(sql);) {// 实例化PreparedStatement
			ResultSet rs = pstmt.executeQuery();// 执行查询语句","结果放到数据集中
			while (rs.next()) {// 遍历数据集
				Vector row = new Vector();// 定义行数据
				row.add(rs.getString(1));// 获取第一个字段编号
				row.add(rs.getString(2));// 获取第二个字段品种
				row.add(rs.getString(3));// 获取第三个字段性别
				row.add(rs.getDouble(4));// 获取第四个字段重量
				row.add(rs.getInt(5));// 获取第七个字段价格
				row.add(rs.getString(6));// 获取第八个字段类别
				row.add(rs.getString(7));//获取第九个字段下单时间
				rows.add(row);// 将行数据添加到记录集合中
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;// 返回所有行数据
	}
	public static void main(String[] args) {
		Pets p = new Pets();
		ArrayList<String> alist = new ArrayList<String>();// 定义集合
		for (int i = 1; i <= 100;) {
			String num = getStuno().toString();// 随机获取编号
			if (!alist.contains(num)) {
				alist.add(num);
				String cate = getCategory(p);
				String sex = getSex();
				Double w = getNum(10, 1000) * 0.1;
				Double weight = (double) Math.round(w * 100) / 100; // 保留两位小数
				Boolean status = true;
				int heat = getNum(0,10000);
				int price = getNum(100, 800);
				String type = getTp(p);
				addPets(num, cate, sex, weight, status, heat, price, type);// 向数据表中增加数据
				i++;// 编号唯一","循环继续往下执行
			}
		}
		JOptionPane.showMessageDialog(null, "sucess");
	}
}