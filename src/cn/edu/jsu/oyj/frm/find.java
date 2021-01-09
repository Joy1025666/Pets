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
 * ��ѯ���ﴰ��
 * */
public class find extends JInternalFrame {
	private static find cframe = null;// �����Ӵ���Ϊ˽��
	// ��̬����������ֻ����һ������synchronized��֤�̰߳���

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
		setTitle("��ѯ������Ϣ");
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

		String[] titles = { "���", "Ʒ��", "�Ա�", "����", "״̬", "�ȶ�", "�۸�", "���" };// ���������ʾ������
		DefaultTableModel model = new DefaultTableModel(titles, 0);// ����������ģ�͵ı����������(Ϊ0��)
		table = new JTable(model) { // ʵ�������װ�ر��ģ�� //********************************//
			public boolean isCellEditable(int rowIndex, int columnIndex) { // �����������ݲ��ɱ༭
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

		JLabel lblNewLabel = new JLabel("���ҹؼ���");
		lblNewLabel.setFont(new Font("΢���ź�", Font.PLAIN, 22));
		lblNewLabel.setBounds(88, 75, 115, 32);
		contentPane.add(lblNewLabel);

		txtKey = new JTextField();
		txtKey.setBounds(218, 75, 356, 38);
		contentPane.add(txtKey);
		txtKey.setColumns(10);

		JButton btnFind = new JButton("��ѯ"); /*************** ��ѯ ******************/
		btnFind.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TableRowSorter sorter = new TableRowSorter<DefaultTableModel>(model);// ���ñ��ģ��������
				table.setRowSorter(sorter);// ���ñ��������
				sorter.setRowFilter(RowFilter.regexFilter(txtKey.getText()));// ��ָ����������ʽ��������

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
