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
 * ������ﴰ��
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

		String[] titles = { "���", "Ʒ��", "�Ա�", "����", "״̬", "�ȶ�", "�۸�", "���" };// ���������ʾ������
		DefaultTableModel model = new DefaultTableModel(titles, 0);// ����������ģ�͵ı����������(Ϊ0��)
		table = new JTable(model) { // ʵ�������װ�ر��ģ�� //********************************//
			public boolean isCellEditable(int rowIndex, int columnIndex) { // �������б�Ų��ɱ༭,����0��
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
		JButton btnDelete = new JButton("����ѡ����");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRow() != -1) {// �Ƿ�ѡ����Ҫɾ������
					if (JOptionPane.showConfirmDialog(null, "ȷ��Ҫ������", "", JOptionPane.YES_NO_OPTION) == 0) {// ȷ���Ի���
						Boolean status = Boolean.parseBoolean((String) table.getValueAt(table.getSelectedRow(), 4));
						if (status == false) {
							JOptionPane.showMessageDialog(null, "����Ʒ�Ѿ�����");
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
							SimpleDateFormat format = new SimpleDateFormat("yyyy��MM��dd�� HH:mm:ss");
							String dateTime = format.format(time);
							model.removeRow(table.convertRowIndexToModel(table.getSelectedRow()));
							// �ӱ��������ɾ����,model1ΪDefaultTableModel���ͣ��������ֱ��ʹ�ñ���getSelectedRow������ȡ��ѡ�е���
							table.convertRowIndexToModel(table.getSelectedRow());

							DatabaseConnectionSql dbcs = new DatabaseConnectionSql();
							String sql1 = "update pets set status='0' where num=?";
							String sql2 = "insert orders(num,category,sex,weight,price,type,time) value(?,?,?,?,?,?,?)";
							try (Connection conn = dbcs.getConnection(); // ��ȡ���ݿ�����
									PreparedStatement pstmt = conn.prepareStatement(sql1);) {// ʵ����
								pstmt.setString(1, num); // ͨ��
								pstmt.executeUpdate();// ִ�и���

								PreparedStatement ps = conn.prepareStatement(sql2);
								ps.setString(1, num);
								ps.setString(2, category);
								ps.setString(3, sex);
								ps.setDouble(4, weight);
								ps.setInt(5, price);
								ps.setString(6, type);
								ps.setString(7, dateTime);
								ps.executeUpdate();// ִ������

							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		});
		btnDelete.setFont(new Font("΢���ź�", Font.PLAIN, 25));
		btnDelete.setBounds(543, 388, 207, 45);
		contentPane.add(btnDelete);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(buy.class.getResource("/image/1.jpg")));
		lblNewLabel.setBounds(0, 0, 847, 464);
		contentPane.add(lblNewLabel);
	}
}
