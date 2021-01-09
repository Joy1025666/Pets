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
 * չʾ������Ϣ����
 * */
public class showMsg extends JInternalFrame {
	private static showMsg cframe = null;// �����Ӵ���Ϊ˽��
	// ��̬����������ֻ����һ������synchronized��֤�̰߳���

	public static synchronized showMsg GetInstance() {
		if (cframe == null) {
			cframe = new showMsg();
		}
		return cframe;
	}

	private JPanel contentPane;// ���崰��������壬���ø����
	private JTable table;// ������
	private DefaultTableModel model;// ����������ģ��
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
		setTitle("չʾ������Ϣ");
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
		setBounds(100, 100, 731, 437);// ���ô���λ�����С
		contentPane = new JPanel();// ʵ�����������
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));// �������߿�
		contentPane.setLayout(null);// ������岼��Ϊ���Բ���
		setContentPane(contentPane);// ������Ĭ�����

		// ���ù������
		JScrollPane scrollPane = new JScrollPane();// �����������
		scrollPane.setBounds(44, 38, 627, 296);// ���ô�С��λ��
		contentPane.add(scrollPane);// �����������뵽���������

//		// ʹ�ö�̬�������ݣ��б����������ݣ�
		titles = new Vector<String>();// ���嶯̬�����ʾ������
		Collections.addAll(titles, "���", "Ʒ��", "�Ա�", "����", "״̬", "�ȶ�", "�۸�", "���");
		Vector<Vector> stuInfo = DataOperate.getSelectAll("select * from pets");

//			ʹ�þ�̬���ݴ���DefaultTableModel����ģ��
		model = new DefaultTableModel(stuInfo, titles);
		table = new JTable(model);// ʹ��DefaultTableModel����ģ��ʵ�������

		scrollPane.setViewportView(table);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(showMsg.class.getResource("/image/4.jpg")));
		lblNewLabel.setBounds(0, 0, 715, 396);
		contentPane.add(lblNewLabel);

	}

}
