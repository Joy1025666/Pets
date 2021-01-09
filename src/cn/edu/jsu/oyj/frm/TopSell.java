package cn.edu.jsu.oyj.frm;

import java.awt.EventQueue;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import cn.edu.jsu.oyj.dao.PetsDAO;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
/**
 * 销量排序窗口
 * */
public class TopSell extends JInternalFrame {

	private static TopSell cframe = null;

	public static synchronized TopSell GetInstance() {
		if (cframe == null) {
			cframe = new TopSell();
		}
		return cframe;
	}

	private JPanel contentPane;
	private JTable table;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TopSell frame = new TopSell();
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
	private TopSell() {
		setTitle("销量排行榜");
		setClosable(true);
		try {
			setClosed(true);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setIconifiable(true);
		setBounds(100, 100, 754, 408);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(78, 34, 567, 260);
		contentPane.add(scrollPane);
		////////////////////////////////////////////////////////////////////////
		String[] titles = { "品种", "销量" };// 定义数组表示表格标题
		DefaultTableModel model = new DefaultTableModel(titles, 0);// 定义表格数据模型的表格标题和行数(为0行)
		table = new JTable(model) { // 实例化表格装载表格模型 //********************************//
		};
		Map<String, Integer> date = new PetsDAO().SellNumber();
		Set<String> s = date.keySet();
		String[] a = new String[2];
		for (String x : s) {
			a[0] = x;
			a[1] = date.get(x).toString();
			model.addRow(a);
		}

		scrollPane.setViewportView(table);

		TableRowSorter sorter = new TableRowSorter<DefaultTableModel>(model);// 设置排序器
		table.setRowSorter(sorter);// 设置表格的排序器
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TopSell.class.getResource("/image/7.jpg")));
		lblNewLabel.setBounds(0, 0, 738, 367);
		contentPane.add(lblNewLabel);
		ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();
		// 设置排序字段，下例为第2个字段降序
		sortKeys.add(new RowSorter.SortKey(1, SortOrder.DESCENDING));
		sorter.setSortKeys(sortKeys);
	}
}
