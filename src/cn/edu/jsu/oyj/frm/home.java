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
					window.frame.setResizable(false); // 设置主窗体最大化后不可调整大小
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
		String lookAndFeel = UIManager.getSystemLookAndFeelClassName(); // 主题设置
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
		frame.setTitle("宠物管理系统");
		frame.setBounds(100, 100, 907, 606);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);// 窗口最大化

		JDesktopPane desktopPane = new JDesktopPane() {
			@Override
			public void paintComponent(Graphics g) {// 重绘面板背景
				// 创建一个未初始化的图像图标，参考API
				ImageIcon icon = new ImageIcon("image" + File.separator + "Dog.jpg");
				// 绘制指定图像中已缩放到适合指定矩形内部的图像，参考API
				g.drawImage(icon.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
			}
		};
		desktopPane.setBackground(Color.WHITE);
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		JMenuBar menuBar = new JMenuBar(); // 菜单栏
		menuBar.setForeground(Color.BLACK);
		menuBar.setBounds(0, 0, 1920, 24); // 设置菜单栏随窗体改变而改变
		desktopPane.add(menuBar);
		
				JMenu center = new JMenu("个人中心");
				
				center.setFont(new Font("微软雅黑", Font.PLAIN, 20));
				menuBar.add(center);
				
				JMenu manager = new JMenu("管理员");
			
				manager.setIcon(new ImageIcon(home.class.getResource("/image/\u7BA1\u7406\u5458.png")));
				manager.setFont(new Font("黑体", Font.PLAIN, 18));
				center.add(manager);
				
				JMenuItem delete = new JMenuItem("删除数据");
				delete.setIcon(new ImageIcon(home.class.getResource("/image/\u5220 \u9664.png")));
				delete.setFont(new Font("黑体", Font.PLAIN, 18));
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
				
				JMenu inport = new JMenu("录入数据");
				inport.setIcon(new ImageIcon(home.class.getResource("/image/\u6DFB\u52A0\u6570\u636E.png")));
				inport.setFont(new Font("黑体", Font.PLAIN, 18));
				manager.add(inport);
				
				JMenuItem inportExcel = new JMenuItem("表格版");
				inportExcel.setIcon(new ImageIcon(home.class.getResource("/image/\u8868\u683C.png")));
				inportExcel.setFont(new Font("黑体", Font.PLAIN, 18));
				inportExcel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							new Inport().addExcel();
							JOptionPane.showMessageDialog(null, "录入成功");
						} catch (Exception e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "文件不存在");
						}
						
					}
				});
				inport.add(inportExcel);
				
				JMenuItem inportTxt = new JMenuItem("文本版");
				inportTxt.setIcon(new ImageIcon(home.class.getResource("/image/\u6587\u672C.png")));
				inportTxt.setFont(new Font("黑体", Font.PLAIN, 18));
				inportTxt.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							new Inport().addWord();
							JOptionPane.showMessageDialog(null, "录入成功");
						} catch (Exception e1) {
//							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "文件不存在");
						}
						
					}
				});
				inport.add(inportTxt);
				manager.add(delete);
				
				JMenuItem set = new JMenuItem("修改数据");
				set.setIcon(new ImageIcon(home.class.getResource("/image/\u4FEE\u6539\u6570\u636E.png")));
				set.setFont(new Font("黑体", Font.PLAIN, 18));
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
				
				JMenu user = new JMenu("用户");
				user.setIcon(new ImageIcon(home.class.getResource("/image/\u7528\u6237.png")));
				user.setFont(new Font("黑体", Font.PLAIN, 18));
				center.add(user);
				
				JMenuItem buyItems = new JMenuItem("购买宠物");
				buyItems.setIcon(new ImageIcon(home.class.getResource("/image/\u8D2D\u4E70.png")));
				buyItems.setFont(new Font("黑体", Font.PLAIN, 18));
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
				
				JMenuItem orders = new JMenuItem("查看订单");
				orders.setIcon(new ImageIcon(home.class.getResource("/image/\u8BA2\u5355.png")));
				orders.setFont(new Font("黑体", Font.PLAIN, 18));
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

		JMenu top = new JMenu("TOP榜");
		top.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		menuBar.add(top);

		JMenuItem heat = new JMenuItem("热度TOP榜");
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
		heat.setFont(new Font("黑体", Font.PLAIN, 18));
		top.add(heat);

		JMenuItem sell = new JMenuItem("销量TOP榜");
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
		sell.setFont(new Font("黑体", Font.PLAIN, 18));
		top.add(sell);

		JMenu printMsg = new JMenu("打印宠物信息");
		printMsg.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		menuBar.add(printMsg);

		JMenuItem text = new JMenuItem("文本版");
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
					JOptionPane.showMessageDialog(null, "文本打印成功");
				} else {
					JOptionPane.showMessageDialog(null, "文本打印失败，请重试");
				}
			}
		});
		text.setFont(new Font("黑体", Font.PLAIN, 18));
		printMsg.add(text);

		JMenuItem excel = new JMenuItem("表格版");
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
					JOptionPane.showMessageDialog(null, "表格打印成功");
				} else {
					JOptionPane.showMessageDialog(null, "表格打印失败，请重试");
				}
			}
		});
		excel.setFont(new Font("黑体", Font.PLAIN, 18));
		printMsg.add(excel);

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false); // 使工具栏固定。
		toolBar.setBounds(0, 26, 1920, 31);
		desktopPane.add(toolBar);

		JButton search = new JButton("");
		search.setIcon(new ImageIcon(home.class.getResource("/image/\u67E5\u8BE2.png")));
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				find f=find.GetInstance();
				desktopPane.add(f);// 将子窗体加入主窗体的顶层面板中
				f.setVisible(true);// 设置子窗体可见
				try {
					f.setSelected(true);// 选中子窗体
				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
				}
				FrameTools.setFrameCenter(f);
			}
		});
		search.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		search.setToolTipText("查询宠物信息");
		toolBar.add(search);

		JButton show = new JButton("");
		show.setIcon(new ImageIcon(home.class.getResource("/image/\u663E\u793A.png")));
		show.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showMsg sm = showMsg.GetInstance();
				desktopPane.add(sm);// 将子窗体加入主窗体的顶层面板中
				sm.setVisible(true);// 设置子窗体可见
				try {
					sm.setSelected(true);// 选中子窗体
				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
				}
				FrameTools.setFrameCenter(sm);
			}
		});
		show.setToolTipText("展示宠物信息");
		show.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		toolBar.add(show);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(home.class.getResource("/image/welcome.png")));
		lblNewLabel.setBounds(314, 90, 605, 103);
		desktopPane.add(lblNewLabel);

		
	}
}
