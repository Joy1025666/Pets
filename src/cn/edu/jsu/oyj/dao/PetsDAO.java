package cn.edu.jsu.oyj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

import cn.edu.jsu.oyj.dbc.DatabaseConnectionSql;
import cn.edu.jsu.oyj.vo.Pets;
public class PetsDAO {
	private Connection conn=new DatabaseConnectionSql().getConnection();
	public PetsDAO() {}
	/**
	 * 从数据库中查询所有的Pets放到List集合中
	 * */
	public List<Pets> getAllPetData() {			
		List<Pets> listpets=new ArrayList<Pets>();
		String sql="select * from Pets";
		try(PreparedStatement pstmt=conn.prepareStatement(sql);) {
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				Pets pet=new Pets();
				pet.setNum(rs.getString(1));
				pet.setCategory(rs.getString(2));
				pet.setSex(rs.getString(3));
				pet.setWeight(rs.getDouble(4));
				pet.setState(rs.getBoolean(5));
				pet.setHeat(rs.getInt(6));
				pet.setPrice(rs.getInt(7));
				pet.setType(rs.getString(8));
				listpets.add(pet);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listpets;
	}
//	public List<Pets> getAllOrderData() {
//		List<Pets> listpets=new ArrayList<Pets>();
//		String sql="select * from orders";
//		try(PreparedStatement pstmt=conn.prepareStatement(sql);) {
//			ResultSet rs=pstmt.executeQuery();
//			Map<String,Integer> m=new HashMap<>();
//			int number=0;
//			while(rs.next()) {
//				number++;
//				m.put(rs.getString(2), number);
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return listpets;
//	}
	public Vector<Vector> getTableRows(List<Pets> list){
		Vector<Vector> rows=new Vector<Vector>();
		for(int i=0;i<list.size();i++) {
			Vector row=new Vector();
			Pets pets=list.get(i);
			Collections.addAll(row, pets.getNum(),pets.getCategory(),pets.getSex(),pets.getWeight(),pets.isState(),pets.getHeat(),pets.getPrice(),pets.getType());
			rows.add(row);
		}
		return rows;
	}
	public static Map<String, Integer> SellNumber() {
		String sql1 = "select *from orders ";
		String sql2 = "Select count(\"*\") from orders where category = ?";
		String sql3 = "insert  into sellnumber(category,number) value(?,?) ";
		Set<String> s = new HashSet<>();
		Map<String, Integer> m = new TreeMap<>();
		DatabaseConnectionSql dbc = new DatabaseConnectionSql();
		try (Connection con = dbc.getConnection(); PreparedStatement p1 = con.prepareStatement(sql1)) {
			ResultSet rs = p1.executeQuery();
			while (rs.next()) {
				s.add(rs.getString(2));
			}
			// 执行sql语句
			DatabaseConnectionSql dbcs = new DatabaseConnectionSql();
			try (Connection conn = dbcs.getConnection();
					PreparedStatement p2 = con.prepareStatement(sql2);
					PreparedStatement p3 = con.prepareStatement(sql3);) {
				for (String x : s) {
					p2.setString(1, x);
					ResultSet r = p2.executeQuery();
					int N = 0;
					while (r.next()) {
						N = r.getInt(1);
						p3.setString(1, x);
						p3.setInt(2, N);
						p3.executeUpdate();
					}
					m.put(x, N);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return m;
	}
}
