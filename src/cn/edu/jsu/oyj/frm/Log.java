package cn.edu.jsu.oyj.frm;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.edu.jsu.oyj.dbc.*;

public class Log extends JFrame {

	private JPanel contentPane;
	private JTextField usename;
	private JTextField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Log frame = new Log();
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
	public Log() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 359);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D");
		lblNewLabel.setBounds(89, 51, 128, 46);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\u5BC6\u7801");
		lblNewLabel_1.setBounds(89, 141, 128, 46);
		contentPane.add(lblNewLabel_1);

		JLabel lblMsg = new JLabel("");
		lblMsg.setForeground(Color.RED);
		lblMsg.setBounds(194, 103, 184, 21);
		contentPane.add(lblMsg);

		usename = new JTextField();
		usename.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				String Sql = "select * from user where id=?";
				boolean flag = true;
				DatabaseConnectionSql dbc = new DatabaseConnectionSql();
				try (Connection con = dbc.getConnection(); PreparedStatement p = con.prepareStatement(Sql);) {
					p.setString(1, usename.getText());
					ResultSet r = p.executeQuery();
					if(r.next()) {
						String pw=r.getString("password");
						if(pw.equals(password.getText())) {
							
						}
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		usename.setBounds(194, 51, 184, 37);
		contentPane.add(usename);
		usename.setColumns(10);

		password = new JTextField();
		password.setColumns(10);
		password.setBounds(194, 141, 184, 37);
		contentPane.add(password);

		JButton btncencel = new JButton("取消");
		btncencel.setBounds(79, 227, 109, 29);
		contentPane.add(btncencel);

		JButton btnsure = new JButton("确定");
		btnsure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sql = "select *from user where usename=?";
				DatabaseConnectionSql dbc = new DatabaseConnectionSql();
				try (Connection con = dbc.getConnection(); PreparedStatement p = con.prepareStatement(sql);) {
					ResultSet r = p.executeQuery();
					while (r.next()) {
						if (r.getString(2) != password.getText()) {
							JOptionPane.showMessageDialog(null, "密码错误");
						} else {
							JOptionPane.showMessageDialog(null, "登录成功");
							new home();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnsure.setBounds(307, 227, 109, 29);
		contentPane.add(btnsure);

		JButton btnregister = new JButton("注册");
		btnregister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				new Register();
			}
		});
		btnregister.setBounds(385, 0, 123, 29);
		contentPane.add(btnregister);

	}
}
