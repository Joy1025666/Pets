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
 * �޸����ݴ���
 * */
public class update extends JInternalFrame {
	private static update cframe = null;// �����Ӵ���Ϊ˽��
	// ��̬����������ֻ����һ������synchronized��֤�̰߳���

	public static synchronized update GetInstance() {
		if (cframe == null) {
			cframe = new update();
		}
		return cframe;
	}

	private JPanel contentPane;// ���崰��������壬���ø����
	private JTable table;// ������
	private DefaultTableModel model;// ����������ģ��

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
		setTitle("�޸ĳ�����Ϣ");

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
		setBounds(100, 100, 921, 532);// ���ô���λ�����С
		contentPane = new JPanel();// ʵ�����������
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));// �������߿�
		contentPane.setLayout(null);// ������岼��Ϊ���Բ���
		setContentPane(contentPane);// ������Ĭ�����

		// ���ù������
		JScrollPane scrollPane = new JScrollPane();// �����������
		scrollPane.setBounds(44, 38, 442, 403);// ���ô�С��λ��
		contentPane.add(scrollPane);// �����������뵽���������

		String[] titles = { "���", "Ʒ��", "�Ա�", "����", "״̬", "�ȶ�", "�۸�", "���" };// ���������ʾ������
		DefaultTableModel model = new DefaultTableModel(titles, 0);// ����������ģ�͵ı����������(Ϊ0��)
		table = new JTable(model);// ʹ��DefaultTableModel����ģ��ʵ�������
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

		JComboBox comboBox = new JComboBox(new String[] { "---����---", "���", "Ʒ��", "�Ա�", "����", "״̬", "�ȶ�", "�۸�", "���" });
		comboBox.addItemListener(new ItemListener() {// ҳ��������ѡ��ı��¼�
			public void itemStateChanged(ItemEvent e) {
				String kind = comboBox.getSelectedItem().toString();// ��ȡ��������ѡ��ֵ
				table.setModel(model);// ���ñ������ģ��
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
					afterMsg.setText("����������");
				} else if (!aftertxt.getText().matches("\\d+") && ("��������ȶȼ۸�".contains(s))) {
					afterMsg.setText("����������");
				} else if (!aftertxt.getText().matches("[\\u4e00-\\u9fa5]") && ("Ʒ���Ա����".contains(s))) {
					afterMsg.setText("����������");
				} else
					afterMsg.setText("");
			}
		});
		aftertxt.setBounds(673, 260, 184, 32);
		contentPane.add(aftertxt);
		aftertxt.setColumns(10);

		JButton sure = new JButton("ȷ��");
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
						JOptionPane.showMessageDialog(null, "���������ݣ�");
					} else if (k.equals("���") && flag) {
						PreparedStatement p1 = conn.prepareStatement(sql1);
						p1.setString(1, aftertxt.getText());
						p1.setString(2, idtext.getText());
						p1.executeUpdate();
						JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
					} else if (k.equals("Ʒ��") && flag) {
						PreparedStatement p2 = conn.prepareStatement(sql2);
						p2.setString(1, aftertxt.getText());
						p2.setString(2, idtext.getText());
						p2.executeUpdate();
						JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
					} else if (k.equals("�Ա�") && flag) {
						PreparedStatement p3 = conn.prepareStatement(sql3);
						p3.setString(1, aftertxt.getText());
						p3.setString(2, idtext.getText());
						p3.executeUpdate();
						JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
					} else if (k.equals("����") && flag) {
						PreparedStatement p4 = conn.prepareStatement(sql4);
						p4.setDouble(1, Double.parseDouble(aftertxt.getText()));
						p4.setString(2, idtext.getText());
						p4.executeUpdate();
						JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
					} else if (k.equals("״̬") && flag) {
						PreparedStatement p5 = conn.prepareStatement(sql5);
						p5.setBoolean(1, Boolean.parseBoolean(aftertxt.getText()));
						p5.setString(2, idtext.getText());
						p5.executeUpdate();
						JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
					} else if (k.equals("�ȶ�") && flag) {
						PreparedStatement p6 = conn.prepareStatement(sql6);
						p6.setInt(1, Integer.parseInt("aftertxt.getText()"));
						p6.setInt(2, Integer.parseInt(idtext.getText()));
						p6.executeUpdate();
						JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
					} else if (k.equals("���") && flag) {
						PreparedStatement p7 = conn.prepareStatement(sql7);
						p7.setString(1, aftertxt.getText());
						p7.setString(2, idtext.getText());
						p7.executeUpdate();
						JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
					} else if (k.equals("�۸�") && flag) {
						PreparedStatement p8 = conn.prepareStatement(sql8);
						p8.setInt(1, Integer.parseInt("aftertxt.getText()"));
						p8.setString(2, idtext.getText());
						p8.executeUpdate();
						JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
					} else if (k.equals("---����---")) {
						JOptionPane.showMessageDialog(null, "ѡ����Ӧ���");
					} else
						JOptionPane.showMessageDialog(null, "�޸�ʧ��");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		sure.setBounds(673, 412, 81, 29);
		contentPane.add(sure);

		JLabel lblAfter = new JLabel("�޸ĳ�");
		lblAfter.setForeground(Color.GREEN);
		lblAfter.setFont(new Font("Serif", Font.PLAIN, 18));
		lblAfter.setBounds(518, 262, 163, 28);
		contentPane.add(lblAfter);

		JLabel id = new JLabel("�����޸�ֵ�ı��");
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
					idMsg.setText("��Ų�����");
				} else
					idMsg.setText("");
				if (idtext.getText().length() == 0) {
					idMsg.setText("��Ų�Ϊ��");
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
