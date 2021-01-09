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
 * �������򴰿�
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
		setTitle("�������а�");
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
		String[] titles = { "Ʒ��", "����" };// ���������ʾ������
		DefaultTableModel model = new DefaultTableModel(titles, 0);// ����������ģ�͵ı����������(Ϊ0��)
		table = new JTable(model) { // ʵ�������װ�ر��ģ�� //********************************//
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

		TableRowSorter sorter = new TableRowSorter<DefaultTableModel>(model);// ����������
		table.setRowSorter(sorter);// ���ñ���������
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TopSell.class.getResource("/image/7.jpg")));
		lblNewLabel.setBounds(0, 0, 738, 367);
		contentPane.add(lblNewLabel);
		ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();
		// ���������ֶΣ�����Ϊ��2���ֶν���
		sortKeys.add(new RowSorter.SortKey(1, SortOrder.DESCENDING));
		sorter.setSortKeys(sortKeys);
	}
}
