package cn.edu.jsu.oyj.frm;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import cn.edu.jsu.oyj.dao.PetsDAO;
import cn.edu.jsu.oyj.dbc.DatabaseConnectionSql;
import cn.edu.jsu.oyj.vo.Pets;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
/**
 * 购买宠物窗口
 * */
public class buy extends JInternalFrame {

	/**
	 * Create the frame.
	 */
	private static buy cframe = null;

	public static synchronized buy GetInstance() {
		if (cframe == null) {
			cframe = new buy();
		}
		return cframe;
	}

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					buy frame = new buy();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	private buy() {
		setIconifiable(true);
		try {
			setClosed(true);
		} catch (PropertyVetoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setClosable(true);
		setBounds(100, 100, 863, 505);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(85, 28, 685, 332);
		contentPane.add(scrollPane);

		String[] titles = { "编号", "品种", "性别", "重量", "状态", "热度", "价格", "类别" };// 定义数组表示表格标题
		DefaultTableModel model = new DefaultTableModel(titles, 0);// 定义表格数据模型的表格标题和行数(为0行)
		table = new JTable(model) { // 实例化表格装载表格模型 //********************************//
			public boolean isCellEditable(int rowIndex, int columnIndex) { // 设置所有编号不可编辑,即第0列
				if (columnIndex <= getColumnCount() && rowIndex <= getRowCount())
					return false;
				return true;
			}
		};

		List<Pets> pets = new PetsDAO().getAllPetData();
		for (Pets p : pets) {
			model.addRow(p.toString().split("\t"));
		}

		scrollPane.setViewportView(table);

////////////////////////////////////////////////////////////
		JButton btnDelete = new JButton("购买选中行");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRow() != -1) {// 是否选择了要删除的行
					if (JOptionPane.showConfirmDialog(null, "确定要购买吗？", "", JOptionPane.YES_NO_OPTION) == 0) {// 确定对话框
						Boolean status = Boolean.parseBoolean((String) table.getValueAt(table.getSelectedRow(), 4));
						if (status == false) {
							JOptionPane.showMessageDialog(null, "该商品已经售罄");
						} else {
							String num = (String) table.getValueAt(table.getSelectedRow(), 0);
							String category = (String) table.getValueAt(table.getSelectedRow(), 1);
							String sex = (String) table.getValueAt(table.getSelectedRow(), 2);
							Double weight = Double.parseDouble((String) table.getValueAt(table.getSelectedRow(), 3));
							Integer heat = Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 5));
							Integer price = Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 6));
							String type = (String) table.getValueAt(table.getSelectedRow(), 7);

							Date d = new Date();
							long time = new Date().getTime();
							SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
							String dateTime = format.format(time);
							model.removeRow(table.convertRowIndexToModel(table.getSelectedRow()));
							// 从表格数据中删除行,model1为DefaultTableModel类型，排序后不能直接使用表格的getSelectedRow方法获取被选中的行
							table.convertRowIndexToModel(table.getSelectedRow());

							DatabaseConnectionSql dbcs = new DatabaseConnectionSql();
							String sql1 = "update pets set status='0' where num=?";
							String sql2 = "insert orders(num,category,sex,weight,price,type,time) value(?,?,?,?,?,?,?)";
							try (Connection conn = dbcs.getConnection(); // 获取数据库连接
									PreparedStatement pstmt = conn.prepareStatement(sql1);) {// 实例化
								pstmt.setString(1, num); // 通配
								pstmt.executeUpdate();// 执行更新

								PreparedStatement ps = conn.prepareStatement(sql2);
								ps.setString(1, num);
								ps.setString(2, category);
								ps.setString(3, sex);
								ps.setDouble(4, weight);
								ps.setInt(5, price);
								ps.setString(6, type);
								ps.setString(7, dateTime);
								ps.executeUpdate();// 执行增加

							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		});
		btnDelete.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		btnDelete.setBounds(543, 388, 207, 45);
		contentPane.add(btnDelete);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(buy.class.getResource("/image/1.jpg")));
		lblNewLabel.setBounds(0, 0, 847, 464);
		contentPane.add(lblNewLabel);
	}
}
