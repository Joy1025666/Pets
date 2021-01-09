package cn.edu.jsu.oyj.frm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import cn.edu.jsu.oyj.dao.DataOperate;
import cn.edu.jsu.oyj.dao.PetsDAO;
import cn.edu.jsu.oyj.vo.Pets;
import javax.swing.ImageIcon;
/**
 * չʾ������Ϣ����
 * */
public class showOrders extends JInternalFrame {
	private static showOrders cframe = null;// �����Ӵ���Ϊ˽��
	// ��̬����������ֻ����һ������synchronized��֤�̰߳���
	public static synchronized showOrders GetInstance() {
		if (cframe == null) {
			cframe = new showOrders();
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
					showOrders frame = new showOrders();
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
	private showOrders() {
		setTitle("�鿴����");
		
		setIconifiable(true);
		try {
			setClosed(true);
		} catch (PropertyVetoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setClosable(true);
		setBounds(100, 100, 915, 437);
		
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.WHITE);
		getContentPane().add(desktopPane, BorderLayout.CENTER);
		contentPane = new JPanel();// ʵ�����������
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));// �������߿�
		contentPane.setLayout(null);// ������岼��Ϊ���Բ���
		setContentPane(contentPane);// ������Ĭ�����

		// ���ù������
		JScrollPane scrollPane = new JScrollPane();// �����������
		scrollPane.setBounds(44, 38, 806, 254);// ���ô�С��λ��
		contentPane.add(scrollPane);// �����������뵽���������

		// ʹ�ö�̬�������ݣ��б����������ݣ�
		titles = new Vector<String>();// ���嶯̬�����ʾ������
		Collections.addAll(titles, "���", "Ʒ��", "�Ա�", "����","�۸�","���","�µ�ʱ��");
		Vector<Vector> stuInfo = DataOperate.getSelectAllOrders("select* from orders");
//			ʹ�þ�̬���ݴ���DefaultTableModel����ģ��
		model = new DefaultTableModel(stuInfo, titles) ;
		table = new JTable(model) { // ʵ�������װ�ر��ģ�� //********************************//
			public boolean isCellEditable(int rowIndex, int columnIndex) { 
				if (columnIndex<=getColumnCount()&&rowIndex<=getRowCount())
					return false;
				return true;
			}
		};
		TableColumn column=table.getColumnModel().getColumn(6);
		column.setPreferredWidth(200);
		
		scrollPane.setViewportView(table);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(showOrders.class.getResource("/image/5.jpg")));
		lblNewLabel.setBounds(0, 0, 899, 396);
		contentPane.add(lblNewLabel);

				
	}

}