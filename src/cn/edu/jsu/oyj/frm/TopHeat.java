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
import java.util.List;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import cn.edu.jsu.oyj.dao.DataOperate;
import cn.edu.jsu.oyj.dao.PetsDAO;
import cn.edu.jsu.oyj.vo.Pets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
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
import javax.swing.ImageIcon;
/**
 * �ȶ����򴰿�
 * */
public class TopHeat extends JInternalFrame {
	private static TopHeat cframe = null;

	public static synchronized TopHeat GetInstance() {
		if (cframe == null) {
			cframe = new TopHeat();
		}
		return cframe;
	}

	private JPanel contentPane;// ���崰��������壬���ø����
	private JTable table;// ������
	private DefaultTableModel model;// ����������ģ��
	private TableRowSorter sorter;// ����������
	private ArrayList<RowSorter.SortKey> sortKeys;// �����������
	private Vector<String> titles;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TopHeat frame = new TopHeat();
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
	private TopHeat() {
		setTitle("�ȶ����а�");
		setIconifiable(true);
		setClosable(true);
		try {
			setClosed(true);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setBounds(100, 100, 882, 606);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(88, 54, 685, 431);
		contentPane.add(scrollPane);

		titles = new Vector<String>();// ���嶯̬�����ʾ������
		Collections.addAll(titles, "���", "Ʒ��", "�Ա�", "����", "״̬", "�ȶ�", "�۸�", "���");
		Vector<Vector> stuInfo = DataOperate.getSelectAll("select * from pets");
//			ʹ�þ�̬���ݴ���DefaultTableModel����ģ��
		model = new DefaultTableModel(stuInfo, titles) {// ʹ��Vectorװ�ر������ģ�ͣ���дgetColumnClass������ʵ�ְ����е�������������
			public Class getColumnClass(int column) {// ��ȡ�е�����
				Class returnValue;
				if ((column >= 0) && (column < getColumnCount())) {
					returnValue = getValueAt(0, column).getClass();
				} else {
					returnValue = Object.class;
				}
				return returnValue;
			}
		};
		table = new JTable(model);// ʹ��DefaultTableModel����ģ��ʵ�������
		sorter = new TableRowSorter<DefaultTableModel>(model);// ����������
		table.setAutoCreateRowSorter(true);// ���ñ���Զ�����

		scrollPane.setViewportView(table);

		TableRowSorter sorter = new TableRowSorter<DefaultTableModel>(model);// ����������
		table.setRowSorter(sorter);// ���ñ���������
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TopHeat.class.getResource("/image/6.jpg")));
		lblNewLabel.setBounds(0, 0, 866, 565);
		contentPane.add(lblNewLabel);
		ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();
		// ���������ֶΣ�����Ϊ��6���ֶ�����
		sortKeys.add(new RowSorter.SortKey(5, SortOrder.DESCENDING));
		sorter.setSortKeys(sortKeys);
	}
}
