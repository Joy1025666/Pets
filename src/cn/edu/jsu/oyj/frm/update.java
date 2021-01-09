package cn.edu.jsu.oyj.frm;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import cn.edu.jsu.oyj.dao.PetsDAO;
import cn.edu.jsu.oyj.dbc.DatabaseConnectionSql;
import cn.edu.jsu.oyj.vo.Pets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.ImageIcon;
/**
 * 修改数据窗口
 * */
public class update extends JInternalFrame {
	private static update cframe = null;// 定义子窗体为私有
	// 静态公开方法，只产生一个对象，synchronized保证线程案例

	public static synchronized update GetInstance() {
		if (cframe == null) {
			cframe = new update();
		}
		return cframe;
	}

	private JPanel contentPane;// 定义窗体内容面板，放置各组件
	private JTable table;// 定义表格
	private DefaultTableModel model;// 定义表格数据模型

	private Vector<String> titles;
	private JTextField aftertxt;
	private JTextField idtext;
	public static boolean flag = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					update frame = new update();
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
	private update() {
		setTitle("修改宠物信息");

		setIconifiable(true);
		try {
			setClosed(true);
		} catch (PropertyVetoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setClosable(true);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.WHITE);
		getContentPane().add(desktopPane, BorderLayout.CENTER);
		setBounds(100, 100, 921, 532);// 设置窗体位置与大小
		contentPane = new JPanel();// 实例化内容面板
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));// 设置面板边框
		contentPane.setLayout(null);// 设置面板布局为绝对布局
		setContentPane(contentPane);// 将窗体默认面板

		// 设置滚动面板
		JScrollPane scrollPane = new JScrollPane();// 创建滚动面板
		scrollPane.setBounds(44, 38, 442, 403);// 设置大小与位置
		contentPane.add(scrollPane);// 将滚动面板加入到内容面板中

		String[] titles = { "编号", "品种", "性别", "重量", "状态", "热度", "价格", "类别" };// 定义数组表示表格标题
		DefaultTableModel model = new DefaultTableModel(titles, 0);// 定义表格数据模型的表格标题和行数(为0行)
		table = new JTable(model);// 使用DefaultTableModel数据模型实例化表格
		List<Pets> pets = new PetsDAO().getAllPetData();
		for (Pets p : pets) {
			model.addRow(p.toString().split("\t"));
		}

		scrollPane.setViewportView(table);

		JLabel lblMsg = new JLabel("\u9009\u62E9\u4FEE\u6539\u7C7B\u522B");
		lblMsg.setForeground(Color.GREEN);
		lblMsg.setFont(new Font("Serif", Font.PLAIN, 18));
		lblMsg.setBounds(518, 175, 163, 28);
		contentPane.add(lblMsg);

		JComboBox comboBox = new JComboBox(new String[] { "---类型---", "编号", "品种", "性别", "重量", "状态", "热度", "价格", "类别" });
		comboBox.addItemListener(new ItemListener() {// 页数下拉框选择改变事件
			public void itemStateChanged(ItemEvent e) {
				String kind = comboBox.getSelectedItem().toString();// 获取下拉框所选的值
				table.setModel(model);// 设置表格数据模型
			}
		});
		comboBox.setBounds(673, 172, 122, 33);
		contentPane.add(comboBox);

		JLabel afterMsg = new JLabel("");
		afterMsg.setForeground(Color.RED);
		afterMsg.setBounds(673, 320, 122, 25);
		contentPane.add(afterMsg);

		JLabel idMsg = new JLabel("");
		idMsg.setForeground(Color.RED);
		idMsg.setBounds(673, 131, 122, 25);
		contentPane.add(idMsg);

		aftertxt = new JTextField();
		aftertxt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				String s = comboBox.getSelectedItem().toString();
				if (aftertxt.getText().length() == 0) {
					afterMsg.setText("请输入数据");
				} else if (!aftertxt.getText().matches("\\d+") && ("编号重量热度价格".contains(s))) {
					afterMsg.setText("请输入数字");
				} else if (!aftertxt.getText().matches("[\\u4e00-\\u9fa5]") && ("品种性别类别".contains(s))) {
					afterMsg.setText("请输入中文");
				} else
					afterMsg.setText("");
			}
		});
		aftertxt.setBounds(673, 260, 184, 32);
		contentPane.add(aftertxt);
		aftertxt.setColumns(10);

		JButton sure = new JButton("确定");
		sure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatabaseConnectionSql dbc = new DatabaseConnectionSql();
				String sql1 = "update pets set num=? where num=?";
				String sql2 = "update pets set category=? where num=?";
				String sql3 = "update pets set sex=? where num=?";
				String sql4 = "update pets set weight=? where num=?";
				String sql5 = "update pets set status=? where num=?";
				String sql6 = "update pets set heat=? where num=?";
				String sql7 = "update pets set price=? where num=?";
				String sql8 = "update pets set type=? where num=?";
				try (Connection conn = dbc.getConnection();) {
					String k = comboBox.getSelectedItem().toString();
					if (aftertxt.getText().length() == 0) {
						JOptionPane.showMessageDialog(null, "请输入数据！");
					} else if (k.equals("编号") && flag) {
						PreparedStatement p1 = conn.prepareStatement(sql1);
						p1.setString(1, aftertxt.getText());
						p1.setString(2, idtext.getText());
						p1.executeUpdate();
						JOptionPane.showMessageDialog(null, "修改成功");
					} else if (k.equals("品种") && flag) {
						PreparedStatement p2 = conn.prepareStatement(sql2);
						p2.setString(1, aftertxt.getText());
						p2.setString(2, idtext.getText());
						p2.executeUpdate();
						JOptionPane.showMessageDialog(null, "修改成功");
					} else if (k.equals("性别") && flag) {
						PreparedStatement p3 = conn.prepareStatement(sql3);
						p3.setString(1, aftertxt.getText());
						p3.setString(2, idtext.getText());
						p3.executeUpdate();
						JOptionPane.showMessageDialog(null, "修改成功");
					} else if (k.equals("重量") && flag) {
						PreparedStatement p4 = conn.prepareStatement(sql4);
						p4.setDouble(1, Double.parseDouble(aftertxt.getText()));
						p4.setString(2, idtext.getText());
						p4.executeUpdate();
						JOptionPane.showMessageDialog(null, "修改成功");
					} else if (k.equals("状态") && flag) {
						PreparedStatement p5 = conn.prepareStatement(sql5);
						p5.setBoolean(1, Boolean.parseBoolean(aftertxt.getText()));
						p5.setString(2, idtext.getText());
						p5.executeUpdate();
						JOptionPane.showMessageDialog(null, "修改成功");
					} else if (k.equals("热度") && flag) {
						PreparedStatement p6 = conn.prepareStatement(sql6);
						p6.setInt(1, Integer.parseInt("aftertxt.getText()"));
						p6.setInt(2, Integer.parseInt(idtext.getText()));
						p6.executeUpdate();
						JOptionPane.showMessageDialog(null, "修改成功");
					} else if (k.equals("类别") && flag) {
						PreparedStatement p7 = conn.prepareStatement(sql7);
						p7.setString(1, aftertxt.getText());
						p7.setString(2, idtext.getText());
						p7.executeUpdate();
						JOptionPane.showMessageDialog(null, "修改成功");
					} else if (k.equals("价格") && flag) {
						PreparedStatement p8 = conn.prepareStatement(sql8);
						p8.setInt(1, Integer.parseInt("aftertxt.getText()"));
						p8.setString(2, idtext.getText());
						p8.executeUpdate();
						JOptionPane.showMessageDialog(null, "修改成功");
					} else if (k.equals("---类型---")) {
						JOptionPane.showMessageDialog(null, "选择相应类别");
					} else
						JOptionPane.showMessageDialog(null, "修改失败");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		sure.setBounds(673, 412, 81, 29);
		contentPane.add(sure);

		JLabel lblAfter = new JLabel("修改成");
		lblAfter.setForeground(Color.GREEN);
		lblAfter.setFont(new Font("Serif", Font.PLAIN, 18));
		lblAfter.setBounds(518, 262, 163, 28);
		contentPane.add(lblAfter);

		JLabel id = new JLabel("输入修改值的编号");
		id.setForeground(Color.GREEN);
		id.setFont(new Font("Serif", Font.PLAIN, 18));
		id.setBounds(518, 86, 163, 28);
		contentPane.add(id);

		idtext = new JTextField();
		idtext.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				List<Pets> p = new PetsDAO().getAllPetData();
//				boolean flag=false;
				for (Pets s : p) {
//					System.out.println(s.getNum() +" "+idtext.getText().toString());
					if (s.getNum().equals((idtext.getText().toString()))) {
						flag = true;
						break;
					}
				}
				if (flag == false) {
					idMsg.setText("编号不存在");
				} else
					idMsg.setText("");
				if (idtext.getText().length() == 0) {
					idMsg.setText("编号不为空");
				}

			}
		});
		idtext.setColumns(10);
		idtext.setBounds(673, 84, 184, 32);
		contentPane.add(idtext);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(update.class.getResource("/image/8.jpg")));
		lblNewLabel.setBounds(0, 0, 905, 491);
		contentPane.add(lblNewLabel);

	}
}
