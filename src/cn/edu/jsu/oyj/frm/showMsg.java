package cn.edu.jsu.oyj.frm;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import cn.edu.jsu.oyj.dao.DataOperate;
import cn.edu.jsu.oyj.dao.PetsDAO;
import cn.edu.jsu.oyj.vo.Pets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
/**
 * 展示宠物信息窗口
 * */
public class showMsg extends JInternalFrame {
	private static showMsg cframe = null;// 定义子窗体为私有
	// 静态公开方法，只产生一个对象，synchronized保证线程案例

	public static synchronized showMsg GetInstance() {
		if (cframe == null) {
			cframe = new showMsg();
		}
		return cframe;
	}

	private JPanel contentPane;// 定义窗体内容面板，放置各组件
	private JTable table;// 定义表格
	private DefaultTableModel model;// 定义表格数据模型
	private Vector<String> titles;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					showMsg frame = new showMsg();
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
	private showMsg() {
		setTitle("展示宠物信息");
		setIconifiable(true);
		try {
			setClosed(true);
		} catch (PropertyVetoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setClosable(true);
		setBounds(100, 100, 450, 300);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.WHITE);
		getContentPane().add(desktopPane, BorderLayout.CENTER);
		setBounds(100, 100, 731, 437);// 设置窗体位置与大小
		contentPane = new JPanel();// 实例化内容面板
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));// 设置面板边框
		contentPane.setLayout(null);// 设置面板布局为绝对布局
		setContentPane(contentPane);// 将窗体默认面板

		// 设置滚动面板
		JScrollPane scrollPane = new JScrollPane();// 创建滚动面板
		scrollPane.setBounds(44, 38, 627, 296);// 设置大小与位置
		contentPane.add(scrollPane);// 将滚动面板加入到内容面板中

//		// 使用动态数组数据（列标题与行数据）
		titles = new Vector<String>();// 定义动态数组表示表格标题
		Collections.addAll(titles, "编号", "品种", "性别", "重量", "状态", "热度", "价格", "类别");
		Vector<Vector> stuInfo = DataOperate.getSelectAll("select * from pets");

//			使用静态数据创建DefaultTableModel数据模型
		model = new DefaultTableModel(stuInfo, titles);
		table = new JTable(model);// 使用DefaultTableModel数据模型实例化表格

		scrollPane.setViewportView(table);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(showMsg.class.getResource("/image/4.jpg")));
		lblNewLabel.setBounds(0, 0, 715, 396);
		contentPane.add(lblNewLabel);

	}

}
