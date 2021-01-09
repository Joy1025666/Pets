package cn.edu.jsu.oyj.frm;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import cn.edu.jsu.oyj.dao.PetsDAO;
import cn.edu.jsu.oyj.vo.Pets;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import javax.swing.ImageIcon;
/**
 * 查询宠物窗口
 * */
public class find extends JInternalFrame {
	private static find cframe = null;// 定义子窗体为私有
	// 静态公开方法，只产生一个对象，synchronized保证线程案例

	public static synchronized find GetInstance() {
		if (cframe == null) {
			cframe = new find();
		}
		return cframe;
	}

	private JPanel contentPane;
	private JTable table;
	private JTextField txtKey;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					find frame = new find();
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
	private find() {
		setTitle("查询宠物信息");
		try {
			setClosed(true);
		} catch (PropertyVetoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 882, 606);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(88, 171, 685, 314);
		contentPane.add(scrollPane);

		String[] titles = { "编号", "品种", "性别", "重量", "状态", "热度", "价格", "类别" };// 定义数组表示表格标题
		DefaultTableModel model = new DefaultTableModel(titles, 0);// 定义表格数据模型的表格标题和行数(为0行)
		table = new JTable(model) { // 实例化表格装载表格模型 //********************************//
			public boolean isCellEditable(int rowIndex, int columnIndex) { // 设置所有数据不可编辑
				if (columnIndex >= 0 && columnIndex <= 6)
					return false;
				return true;
			}
		};

		List<Pets> pets = new PetsDAO().getAllPetData();
		for (Pets p : pets) {
			model.addRow(p.toString().split("\t"));
		}

		scrollPane.setViewportView(table);

		JLabel lblNewLabel = new JLabel("查找关键字");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		lblNewLabel.setBounds(88, 75, 115, 32);
		contentPane.add(lblNewLabel);

		txtKey = new JTextField();
		txtKey.setBounds(218, 75, 356, 38);
		contentPane.add(txtKey);
		txtKey.setColumns(10);

		JButton btnFind = new JButton("查询"); /*************** 查询 ******************/
		btnFind.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TableRowSorter sorter = new TableRowSorter<DefaultTableModel>(model);// 设置表格模型排序器
				table.setRowSorter(sorter);// 设置表格排序器
				sorter.setRowFilter(RowFilter.regexFilter(txtKey.getText()));// 按指定的正则表达式过滤数据

			}
		});
		btnFind.setBounds(691, 75, 82, 34);
		contentPane.add(btnFind);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(find.class.getResource("/image/3.jpg")));
		lblNewLabel_1.setBounds(0, 0, 866, 579);
		contentPane.add(lblNewLabel_1);
	}
}
