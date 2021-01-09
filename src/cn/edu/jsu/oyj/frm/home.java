package cn.edu.jsu.oyj.frm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.io.File;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import cn.edu.jsu.oyj.dao.FrameTools;
import cn.edu.jsu.oyj.dao.Inport;
import cn.edu.jsu.oyj.dao.PetsDAO;
import cn.edu.jsu.oyj.dao.PetsExcel;
import cn.edu.jsu.oyj.dao.PetsText;

import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.event.MenuListener;
import javax.swing.event.MenuEvent;

public class home {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					home window = new home();
					window.frame.setVisible(true);
					window.frame.setResizable(false); // ������������󻯺󲻿ɵ�����С
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public home() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		String lookAndFeel = UIManager.getSystemLookAndFeelClassName(); // ��������
		try {
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (InstantiationException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IllegalAccessException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (UnsupportedLookAndFeelException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(home.class.getResource("/image/\u5BA0\u7269.png")));
		frame.setFont(new Font("Dialog", Font.BOLD, 15));
		frame.setTitle("�������ϵͳ");
		frame.setBounds(100, 100, 907, 606);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);// �������

		JDesktopPane desktopPane = new JDesktopPane() {
			@Override
			public void paintComponent(Graphics g) {// �ػ���屳��
				// ����һ��δ��ʼ����ͼ��ͼ�꣬�ο�API
				ImageIcon icon = new ImageIcon("image" + File.separator + "Dog.jpg");
				// ����ָ��ͼ���������ŵ��ʺ�ָ�������ڲ���ͼ�񣬲ο�API
				g.drawImage(icon.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
			}
		};
		desktopPane.setBackground(Color.WHITE);
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		JMenuBar menuBar = new JMenuBar(); // �˵���
		menuBar.setForeground(Color.BLACK);
		menuBar.setBounds(0, 0, 1920, 24); // ���ò˵����洰��ı���ı�
		desktopPane.add(menuBar);
		
				JMenu center = new JMenu("��������");
				
				center.setFont(new Font("΢���ź�", Font.PLAIN, 20));
				menuBar.add(center);
				
				JMenu manager = new JMenu("����Ա");
			
				manager.setIcon(new ImageIcon(home.class.getResource("/image/\u7BA1\u7406\u5458.png")));
				manager.setFont(new Font("����", Font.PLAIN, 18));
				center.add(manager);
				
				JMenuItem delete = new JMenuItem("ɾ������");
				delete.setIcon(new ImageIcon(home.class.getResource("/image/\u5220 \u9664.png")));
				delete.setFont(new Font("����", Font.PLAIN, 18));
				delete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Delete d=Delete.GetInstance();
						desktopPane.add(d);
						d.setVisible(true);
						try {
							d.setSelected(true);
						} catch (PropertyVetoException e) {
							e.printStackTrace();
						}
						FrameTools.setFrameCenter(d);
					}
				});
				
				JMenu inport = new JMenu("¼������");
				inport.setIcon(new ImageIcon(home.class.getResource("/image/\u6DFB\u52A0\u6570\u636E.png")));
				inport.setFont(new Font("����", Font.PLAIN, 18));
				manager.add(inport);
				
				JMenuItem inportExcel = new JMenuItem("����");
				inportExcel.setIcon(new ImageIcon(home.class.getResource("/image/\u8868\u683C.png")));
				inportExcel.setFont(new Font("����", Font.PLAIN, 18));
				inportExcel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							new Inport().addExcel();
							JOptionPane.showMessageDialog(null, "¼��ɹ�");
						} catch (Exception e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "�ļ�������");
						}
						
					}
				});
				inport.add(inportExcel);
				
				JMenuItem inportTxt = new JMenuItem("�ı���");
				inportTxt.setIcon(new ImageIcon(home.class.getResource("/image/\u6587\u672C.png")));
				inportTxt.setFont(new Font("����", Font.PLAIN, 18));
				inportTxt.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							new Inport().addWord();
							JOptionPane.showMessageDialog(null, "¼��ɹ�");
						} catch (Exception e1) {
//							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "�ļ�������");
						}
						
					}
				});
				inport.add(inportTxt);
				manager.add(delete);
				
				JMenuItem set = new JMenuItem("�޸�����");
				set.setIcon(new ImageIcon(home.class.getResource("/image/\u4FEE\u6539\u6570\u636E.png")));
				set.setFont(new Font("����", Font.PLAIN, 18));
				set.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						update u=update.GetInstance();
						desktopPane.add(u);
						u.setVisible(true);
						try {
							u.setSelected(true);
						} catch (PropertyVetoException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}FrameTools.setFrameCenter(u);
					}
				});
				manager.add(set);
				
				JMenu user = new JMenu("�û�");
				user.setIcon(new ImageIcon(home.class.getResource("/image/\u7528\u6237.png")));
				user.setFont(new Font("����", Font.PLAIN, 18));
				center.add(user);
				
				JMenuItem buyItems = new JMenuItem("�������");
				buyItems.setIcon(new ImageIcon(home.class.getResource("/image/\u8D2D\u4E70.png")));
				buyItems.setFont(new Font("����", Font.PLAIN, 18));
				buyItems.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						buy b=buy.GetInstance();
						desktopPane.add(b);
						b.setVisible(true);
						try {
							b.setSelected(true);
						} catch (PropertyVetoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						FrameTools.setFrameCenter(b);
					}
				});
				user.add(buyItems);
				
				JMenuItem orders = new JMenuItem("�鿴����");
				orders.setIcon(new ImageIcon(home.class.getResource("/image/\u8BA2\u5355.png")));
				orders.setFont(new Font("����", Font.PLAIN, 18));
				orders.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						showOrders s=showOrders.GetInstance();
						desktopPane.add(s);
						s.setVisible(true);
						try {
							s.setSelected(true);
						} catch (PropertyVetoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}FrameTools.setFrameCenter(s);
					}
				});
				user.add(orders);

		JMenu top = new JMenu("TOP��");
		top.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		menuBar.add(top);

		JMenuItem heat = new JMenuItem("�ȶ�TOP��");
		heat.setIcon(new ImageIcon(home.class.getResource("/image/\u70ED\u5EA6.png")));
		heat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TopHeat t=TopHeat.GetInstance();
				desktopPane.add(t);
				t.setVisible(true);
				try {
					t.setSelected(true);
				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
				}FrameTools.setFrameCenter(t);
			}
		});
		heat.setFont(new Font("����", Font.PLAIN, 18));
		top.add(heat);

		JMenuItem sell = new JMenuItem("����TOP��");
		sell.setIcon(new ImageIcon(home.class.getResource("/image/\u9500\u552E.png")));
		sell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TopSell t=TopSell.GetInstance();
				desktopPane.add(t);
				t.setVisible(true);
				try {
					t.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}FrameTools.setFrameCenter(t);
			}
		});
		sell.setFont(new Font("����", Font.PLAIN, 18));
		top.add(sell);

		JMenu printMsg = new JMenu("��ӡ������Ϣ");
		printMsg.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		menuBar.add(printMsg);

		JMenuItem text = new JMenuItem("�ı���");
		text.setIcon(new ImageIcon(home.class.getResource("/image/\u6587\u672C.png")));
		text.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean flag = false;
				try {
					new PetsText().exportText(new PetsDAO().getAllPetData());
					flag = true;
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				if (flag) {
					JOptionPane.showMessageDialog(null, "�ı���ӡ�ɹ�");
				} else {
					JOptionPane.showMessageDialog(null, "�ı���ӡʧ�ܣ�������");
				}
			}
		});
		text.setFont(new Font("����", Font.PLAIN, 18));
		printMsg.add(text);

		JMenuItem excel = new JMenuItem("����");
		excel.setIcon(new ImageIcon(home.class.getResource("/image/\u8868\u683C.png")));
		excel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean flag = false;
				try {
					new PetsExcel().exportExcel(new PetsDAO().getAllPetData());
					flag = true;
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				if (flag) {
					JOptionPane.showMessageDialog(null, "����ӡ�ɹ�");
				} else {
					JOptionPane.showMessageDialog(null, "����ӡʧ�ܣ�������");
				}
			}
		});
		excel.setFont(new Font("����", Font.PLAIN, 18));
		printMsg.add(excel);

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false); // ʹ�������̶���
		toolBar.setBounds(0, 26, 1920, 31);
		desktopPane.add(toolBar);

		JButton search = new JButton("");
		search.setIcon(new ImageIcon(home.class.getResource("/image/\u67E5\u8BE2.png")));
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				find f=find.GetInstance();
				desktopPane.add(f);// ���Ӵ������������Ķ��������
				f.setVisible(true);// �����Ӵ���ɼ�
				try {
					f.setSelected(true);// ѡ���Ӵ���
				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
				}
				FrameTools.setFrameCenter(f);
			}
		});
		search.setFont(new Font("΢���ź�", Font.PLAIN, 25));
		search.setToolTipText("��ѯ������Ϣ");
		toolBar.add(search);

		JButton show = new JButton("");
		show.setIcon(new ImageIcon(home.class.getResource("/image/\u663E\u793A.png")));
		show.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showMsg sm = showMsg.GetInstance();
				desktopPane.add(sm);// ���Ӵ������������Ķ��������
				sm.setVisible(true);// �����Ӵ���ɼ�
				try {
					sm.setSelected(true);// ѡ���Ӵ���
				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
				}
				FrameTools.setFrameCenter(sm);
			}
		});
		show.setToolTipText("չʾ������Ϣ");
		show.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		toolBar.add(show);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(home.class.getResource("/image/welcome.png")));
		lblNewLabel.setBounds(314, 90, 605, 103);
		desktopPane.add(lblNewLabel);

		
	}
}
