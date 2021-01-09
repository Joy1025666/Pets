package cn.edu.jsu.oyj.dao;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JInternalFrame;

import cn.edu.jsu.oyj.frm.showMsg;

public class FrameTools {
	// 设置子窗体居中显示
	public static void setFrameCenter(JInternalFrame jif) {
		Toolkit tk = Toolkit.getDefaultToolkit();// 获取默认工具包。
		Dimension d = tk.getScreenSize();// 获取屏幕尺寸
		int swidth = d.width;// 获取屏幕宽度
		int sheight = d.height;// 获取屏幕高度
		int x = (swidth - jif.getWidth()) / 2;// (屏幕宽度-子窗体宽度)/2
		int y = (sheight - jif.getHeight()) / 2;// (屏幕高度-子窗体高度)/2
		// 设置子窗体位置，高度-70，70包含标题、菜单栏和工具栏的高度
		jif.setLocation(x, y - 30);
	}
}
